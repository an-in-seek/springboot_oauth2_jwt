package com.example.oauth2jwt.board.map;

import com.example.oauth2jwt.board.dto.BoardDto;
import com.example.oauth2jwt.board.entity.Board;
import com.example.oauth2jwt.common.map.EntityMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BoardMapStruct extends EntityMapStruct<BoardDto, Board> {

}
