package com.example.oauth2jwt.board.dto;

import com.example.oauth2jwt.board.annotation.BoardType;
import com.example.oauth2jwt.user.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @Size(max = 10)
    private final String title;

    @NotBlank
    @Size(max = 100)
    private final String content;

    @NotBlank
    private final BoardType boardType;

    private final UserDto user;

    private final LocalDateTime createdDate;

    private final LocalDateTime updatedDate;
}
