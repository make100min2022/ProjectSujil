package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.BeachDTO;

public class BeachDAO {
	public List<BeachDTO> BeachSearch(SqlSession session, String doNm) {
		List<BeachDTO> list = session.selectList("BeachSearch", doNm);
		
		return list;
	}
}
