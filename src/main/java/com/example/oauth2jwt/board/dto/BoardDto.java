package com.example.oauth2jwt.board.dto;

import com.example.oauth2jwt.board.annotation.BoardType;
import com.example.oauth2jwt.user.dto.UserDto;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Builder
public class BoardDto {

    private final Long id;

    @NotBlank
    @Size(max = 50)
    private final String title;

    @NotBlank
    @Size(max = 200)
    private final String content;

    private final BoardType boardType;

    private final UserDto user;

    private final LocalDateTime createdDate;

    private final LocalDateTime updatedDate;
}
