package com.ict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/secu/*")
@Controller
public class SecurityController {
	
	@GetMapping("/all")
	public void doAll() {
		log.info("모든 회원이용가능한 all");
	}
	@GetMapping("/member")
	public void doMember() {
		log.info("ȸ���鸸 ���� ������ Member ����");
	}
	@GetMapping("/admin")
	public void doAdmin() {
		log.info("��ڸ� ���� ������ Admin ����");
	}

}
