package com.beyond.mvc;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beyond.mvc.member.model.vo.Member;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	/*
	 * @ResponseBody
	 *   - 일반적으로 컨트롤러 메소드의 반환형이 String일 경우, 뷰의 이름을 반환한다.
	 *   - @ResponseBody 붙은 메소드의 반환형이 String일 경우 해당 요청을 보낸 클라이언트에 전달할 데이터를 의미한다.
	 *   
	 * jackson 라이브러리
	 *   - 자바 객체를 JSON 형태의 데이터로 변환하기 위한 라이브러리이다.
	 *   - 스프링에서는 jackson 라이브러리를 추가하고 @ResponseBody을 사용하면 컨트롤러 메소드에서 리턴되는 객체를
	 *     자동으로 JSON 문자열로 변경해서 응답한다.
	 */
	@GetMapping("/test")
	@ResponseBody
    public Object test() {
		Member member = new Member();
		
		member.setNo(1);
		member.setId("hong123");
		member.setPassword("1234");
		member.setName("홍길동");
		member.setAddress("서울특별시");
		member.setEmail("hong123@naver.com");
//		
//        return member;
		
//		Map<String, Object> map = new HashMap<>();
//		
//		map.put("test1", null);
//		map.put("test2", "hi");
//		map.put("test3", 10);
//		map.put("test4", false);
//		map.put("test5", member);
		
//		return map;
		
		List<Object> list = new ArrayList<>();
		
		list.add(null);
		list.add("hi");
		list.add(10);
		list.add(false);
//		list.add(member);
		
		return list;
    }
	
}
