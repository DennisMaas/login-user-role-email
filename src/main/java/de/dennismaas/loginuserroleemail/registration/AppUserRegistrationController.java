package de.dennismaas.loginuserroleemail.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class AppUserRegistrationController {

    private AppUserRegistrationService registrationService;

    public String register(@RequestBody AppUserRegistrationRequest request){
        return registrationService.register(request);
    }
}
