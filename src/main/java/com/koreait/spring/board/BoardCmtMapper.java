package com.koreait.spring.board;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardCmtMapper {
    int insBoardCmt(BoardCmtEntity param);
    List<BoardCmtDomain> selBoardCmtList(BoardCmtEntity param);
}

// interface 메서드들에 public abstract가 없는 이유는,,, 사실 무조건 public abstract만이 올 수 있기에
// 생략해도 자동으로 붙여주기 때문