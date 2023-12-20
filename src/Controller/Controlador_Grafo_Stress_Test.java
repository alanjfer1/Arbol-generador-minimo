package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Modelo.CreadorDeGrafos;
import Modelo.GrafoPesado;
import Vista.Marco;


public class Controlador_Grafo_Stress_Test extends MouseAdapter{
	private Marco marco;
	private CreadorDeGrafos cg;
	private GrafoPesado nuevoGrafo;
	
//==============================================================================
	public Controlador_Grafo_Stress_Test(Marco marco) {
		this.marco= marco;
		cg = new CreadorDeGrafos();
	}
//==============================================================================
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		//ESTE VALOR LO VA A NECESITAR LUEGO LOS ALGORITMOS PARA SABER QUE 
		//VAN A TRABAJAR CON UN GRAFO GENERADO DE MANERA RANDOM
		marco.setNumero_EntradaGrafo_O_GrafoStress(2);
		
		nuevoGrafo = new GrafoPesado(50);
		nuevoGrafo = nuevoGrafo.aleatorio(nuevoGrafo);
		
		cg.setGrafoPesado(nuevoGrafo);
		
		marco.getInfo().setText(nuevoGrafo.dar_Info_Grafo());
			
	}
	
//------------------------------------------------------------------------------
	
	public CreadorDeGrafos getCreadorGrafo() {
		return this.cg;
	}

	public GrafoPesado getNuevoGrafo() {
		return nuevoGrafo;
	}

	public void setNuevoGrafo(GrafoPesado nuevoGrafo) {
		this.nuevoGrafo = nuevoGrafo;
	}
	
	
}
