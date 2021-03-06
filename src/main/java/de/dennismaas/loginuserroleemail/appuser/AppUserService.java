package de.dennismaas.loginuserroleemail.appuser;

import de.dennismaas.loginuserroleemail.registration.token.ConfirmationToken;
import de.dennismaas.loginuserroleemail.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found.";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }


    public String signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail())
                .isPresent();

        if (userExists) {

            AppUser appUserWithTokenExpired = appUserRepository.findByEmail(appUser.getEmail()).get();
            Boolean enabled = appUserWithTokenExpired.getEnabled();

            if (!enabled) {
                String token = UUID.randomUUID().toString();

                saveConfirmationToken(appUserWithTokenExpired, token);
            }

            throw new IllegalStateException("email already taken");
        }


        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        saveConfirmationToken(appUser, token);
        return token;
    }

    private void saveConfirmationToken(AppUser appUser, String token) {

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
                );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
