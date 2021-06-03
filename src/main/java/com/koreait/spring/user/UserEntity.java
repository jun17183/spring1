package com.koreait.spring.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Alias("UserEntity")
public class UserEntity {
    private int iuser;
    private String uid;
    private String upw;
    private String unm;
    private int gender;
    private String regdt;
    private String profileImg;
}
