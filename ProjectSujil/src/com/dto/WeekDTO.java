package com.dto;

public class WeekDTO {
	
	private String cltdt;
	private String bacteria;
	private String TotalColi;
	private String Coli;
	private String Ammonia;
	private String Nnitrogen;//Nitrate nitrogen
	private String Evaresi;//Evaporation residue
	public String getCltdt() {
		return cltdt;
	}
	public void setCltdt(String cltdt) {
		this.cltdt = cltdt;
	}
	public String getBacteria() {
		return bacteria;
	}
	public void setBacteria(String bacteria) {
		this.bacteria = bacteria;
	}
	public String getTotalColi() {
		return TotalColi;
	}
	public void setTotalColi(String totalColi) {
		TotalColi = totalColi;
	}
	public String getColi() {
		return Coli;
	}
	public void setColi(String coli) {
		Coli = coli;
	}
	public String getAmmonia() {
		return Ammonia;
	}
	public void setAmmonia(String ammonia) {
		Ammonia = ammonia;
	}
	public String getNnitrogen() {
		return Nnitrogen;
	}
	public void setNnitrogen(String nnitrogen) {
		Nnitrogen = nnitrogen;
	}
	public String getEvaresi() {
		return Evaresi;
	}
	public void setEvaresi(String evaresi) {
		Evaresi = evaresi;
	}
	@Override
	public String toString() {
		return "WeekDTO [cltdt=" + cltdt + ", bacteria=" + bacteria + ", TotalColi=" + TotalColi + ", Coli=" + Coli
				+ ", Ammonia=" + Ammonia + ", Nnitrogen=" + Nnitrogen + ", Evaresi=" + Evaresi + "]";
	}
	public WeekDTO(String cltdt, String bacteria, String totalColi, String coli, String ammonia, String nnitrogen,
			String evaresi) {
		super();
		this.cltdt = cltdt;
		this.bacteria = bacteria;
		TotalColi = totalColi;
		Coli = coli;
		Ammonia = ammonia;
		Nnitrogen = nnitrogen;
		Evaresi = evaresi;
	}
	public WeekDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}