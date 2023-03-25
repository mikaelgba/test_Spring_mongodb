package com.br.calcme.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class UserDTO {
    @NotBlank(message = "O campo nome é obrigatório")
    private String name;

    @NotBlank(message = "O campo telefone é obrigatório")
    private String phone;

    @Email(message = "O campo email deve ser um endereço de e-mail válido")
    @NotBlank(message = "O campo email é obrigatório")
    private String email;

    private LocalDateTime created;

    private LocalDateTime updated;

    public UserDTO(String name, String phone, String email, LocalDateTime created, LocalDateTime updated) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.created = created;
        this.updated = updated;
    }

    public UserDTO() {}

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