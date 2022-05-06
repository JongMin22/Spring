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
		log.info("All ������");
	}
	@GetMapping("/member")
	public void doMember() {
		log.info("Memner ������");
	}
	@GetMapping("/admin")
	public void doAdmin() {
		log.info("������ ������ ");
	}

}
