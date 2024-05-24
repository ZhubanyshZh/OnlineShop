package org.example.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
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
