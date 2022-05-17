package com.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ict.domain.ReplyVO;
import com.ict.service.ReplyService;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/replies")
@Log4j
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	// consumes�� �� �޼����� �Ķ���͸� �Ѱ��� �� � �������� �Ѱ������� �����ϴµ� �⺻������ rest��Ŀ����� json�� ���
	// produeces�� �Է¹��� �����͸� ���� ������ ������ ���� ����ڿ��� ����� ������ �������� ������ ��Ÿ���ϴ�.
	// jackson-databind �߰��ؾ� �۵�
	@PostMapping(value="", consumes="application/json", produces= {MediaType.TEXT_PLAIN_VALUE})
	// produces�� TEXT_PLAIN_VALUES�� �����Ƿ� ����ڵ�� ���ڿ��� �ѱ�
	public ResponseEntity<String> register(
			// rest��Ʈ�ѷ����� �޴� �Ķ���� �տ� 
			// @RequestBody ������̼��� �پ��
			// consumes�� �����
			@RequestBody ReplyVO vo){
		// ���� entity�� ���� ����
		ResponseEntity<String> entity = null;
		try {
			// ���� �۾��� ���� ���� �� ������ ���ٸ�...
			service.addReply(vo);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
			
		} catch(Exception e) {
			// catch�� �Ѿ�Դٴ°� �۾��� ������ ������ ���� ��Ȳ
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		// ���� try���̳� catch������ ���� entity���� ����
		return entity;
	}
	@GetMapping(value="all/{bno}", 
			// ���� ���ڵ����� bno�� �־ ��ȸ�ϱ⵵ �ϰ� 
			// PathVariable ������̼����� �̹� �Էµ����Ͱ� ��õǾ����Ƿ� 
			// consumes�� ���� ���� �ʾƵ� �˴ϴ�.
			// produces�� ��� ����� XML�ε�, JSON�ε� ǥ�� �� �� �ֵ���
			// �Ʒ��� ���� 2���� ��� ����ϴ�.
			produces= {MediaType.APPLICATION_ATOM_XML_VALUE,
										MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<ReplyVO>> list (@PathVariable("bno") Long bno){
		ResponseEntity<List<ReplyVO>> entity = null;
		try {
			entity = new ResponseEntity<>(
					service.listReply(bno),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	// �Ϲ� ����� �ƴ� rest��Ŀ����� �������� post�� �ƴ�
	// delete ������� ��û�ϱ� ������ @DeleteMapping�� ���ϴ�.
	@DeleteMapping(value="/{rno}", 
			produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove (@PathVariable("rno") Long rno){
		ResponseEntity<String> entity = null;
		try {
			service.removeReply(rno);
			entity = new ResponseEntity<String>(
					"SUCCESS",HttpStatus.OK);
		} catch (Exception e) {
			
			entity = new ResponseEntity<String>(
					e.getMessage(),HttpStatus.BAD_REQUEST);}
		return entity;
	}
	@RequestMapping(method= {RequestMethod.PUT,RequestMethod.PATCH},
					value="/{rno}",
					consumes="application/json",
					produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(
			// VO�� �켱 payloard�� ���� ������(json)�� �޾ƿɴϴ�.
			// @RequestBody�� ���� vo�� 
			// payload�� ���� �����͸� vo�� ȯ���ؼ� �����ɴϴ�.
			@RequestBody ReplyVO vo, @PathVariable("rno") Long rno){
				// �� ��۹�ȣ�� PathVariable�� �޾ƿɴϴ�.
	ResponseEntity<String> entity = null;
	try {
		log.info("������ :" + vo);
		vo.setRno(rno);
		log.info("������ :" + vo);
		service.modifyReply(vo);
		entity = new ResponseEntity<String>(
				"SUCCESS",HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(
					e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	return entity;
	}
			
}
