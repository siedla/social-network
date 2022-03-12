package com.siedla.socialnetwork.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleUser implements Serializable {

    @Size(min = 2, max = 30)
    private String firstName;

    @Size(min = 2, max = 30)
    private String lastName;

    @NotEmpty
    @Email
    @Size(min = 5, max = 50)
    private String email;

    @NotNull
    @Size(min = 5, max = 30)
    private String password;
}
