package com.koreait.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String main() {
        return "home";
    }

    // 만약 주소값 명이 home이라면 (return 하고자 하는 jsp 파일명과 같다면)
    // 리턴 타입을 void로 해도 된다.
}
