package de.dennismaas.loginuserroleemail.email;

public interface EmailSender {
    void send(String to, String email);
}
