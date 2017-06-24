package com.fpe.blocknotas3.persistenciareal;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.fpe.blocknotas3.logic.Student;

@ManagedBean
@SessionScoped
public class StudentController {
	
	private List<Student> students;
	private StudentDbUtil studentDbUtil;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public StudentController() throws Exception {
		students = new ArrayList<>();
		studentDbUtil = StudentDbUtil.getInstance();
	}
	
	public List<Student> getStudents() {
		return students;
	}
	
	public void loadStudents() {
		
		try {
			//lee todos los students de la DB
			students = studentDbUtil.getStudents();
		}catch (Exception e) {
			logger.log(Level.SEVERE, "Error en loadStudents", e);
			//mostremos el error en el JSF
			addMessage(e.getMessage());
		}
		
	}
	
	public String addStudent(Student theStudent) {
		
		logger.info("Adding student: " + theStudent);
		try{
			//añadamos el student a la DB
			studentDbUtil.addStudent(theStudent);
		}catch (Exception e) {
			logger.log(Level.SEVERE, "Error en addStudent", e);
			//mostremos el error en el JSF
			addMessage(e.getMessage());
		}
		
		return "lista-db-students?faces-redirect=true";
	}
	
	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	
}
