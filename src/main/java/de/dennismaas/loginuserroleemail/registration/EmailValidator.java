package de.dennismaas.loginuserroleemail.registration;

import java.util.function.Predicate;
/*
import org.apache.commons.validator.routines.EmailValidator;
*/


public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String email) {
        //TODO: Regex to validate
        // String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return true;
    }
}
