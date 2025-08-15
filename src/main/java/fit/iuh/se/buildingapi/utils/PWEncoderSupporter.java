package fit.iuh.se.buildingapi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PWEncoderSupporter {
    public static String encode(String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public static boolean match(String requestPassword, String userPassword){
        return new BCryptPasswordEncoder().matches(requestPassword, userPassword);
    }
}
