package com.br.calcme.services;

import com.br.calcme.DTO.UserDTO;
import com.br.calcme.VO.UserVO;
import com.br.calcme.models.User;
import com.br.calcme.utils.filter.UserFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    Page<UserVO> findAll(Pageable pageable);

    Page<UserVO> findWithFilters(UserFilter filter, Pageable pageable);

    UserDTO findById(String id);

    UserVO save(UserDTO userDTO);

    UserDTO update(String id, UserDTO userDTO);

    void delete(String id);

}
