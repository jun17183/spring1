package com.koreait.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService service;

    @RequestMapping("/list")
    public String list(Model model) {
        List<BoardDomain> list = service.selBoardList();
        model.addAttribute("list", list);
        return "board/list";
    }

    @RequestMapping("/detail")
    public String detail(BoardDTO param, Model model) {
        BoardDomain data = service.selBoard(param);
        model.addAttribute("data", data);
        return "board/detail";
    }
    // BoardDTO를 매개변수로 받는다고 적어주고 웹 쪽에서 iboard 값을 보내면 스프링이 자동으로 해당 값을 DTO에 set 시킴
    // 그 DTO(param)을 바로 메서드 전달인자로 보내주면 된다.

    @ResponseBody
    @RequestMapping(value = "/cmtInsSel", method = RequestMethod.POST)
    public Map<String, Integer> cmtInsSel(@RequestBody BoardCmtEntity param) {   // key => String, value = Int
        System.out.println("param : " + param);
        Map<String, Integer> data = new HashMap<>();
        data.put("result", 1);

        return data;
    }
    // 원래 컨트롤러의 목적은 jsp 파일을 여는 것(jsp 위치 값 제공)
    // @ResponseBody를 붙여주면 요청이 들어온 데이터를 문자열 형태로 변경해준다. 특히 json 형태의 문자열로.
    // 만약 @ResponseBody를 붙여준 상태로 객체를 리턴해주면 해당 객체를 json 형태로 변환한 문자열로 리턴
    // (ResponseBody를 사용하려면 jackson이나 gson같은 라이브러리 설치가 우선적으로 필요함)
}

