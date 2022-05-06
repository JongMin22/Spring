package com.ict.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class CommonController {
	
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model){
		log.info("���� �ź� : " + auth);
		
		model.addAttribute("errorMessage","���� �ź�");
	}
	
	
	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model){
			log.info("error ���� : " + error);
			log.info("logout ���� : " + logout);
			
			if(error != null) {
					model.addAttribute("error", "�α��� ���� �����Դϴ�. ����Ȯ���� �ٽ� ���ּ���.");
			}
			if(logout != null) {
				model.addAttribute("logout", "�α׾ƿ� �߽��ϴ�..");
			}
	}
	
}
