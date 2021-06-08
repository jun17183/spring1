package com.koreait.spring.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardCmtDomain extends BoardCmtEntity {
    private String writerNm;
    private String profileImg;
}

// ToString -> 해당 객체를 sysout으로 찍으면 원래는 주소값이 나오지만 ToString을 쓰면 멤버 필드가
// 문자열 형식으로 출력됨