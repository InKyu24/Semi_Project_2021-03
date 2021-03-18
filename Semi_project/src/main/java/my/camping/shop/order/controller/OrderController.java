package my.camping.shop.order.controller;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import my.camping.shop.member.vo.MemberVO;
import my.camping.shop.order.service.OrderService;
import my.camping.shop.order.vo.OrderVO;

@Controller("ordercontroller")
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	 
	///////////// 주문 처리 //////////////////
	@RequestMapping(value = "/order.camp", method= RequestMethod.POST, produces = "application/text; charset=utf8")			
	@ResponseBody
	public String order(HttpServletRequest req, HttpServletResponse res){
		JSONObject json=null;
		try {
			BufferedReader br=req.getReader();
			JSONParser parser=new JSONParser();
			json=(JSONObject) parser.parse(br);   // json 은 {"product":[{"quantity": 수량,"name": "제품명"}]}
			JSONArray jray=(JSONArray) json.get("product");
			
			Object []array= jray.toArray();
			
			ArrayList<OrderVO> list=new ArrayList<OrderVO>();
			for(Object o : array) {
				
				JSONObject j=(JSONObject) o;
				String prodname=(String) j.get("name"); // prodname 은 제품명
				long quantity=(Long) j.get("quantity"); //quantity는 수량
				OrderVO orderVO=new OrderVO(prodname,quantity);
				
				HttpSession session=req.getSession();
				MemberVO memberVO=(MemberVO) session.getAttribute("member");
				orderVO.setId(memberVO.getId());
				
				list.add(orderVO);
			}
			System.out.println("총 "+list.size()+"개 품목을 주문합니다");
			System.out.println(list);
			long order_group_no=orderService.order(list);

			json=new JSONObject();			
			if(true) {		
				json.put("order_group_no", order_group_no);
			} else {
				json.put("msg", "주문 실패");
			}
		}catch(Exception e) {
			e.printStackTrace();
			json.put("msg", e.getMessage());
		}	
		return json.toJSONString();
		
	}

}
