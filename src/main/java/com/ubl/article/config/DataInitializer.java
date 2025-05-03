package com.ubl.article.config;

import com.ubl.article.model.Article;
import com.ubl.article.model.User;
import com.ubl.article.repository.ArticleRepository;
import com.ubl.article.repository.UserRepository;
import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initData(UserRepository userRepository, ArticleRepository articleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            System.out.println("🟢 DataInitializer berjalan...");
            // Tambah User admin jika belum ada
            if (userRepository.findByUsername("admin").isEmpty()) {
                String encodedPassword = passwordEncoder.encode("password");
                User admin = new User("admin", encodedPassword, "ADMIN");
                userRepository.save(admin);
            }
            
            // Tambah User api jika belum ada
            if (userRepository.findByUsername("apiuser").isEmpty()) {
                String encodedPassword = passwordEncoder.encode("password");
                User apiuser = new User("apiuser", encodedPassword, "API");
                userRepository.save(apiuser);
            }

            // Tambah beberapa artikel jika belum ada
            if (articleRepository.count() == 0) {
                Article a1 = new Article("Artikel Pertama", "Ini adalah isi dari artikel pertama.", LocalDateTime.now());
                Article a2 = new Article("Artikel Kedua", "Ini adalah isi dari artikel kedua.", LocalDateTime.now());
                articleRepository.save(a1);
                articleRepository.save(a2);
            }
        };
    }
}
