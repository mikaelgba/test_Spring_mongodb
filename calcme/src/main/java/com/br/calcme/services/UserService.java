package com.br.calcme.services;

import com.br.calcme.DTO.UserDTO;
import com.br.calcme.VO.UserVO;
import com.br.calcme.models.User;

import java.util.List;

public interface UserService {

    List<UserVO> findAll();

    UserDTO findById(String id);

    UserVO save(UserDTO userDTO);

    UserDTO update(String id, UserDTO userDTO);

    void delete(String id);
}
