package Vista;

import java.awt.EventQueue;

import javax.swing.*;

import Controller.Controlador_Algoritmo_Kruskal;
import Controller.Controlador_Algoritmo_Prim;
import Controller.Controlador_Grafo_Stress_Test;
import Controller.Controlador_Ingresar_Grafo;
import Controller.Controlador_Leer_Archivo;
import Modelo.CreadorDeGrafos;
//import Controller.Controlador_Leer_Archivo;



import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.ScrollPane;

public class Marco extends JFrame{

	private JFrame frame;
	private Controlador_Ingresar_Grafo controladorGrafoUsuario= new Controlador_Ingresar_Grafo(this);
	private Controlador_Grafo_Stress_Test controlador_Grafo_Stress= new Controlador_Grafo_Stress_Test(this);
	private Controlador_Leer_Archivo controlador_Archivo= new Controlador_Leer_Archivo(this);
	private JTextArea info;
	private JButton prim; 
	private int numero_EntradaGrafo_O_GrafoStress = 0; //ESTE NUMERO INDICA SI EL GRAFO SE OBTIENE INGRESADO, DE ARCHIVO O DE STRESS
														// 1 = INGRESAR GRAFO, 2 = STRESS TEST, 3= LEER DE ARCHIVO


	/**
	 * Create the application.
	 */
	public Marco() {
		
		initialize();	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void initialize() {
		
		//--Inicializo Marco
		frame = new JFrame();
		frame.getContentPane().addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
			}
		});
		frame.setVisible(true);
		//--Posicionamiento, ancho, alto, terminar el programa al cerrar y sin Layout.
		frame.setSize(500,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		
		
		//---BOTON INGRESAR GRAFO MANUALMENTE-------------------------
		JButton ingresar_Grafo = new JButton("Ingresar grafo manualmente");
		ingresar_Grafo.setBounds(150, 20, 200, 34);
		ingresar_Grafo.addMouseListener(controladorGrafoUsuario);
		frame.getContentPane().add(ingresar_Grafo);
		
		//---BOTON GRAFO STRESS TEST-------------------------
		JButton grafo_Stress = new JButton("Generar grafo para Stress Test");
		grafo_Stress.setBounds(38, 77, 213, 34);
		grafo_Stress.addMouseListener(controlador_Grafo_Stress);
		frame.getContentPane().add(grafo_Stress);
		
		
		
		//---BOTON LEER ARCHIVO-------------------------
		JButton leer_Archivo = new JButton("Leer desde archivo");
		leer_Archivo.setBounds(272, 77, 186, 34);
		leer_Archivo.addMouseListener(controlador_Archivo);
		frame.getContentPane().add(leer_Archivo);

		//---BOTON ALGORITMO PRIM-------------------------
		JButton prim = new JButton("Calcular Prim");
		prim.setBounds(66, 500, 150, 40);
		prim.addMouseListener(new Controlador_Algoritmo_Prim(this));
		frame.getContentPane().add(prim);
		
		//---BOTON ALGORITMO KRUSKAL-------------------------
		JButton kruskal = new JButton("Calcular Kruskal");
		kruskal.setBounds(279, 500, 150, 40);
		kruskal.addMouseListener(new Controlador_Algoritmo_Kruskal(this));
		frame.getContentPane().add(kruskal);
		
		//---TEXTAREA------------------------------
		info= new JTextArea();
		info.setLineWrap(true);
		info.setEditable(false);
		info.setBackground(Color.WHITE);
		info.setBounds(38, 120, 430, 368);
		frame.getContentPane().add(info);
		
		ScrollPane infoScroll = new ScrollPane();
		infoScroll.add(info);
		infoScroll.setBackground(Color.WHITE);
		infoScroll.setBounds(38, 120, 430, 348);
		frame.getContentPane().add(infoScroll);
		


		

		
		
		//---------PONGO EL MARCO A LA ESCUCHA DE EVENTOS------
		//frame.addWindowListener(inicio);
	}
	
//------------------------------------------------------------------------------	
			//GETTERS AND SETTERS
	
	public JTextArea getInfo() {
		return info;
	}	

	public Controlador_Ingresar_Grafo getControladorGrafoUsuario() {
		return controladorGrafoUsuario;
	}

	public JButton getPrim() {
		return prim;
	}

	public Controlador_Grafo_Stress_Test getControlador_Grafo_Stress() {
		return controlador_Grafo_Stress;
	}

	public Controlador_Leer_Archivo getControlador_Archivo() {
		return controlador_Archivo;
	}
	
	public int getNumero_EntradaGrafo_O_GrafoStress() {
		return numero_EntradaGrafo_O_GrafoStress;
	}

	public void setNumero_EntradaGrafo_O_GrafoStress(int numero_EntradaGrafo_O_GrafoStress) {
		this.numero_EntradaGrafo_O_GrafoStress = numero_EntradaGrafo_O_GrafoStress;
	}
	
	
	
}
