package com.pikulokama.kinocmstest.dto.form;

import com.pikulokama.kinocmstest.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateFormDto {

    @NotBlank(message = "Username is required")
    @Pattern(regexp = "\\w{2,100}", message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Password is required")
    private String passwordRepeat;

    @NotNull(message = "Role is required")
    private User.UserRole role;

}
