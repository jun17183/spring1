package com.koreait.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")    // 1차 주소
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/login")   // 2차 주소(1차 주소가 없으면 바로 /user/login 이라고 줘야 함)
    public String login() {
        return "user/login";
    }

    @RequestMapping("/join")
    public String join() {
        return "user/join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(UserEntity param) {
        System.out.println("param : " + param);
        service.join(param);
        return "redirect:/user/login";
    }
}

// RequestMapping 안에 입력한 주소로 요청이 들어오면 해당 메서드를 실행함
// RequestMapping 의 () 안에는 다양한 옵션을 넣을 수 있는데, 옵션을 2개 이상 입력할 거라면 안의 주소도
// 그냥 /login 이라고 적으면 안 되고 value="/login" 이라고 해야 한다.

// 옵션 중에는 method 가 있고 우리가 아는 그 get or post 를 말한다. 물론 디폴트는 get
// (method=RequestMethod.GET or POST)
// 같은 주소라도 get 으로 요청받았을 때와 post 로 요청받았을 때 구분 가능

// mapping 한 메서드의 return 은 redirect: 라는 키워드를 넣지 않는 이상
// 무조건 디스패처서블릿을 통해 해당 return 문자열의 주소를 받아 실행시킨다. (디스패처 서블릿의 viewResolver 부분)
// redirect 는 jsp 에서의 sendRedirect 랑 같은 기능. 거기로 넘기는 것

// * Controller : Mapping 담당. 연결 담당 *

// <Autowired>
// @를 통해 bean 등록 => 객체를 생성했다는 말
// bean 에 등록된, 생성된 객체를 Autowired 를 통해 받아서(UserService) service 라는 변수에 담음
