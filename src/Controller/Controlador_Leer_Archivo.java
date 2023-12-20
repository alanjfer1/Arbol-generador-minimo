package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import Modelo.CreadorDeGrafos;
import Modelo.GrafoPesado;
import Vista.Marco;

public class Controlador_Leer_Archivo extends MouseAdapter{
	private Marco marco;
	private CreadorDeGrafos cg;
	private static GrafoPesado miGrafo;
	
	private static ArrayList<String> nombres= new ArrayList<String>();
	
//==============================================================================	
	public Controlador_Leer_Archivo(Marco marco) {
		this.marco= marco;
		cg = new CreadorDeGrafos();
	}
//==============================================================================
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		//ESTE VALOR LO VA A NECESITAR LUEGO LOS ALGORITMOS PARA SABER QUE 
		//VAN A TRABAJAR CON UN GRAFO QUE SE LEE DESDE UN ARCHIVO
		marco.setNumero_EntradaGrafo_O_GrafoStress(3);
		
		crear_Grafo_Leyendo_Archivo();

		cg.setGrafoPesado(miGrafo);
		
		marco.getInfo().setText(miGrafo.dar_Info_Grafo());
		
	}
	
//------------------------------------------------------------------------------
	
	@SuppressWarnings("finally")
	public static GrafoPesado crear_Grafo_Leyendo_Archivo() 
	{
		
		try {
			
			//Uso File Reader para leer el archivo.
			FileReader entrada= new FileReader("C:/Users/Jarl Alan/Desktop/Workplace-Eclipse/agmespias/agmespias/src/grafo.txt");
			BufferedReader mibuffer= new BufferedReader(entrada);
			String linea="";
			
			linea= mibuffer.readLine(); //La primera linea tiene el numero de vertices del grafo.
			
			miGrafo = new GrafoPesado(Integer.parseInt(linea));
			
			int miVertice = 0;
			while ( linea != null)
			{
				String[] informacion_De_Cada_Linea;
				
				linea= mibuffer.readLine();
				
				if (linea != null)
				{
					//En cada linea la informacion del vertice esta separada por ;
					//Voy cortando la informacion y la guardo en un []
					informacion_De_Cada_Linea = linea.split(";");
					
					int cont = 0; //Como la informacion de vertices viene de 2 en 2, uso un cont
					int vertice_A_Donde_Apunta = 0;
					float peso_De_Arista;
					
					for (String s : informacion_De_Cada_Linea)
					{
						if (cont == 0) //El primer string de cada linea es el nombre del espia
						{	
							nombres.add(s);
							
						}
						else
						{
							//La info viene de dos en dos, primero vertice apuntado, luego el peso.
							//Teniendo esto en cuenta armo el grafo
						
							if (cont > 0 && cont % 2 == 0 )
							{
								peso_De_Arista = Float.parseFloat(s) ;
								miGrafo.agregarArista(miVertice, vertice_A_Donde_Apunta, peso_De_Arista);
						
							}
							else
							{
								vertice_A_Donde_Apunta = Integer.parseInt(s);
							}

						}
						cont++;
					}
					
					
				}
				miVertice ++;
			}

			entrada.close();
		
		} 
		catch(Exception e) {
			
			e.printStackTrace();
		} 
		finally {
			return miGrafo; 
		}
	}	
//------------------------------------------------------------------------------	

	public CreadorDeGrafos getCreadorGrafo() {
		return cg;
	}
	
//------------------------------------------------------------------------------	
	
}
