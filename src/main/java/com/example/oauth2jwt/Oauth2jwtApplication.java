package com.example.oauth2jwt;

import com.example.oauth2jwt.board.annotation.BoardType;
import com.example.oauth2jwt.board.entity.Board;
import com.example.oauth2jwt.board.repository.BoardRepository;
import com.example.oauth2jwt.security.config.AppProperties;
import com.example.oauth2jwt.user.entity.User;
import com.example.oauth2jwt.user.entity.UserRole;
import com.example.oauth2jwt.user.repository.UserRepository;
import com.example.oauth2jwt.user.repository.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.stream.IntStream;

import static com.example.oauth2jwt.common.enums.UserRoleType.ROLE_ADMIN;
import static com.example.oauth2jwt.common.enums.UserRoleType.ROLE_MODERATOR;
import static com.example.oauth2jwt.common.enums.UserRoleType.ROLE_USER;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class Oauth2jwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2jwtApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(UserRepository userRepository, UserRoleRepository userRoleRepository, BoardRepository boardRepository) {
		return (args) -> {
			// Role 기준정보 저장
			userRoleRepository.save(UserRole.builder().name(ROLE_ADMIN).build());
			userRoleRepository.save(UserRole.builder().name(ROLE_MODERATOR).build());
			userRoleRepository.save(UserRole.builder().name(ROLE_USER).build());

			User user = userRepository.save(User.builder()
					.username("seek")
					.password("1234")
					.email("seek@naver.com")
					.build());

			IntStream.rangeClosed(1, 200).forEach(index ->
					boardRepository.save(Board.builder()
							.title(String.format("제목%s", index))
							.content(String.format("내용%s", index))
							.boardType(BoardType.FREE)
							.user(user)
							.build())
			);
		};
	}
}
