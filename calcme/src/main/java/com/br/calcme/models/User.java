package com.br.calcme.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Document(collection = "users")
public class User {
    @Id
    private String id;

    @NotBlank(message = "O campo nome é obrigatório")
    private String name;

    @NotBlank(message = "O campo telefone é obrigatório")
    private String phone;

    @Email(message = "O campo email deve ser um endereço de e-mail válido")
    @NotBlank(message = "O campo email é obrigatório")
    private String email;

    @NotNull(message = "O campo criado é obrigatório")
    private LocalDateTime created;

    @NotNull(message = "O campo atualizado é obrigatório")
    private LocalDateTime updated;

    public User(String name, String phone, String email, LocalDateTime created, LocalDateTime updated) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.created = created;
        this.updated = updated;
    }

    public User() {}

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