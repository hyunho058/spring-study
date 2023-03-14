package org.example.paswword;

import org.example.password.PasswordGenerator;

public class CorrectFixedPasswordGenerator implements PasswordGenerator {
    @Override
    public String generatePassword() {
        return "abcdefgh";
    }
}
