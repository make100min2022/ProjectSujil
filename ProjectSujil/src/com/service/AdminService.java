package com.service;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.config.MySqlSessionFactory;
import com.dao.AdminDAO;
import com.dto.AdminDTO;

public class AdminService {
	public AdminDTO AdminLogin(AdminDTO dto) {
		SqlSession session = MySqlSessionFactory.getSession();
		AdminDTO aDTO = new AdminDTO();
		AdminDAO dao = new AdminDAO();
		try {
			dao.AdminLogin(session, dto);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
		
		return aDTO;
	}
}
