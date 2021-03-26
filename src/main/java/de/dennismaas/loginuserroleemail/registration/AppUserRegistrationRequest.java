package de.dennismaas.loginuserroleemail.registration;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AppUserRegistrationRequest {

    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;
}
