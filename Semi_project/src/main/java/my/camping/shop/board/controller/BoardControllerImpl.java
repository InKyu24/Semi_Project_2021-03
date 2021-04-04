package my.camping.shop.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import my.camping.shop.member.util.MemberException;

@Controller("boardcontroller")
@RequestMapping("/board")
public class BoardControllerImpl implements BoardController{
	
	@Override
	@RequestMapping (value = "/board.camp", method= {RequestMethod.GET, RequestMethod.POST})
	public String board () {
		
		return "board/listArticles";
	}
	
	@Override
	@RequestMapping (value = "/write.camp", method= {RequestMethod.GET, RequestMethod.POST})
	public String write (){
		return "board/articleForm";
	}

}
