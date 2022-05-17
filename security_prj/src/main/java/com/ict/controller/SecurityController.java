package com.ict.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ict.domain.AuthVO;
import com.ict.domain.MemberVO;
import com.ict.service.SecurityService;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/secu/*")
@Controller
public class SecurityController {
	
	@Autowired
	private SecurityService service;
	
	@Autowired
	private PasswordEncoder pwen;
	
	
	@GetMapping("/all")
	public void doAll() {
		log.info("All 페이지");
	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	@GetMapping("/member")
	public void doMember() {
		log.info("Member 페이지");
	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/admin")
	public void doAdmin() {
		log.info("관리자 페이지");
	}
	
	@PreAuthorize("permitAll")
	@GetMapping("/join")
	public void joinForm() {
		log.info("회원가입창 접속감지");
	}
	@PreAuthorize("permitAll")
	@PostMapping("/join")
	public void join(MemberVO vo, String[] role) {
		log.info("정보 : " + vo);
		String beforeCrPw = vo.getUserPw();
		log.info("암호화 전 : " + beforeCrPw);
		vo.setUserPw(pwen.encode(beforeCrPw));
		log.info("암호화 후 : " + vo.getUserPw());
		
		vo.setAuthList(new ArrayList<AuthVO>());
		
		for(int i=0; i < role.length; i++) {
			vo.getAuthList().add(new AuthVO());
			vo.getAuthList().get(i).setAuth(role[i]);
			vo.getAuthList().get(i).setUserId(vo.getUserId());
			
		}
		log.info(vo.getAuthList());
		
		service.insertMember(vo);
	}
	
	
}
