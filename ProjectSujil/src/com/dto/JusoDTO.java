package com.dto;

public class JusoDTO {
	private String sido;
	private String sigun;
	private String dong;
	private String sgccd;
	private String sitecd;
	
	public JusoDTO() {
		super();
	}

	public JusoDTO(String sido, String sigun, String dong, String sgccd, String sitecd) {
		super();
		this.sido = sido;
		this.sigun = sigun;
		this.dong = dong;
		this.sgccd = sgccd;
		this.sitecd = sitecd;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getSigun() {
		return sigun;
	}

	public void setSigun(String sigun) {
		this.sigun = sigun;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getSgccd() {
		return sgccd;
	}

	public void setSgccd(String sgccd) {
		this.sgccd = sgccd;
	}

	public String getSitecd() {
		return sitecd;
	}

	public void setSitecd(String sitecd) {
		this.sitecd = sitecd;
	}

	@Override
	public String toString() {
		return "JusoDTO [sido=" + sido + ", sigun=" + sigun + ", dong=" + dong + ", sgccd=" + sgccd + ", sitecd="
				+ sitecd + "]";
	}

	
	
	
}
