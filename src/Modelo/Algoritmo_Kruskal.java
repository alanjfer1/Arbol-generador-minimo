package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Algoritmo_Kruskal {
	private static GrafoPesado nuevo_Grafo_AGM;
	private static Map<Integer, Float> aristas_Del_Arbol;
	//Necesario para saber si es conexo---------------
	private static ArrayList<Integer> L;
	private static boolean marcados[];
	private static int[] arreglo_Union_Find;
	
	//------------------------------------------------------------------------------
	
		public static GrafoPesado encontrar_Arbol_Generador_Minimo(GrafoPesado grafo)
		{
			
			nuevo_Grafo_AGM= new GrafoPesado(grafo.tamano());
			
			arreglo_Union_Find= new int[grafo.tamano()];
			
			if(esConexo(grafo)) 
			{
			
				//AL COMIENZO CADA VERTICE ES PADRE DE SI MISMO:
				for(int i = 0; i < grafo.tamano(); i++) {
					arreglo_Union_Find[i] = i;
				}
					
				aristas_Del_Arbol = new HashMap<Integer, Float>();
				
				int i= 1;
				
				long tiempo_Inicial_Antes_Del_Algoritmo = System.currentTimeMillis();
				
				while (i < grafo.tamano() )
				{
					eleccion_De_Arista_Minima(grafo, nuevo_Grafo_AGM);
								
					i++;
				}
				
				long tiempo_Final_Despues_Del_Algoritmo = System.currentTimeMillis();
				
				long tiempo_Total = tiempo_Final_Despues_Del_Algoritmo - tiempo_Inicial_Antes_Del_Algoritmo;
				
				nuevo_Grafo_AGM.setTiempo_Para_Prim_O_Kruskal(tiempo_Total);
			}
			
			return nuevo_Grafo_AGM;
			
		}
	

	
	private static void eleccion_De_Arista_Minima(GrafoPesado grafo, GrafoPesado nuevoGrafo) 
	{
		float valorArista = 0;
		
		float valor_Arista_minima =1000000000;
		
		int vertice_Destino =0;
		
		int vertice_Origen =0;
		
		int cont=0;
		
		//OBTENGO LA LISTA DE VERTICES DEL GRAFO
		ArrayList<HashMap<Integer, Float>> verticesDelGrafo = grafo.getVertices();
		
		//RECORRO CADA VERTICE 
		for(HashMap<Integer, Float> vertice : verticesDelGrafo)
		{
			//OBTENGO TODOS LOS VECINOS(KEYS) DEL VERTICE EN CUESTION Y LOS RECORRO
			for ( Integer key : vertice.keySet()) 
			{
				//REVISO QUE LA ARISTA NO HAYA SIDO USADA YA ENTRE AMBOS VERTICES
				if(key > cont && !nuevo_Grafo_AGM.existeArista(key, cont))
				{ //HASTA AHORA PUEDE SER LA POSIBLE ARISTA MINIMA:
					
					valorArista = vertice.get(key);
					
					//SI ES MENOR QUE LA ARISTA MINIMA ACTUAL
					if(valorArista < valor_Arista_minima)
					{ 
						//USO FIND PARA SABER SI PERTENECEN A LA MISMA CONEXA
						if (find(key, cont) == false) 
						{
							valor_Arista_minima= valorArista;
							vertice_Destino= key;
							vertice_Origen= cont;
							
						}
					}
				}
			}
			cont++;
			
		}
		
		union(vertice_Origen, vertice_Destino);
				
		aristas_Del_Arbol.put(vertice_Origen, valor_Arista_minima);
		
		nuevo_Grafo_AGM.agregarArista(vertice_Origen, vertice_Destino, valor_Arista_minima);
		
	}

//------------------------------------------------------------------------------	
	
@SuppressWarnings("unused")
private static boolean esta(int cont, Set<Integer> alcanzables_Del_Destino) {
		for( Integer v: alcanzables_Del_Destino)
		{	
			if( v == cont)
				return true;
		}
		return false;
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

public static Set<Integer> alcanzables(GrafoPesado g, int origen) {
		
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

//------------------------------------------------------------------------------

	private static void inicializar(GrafoPesado g, int origen) {
		
		L = new ArrayList<Integer>();
		L.add(origen);
		marcados = new boolean[(g.tamano())];
	}
	


//------------------------------------------------------------------------------
//agrego los vecinos del vertice a L	
	
	private static void agregarVecinosPendientes(GrafoPesado g, int i) {
		
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
	
		public static int root(int i) //peor caso O(n)
		{
			
			ArrayList<Integer> vertices_Que_Cambian_DePadre= new ArrayList<Integer>();
					
			while(arreglo_Union_Find[i] != i)
			{
				vertices_Que_Cambian_DePadre.add(i);
				i= arreglo_Union_Find[i];
			}
			
			//ENCONTRE LA RAIZ. CAMBIO EL PADRE DE TODOS LOS VERTICES POR LOS CUALES PASE.
			for(Integer m : vertices_Que_Cambian_DePadre)
				arreglo_Union_Find[m] = i;
				
			return i;
		}
		
		//------------------------------------------------------------------------------
		
		public static boolean find(int i, int j)
		{
			return root(i) == root(j);
		}
		
	//------------------------------------------------------------------------------
		
		public static void union(int i, int j)
		{
			int ri= root(i);
			int rj= root(j);
			
			if (j == rj)
				arreglo_Union_Find[rj]=ri;
			else
				arreglo_Union_Find[ri]= rj;
		}
		
		
//------------------------------------------------------------------------------


}
