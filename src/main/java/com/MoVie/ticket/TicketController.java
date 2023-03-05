package com.MoVie.ticket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MoVie.api.BoxOfficeAPI;
import com.MoVie.mypage.bo.MypageBO;
import com.MoVie.mypage.model.UserCardView;
import com.MoVie.search.bo.SearchBO;
import com.MoVie.ticket.bo.TicketBO;
import com.MoVie.ticket.model.TicketCard;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/ticket")
@Controller
public class TicketController {

	@Autowired
	private BoxOfficeAPI boxOfficeAPI;
	@Autowired
	private SearchBO searchBO;
	@Autowired
	private TicketBO ticketBO;
	@Autowired
	private MypageBO mypageBO;
	
	@GetMapping("/select_plan_view")
	public String selectPlan(Model model, HttpSession session) {
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/user/sign_in_view";
		} 
		List<LinkedHashMap<String, String>> result = boxOfficeAPI.getDailyBoxOffice();
		model.addAttribute("result", result);
		model.addAttribute("viewName", "ticket/selectPlan");
		return "template/layout";
	}
	
	@GetMapping("/select_plan_date")
	public String selectDate(Model model) {
		// 일주일 계산
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 E요일");
		Calendar day = Calendar.getInstance();
		String date = "";
		
		List<String> week = new ArrayList<>();
		
		for (int i = 0; i <= 6; i++) {
			if (i != 0) {
				day.add(Calendar.DATE, 1);
			}
			date = sdf.format(day.getTime());
			week.add(date);
		}
		
		model.addAttribute("week", week);
		return "ticket/selectDate";
	}
	
	@GetMapping("/select_plan_time")
	public String selectTime(
			@RequestParam("movieCd") String movieCd,
			@RequestParam("date") String date,
			Model model) {
		 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		 //2023년 02월 21일 화요일 16:42~18:46
		 SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy년 MM월 dd일 E요일");
		 SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy년 MM월 dd일 E요일 HH:mm");
		 
		 Calendar time = Calendar.getInstance();
		 Date now = Calendar.getInstance().getTime();
		 time.set(Calendar.HOUR_OF_DAY, 9);
		 time.set(Calendar.MINUTE, 0);
		 // 담을 시간
		 List<String> timeList = new ArrayList<>();
		 // 상영시간 호출
 		 Map<String, Object> movie = searchBO.getDetailMovie(movieCd);
		
 		 int showTm = Integer.valueOf((String)movie.get("showTm"));
		
 		 // 한 영화는 5번 하루에 다섯번
		 for (int i = 0; i < 6; i++) {
			//boolean b = now.before(time.getTime());
			String runtime = sdf.format(time.getTime());
			// 영화시작후 영화상영시간
			time.add(Calendar.MINUTE, showTm);
			runtime = runtime + "~" + sdf.format(time.getTime());
			// 30분 극장 정비시간
			time.add(Calendar.MINUTE, 30);
			//if (b) {
				timeList.add(runtime);
			//}
		 } 
		 model.addAttribute("timeList", timeList);
		 return "ticket/selectTime";
	}
	
	@GetMapping("/select_seat_view")
	public String selectSeat(
			@RequestParam("movieCd") String movieCd,
			@RequestParam("plan") String plan,
			HttpSession session,
			Model model) {
		if (movieCd == null) {
			return "redirect:/ticket/select_plan_view";
		}
		
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/user/sign_in_view";
		}
		
		Map<String, Object> movie = searchBO.getDetailMovie(movieCd);
		
		model.addAttribute("movie", movie);
		model.addAttribute("plan", plan);
		model.addAttribute("viewName", "ticket/selectSeat");
		return "template/layout";
	}
	
	@GetMapping("/select_headcount")
	public String selectHeadcount(
			@RequestParam("headcount") int headcount,
			Model model
			) {
		model.addAttribute("headcount", headcount);
		return "ticket/selectHeadcount";
	}
	
	@GetMapping("/select_seat_num")
	public String selectSeatNum(
			@RequestParam("headcount") int headcount,
			@RequestParam("movieCd") int movieCd,
			@RequestParam("plan") String plan,
			Model model
			) {
		List<Character> charList = new ArrayList<>();
		for (char i = 'A'; i <= 'J'; i++) {
			charList.add(i);
		}
		List<String> seatList = ticketBO.getTicketSeatByMovieCdPlan(movieCd, plan);
		
		model.addAttribute("seatList", seatList);
		model.addAttribute("charList", charList);
		model.addAttribute("headcount", headcount);
		return "ticket/selectSeatNum";
	}
	
	@GetMapping("/ticket_box_view")
	public String ticketBox(
			@RequestParam(value="userId", required=false) Integer userId,
			HttpSession session,
			Model model
			) {
		int sessionId = 0;
		if ((Integer) session.getAttribute("userId") == null) {
			return "redirect:/user/sign_in_view";
		} else {
			sessionId = (Integer) session.getAttribute("userId");
		}
		if (userId == null) {
			return "redirect:/main";
		}
		if (userId != sessionId) {
			return "redirect:/main";
		}
		
		UserCardView card = mypageBO.generateCard(userId, sessionId);

		List<TicketCard> ticketList = ticketBO.getTicketCardListByUserId(sessionId);
		
		model.addAttribute("ticketList", ticketList);
		model.addAttribute("result", card);
		model.addAttribute("viewName", "ticket/ticketBox");
		return "template/layout";
	}
	
}
