package com.koreait.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/cmt", method = RequestMethod.POST)
    public Map<String, Integer> cmtIns(@RequestBody BoardCmtEntity param) {   // key => String, value = Int
        System.out.println("param : " + param);

        int result = service.insBoardCmt(param);

        Map<String, Integer> data = new HashMap();
        data.put("result", result);

        return data;
    }
    // 원래 컨트롤러의 목적은 jsp 파일을 여는 것(jsp 위치 값 제공)
    // @ResponseBody를 붙여주면 요청이 들어온 데이터를 문자열 형태로 변경해준다. 특히 json 형태의 문자열로.
    // 만약 @ResponseBody를 붙여준 상태로 객체를 리턴해주면 해당 객체를 json 형태로 변환한 문자열로 리턴
    // (ResponseBody를 사용하려면 jackson이나 gson같은 라이브러리 설치가 우선적으로 필요함)

    @ResponseBody
    @RequestMapping(value = "/cmt/{iboard}")
    public List<BoardCmtDomain> cmtSel(@PathVariable("iboard") int iboard) {    // 이름이 일치하면 ("iboard") 생략 가능
        BoardCmtEntity param = new BoardCmtEntity();
        param.setIboard(iboard);
        List<BoardCmtDomain> list = service.selBoardCmtList(param);

        return list;
    }
    // get방식으로 받을 땐 매개변수에 RequestBody 적으면 안 됨
    // 왜냐하면 위의 post 방식은 값을 json 형태로 받아왔기 때문이고 지금은 그냥 문자열 주소값으로 받아오기 때문

    @ResponseBody
    @RequestMapping(value = "/cmt/{icmt}", method = RequestMethod.DELETE)
    public Map<String, Integer> cmtDel(@PathVariable int icmt) {
        BoardCmtEntity param = new BoardCmtEntity();
        param.setIboard(icmt);
        int result = service.delBoardCmt(param);

        Map<String, Integer> data = new HashMap();
        data.put("result", result);

        return data;
    }

    @ResponseBody
    @RequestMapping(value = "/cmt", method = RequestMethod.PUT)
    public Map<String, Integer> cmtMod(@RequestBody BoardCmtEntity param) {
        int result = service.modBoardCmt(param);

        Map<String, Integer> data = new HashMap();
        data.put("result", result);

        return data;
    }
}

// RestController 라는 친구가 있는데, 이 친구는 @ResponseBody를 안 적어도 모든 메서드에 ResponseBody가 박힘
