package edu.eci.dosw.DOSW_Library.Util;

import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Document.UserMongoEntity;
import edu.eci.dosw.DOSW_Library.Persistence.nonrelational.Repository.UserRepositoryMongo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepositoryMongo userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String contactEmail = "main@library.co";

            if (userRepository.findByEmail(contactEmail).isEmpty()) {
                UserMongoEntity admin = new UserMongoEntity();
                admin.setUserId("ADM-101");
                admin.setEmail(contactEmail);
                admin.setName("Library Admin");
                admin.setUsername("sysadmin");
                admin.setPassword(passwordEncoder.encode("abc123"));
                admin.setRole("ADMIN");
                admin.setJoinedAt(LocalDateTime.now());

                userRepository.save(admin);
                System.out.println("### Setup completed successfully ###");
            }
        };
    }
}