package com.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.dto.JusoDTO;

public class JusoDAO {

	public List<String> setSigun(SqlSession session, String sido) {
		List<String> sigunList = session.selectList("JusoMapper.setSigun", sido);
		return sigunList;
	}
	
	public List<String> setDong(SqlSession session, String sigun) {
		List<String> dongList = session.selectList("JusoMapper.setDong", sigun);
		return dongList;
	}

	public List<JusoDTO> searchCode(SqlSession session, Map<String, String> map) {
		List<JusoDTO> codeList = session.selectList("JusoMapper.searchCode", map);
		return codeList;
	}
}
