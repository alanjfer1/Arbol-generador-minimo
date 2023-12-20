package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JOptionPane;

public class GrafoPesado {
	
	private ArrayList< HashMap< Integer, Float > > vertices;
	ArrayList <Integer> verticesMarcados = new ArrayList<Integer>();
	private int numero_De_Aristas;
	private int numero_De_Vertices;
	private float peso=0;
	private long tiempo_Para_Prim_O_Kruskal;

	
//------------------------------------------------------------------------------
	
	public GrafoPesado(int n) {
		numero_De_Vertices = n;
		vertices= new ArrayList< HashMap< Integer, Float > >();
		
		for (int i=0; i<n; ++i) {
			vertices.add(new HashMap< Integer, Float >());
		}
	}
	
//------------------------------------------------------------------------------
	
	public void mostrarGrafo()
	{
		int contador_vertices= 0;
		
		for(HashMap<Integer, Float> vertice : this.getVertices())
		{
			System.out.println("");
			System.out.println( "ARISTAS DE VERTICE NRO: "+ contador_vertices);
			
			for ( Integer key : vertice.keySet())
			 {
				System.out.println(contador_vertices + " ---> "+ key + ". PESO: "+ vertice.get(key) + "\n");
			 }
			contador_vertices++;
		}
	}
	
//------------------------------------------------------------------------------
	
	public String dar_Info_Grafo()
	{	
		StringBuilder sb= new StringBuilder();
		
		int contador_vertices= 0;
			
		for(HashMap<Integer, Float> vertice : this.getVertices())
		{
			sb.append("\n" +"  ARISTAS DE VERTICE NRO: "+ contador_vertices + "\n"+ "\n");		
			for ( Integer vecino : vertice.keySet())
			 {
				sb.append(contador_vertices + " ---> "+ vecino + ". PESO: "+ vertice.get(vecino) + "\n");
			 }
			contador_vertices++;
		}
		
		sb.append("\n" +"    PESO TOTAL DEL ARBOL: \n   " + getPeso());
		return sb.toString();
	}	
	
	public String dar_Info_Grafo(ArrayList <String> nombres)
	{	
		StringBuilder sb= new StringBuilder();
		
		int contador_vertices= 0;
			
		for(HashMap<Integer, Float> vertice : this.getVertices())
		{
			sb.append("\n" +"  ARISTAS DE VERTICE: "+ nombres.get(contador_vertices).toUpperCase() + "\n"+ "\n");		
			for ( Integer vecino : vertice.keySet())
			 {
				sb.append("   Con vertice vecino: "+ nombres.get(vecino).toUpperCase() + ", valor de arista: "+ vertice.get(vecino) + "\n");
			 }
			contador_vertices++;
		}
		
		sb.append("\n" +"  PESO TOTAL DEL ARBOL: \n    " + peso);
		return sb.toString();
	}	
	
//------------------------------------------------------------------------------	
	
	public void agregarArista(int i, int j, float valor)
	{  //O(1) promedio
		
		if (i != j) 
		{
		vertices.get(i).put(j, valor);
		vertices.get(j).put(i, valor);
		sumarPeso(valor);
		numero_De_Aristas++;
		}
	}
	
//------------------------------------------------------------------------------
	
	public void eliminarArista(int i, int j) {  //O(1) promedio
		vertices.get(i).remove(j);
		vertices.get(j).remove(j);
		
		numero_De_Aristas--;
	}
	
//------------------------------------------------------------------------------	
	
	public boolean existeArista(int i, int j) {  //O(1) promedio
		return vertices.get(i).containsKey(j);
	}
	
//------------------------------------------------------------------------------	
	
	public HashMap<Integer, Float> vecinos(int vertice){  //O(1)
		return vertices.get(vertice);
	}	
	
//------------------------------------------------------------------------------	
	
	public int tamano() {
		return vertices.size();
	}
	
//------------------------------------------------------------------------------
	
	@SuppressWarnings("static-access")
	public void setearAristas(ArrayList<String> nombres)
	{		
		//Voy a recorrer los espias, pararme en cada uno de ellos y preguntar si se encuentra con el resto de espias
		for(int i=0; i<this.tamano()-1;i++)
		{
			for(int j=0; j<this.tamano(); j++)
			{
				//Me aseguro de no preguntar por un bucle o repetir encuentro
				if(j != i && j > i)
				{				
					JOptionPane aristas_SI_ONO= new JOptionPane();
					
					int res= aristas_SI_ONO.showConfirmDialog(aristas_SI_ONO, "Se encuentra el espia " + nombres.get(i) + " con el espia "+nombres.get(j)+"?" );
				
					if (res == 0) 
					{
						JOptionPane valor_Arista= new JOptionPane();
						
						float v = 200;
						while(v == 200)
						{
							v= Float.parseFloat(valor_Arista.showInputDialog("Cual es la probabilidad del encuentro?  (0 a 1)"));
						
							if (v < 0 || v > 1)
							{
								JOptionPane.showMessageDialog(null,
									"La probabilidad debe ser entre [0 y 1]",
					    		    "Warning",
					    		    JOptionPane.WARNING_MESSAGE);
								v=200;
							}
							else
								agregarArista(i, j, v);
						}
					}
					
				}
			}
		}
	}
		
//------------------------------------------------------------------------------	
	
	public void sumarPeso(float n) {
		peso+= n;
	}

//------------------------------------------------------------------------------	
	public GrafoPesado aleatorio(GrafoPesado g) 
	{
		
		Random random= new Random(0);
		
		for(int i=0; i<g.numero_De_Vertices; i++)
		for(int j=i+1; j<g.numero_De_Vertices; j++)
		{
			if( random.nextDouble() < 0.3)
				g.agregarArista(i, j, (int)(Math.random()*20+1));
		}
		
		return g;
	}
	
//------------------------------------------------------------------------------

	//GETTERS AND SETTERS
	
	public ArrayList<HashMap< Integer, Float>> getVertices()
	{
		return vertices;
	}
	
	public float getPeso() {
		return peso;	
	}
	
	public int getNumeroAristas() {
		return numero_De_Aristas;
	}
	
	public long getTiempo_Para_Prim_O_Kruskal() {
		return tiempo_Para_Prim_O_Kruskal;
	}

	public void setTiempo_Para_Prim_O_Kruskal(long tiempo_Para_Prim_O_Kruskal) {
		this.tiempo_Para_Prim_O_Kruskal = tiempo_Para_Prim_O_Kruskal;
	}

	public int getNumero_De_Vertices() {
		return numero_De_Vertices;
	}

	public void setNumero_De_Vertices(int numero_De_Vertices) {
		this.numero_De_Vertices = numero_De_Vertices;
	}
	
}
	
	
	
	
	
	
	
	
	
	
