package com.example.oauth2jwt.board.controller;

import com.example.oauth2jwt.board.dto.BoardDto;
import com.example.oauth2jwt.board.entity.Board;
import com.example.oauth2jwt.board.map.BoardMapStruct;
import com.example.oauth2jwt.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;
    private final BoardMapStruct boardMapStruct;

    @PostMapping
    public ResponseEntity<?> postBoard(@RequestBody BoardDto boardDto) {
        Board savedBoard = boardService.save(boardDto);
        BoardDto savedBoardDto = boardMapStruct.toDto(savedBoard);
        return new ResponseEntity<>(savedBoardDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getBoard() {
        List<Board> boards = boardService.findAll();
        Stream<Object> boardsDto = boards.stream().map(board -> {
            BoardDto dto = boardMapStruct.toDto(board);
            return dto;
        });
        return new ResponseEntity<>(boardsDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBoardById(@PathVariable Long id) {
        Board board = boardService.findBoardById(id);
        BoardDto boardDto = boardMapStruct.toDto(board);
        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putBoard(@PathVariable Long id, @RequestBody BoardDto boardDto) {
        Board updatedBoard = boardService.updateById(id, boardDto);
        BoardDto updatedBoardDto = boardMapStruct.toDto(updatedBoard);
        return new ResponseEntity<>(updatedBoardDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
