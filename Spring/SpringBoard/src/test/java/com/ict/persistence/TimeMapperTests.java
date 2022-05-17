package com.ict.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ict.time.mapper.TimeMapper;

import lombok.extern.log4j.Log4j;


// Runwith, ContextConfiguration, Log4j ������̼� ���̱�
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TimeMapperTests {
	
	// �������̽��� ȣ���Ϸ��� ����Ŭ����ȭ �ؾ���.
	// ������ ����(@AutoWired)�ϸ� �ڵ����� ���̹�Ƽ���� ��������
	
	@Autowired
	private TimeMapper timeMapper;
	
	// @Test
	public void testGetTime() {
		log.info("���� �ð� ��ȸ��...");
		log.info(timeMapper.getTime());
		
	}

	// testGetTime2�� ���� timeMapper�� getTime2�� ȣ�����ּ���.
	@Test
	public void testGetTime2() {
		log.info("���� �ð� ��ȸ");
		log.info(timeMapper.getTime2());
	}
}
