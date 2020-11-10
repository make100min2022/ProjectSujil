package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.AdminDTO;
import com.dto.QnaDTO;

public class QnaDAO {
	
	public int qnaAdd(SqlSession session, QnaDTO dto) {
		int n = session.insert("QnaMapper.qnaAdd", dto);
		return n;
	}
	public int qnaDel(SqlSession session, String qNum) {
		int n = session.delete("QnaMapper.qnaDel", qNum);
		return n;
	}
	public int qnaUpdate(SqlSession session, QnaDTO dto) {
		int n = session.update("QnaMapper.qnaUpdate", dto);
		return n;
	}
	public List<QnaDTO> qnaRead(SqlSession session) {
		List<QnaDTO> list = session.selectList("QnaMapper.qnaRead");
		return list;
	}
	public String qnaReplyRead(SqlSession session, int num) {
		String content = session.selectOne("QnaMapper.qnaReplyRead", num);
		return content;
	}
	public int replayUpdate(SqlSession session, QnaDTO dto) {
		int n = session.update("QnaMapper.qnaReplyUpdate", dto);
		return n;
	}
	public QnaDTO qnaUpdateRead(SqlSession session, String qnum) {
		QnaDTO qDTO = session.selectOne("QnaMapper.qnaUpdateRead", qnum);
		return qDTO;
	}
}
