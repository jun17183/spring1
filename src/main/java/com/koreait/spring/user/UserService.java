package com.koreait.spring.user;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private HttpSession session;

    public String login(UserEntity param) {
        UserEntity result = mapper.selUser(param);
        if (result == null) {   // id 없음
            return "/user/login?err=1";
        } else if (BCrypt.checkpw(param.getUpw(), result.getUpw())) {  // id, password 일치
            result.setUpw(null);
            session.setAttribute("loginUser", result);
            return "/board/list";
        } else {    // password 불일치
            return "/user/login?err=2";
        }
    }

    public int join(UserEntity param) {
        String cryptPw = BCrypt.hashpw(param.getUpw(),BCrypt.gensalt());
        param.setUpw(cryptPw);
        return mapper.insUser(param);
    }
}

// Service : 로직 담당
/*
    만약에 UserService1 implements UserServiceImpl 클래스와
    UserService2 implements UserServiceImpl 클래스가 있다고 하자. 둘다 UserServiceImpl을 상속하는.

    두 클래스를 @Service처럼 어노테이션을 사용하여 빈 등록(객체화)을 했다고 하자.
    이걸 Controller에서 사용하기 위해 @Autowired로 부르면 에러가 터진다.
    왜냐하면 UserServiceImpl 중에서 어떤 클래스를 사용해야 할지 모르기 때문. (반대로 하나도 없어도 에러가 터짐)
    이 경우를 막기 위해 @Service("service1") @Service("service2") 이렇게 빈 등록을 하고
    Controller에서 service1을 쓸지 2를 쓸지 입력을 해주면 된다.
    문제는 이 경우 소스 수정이 불가피하다는 점.

    소스 수정을 피하려면 xml을 이용하는 것이 좋다.
    xml에 UserService1과 UserService2를 만들어두고 이를 모두 객체화시킨다.
    또한 컨트롤러도 객체화시킨 후 해당 컨트롤러에 어떤 Service를 연결해 줄지를 정해주기만 하면 된다.
*/
