package com.fpe.blocknotas3.logic;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.fpe.blocknotas3.persistenciavirtual.AlumnosAceptados;

@ManagedBean
public class Student {

	private String name;
	private String curso;
	private Integer edad;

	List<String> cursosDisponibles;
	
	public Student() {
		cursosDisponibles = new ArrayList<>();
		
		cursosDisponibles.add("Dinamica de Fluidos");
		cursosDisponibles.add("Computación inicial");
		cursosDisponibles.add("Experto en computación");
		cursosDisponibles.add("Aerodinámica Avanzada");
		cursosDisponibles.add("Inglés para Turismo");
		cursosDisponibles.add("Master en Explosivos");
		cursosDisponibles.add("Ofimática para Managers");
		cursosDisponibles.add("Saber expresarse en público");
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
	
	public List<String> getCursosDisponibles() {
		return cursosDisponibles;
	}

	public void isStudendtAccepted() {

		String textoMsg = "El alumno " + name + " del curso " + curso + " de edad " + edad + " ha sido rechazado con el número: ";
		int numeroAleatorio = (int) Math.round(Math.random() * 100);
		System.out.println("numeroAleatorio = " + numeroAleatorio);
		if (numeroAleatorio > 50) {
			textoMsg = "El alumno " + name + " del curso " + curso + " de edad " + edad + " ha sido aceptado con el número: ";
			//añadamos el alumno a la lista de los aceptados
			Student aceptadoAlu = new Student();
			aceptadoAlu.setName(name);
			aceptadoAlu.setCurso(curso);
			aceptadoAlu.setEdad(edad);
			AlumnosAceptados aa = new AlumnosAceptados();
			aa.aceptaAlumno(aceptadoAlu);
		}
		textoMsg += String.valueOf(numeroAleatorio);
		addMessage(textoMsg);

	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}