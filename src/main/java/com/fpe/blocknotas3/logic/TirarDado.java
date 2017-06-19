package com.fpe.blocknotas3.logic;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
public class TirarDado {
	
	public void generaRandomNumber(ActionEvent actionEvent) {
		
		int numeroAleatorio = (int) Math.round(Math.random()*99);
		String numGenerado = String.valueOf(numeroAleatorio);
		addMessage("El número obtenido es: " + numGenerado);
	}

	public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
