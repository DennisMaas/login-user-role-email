package de.dennismaas.loginuserroleemail.registration;


import de.dennismaas.loginuserroleemail.appuser.AppUser;
import de.dennismaas.loginuserroleemail.appuser.AppUserRole;
import de.dennismaas.loginuserroleemail.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserRegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

    public String register (AppUserRegistrationRequest request){
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        return appUserService.signUpUser(new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER

        ));
    }
}
