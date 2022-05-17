package com.ict.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class OracleConncetionPoolTest {

	@Autowired
	private DataSource dataSource;
	// 테스트 수행시 @Test가 붙은 메서드 하나하나를 전부 실행함
	// 그래서 보통 하나의 테스트가 끝나면 주석처리해서 안제든 실행은 가능하지만
	// 현재는 실행이 안 되게 조치함
	//@Test
	public void testConnection() {
		try(Connection con = dataSource.getConnection()) {
			// Log.info(con);
			// Log.info("hikariCP connection");
			log.info(con);
			log.info("hikariCP connection");
			log.info("히카리 연결 끝");
			
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Test
	public void testMyBatis() {
		try(SqlSession session = sqlSessionFactory.openSession();
				Connection con = session.getConnection();){
			log.info("마이바티스 연결 시작");
			log.info(session);
			log.info(con);
		}catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
}
