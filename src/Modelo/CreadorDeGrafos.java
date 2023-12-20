package Modelo;

import java.util.ArrayList;

public class CreadorDeGrafos {
	GrafoPesado grafo;
	
	ArrayList<String> nombresEspias= new ArrayList<String>();
	
	

//------------------------------------------------------------------------------

	public GrafoPesado crearGrafo(int n, ArrayList<String> nombres)
	{
		grafo = new GrafoPesado(n);
		
		this.nombresEspias = nombres;
		
		grafo.setearAristas(nombresEspias);
		
		return grafo;
	}
	
//------------------------------------------------------------------------------
	
		//GETTERS AND SETTERS
	public GrafoPesado getGrafo() {
		return grafo;
	}
	
	public void setGrafoPesado(GrafoPesado g) {
		this.grafo= g;
	}
	
	public ArrayList<String> getNombreEspias (){
		return nombresEspias;
	}

	public void setNombreEspias(ArrayList<String> s) {
		this.nombresEspias = s;
	}
	
	


	
	

}