package com.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.JusoDAO;
import com.dto.JusoDTO;

public class JusoService {
	JusoDAO dao;
	
	public JusoService() {
		super();
		dao = new JusoDAO();
	}

public List<String> setSigun(String sido) {
		
		SqlSession session = MySqlSessionFactory.getSession();
		List<String> sigunList = null;
		try {
			sigunList = dao.setSigun(session, sido);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return sigunList;
	}

	public List<String> setDong(String sigun) {
		SqlSession session = MySqlSessionFactory.getSession();
		List<String> dongList = null;
		try {
			dongList = dao.setDong(session, sigun);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dongList;
	}

	public List<JusoDTO> searchCode(Map<String, String> map) {
		SqlSession session = MySqlSessionFactory.getSession();
		List<JusoDTO> codeList = null;
		try {
			codeList = dao.searchCode(session, map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return codeList;
	}
}
