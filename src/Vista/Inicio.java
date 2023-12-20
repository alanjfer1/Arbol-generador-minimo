package Vista;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class Inicio extends JFrame{

	private JFrame frame;
	private JButton espias;
	private Marco marco;
	private JPanel panel;
	private ImageIcon img = new ImageIcon(getClass().getResource("/Imagenes/espia.png")); 

	/**
	 * Create the application.
	 */
	public Inicio() {
		initialize();
		frame.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
//------------------------------------------------------------------------------
	
	public void initialize() {
		
		//--Inicializo Marco
	
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.getContentPane().addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
			}
		});
		frame.setVisible(true);
		//--Posicionamiento, ancho, alto, terminar el programa al cerrar y sin Layout.
		frame.setSize(500,500);;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Espias");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 34));
		lblNewLabel.setBounds(38, 108, 105, 83);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Arbol generador minimo");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(38, 178, 169, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton botonInicial = new JButton("Comenzar");
		botonInicial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				marco = new Marco();
				marco.setVisible(true);
			}
		});
		botonInicial.setBounds(49, 206, 117, 29);
		frame.getContentPane().add(botonInicial);

		
		JLabel labelDibujo = new JLabel("");
		labelDibujo.setBounds(0, 0, 500, 472);
		labelDibujo.setIcon(img);
		labelDibujo.setLayout( new BorderLayout() );	
		frame.getContentPane().add(labelDibujo);
		

		//---------PONGO EL MARCO A LA ESCUCHA DE EVENTOS------
		//frame.addWindowListener(inicio);
	}
	
//------------------------------------------------------------------------------	

	/*public JTextArea getInfo() {
		return info;
	}*/
	
//------------------------------------------------------------------------------
	


}