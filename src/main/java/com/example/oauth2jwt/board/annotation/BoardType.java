package com.example.oauth2jwt.board.annotation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardType {
    NOTICE("NOTICE"),
    FREE("FREE");
    private String value;
}
