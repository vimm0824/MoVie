package com.MoVie.info;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MoVie.info.bo.InfoBO;

@RequestMapping("/info")
@Controller
public class InfoController {

	@Autowired
	private InfoBO infoBO;
	
	@GetMapping("/boxoffice_view")
	public String boxoffice(Model model) {
		List<Map<String, Object>> list = infoBO.getBoxOfficeView();
		
		model.addAttribute("result", list);
		model.addAttribute("viewName", "info/boxoffice");
		return "template/layout";
	}
}
