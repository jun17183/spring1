package com.koreait.spring.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// @ToString
public class BoardEntity {
    private int iboard;
    private String title;
    private String ctnt;
    private int iuser;
    private String regdt;
}

// DB와 동일
