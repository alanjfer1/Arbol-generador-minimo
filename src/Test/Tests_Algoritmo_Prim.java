package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Modelo.AlgoritmoPrim;
import Modelo.GrafoPesado;

public class Tests_Algoritmo_Prim {


	private GrafoPesado inicializarGrafoNoConexo() 
	{
		GrafoPesado g= new GrafoPesado(5);
		g.agregarArista(0, 1, 10);
		g.agregarArista(0, 2, 20);
		g.agregarArista(2, 3, 5);
		return g;
	}
	
	private GrafoPesado inicializarGrafoConexo() 
	{
		GrafoPesado g= new GrafoPesado(6);
		g.agregarArista(0, 1, 5);
		g.agregarArista(0, 2, 9);
		g.agregarArista(0, 3, 30);
		g.agregarArista(1, 3, 2);
		g.agregarArista(1, 4, 25);
		g.agregarArista(2, 3, 11);
		g.agregarArista(2, 5, 10);
		g.agregarArista(3, 4, 18);
		g.agregarArista(3, 5, 22);
		g.agregarArista(4, 5, 15);
		return g;
	}
	
	
	@Test
	public void GrafoVacioTest() 
	{
		GrafoPesado grafo= new GrafoPesado(0);
		
		GrafoPesado nuevoGrafo = AlgoritmoPrim.encontrarArbolGeneradorMinimo(grafo);
		
		assertEquals(0, nuevoGrafo.getNumeroAristas());
		
		assertEquals(0, nuevoGrafo.getPeso(), 0.1);	
	}
	

	
	@Test
	public void ArbolGeneradorMinimoTest()
	{
		GrafoPesado g= inicializarGrafoConexo();
		
		GrafoPesado g2= AlgoritmoPrim.encontrarArbolGeneradorMinimo(g);
		
		assertEquals(5, g2.getNumeroAristas());
		
		assertEquals(41, g2.getPeso(), 0.1);	
	}
	
	@Test
	public void grafoNOConexoTest()
	{
		GrafoPesado g = inicializarGrafoNoConexo();
		
		GrafoPesado g2 = AlgoritmoPrim.encontrarArbolGeneradorMinimo(g);
		
		assertEquals(0, g2.getNumeroAristas());
		
		assertEquals(0, g2.getPeso(), 0.1);	
	}

}
