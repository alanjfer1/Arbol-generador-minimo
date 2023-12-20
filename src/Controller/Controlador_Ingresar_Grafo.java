package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Modelo.GrafoPesado;
import Modelo.CreadorDeGrafos;
import Vista.Marco;

public class Controlador_Ingresar_Grafo extends MouseAdapter{
	
	private Marco marco;
	private CreadorDeGrafos cg;
	private GrafoPesado nuevoGrafo;
	ArrayList<String> nombres_de_Los_Espias= new ArrayList<String>();
	
//==============================================================================	
	public Controlador_Ingresar_Grafo(Marco marco) {
		this.marco= marco;

		cg = new CreadorDeGrafos();
		

	}
//==============================================================================
	
	@SuppressWarnings("static-access")
	@Override
	public void mouseClicked(MouseEvent e) {
		
		//ESTE VALOR LO VA A NECESITAR LUEGO LOS ALGORITMOS PARA SABER QUE 
		//VAN A TRABAJAR CON UN GRAFO INGRESADO POR EL USUARIO
		marco.setNumero_EntradaGrafo_O_GrafoStress(1); 
		
		//---TODA LA PARTE VISUAL E INTERACTIVA DEL GRAFO---------------
		JOptionPane cuantos_Espias= new JOptionPane();
		
		int numero_De_Espias= Integer.parseInt(cuantos_Espias.showInputDialog("Cuantos espias son?"));
		
		for(int i=0; i<numero_De_Espias; i++)
		{
			String espia= cuantos_Espias.showInputDialog("Ingrese el nombre del espia: ");
			
			nombres_de_Los_Espias.add(espia);
		}
		
		//---CREO EL GRAFO CON LA INFORMACION----------------
		
		nuevoGrafo = cg.crearGrafo(numero_De_Espias, nombres_de_Los_Espias);
		
		marco.getInfo().setText(nuevoGrafo.dar_Info_Grafo(nombres_de_Los_Espias));
		
	}
//------------------------------------------------------------------------------
	
	//GETTERS AND SETTERS	
	public CreadorDeGrafos getCreadorGrafo() {
		return cg;
	}

	public GrafoPesado getNuevoGrafo() {
		return nuevoGrafo;
	}
	
	public ArrayList<String> getNombreEspias() {
		return  nombres_de_Los_Espias;
	}
	
	
	
	
}
