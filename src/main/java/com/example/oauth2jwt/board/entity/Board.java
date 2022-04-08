package com.example.oauth2jwt.board.entity;

import com.example.oauth2jwt.board.annotation.BoardType;
import com.example.oauth2jwt.common.entity.BaseDateTimeEntity;
import com.example.oauth2jwt.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Board extends BaseDateTimeEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private BoardType boardType;

    @ManyToOne
    private User user;

    @Builder
    public Board(Long id, String title, String content, BoardType boardType, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.boardType = boardType;
        this.user = user;
    }
}
