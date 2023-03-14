package org.example.paswword;

import org.example.password.PasswordGenerator;

public class WrongFixedPasswordGenerator implements PasswordGenerator {
    @Override
    public String generatePassword() {
        return "null";
    }
}
