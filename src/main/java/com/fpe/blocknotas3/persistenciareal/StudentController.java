package com.fpe.blocknotas3.persistenciareal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
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
	
	public String loadStudent(String studentName){
		
		logger.info("Loading student: " + studentName);
		
//		int idStudent = findIdStudent(studentName);
		
		try{
			Student theStudent = studentDbUtil.getStudent(studentName);
			
			//pongo el objeto student en un parametro para poder usarlo desde el jsf
			ExternalContext externalContext = 
					FacesContext.getCurrentInstance().getExternalContext();		

			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("student", theStudent);	
//			requestMap.put("idStudent", idStudent);	
		
		}catch (Exception e) {
			logger.log(Level.SEVERE, "Error loading student: " + studentName);
			addMessage(e.getMessage());
			return null;
		}
		logger.info("reenviando a update-student-form.xhtml\n");
		return "update-student-form.xhtml";
	}
	
	public String updateStudent(Student theStudent, String oldName){
		
		logger.info("updating student: " + theStudent);
		logger.info("oldName="+oldName);
		
		
		try{
			//actualicemos el student en la DB
			studentDbUtil.updateStudent(theStudent, oldName);
		}catch (Exception e) {
			logger.log(Level.SEVERE, "Error updating student: " + theStudent);
			addMessage(e.getMessage());
			return null;
		}
		
		return "lista-db-students?faces-redirect=true";
	}

//	public int findIdStudent(String studentName){
//		
//		logger.info("averiguando id del estudent: " + studentName);
//		int id = 0;
//		try{
//			 id = studentDbUtil.giveMeStudentId(studentName);
//		}catch (Exception e){
//			logger.log(Level.SEVERE, "Error averiguando el id del student: " + studentName);
//			addMessage(e.getMessage());
//			return -1;
//		}
//		return id;
//		
//	}
	
	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	
}
