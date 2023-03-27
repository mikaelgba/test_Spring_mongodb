package com.br.calcme.services.impl;

import com.br.calcme.DTO.UserDTO;
import com.br.calcme.VO.UserVO;
import com.br.calcme.exceptions.UserAlreadyExistsException;
import com.br.calcme.exceptions.UserNotFoundException;
import com.br.calcme.models.User;
import com.br.calcme.repositories.UserRepository;
import com.br.calcme.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserVO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserVO::new).collect(Collectors.toList());
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
