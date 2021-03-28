package de.dennismaas.loginuserroleemail.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;


@Service
public class EmailValidator implements Predicate<String> {

    /*
    * This regex example uses all the characters permitted by RFC 5322,
    * which governs the email message format. Among the permitted characters are
    * some that present a security risk if passed directly from user input to an SQL statement,
    * such as the single quote (‘) and the pipe character (|).
    * You should be sure to escape sensitive characters when inserting the email address
    * into a string passed to another program, in order to prevent security holes such as SQL injection attacks.
    */
    private final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    @Override
    public boolean test(String email) {

        return email.matches(EMAIL_PATTERN);
    }
}
