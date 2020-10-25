package com.devsuperior.dsCatalog.entities;

import com.devsuperior.dsCatalog.dto.UserDTO;

public class UserInsertDTO  extends UserDTO{
    private static final long serialVersionUID = 1L;
    private String password;

    public UserInsertDTO() {
        super();
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
