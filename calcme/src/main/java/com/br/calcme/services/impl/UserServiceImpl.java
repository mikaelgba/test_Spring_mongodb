package com.br.calcme.services.impl;

import com.br.calcme.DTO.UserDTO;
import com.br.calcme.VO.UserVO;
import com.br.calcme.exceptions.UserAlreadyExistsException;
import com.br.calcme.exceptions.UserNotFoundException;
import com.br.calcme.models.User;
import com.br.calcme.repositories.UserRepository;
import com.br.calcme.services.UserService;
import com.br.calcme.utils.filter.UserFilter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Page<UserVO> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(user -> new ModelMapper().map(user, UserVO.class));
    }

    @Override
    public Page<UserVO> findWithFilters(UserFilter filter, Pageable pageable) {
        Query query = new Query();
        if (filter.getName() != null && !filter.getName().isEmpty()) {
            query.addCriteria(Criteria.where("name").regex(filter.getName(), "i"));
        }
        if (filter.getId() != null && !filter.getId().isEmpty()) {
            query.addCriteria(Criteria.where("id").is(filter.getId()));
        }
        if (filter.getEmail() != null && !filter.getEmail().isEmpty()) {
            query.addCriteria(Criteria.where("email").regex(filter.getEmail(), "i"));
        }
        if (filter.getPhone() != null && !filter.getPhone().isEmpty()) {
            query.addCriteria(Criteria.where("phone").regex(filter.getPhone(), "i"));
        }
        query.with(Sort.by(Sort.Direction.ASC, "name")); // critério de ordenação por nome em ordem crescente
        long count = mongoTemplate.count(query, User.class);
        query.with(pageable);
        List<User> users = mongoTemplate.find(query, User.class);
        List<UserVO> userVOs = users.stream()
                .map(user -> new ModelMapper().map(user, UserVO.class))
                .collect(Collectors.toList());
        return new PageImpl<>(userVOs, pageable, count);
    }

    @Override
    public UserDTO findById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        return convertToDTO(user);
    }

    @Override
    public UserVO save(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email already exists");
        }
        User user = convertToEntity(userDTO);
        user.setCreated(LocalDateTime.now());
        user.setUpdated(LocalDateTime.now());
        User savedUser = userRepository.save(user);
        return new UserVO(savedUser);
    }


    @Override
    public UserDTO update(String id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        BeanUtils.copyProperties(userDTO, user, "id", "created");
        user.setUpdated(LocalDateTime.now());
        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    @Override
    public void delete(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        userRepository.deleteById(user.getId());
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getName(),
                user.getPhone(),
                user.getEmail(),
                user.getCreated(),
                user.getUpdated()
        );
    }

    private User convertToEntity(UserDTO userDTO) {
        return new User(
                userDTO.getName(),
                userDTO.getPhone(),
                userDTO.getEmail(),
                userDTO.getCreated(),
                userDTO.getUpdated()
        );
    }
}
