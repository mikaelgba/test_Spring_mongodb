package com.br.calcme.services;

import com.br.calcme.DTO.UserDTO;
import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findById(String id);

    UserDTO save(UserDTO userDTO);

    UserDTO update(String id, UserDTO userDTO);

    void delete(String id);
}
