package com.fpe.blocknotas3.junit4.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fpe.blocknotas3.logic.Student;

public class TestStudent {
	
	Student st;

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStudentStringStringInteger() {
		Student st = new Student("Miguel Pozos", "Dinámica de Fluidos", 89);
		
	}

	@Test
	public void testGetName() {
		Student st = new Student("Miguel Pozos", "Dinámica de Fluidos", 89);
		Assert.assertEquals("Miguel Pozos", st.getName());
	}

	@Test
	public void testGetCurso() {
		Student st = new Student("Miguel Pozos", "Dinámica de Fluidos", 89);
		Assert.assertEquals("Dinámica de Fluidos", st.getCurso());
	}
	
	@Test
	public void testGetEdad() {
		Student st = new Student("Miguel Pozos", "Dinámica de Fluidos", 89);
		Assert.assertEquals(89, ((int)st.getEdad()));
	}

	

}
