package com.fpe.blocknotas3.persistenciareal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.fpe.blocknotas3.logic.Student;


public class StudentDbUtil {
	
	private static StudentDbUtil instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/students_con";
	
	//hagámosla singleton
	public static StudentDbUtil getInstance() throws Exception {
		if (instance == null) {
			instance = new StudentDbUtil();
		}
		
		return instance;
	}
	
	private StudentDbUtil() throws Exception {
		dataSource = getDataSource();
	}

	private DataSource getDataSource() throws NamingException{
		Context context = new InitialContext();
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		return theDataSource;
	}
	
	public List<Student> getStudents() throws Exception {
		List<Student> students = new ArrayList<>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();
			String sql = "SELECT * FROM students ORDER BY nombre";
			myStmt =  myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()){
				String name = myRs.getString("nombre");
				System.out.println("leyendo de la db name="+name);
				String course = myRs.getString("curso");
				System.out.println("leyendo de la db course="+course);
				Integer age = myRs.getInt("edad");
				System.out.println("leyendo de la db age="+String.valueOf(age));
				Student tmpStudent = new Student(name, course, age);
				students.add(tmpStudent);
			}
			close (myConn, myStmt, myRs);
			return students;
		} 
		catch (Exception e) {
			System.out.println("excepcion en getStudents\n" + e);
			close (myConn, myStmt, myRs);
			return null;
		}
	}
	
	public void addStudent(Student theStudent) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = getConnection();
			String sql = "INSERT INTO students (nombre, curso, edad) values (?, ?, ?)";
						
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, theStudent.getName());
			myStmt.setString(2, theStudent.getCurso());
			myStmt.setInt(3, theStudent.getEdad());
			
			myStmt.execute();
			
		} finally {
			close(myConn,myStmt);
		}
		
	}

	private void close(Connection theConn, Statement theStmt) {
		close(theConn, theStmt, null);
	}
	
	private void close(Connection theConn, Statement theStmt, ResultSet theRs) {
		try {
			if (theRs != null) {
				theRs.close();
			}

			if (theStmt != null) {
				theStmt.close();
			}

			if (theConn != null) {
				theConn.close();
			}
			
		} catch (Exception exc) {
			System.out.println("excepción en close\n" + exc);;
		}
	}	

	private Connection getConnection() throws Exception {
		Connection theConn = dataSource.getConnection();
		return theConn;
	}

}
