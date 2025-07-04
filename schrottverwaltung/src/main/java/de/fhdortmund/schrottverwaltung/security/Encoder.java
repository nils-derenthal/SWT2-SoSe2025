package de.fhdortmund.schrottverwaltung.security;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.security.MessageDigest.getInstance;

@Service
public class Encoder {
    public String encrypt(String input)  {
        try {
            final MessageDigest digest = getInstance("SHA3-256");
            final byte[] hashbytes = digest.digest(
                    input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashbytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
}

