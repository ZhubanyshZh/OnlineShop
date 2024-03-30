package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChangePasswordDto {

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    private Long id;
}
