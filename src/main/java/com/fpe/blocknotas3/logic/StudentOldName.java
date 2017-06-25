package com.fpe.blocknotas3.logic;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class StudentOldName {
	
	private String oldName;
	
	public StudentOldName() {
		
	}

	public String getOldName() {
		return oldName;
	}

//	public void setOldName(String oldName) {
//		this.oldName = oldName;
//	}
	
	public void imputandoOldName(String oldName) {
		this.oldName = oldName;
		System.out.println("Estableciendo oldName a: " + oldName);
	}
	
	

}
