package com.example.oauth2jwt;

import com.example.oauth2jwt.common.enums.UserRoleType;
import com.example.oauth2jwt.user.entity.UserRole;
import com.example.oauth2jwt.user.repository.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.example.oauth2jwt.common.enums.UserRoleType.ROLE_ADMIN;
import static com.example.oauth2jwt.common.enums.UserRoleType.ROLE_MODERATOR;
import static com.example.oauth2jwt.common.enums.UserRoleType.ROLE_USER;

@EnableJpaAuditing
@SpringBootApplication
public class Oauth2jwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2jwtApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(UserRoleRepository userRoleRepository) {
		return (args) -> {
			// Role 기준정보 저장
			userRoleRepository.save(UserRole.builder().name(ROLE_ADMIN).build());
			userRoleRepository.save(UserRole.builder().name(ROLE_MODERATOR).build());
			userRoleRepository.save(UserRole.builder().name(ROLE_USER).build());
		};
	}
}
