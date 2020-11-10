package com.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.config.MySqlSessionFactory;
import com.dao.AdminDAO;
import com.dao.QnaDAO;
import com.dto.AdminDTO;
import com.dto.QnaDTO;

public class QnaService {
	public int qnaAdd(QnaDTO dto) {
		SqlSession session = MySqlSessionFactory.getSession();
		QnaDTO aDTO = new QnaDTO();
		QnaDAO dao = new QnaDAO();
		int n=0;
		try {
			n = dao.qnaAdd(session, dto);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			session.close();
		}
		
		return n;
	}
	public int qnaDel(String qNum) {
		SqlSession session = MySqlSessionFactory.getSession();
		QnaDTO aDTO = new QnaDTO();
		QnaDAO dao = new QnaDAO();
		int n=0;
		try {
			n = dao.qnaDel(session, qNum);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			session.close();
		}
		
		return n;
	}
	public int qnaUpdate(QnaDTO dto) {
		SqlSession session = MySqlSessionFactory.getSession();
		QnaDAO dao = new QnaDAO();
		int n=0;
		try {
			System.out.println(dto.getQnum());
			n = dao.qnaUpdate(session, dto);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			session.close();
		}
		
		return n;
	}
	public List<QnaDTO> qnaRead() {
		SqlSession session = MySqlSessionFactory.getSession();
		List<QnaDTO> list = null;
		QnaDAO dao = new QnaDAO();
		try {
			list = dao.qnaRead(session);
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			session.close();
		}
		
		return list;
	}
	
	public int replayUpdate(QnaDTO dto) {
		SqlSession session = MySqlSessionFactory.getSession();
		QnaDAO dao = new QnaDAO();
		int n = 0;
		try {
			n = dao.replayUpdate(session, dto);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			session.close();
		}
		
		return n;
	}
	
	public QnaDTO qnaUpdateRead(String qnum) {
		SqlSession session = MySqlSessionFactory.getSession();
		QnaDAO dao = new QnaDAO();
		QnaDTO qDTO = new QnaDTO();
		int n = 0;
		try {
			qDTO = dao.qnaUpdateRead(session, qnum);
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			session.close();
		}
		
		return qDTO;
	}
	
}
