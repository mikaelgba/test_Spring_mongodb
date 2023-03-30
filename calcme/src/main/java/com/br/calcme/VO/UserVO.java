package com.br.calcme.VO;

import com.br.calcme.DTO.UserDTO;
import com.br.calcme.models.User;

import java.time.LocalDateTime;

public class UserVO {

    private String id;
    private String name;
    private String phone;
    private String email;
    private LocalDateTime created;
    private LocalDateTime updated;

    // construtores, getters e setters

    public UserVO() {
        // Construtor padrão sem parâmetros
    }
    public UserVO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.created = user.getCreated();
        this.updated = user.getUpdated();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
