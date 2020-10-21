package com.dto;

public class DayDTO {
	private String date;
	private String taste;
	private String smell;
	private String chromaticity;
	private String pH;
	private String turbidity;
	private String residualCI;
	public DayDTO() {
		super();
	}
	public DayDTO(String date, String taste, String smell, String chromaticity, String pH, String turbidity,
			String residualCI) {
		super();
		this.date = date;
		this.taste = taste;
		this.smell = smell;
		this.chromaticity = chromaticity;
		this.pH = pH;
		this.turbidity = turbidity;
		this.residualCI = residualCI;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTaste() {
		return taste;
	}
	public void setTaste(String taste) {
		this.taste = taste;
	}
	public String getSmell() {
		return smell;
	}
	public void setSmell(String smell) {
		this.smell = smell;
	}
	public String getChromaticity() {
		return chromaticity;
	}
	public void setChromaticity(String chromaticity) {
		this.chromaticity = chromaticity;
	}
	public String getpH() {
		return pH;
	}
	public void setpH(String pH) {
		this.pH = pH;
	}
	public String getTurbidity() {
		return turbidity;
	}
	public void setTurbidity(String turbidity) {
		this.turbidity = turbidity;
	}
	public String getResidualCI() {
		return residualCI;
	}
	public void setResidualCI(String residualCI) {
		this.residualCI = residualCI;
	}
	@Override
	public String toString() {
		return "DayDTO [date=" + date + ", taste=" + taste + ", smell=" + smell + ", chromaticity=" + chromaticity
				+ ", pH=" + pH + ", turbidity=" + turbidity + ", residualCI=" + residualCI + "]";
	}
	
}
