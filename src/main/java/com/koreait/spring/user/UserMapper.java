package com.koreait.spring.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserEntity param);
}


// Mapper : DAO 역할. data 에서 가져오는 역할