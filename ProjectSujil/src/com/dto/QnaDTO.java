package com.dto;

public class QnaDTO {
	private int Qnum;
	private String Qtitle;
	private String Qusername;
	private String Qpasswd4;
	private String Qcontent;
	private String Qhide;
	private String Qemail;
	private String Qreply;
	public QnaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QnaDTO(int qnum, String qtitle, String qusername, String qpasswd4, String qcontent, String qhide,
			String qemail, String qreply) {
		super();
		Qnum = qnum;
		Qtitle = qtitle;
		Qusername = qusername;
		Qpasswd4 = qpasswd4;
		Qcontent = qcontent;
		Qhide = qhide;
		Qemail = qemail;
		Qreply = qreply;
	}
	public int getQnum() {
		return Qnum;
	}
	public void setQnum(int qnum) {
		Qnum = qnum;
	}
	public String getQtitle() {
		return Qtitle;
	}
	public void setQtitle(String qtitle) {
		Qtitle = qtitle;
	}
	public String getQusername() {
		return Qusername;
	}
	public void setQusername(String qusername) {
		Qusername = qusername;
	}
	public String getQpasswd4() {
		return Qpasswd4;
	}
	public void setQpasswd4(String qpasswd4) {
		Qpasswd4 = qpasswd4;
	}
	public String getQcontent() {
		return Qcontent;
	}
	public void setQcontent(String qcontent) {
		Qcontent = qcontent;
	}
	public String getQhide() {
		return Qhide;
	}
	public void setQhide(String qhide) {
		Qhide = qhide;
	}
	public String getQemail() {
		return Qemail;
	}
	public void setQemail(String qemail) {
		Qemail = qemail;
	}
	public String getQreply() {
		return Qreply;
	}
	public void setQreply(String qreply) {
		Qreply = qreply;
	}


}