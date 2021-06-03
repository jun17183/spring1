package com.koreait.spring.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
    private int iboard;
    private int startIdx;
    private int recordCnt;
    private int searchType;
    private String searchText;
}

// parameter용
