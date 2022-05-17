package com.ict.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.domain.TestVO;

@RestController
@RequestMapping("/test")
// Ŭ���� ���� ���� RequestMapping�� �ش� ��Ʈ�ѷ��� ����� �����ּҸ� �������ݴϴ�.
public class TestController {
	
	@RequestMapping("/hello")
	public String sayHello(){
		return "Hello Hello";
	}
	@RequestMapping("/sendVO")
	public TestVO sendTestVO() {
		
		TestVO testVO = new TestVO() ;
			
			testVO.setName("������");
			testVO.setAge(23);
			testVO.setMno(1);
		return testVO;	
	}
	@RequestMapping("/sendVOList")
	public List<TestVO> sendVOList(){
		
		List<TestVO> list = new ArrayList<>();
		for(int i =0; i<10; i++) {
			TestVO vo = new TestVO();
			vo.setMno(i);
			vo.setName(i+"����");
			vo.setAge(20+i);
			list.add(vo);
			
		}
		return list;		
	}
	@RequestMapping("/sendMap")
	public Map<Integer, TestVO> sendMap(){
		
		Map<Integer, TestVO> map = new HashMap<>();
		for(int i =0; i<10; i++) {
			TestVO vo = new TestVO();
			vo.setMno(i);
			vo.setName(i+"����");
			vo.setAge(20+i);
			map.put(i,vo);
		
		
	}
	return map;
	}
	@RequestMapping("/sendErrorAuth")
	public ResponseEntity<Void> sendListAuth(){
		
		return // �͸� Ŭ������ ó���ص� �������
			new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	@RequestMapping("/sendErrorNot")
	public ResponseEntity<List<TestVO>> sendListNot(){
		List<TestVO> list = new ArrayList<>();
		for(int i =0; i<10; i++) {
			TestVO vo = new TestVO();
			vo.setMno(i);
			vo.setName(i+"����");
			vo.setAge(20+i);
			list.add(vo);
			
		}
		return // �͸� Ŭ������ ó���ص� �������
				new ResponseEntity<List<TestVO>>(list,HttpStatus.NOT_FOUND);
	}


}
