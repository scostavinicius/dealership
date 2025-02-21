package com.dealership.dto;

import com.dealership.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private User.Role role;

    public  UserDTO(User user) {
        BeanUtils.copyProperties(user, this);
    }
}
