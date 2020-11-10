package com.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.BeachDAO;
import com.dto.BeachDTO;

public class BeachService {
	public List<BeachDTO> BeachSearch(String doNm) {
		SqlSession session = MySqlSessionFactory.getSession();
		BeachDAO dao = new BeachDAO();
		List<BeachDTO> list=null;
		try {
			list = dao.BeachSearch(session, doNm);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
		
		return list;
	}
	
}

