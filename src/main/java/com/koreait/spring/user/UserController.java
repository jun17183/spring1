package com.koreait.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/user")    // 1차 주소
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/login")   // 2차 주소(1차 주소가 없으면 바로 /user/login 이라고 줘야 함)
    public String login(@RequestParam(value = "err", required = false, defaultValue="0") int err
        , Model model) { // RequestParam => getParamteter
        switch (err) {
            case 1: // id 없음
                model.addAttribute("errMsg", "아이디를 확인해주세요.");   // model = request와 같은 주머니
                break;                                                      // model이 받아서 대신 request에 담아줌
            case 2: // password 틀림
                model.addAttribute("errMsg", "비밀번호를 확인해주세요.");
                break;
        }
        return "user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(UserEntity param) {
        return "redirect:" + service.login(param);
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

    @RequestMapping("/profile")
    public String profile() {
        return "user/profile";
    }

    // @PostMapping("/profile") 이렇게만 적어줄 수도 있다.
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String profile(MultipartFile profileImg) {
        return "redirect:" + service.uploadProfile(profileImg);
    }
    // 파일을 하나 보낼 땐 배열말고 그냥 multipartfile만, 아니면 배열까지.
    // 매개변수 명은 form의 input name이랑 맞춰주는 것이 좋다. 아니면 뭐 손봐야 함
    // (사실 file은 괜찮은데 다른 경우에는 이름이 다르면 추가로 적어줄게 필요함)
}

// Controller : Mapping 담당. 연결 담당 *
// @Controller => bean 등록 + 서블릿과 연결하여 요청을 받아 매핑된 메서드를 실행시켜줌

// RequestMapping 안에 입력한 주소로 요청이 들어오면 해당 메서드를 실행함
// RequestMapping의 () 안에는 다양한 옵션을 넣을 수 있는데, 옵션을 2개 이상 입력할 거라면 안의 주소도
// 그냥 /login이라고 적으면 안 되고 value="/login"이라고 해야 한다.

// 옵션 중에는 method가 있고 우리가 아는 그 get or post를 말한다. 물론 디폴트는 get
// (method=RequestMethod.GET or POST)
// 같은 주소라도 get으로 요청받았을 때와 post로 요청받았을 때 구분 가능

// mapping한 메서드의 return 은 redirect: 라는 키워드를 넣지 않는 이상
// 무조건 디스패처서블릿을 통해 해당 return 문자열의 주소를 받아 실행시킨다. (디스패처 서블릿의 viewResolver 부분)
// redirect는 jsp에서의 sendRedirect랑 같은 기능. 거기로 넘기는 것

// <Autowired>
// @를 통해 bean 등록 => 객체를 생성했다는 말
// bean에 등록된, 생성된 객체를 Autowired를 통해 받아서(UserService) service라는 변수에 담음

// <RequestParam>
// @RequestParam => getParameter와 동일
// 이 친구는 무조건 값을 받음. 그렇기에 err=? 라는 데이터가 넘어오지 않는다면 에러를 터뜨림
// 그래서 required=false로 해주어야 함 (정확히는 값이 넘어오지 않았다면 주소창에 err=? 가 안 찍혀도 되도록 해줌)
// 또한 값을 안 보낸다면 default 값을 0으로 주어 메세지를 보내지 않으면 자동으로 0이 넘어가도록 해준다.
// (사실 defaultValue를 주었기에 required는 없어도 큰 상관이 없다.)
// 문자열을 넘겨줄 경우 값을 넘겨주기 싫을 땐 그냥 required만 해줘도 된다.
// string은 int와 달리 null을 받을 수 있기 때문
