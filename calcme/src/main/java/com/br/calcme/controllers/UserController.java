package com.br.calcme.controllers;

import com.br.calcme.DTO.UserDTO;
import com.br.calcme.VO.UserVO;
import com.br.calcme.services.UserService;
import com.br.calcme.utils.filter.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Page<UserVO> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return userService.findAll(pageable);
    }

    @GetMapping("/filter")
    public Page<UserVO> findWithFilters(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        UserFilter filter = new UserFilter(name, id, email, phone);
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return userService.findWithFilters(filter, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        UserDTO userDTO = userService.findById(id);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    public ResponseEntity<UserVO> create(@RequestBody @Validated UserDTO userDTO) {
        UserVO savedUserDTO = userService.save(userDTO);
        return new ResponseEntity<>(savedUserDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody @Validated UserDTO userDTO) {
        UserDTO updatedUserDTO = userService.update(id, userDTO);
        return ResponseEntity.ok(updatedUserDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
