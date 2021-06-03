package com.koreait.spring.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDomain extends BoardEntity {
    private String writerNm;
    private String profileImg;
}

// Join 과정에서 table에 있는 칼럼 외에도 다른 데이터가 필요함
// 이를 domain에 담아줌

