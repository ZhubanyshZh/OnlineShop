package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
    private boolean successSigned = false;
    private boolean alreadyExistSuchUser;
    private String nameError;
    private String phoneNumError;
    private String birthdayError;
    private String addressError;
    private String emailError;
    private String passwordError;
}
