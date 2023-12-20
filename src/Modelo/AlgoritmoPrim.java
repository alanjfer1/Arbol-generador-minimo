package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class AlgoritmoPrim {

	private static Set<Integer> vertices_Del_Arbol;
	
	private static GrafoPesado nuevo_Grafo_AGM;
	
	//Necesario para saber si es conexo---------------
	private static ArrayList<Integer> L;
	private static boolean marcados[];
	
	
//------------------------------------------------------------------------------
	
	public static GrafoPesado encontrarArbolGeneradorMinimo(GrafoPesado grafo)
	{
		nuevo_Grafo_AGM= new GrafoPesado(grafo.tamano());
		
		vertices_Del_Arbol = new HashSet<Integer>();
		
		if(esConexo(grafo)) 
		{
			vertices_Del_Arbol.add(0);
				
			int i= 1;
			
			long tiempo_Inicial_Antes_Del_Algoritmo = System.currentTimeMillis();
		
			while (i < grafo.tamano() )
			{
				eleccionDeAristaMinima(grafo, vertices_Del_Arbol);
						
				i++;
			}
			
			long tiempo_Final_Despues_Del_Algoritmo = System.currentTimeMillis();
			
			long tiempo_Total = tiempo_Final_Despues_Del_Algoritmo - tiempo_Inicial_Antes_Del_Algoritmo;
			
			nuevo_Grafo_AGM.setTiempo_Para_Prim_O_Kruskal(tiempo_Total);
		}
		
		return nuevo_Grafo_AGM;
	}

//------------------------------------------------------------------------------

	private static void eleccionDeAristaMinima(GrafoPesado grafo, Set<Integer> vertices_Del_Arbol) {
		
		float valor_Arista_minima =1000000000;
		
		int vertice_Destino =0;
		
		int vertice_Origen =0;
		
		//RECORRO LOS VERTICES DEL ARBOL QUE ESTOY ARMANDO
		for( Integer vertice : vertices_Del_Arbol)
		{
			//ME PARO EN EL VERTICE
			HashMap<Integer, Float> v= grafo.getVertices().get(vertice);
			
			//RECORRO TODOS SUS VECINOS BUSCANDO LA ARISTA DE MENOR PESO
			for ( Integer key : v.keySet())
			 {
				 //SI EL VECINO NO ESTA AUN EN LA COMPONENTE CONEXA:
				 if (!vertices_Del_Arbol.contains(key))
				 {
					 //REVISO QUE EL PESO DE LA ARISTA CON EL VECINO SEA EL MINIMO
					 if(v.get(key) < valor_Arista_minima)
					 {
						 valor_Arista_minima= v.get(key);
						 vertice_Destino= key;
						 vertice_Origen= vertice;
						
					 }
				 }
			 }
		}
		
		vertices_Del_Arbol.add(vertice_Destino);
		
		nuevo_Grafo_AGM.agregarArista(vertice_Origen, vertice_Destino, valor_Arista_minima);
		
	}
	
//------------------------------------------------------------------------------
	public static boolean esConexo(GrafoPesado grafo)
	{
		if (grafo == null)
			throw new IllegalArgumentException("Se intento consultar un grafo que es null");
		if(grafo.tamano() == 0)
			return true;
		
		return alcanzables(grafo, 0).size() == grafo.tamano();
	}
		
//------------------------------------------------------------------------------	
		
	private static Set<Integer> alcanzables(GrafoPesado g, int origen)
	{	
		Set<Integer> retorno= new HashSet<Integer>();
		inicializar(g,origen);
		while ( L.size() > 0) 
		{
			int i = L.get(0);
			marcados[i] = true;
			retorno.add(i);		
			agregarVecinosPendientes(g, i);
			L.remove(0);
		}
			return retorno;
	}
	
	public void sumarPeso(int n) {
	}
	
//------------------------------------------------------------------------------

	private static void inicializar(GrafoPesado g, int origen)
	{		
		L = new ArrayList<Integer>();
		
		L.add(origen);
		
		marcados = new boolean[(g.tamano())];
	}
			


//------------------------------------------------------------------------------
		
	private static void agregarVecinosPendientes(GrafoPesado g, int i) 
	{		
		HashMap<Integer, Float> vecinos = g.vecinos(i);
				
		for ( Integer key : vecinos.keySet())
			{
				if(marcados[key] == false && L.contains(key) == false)
				{
					L.add(key);
				}
			}
				
	}	
	
//------------------------------------------------------------------------------	
	
	
	
	
	
	
	
	
	
	
	
}
