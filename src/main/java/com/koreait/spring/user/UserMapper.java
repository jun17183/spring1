package com.koreait.spring.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserEntity param);
    UserEntity selUser(UserEntity param);
    int updUser(UserEntity param);
}


// Mapper : DAO 역할. data에서 가져오는 역할