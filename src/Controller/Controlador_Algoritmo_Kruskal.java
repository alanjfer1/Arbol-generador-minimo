package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import Modelo.Algoritmo_Kruskal;
import Modelo.CreadorDeGrafos;
import Modelo.GrafoPesado;
import Vista.Marco;

public class Controlador_Algoritmo_Kruskal extends MouseAdapter{
	private Marco marco;
	private CreadorDeGrafos cg;
	
//==============================================================================
		public Controlador_Algoritmo_Kruskal(Marco marco) {
			this.marco= marco;
		}
//==============================================================================
		
	@Override
	public void mouseClicked(MouseEvent e) {
		
		obtenerCreadorDeGrafo();
		
		
	    if(cg.getGrafo() ==null) {
	    	JOptionPane.showMessageDialog(null,
	    		    "Por favor, ingrese grafo.",
	    		    "Warning",
	    		    JOptionPane.WARNING_MESSAGE);
	    	
	    } else {
	    	
	    	
			
		GrafoPesado nuevo_grafo = Algoritmo_Kruskal.encontrar_Arbol_Generador_Minimo(cg.getGrafo());
		
		long tiempo = nuevo_grafo.getTiempo_Para_Prim_O_Kruskal(); 
		
		marco.getInfo().setText("==================ALGORITMO KRUSKAL====================== " + "\n" 
		+ llamarDarInfo(nuevo_grafo) + "\n" + "\n" + "Tiempo de ejecucion del algoritmo: " + tiempo + " Seg.");
		
	    }
	}

//------------------------------------------------------------------------------
	
public void obtenerCreadorDeGrafo() {
		
		//NECESITO SABER A QUE INSTANCIA DE CONTROLADO PEDIRLE EL GRAFO PARA TRABAJAR
		
		if(marco.getNumero_EntradaGrafo_O_GrafoStress() == 1) //1 PARA GRAFO INGRESADO
			cg= marco.getControladorGrafoUsuario().getCreadorGrafo();
		
		else if (marco.getNumero_EntradaGrafo_O_GrafoStress() == 2) //2 PARA GRAFO STRESS
			cg= marco.getControlador_Grafo_Stress().getCreadorGrafo();
		
		else
			cg= marco.getControlador_Archivo().getCreadorGrafo();
	}

//------------------------------------------------------------------------------
	//Este metodo en caso de que el usuario ingrese un grafo de espias
	//con nombres, va a mnostrar los nombres cuando se muestre el grafo
	public String llamarDarInfo(GrafoPesado g) {
		
		String s;
		
		if(marco.getNumero_EntradaGrafo_O_GrafoStress() == 1)
			s = g.dar_Info_Grafo(cg.getNombreEspias());
		else
			s= g.dar_Info_Grafo();
		
		return s;
		
	}

//------------------------------------------------------------------------------
}
