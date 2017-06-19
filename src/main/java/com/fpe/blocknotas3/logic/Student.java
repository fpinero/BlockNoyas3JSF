package com.fpe.blocknotas3.logic;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class Student {

	private String name;
	private String curso;
	private Integer edad;

	public Student() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Integer getEdad() {
//		if (edad == null) {
//			edad = 0;
//		}
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public void isStudendtAccepted() {

		String textoMsg = "El alumno " + name + " del curso " + curso + " de edad " + edad + " ha sido rechadazo con el número: ";
		int numeroAleatorio = (int) Math.round(Math.random() * 100);
		System.out.println("numeroAleatorio = " + numeroAleatorio);
		if (numeroAleatorio > 50) {
			textoMsg = "El alumno " + name + " del curso " + curso + " de edad " + edad + " ha sido aceptado con el número: ";
		}
		textoMsg += String.valueOf(numeroAleatorio);
		addMessage(textoMsg);

	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}