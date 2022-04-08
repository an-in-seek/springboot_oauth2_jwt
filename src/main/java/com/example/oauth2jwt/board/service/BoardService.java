package com.example.oauth2jwt.board.service;

import com.example.oauth2jwt.board.dto.BoardDto;
import com.example.oauth2jwt.board.entity.Board;
import com.example.oauth2jwt.board.map.BoardMapStruct;
import com.example.oauth2jwt.board.repository.BoardRepository;
import com.example.oauth2jwt.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardMapStruct boardMapStruct;

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Board findBoardById(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Board", "id", id));
    }

    public Board save(BoardDto boardDto) {
        Board board = boardMapStruct.toEntity(boardDto);
        boardRepository.save(board);
        return board;
    }

    public Board updateById(Long id, BoardDto boardDto) {
        Board board = findBoardById(id);
        boardMapStruct.updateEntityFromDto(boardDto, board);
        boardRepository.save(board);
        return board;
    }

    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }
}
