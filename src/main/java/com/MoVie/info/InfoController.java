package com.MoVie.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.lookup.TypeSystem.HashedParameterizedTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MoVie.api.model.DailyBoxOffice;
import com.MoVie.api.model.NaverSearch;
import com.MoVie.common.BoxOfficeAPI;
import com.MoVie.common.NaverSearchAPI;
import com.MoVie.info.bo.InfoBO;
import com.MoVie.info.model.BoxOfficeView;

@RequestMapping("/info")
@Controller
public class InfoController {

	@Autowired
	private InfoBO infoBO;
	@Autowired
	private NaverSearchAPI api;
	@Autowired
	private BoxOfficeAPI bApi;
	
	@GetMapping("/boxoffice_view")
	public String boxoffice(Model model) {
		//List<Map<String, String>> result = infoBO.getBoxOfficeView();
		List<Map<String, Object>> list = infoBO.getBoxOfficeView();
		
		model.addAttribute("result", list);
		//model.addAttribute("list", list);
		model.addAttribute("viewName", "info/boxoffice");
		return "template/layout";
	}
}
