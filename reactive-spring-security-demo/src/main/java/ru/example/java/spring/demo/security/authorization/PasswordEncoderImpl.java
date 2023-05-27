package ru.example.java.spring.demo.security.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.example.java.spring.demo.config.EncoderProperties;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PasswordEncoderImpl implements PasswordEncoder {

    private final EncoderProperties properties;

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            byte[] encoded = SecretKeyFactory.getInstance(properties.getAlgorithm())
                    .generateSecret(new PBEKeySpec(properties.getSecret().toCharArray()))
                    .getEncoded();
            return Base64.getEncoder().encodeToString(encoded);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return Objects.equals(encode(rawPassword), encodedPassword);
    }
}
