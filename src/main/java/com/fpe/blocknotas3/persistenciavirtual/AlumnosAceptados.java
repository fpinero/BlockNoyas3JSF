package com.fpe.blocknotas3.persistenciavirtual;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.fpe.blocknotas3.logic.Student;

@ManagedBean
@SessionScoped
public class AlumnosAceptados implements Serializable{

	public static List<Student> listaAlumnosAceptados = new ArrayList<>();

	public AlumnosAceptados() {

	}
	
	public void aceptaAlumno(Student student){
		
		listaAlumnosAceptados.add(student);
		System.out.println("El student " + student.getName() +" ha sido añadido a la lista de los aceptados");
		System.out.println("***********************************");
		System.out.println("********Alumnos Aceptados*********");
		
		for (Student st : listaAlumnosAceptados) {
			System.out.println(st.getName());
		}
		
	}

	public List<Student> getListaAlumnosAceptados() {
		return listaAlumnosAceptados;
	}
	
	
	

}