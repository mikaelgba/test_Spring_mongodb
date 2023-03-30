package com.br.calcme.utils.filter;

public class UserFilter {
    private String name;
    private String id;
    private String email;
    private String phone;

    public UserFilter(String name, String id, String email, String phone) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public boolean hasNameFilter() {
        return name != null && !name.isEmpty();
    }

    public boolean hasEmailFilter() {
        return email != null && !email.isEmpty();
    }

    public boolean hasPhoneFilter() {
        return phone != null && !phone.isEmpty();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
