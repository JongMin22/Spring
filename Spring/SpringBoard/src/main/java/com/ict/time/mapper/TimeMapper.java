package com.ict.time.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	
	// ���̹�Ƽ���� �������̽� ���Ͽ� �޼��带 ���� �ϰ�(���๮ ���� ���� ����)
	// ������ �������� �ش� �޼��忡 �����ϸ� ������ �����ϴ�.
	
	
	// 1. ������̼��� ����� �ش� �޼��� ȣ��� DB�� ������ �������� �ۼ�
	@Select("SELECT SYSDATE from dual")
	public String getTime();

	// 2. �޼��常 ����� ���� �ܺ� xml���ϰ� ������ �ش� ���Ͽ� �������� �ۼ�(�ַ� ���� ���)
	public String getTime2();
	
}
