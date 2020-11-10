package com.dao;

import org.apache.ibatis.session.SqlSession;

import com.dto.AdminDTO;

public class AdminDAO {
	
	public AdminDTO AdminLogin(SqlSession session, AdminDTO dto) {
		AdminDTO aDTO = new AdminDTO();
		aDTO = session.selectOne("AdminLogin", dto);
		
		return aDTO;
	}
}
