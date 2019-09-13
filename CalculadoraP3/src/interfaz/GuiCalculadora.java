package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import logica.Calculadora;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.border.MatteBorder;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.DebugGraphics;
import javax.swing.ImageIcon;

public class GuiCalculadora 
{
	private JFrame frmCalculadora;
	private JTextField resultado;
	
	private JScrollPane scrollPaneHistorial;
	private JPanel panelHistorial;
	
	private JButton num_0, num_1, num_2, num_3, num_4, num_5, num_6, num_7, num_8, num_9, boton_Punto,
						boton_Sum, boton_Rest, boton_Mult, boton_Div, boton_Igual, boton_Borrar, boton_Reset,
							verHistorial, guardar, limpiar;

	private Color Gris1, Gris2, Gris3, Gris4, Gris5, Naranja1, Naranja2; 
	
	private Calculadora calculadora;
	
	private String numeroActual;
	private String ultimaOperacion;
	
	private boolean sePresionoIgual; //Para controlar si por ultima vez se presiono '='
	
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					GuiCalculadora window = new GuiCalculadora();
					window.frmCalculadora.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GuiCalculadora() 
	{
		initialize();
	}

	private void initialize() 
	{
		//Inicializacion
		calculadora = new Calculadora();
		numeroActual = "0";
		ultimaOperacion = "";
		sePresionoIgual = false;
		
		Gris1 = new Color(13, 13, 13);      //El numero al final del nombre indica el tono,
		Gris2 = new Color(38,38,38);		//el 1 es mas oscuro que el 2, el 2 mas que el 3, etc...
		Gris3 = new Color(51,51,51);
		Gris4 = new Color(77, 77, 77);
		Gris5 = new Color(102, 102, 102);
		Naranja1 = new Color(204, 82, 0);
		Naranja2 = new Color(255,133,51);
		
		inicializarCalculadora();
		inicializarBotonesNum();
		inicializarBotonesOperadores();
		
		//Funciones
		num_0.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!sePresionoIgual)
				{
					if(!operacion().equals("0")) 
					{
						if(!ultimoNumero().equals("0")) 
						{
							numeroActual += num_0.getText();
							agregar(num_0.getText());
						}
						
						else if(ultimoNumeroEsDecimal()) 
						{
							numeroActual += num_0.getText();
							agregar(num_0.getText());
						}
						
					}
				}
				else 
				{
					resultado.setText(num_0.getText());
					numeroActual = num_0.getText();
					sePresionoIgual = false;
				}
			}
		});
		
		ActionListener accion1Al9 = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				JButton boton = (JButton) e.getSource();
				
				if(!sePresionoIgual) 
				{
					if(ultimoNumero().equals("0"))
					{
						reemplazarUltimoValor(boton.getText());
						numeroActual = boton.getText();
					}
					else 
					{
						agregar(boton.getText());
						numeroActual += boton.getText();
					}
				}
				else 
				{
					resultado.setText(boton.getText());
					numeroActual = boton.getText();
					sePresionoIgual = false;
				}	
			}
		};
		
		num_1.addActionListener(accion1Al9);
		num_2.addActionListener(accion1Al9);
		num_3.addActionListener(accion1Al9);
		num_4.addActionListener(accion1Al9);
		num_5.addActionListener(accion1Al9);
		num_6.addActionListener(accion1Al9);
		num_7.addActionListener(accion1Al9);
		num_8.addActionListener(accion1Al9);
		num_9.addActionListener(accion1Al9);
		
		boton_Rest.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				if(!sePresionoIgual)
				{
					if(operacion().equals("0")) 
					{
						resultado.setText(boton_Rest.getText());
						numeroActual = boton_Rest.getText();
					}
					else 
					{
						if(ultimoCaracter() == '*' || ultimoCaracter() == '/')
						{
							numeroActual = "-";
							agregar(boton_Rest.getText());
						}
						else if(ultimoCaracter() == '+')
						{
							numeroActual = "-";
							reemplazarUltimoValor(boton_Rest.getText());
						}
						else if(ultimoCaracter() == '.') 
						{
							calculadora.obtenerNumero(Double.parseDouble(numeroActual));
							calculadora.obtenerOperador(boton_Rest.getText());
							numeroActual = "";
							
							reemplazarUltimoValor(boton_Rest.getText());;
						}
						else if(ultimoCaracter() != '-') 
						{
							calculadora.obtenerNumero(Double.parseDouble(numeroActual));
							calculadora.obtenerOperador(boton_Rest.getText());
							numeroActual = "";
							agregar(boton_Rest.getText());	
						}	
					}
				}
				else
					operarConUltimoResultado(boton_Rest.getText());
				
			}
		});
		
		ActionListener accionOperadores = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JButton operador = (JButton) e.getSource();
				
				if(!sePresionoIgual)
				{
					if(ultimoCaracter() == '-') 
					{
						if(numeroActual.equals("-")) 
							borrarUltimoValor();
						else
						{
							calculadora.obtenerOperador(operador.getText());
							reemplazarUltimoValor(operador.getText());
						}
							
					}
				
					else if(ultimoEsSigno())
					{
						calculadora.obtenerOperador(operador.getText());
						reemplazarUltimoValor(operador.getText());
					}
					
					else if(ultimoCaracter() == '.') 
					{
						calculadora.obtenerNumero(Double.parseDouble(numeroActual));
						calculadora.obtenerOperador(operador.getText());
						
						numeroActual = "";
						
						reemplazarUltimoValor(operador.getText());
					}
					
					else
					{
						if(!numeroActual.equals(""))
							calculadora.obtenerNumero(Double.parseDouble(numeroActual));
						
						calculadora.obtenerOperador(operador.getText());
						numeroActual = "";
			
						agregar(operador.getText());
					}
				}
				else 
					operarConUltimoResultado(operador.getText());
			}
		};
		
		boton_Mult.addActionListener(accionOperadores);
		boton_Sum.addActionListener(accionOperadores);
		boton_Div.addActionListener(accionOperadores);
		
		boton_Igual.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!sePresionoIgual) 
				{
					ultimaOperacion = operacion();
					
					if(!numeroActual.equals(""))
						calculadora.obtenerNumero(Double.parseDouble(numeroActual));
					
					numeroActual = "";
					
					calculadora.calcular();
					
					if(esEntero(calculadora.getResultado()))
						resultado.setText(String.valueOf((int)calculadora.getResultado()));
					else
						resultado.setText(String.valueOf(calculadora.getResultado()));
					
					sePresionoIgual = true;
				}	
			}
		});	
		
		
		boton_Reset.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				calculadora.reset();
				
				resetear();
				
			}
		});
		
		
		boton_Borrar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{	
				if(!sePresionoIgual)
					borrarUltimoValor();
				
			}
		});
		
		
		boton_Punto.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(ultimoEsSigno())
				{
					numeroActual = "0.";
					agregar(numeroActual);
				}
				if(!ultimoNumeroEsDecimal()) 
				{
					numeroActual += boton_Punto.getText();
					agregar(boton_Punto.getText());
				}
			}
		});
		
		verHistorial.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(frmCalculadora.getWidth() == 336) 
				{
					frmCalculadora.setSize(500, 339);
					verHistorial.setIcon(new ImageIcon(GuiCalculadora.class.getResource("/interfaz/flechasIzq.png")));
					verHistorial.setToolTipText("Ocultar historial");
				}
				else 
				{
					frmCalculadora.setSize(336, 339);
					verHistorial.setIcon(new ImageIcon(GuiCalculadora.class.getResource("/interfaz/flechasDer.png")));
					verHistorial.setToolTipText("Mostrar historial");
				}
			}
		});	
		
		guardar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(sePresionoIgual) 
				{
					JButton boton= new JButton(resultado.getText());
					boton.setToolTipText(ultimaOperacion + " = " + resultado.getText());
					boton.setBorder(new MatteBorder(3, 3, 3, 3, Naranja1));
					boton.setForeground(new Color(255, 255, 255));
					boton.setBackground(Gris2);
					boton.setFont(new Font("Arial", Font.BOLD, 10));
					boton.setFocusable(false);
					boton.setBorderPainted(false);
					boton.addActionListener(new ActionListener() 
					{	
						@Override
						public void actionPerformed(ActionEvent e) 
						{
							if(!sePresionoIgual)
							{
								if(ultimoNumero().equals("0"))
								{
									reemplazarUltimoValor(boton.getText());
									numeroActual = boton.getText();
								}
								else 
								{
									agregar(boton.getText());
									numeroActual += boton.getText();
								}
							}
							
						}
					});
					efectoHoverBoton(boton, Naranja2, Gris2);
					
					panelHistorial.add(boton);
					panelHistorial.updateUI();
				}
			}
		});
		
		limpiar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				panelHistorial.removeAll();
				panelHistorial.updateUI();
			}
		});
	}

	//INICIALIZACION DE BOTONES
	private void inicializarCalculadora() 
	{
		frmCalculadora = new JFrame();
		frmCalculadora.setResizable(false);
		frmCalculadora.setIconImage(Toolkit.getDefaultToolkit().getImage(GuiCalculadora.class.getResource("/interfaz/iconCalculadora.png")));
		frmCalculadora.setTitle("Calculadora");
		frmCalculadora.setBounds(100, 100, 336, 338);
		frmCalculadora.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCalculadora.getContentPane().setLayout(null);
		frmCalculadora.getContentPane().setBackground(Gris1);
		
		resultado = new JTextField("0");
		resultado.setBackground(new Color(13,13,13));
		resultado.setForeground(new Color(204, 82, 0));
		resultado.setEditable(false);
		resultado.setFont(new Font("Tw Cen MT", Font.PLAIN, 50));
		resultado.setBorder(null);
		resultado.setBounds(10, 8, 312, 54);
		frmCalculadora.getContentPane().add(resultado);
		resultado.setColumns(10);
		
		scrollPaneHistorial = new JScrollPane();
		scrollPaneHistorial.setBorder(null);
		scrollPaneHistorial.setBounds(343, 71, 141, 228);
		scrollPaneHistorial.getVerticalScrollBar().setBackground(Gris4);
		
//		scrollPaneHistorial.getVerticalScrollBar().setUI(new BasicScrollBarUI()
//		{
//			@Override
//			public void configureScrollBarColors()
//			{
//				this.thumbColor = colorNum;
//			}
//			
//		});
		
		frmCalculadora.getContentPane().add(scrollPaneHistorial);
		
		
		panelHistorial = new JPanel();
		panelHistorial.setBackground(Gris2);
		scrollPaneHistorial.setViewportView(panelHistorial);
		panelHistorial.setLayout(new GridLayout(0, 1, 0, 0));
		
	}

	private void inicializarBotonesNum() 
	{		
		num_0 = new JButton("0");
		num_0.setForeground(new Color(255, 255, 255));
		num_0.setBackground(Gris2);
		num_0.setFont(new Font("Arial", Font.BOLD, 20));
		num_0.setBorderPainted(false);
		num_0.setFocusable(false);
		num_0.setBounds(66, 256, 66, 54);
		frmCalculadora.getContentPane().add(num_0);
	
		num_1 = new JButton("1");
		num_1.setForeground(new Color(255, 255, 255));
		num_1.setBackground(Gris2);
		num_1.setFont(new Font("Arial", Font.BOLD, 20));
		num_1.setBorderPainted(false);
		num_1.setFocusable(false);
		num_1.setBounds(0, 202, 66, 54);
		frmCalculadora.getContentPane().add(num_1);
	
		num_2 = new JButton("2");
		num_2.setForeground(new Color(255, 255, 255));
		num_2.setBackground(Gris2);
		num_2.setFont(new Font("Arial", Font.BOLD, 20));
		num_2.setBorderPainted(false);
		num_2.setFocusable(false);
		num_2.setBounds(66, 202, 66, 54);
		frmCalculadora.getContentPane().add(num_2);
		
		num_3 = new JButton("3");
		num_3.setForeground(new Color(255, 255, 255));
		num_3.setBackground(Gris2);
		num_3.setFont(new Font("Arial", Font.BOLD, 20));
		num_3.setBorderPainted(false);
		num_3.setFocusable(false);
		num_3.setBounds(132, 202, 66, 54);
		frmCalculadora.getContentPane().add(num_3);
		
		num_4 = new JButton("4");
		num_4.setForeground(new Color(255, 255, 255));
		num_4.setBackground(Gris2);
		num_4.setFont(new Font("Arial", Font.BOLD, 20));
		num_4.setBorderPainted(false);
		num_4.setFocusable(false);
		num_4.setBounds(0, 148, 66, 54);
		frmCalculadora.getContentPane().add(num_4);
	
		num_5 = new JButton("5");
		num_5.setForeground(new Color(255, 255, 255));
		num_5.setBackground(Gris2);
		num_5.setFont(new Font("Arial", Font.BOLD, 20));
		num_5.setBorderPainted(false);
		num_5.setFocusable(false);
		num_5.setBounds(66, 148, 66, 54);
		frmCalculadora.getContentPane().add(num_5);
		
		num_6 = new JButton("6");
		num_6.setForeground(new Color(255, 255, 255));
		num_6.setBackground(Gris2);
		num_6.setFont(new Font("Arial", Font.BOLD, 20));
		num_6.setBorderPainted(false);
		num_6.setFocusable(false);
		num_6.setBounds(132, 148, 66, 54);
		frmCalculadora.getContentPane().add(num_6);	
		
		num_7 = new JButton("7");
		num_7.setBorderPainted(false);
		num_7.setForeground(new Color(255, 255, 255));
		num_7.setBackground(Gris2);
		num_7.setFont(new Font("Arial", Font.BOLD, 20));
		num_7.setFocusable(false);
		num_7.setBounds(0, 94, 66, 54);
		frmCalculadora.getContentPane().add(num_7);
		
		num_8 = new JButton("8");
		num_8.setForeground(new Color(255, 255, 255));
		num_8.setBackground(Gris2);
		num_8.setFont(new Font("Arial", Font.BOLD, 20));
		num_8.setBorderPainted(false);
		num_8.setFocusable(false);
		num_8.setBounds(66, 94, 66, 54);
		frmCalculadora.getContentPane().add(num_8);
		
		num_9 = new JButton("9");
		num_9.setForeground(new Color(255, 255, 255));
		num_9.setBackground(Gris2);
		num_9.setFont(new Font("Arial", Font.BOLD, 20));
		num_9.setBorderPainted(false);
		num_9.setFocusable(false);
		num_9.setBounds(132, 94, 66, 54);
		frmCalculadora.getContentPane().add(num_9);
		
		boton_Punto = new JButton(".");
		boton_Punto.setForeground(new Color(255, 255, 255));
		boton_Punto.setBackground(Gris2);
		boton_Punto.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Punto.setBorderPainted(false);
		boton_Punto.setFocusable(false);
		boton_Punto.setBounds(0, 256, 66, 54);
		frmCalculadora.getContentPane().add(boton_Punto);
		
		efectoHoverBoton(num_0, Gris3, Gris2);
		efectoHoverBoton(num_1, Gris3, Gris2);
		efectoHoverBoton(num_2, Gris3, Gris2);
		efectoHoverBoton(num_3, Gris3, Gris2);
		efectoHoverBoton(num_4, Gris3, Gris2);
		efectoHoverBoton(num_5, Gris3, Gris2);
		efectoHoverBoton(num_6, Gris3, Gris2);
		efectoHoverBoton(num_7, Gris3, Gris2);
		efectoHoverBoton(num_8, Gris3, Gris2);
		efectoHoverBoton(num_9, Gris3, Gris2);
		efectoHoverBoton(boton_Punto, Gris3, Gris2);
	}

	private void inicializarBotonesOperadores() 
	{
		boton_Sum = new JButton("+");
		boton_Sum.setBorderPainted(false);
		boton_Sum.setBounds(198, 175, 66, 81);
		boton_Sum.setForeground(new Color(255, 255, 255));
		boton_Sum.setBackground(Gris4);
		boton_Sum.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Sum.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Sum);
	
		boton_Rest = new JButton("-");
		boton_Rest.setBounds(264, 175, 66, 81);
		boton_Rest.setForeground(new Color(255, 255, 255));
		boton_Rest.setBackground(Gris4);
		boton_Rest.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Rest.setBorderPainted(false);
		boton_Rest.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Rest);
		
		boton_Div = new JButton("/");
		boton_Div.setBounds(264, 94, 66, 81);
		boton_Div.setForeground(new Color(255, 255, 255));
		boton_Div.setBackground(Gris4);
		boton_Div.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Div.setBorderPainted(false);
		boton_Div.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Div);
		
		boton_Mult = new JButton("*");
		boton_Mult.setBounds(198, 94, 66, 81);
		boton_Mult.setForeground(new Color(255, 255, 255));
		boton_Mult.setBackground(Gris4);
		boton_Mult.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Mult.setBorderPainted(false);
		boton_Mult.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Mult);
		
		boton_Borrar = new JButton("<");
		boton_Borrar.setBounds(264, 256, 66, 54);
		boton_Borrar.setForeground(new Color(255, 255, 255));
		boton_Borrar.setBackground(Gris4);
		boton_Borrar.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Borrar.setBorderPainted(false);
		boton_Borrar.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Borrar);
	
		boton_Reset = new JButton("C");
		boton_Reset.setBounds(198, 256, 66, 54);
		boton_Reset.setForeground(new Color(255, 255, 255));
		boton_Reset.setBackground(Gris4);
		boton_Reset.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Reset.setBorderPainted(false);
		boton_Reset.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Reset);
	
		boton_Igual = new JButton("=");
		boton_Igual.setBounds(132, 256, 66, 54);
		boton_Igual.setForeground(new Color(255, 255, 255));
		boton_Igual.setBackground(Naranja1);
		boton_Igual.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Igual.setFocusable(false);
		boton_Igual.setBorderPainted(false);
		frmCalculadora.getContentPane().add(boton_Igual);
		
		verHistorial = new JButton("");
		verHistorial.setIcon(new ImageIcon(GuiCalculadora.class.getResource("/interfaz/flechasDer.png")));
		verHistorial.setBounds(198, 71, 132, 23);
		verHistorial.setToolTipText("Mostrar historial");
		verHistorial.setForeground(new Color(255, 255, 255));
		verHistorial.setBackground(Gris1);
		verHistorial.setFont(new Font("Arial", Font.BOLD, 15));
		verHistorial.setBorderPainted(false);
		verHistorial.setFocusable(false);
		frmCalculadora.getContentPane().add(verHistorial);
		
		guardar = new JButton("");
		guardar.setIcon(new ImageIcon(GuiCalculadora.class.getResource("/interfaz/guardar.png")));
		guardar.setBounds(0, 71, 198, 23);
		guardar.setForeground(Color.WHITE);
		guardar.setFont(new Font("Arial", Font.BOLD, 10));
		guardar.setFocusable(false);
		guardar.setBorderPainted(false);
		guardar.setBackground(Gris1);
		frmCalculadora.getContentPane().add(guardar);
		
		limpiar = new JButton();
		limpiar.setIcon(new ImageIcon(GuiCalculadora.class.getResource("/interfaz/limpiar.png")));
		limpiar.setBounds(343, 8, 141, 54);
		limpiar.setToolTipText("Limpiar historial");
		limpiar.setForeground(Color.WHITE);
		limpiar.setFont(new Font("Arial", Font.BOLD, 10));
		limpiar.setFocusable(false);
		limpiar.setBorderPainted(false);
		limpiar.setBackground(new Color(38, 38, 38));
		frmCalculadora.getContentPane().add(limpiar);

		efectoHoverBoton(boton_Sum, Gris5, Gris4);
		efectoHoverBoton(boton_Rest, Gris5, Gris4);
		efectoHoverBoton(boton_Div, Gris5, Gris4);
		efectoHoverBoton(boton_Mult, Gris5, Gris4);
		efectoHoverBoton(boton_Borrar, Gris5, Gris4);
		efectoHoverBoton(boton_Reset, Gris5, Gris4);
		efectoHoverBoton(boton_Igual, Naranja2, Naranja1);
		efectoHoverBoton(verHistorial, Naranja1, Gris1);
		efectoHoverBoton(guardar, Naranja1, Gris1);	
		efectoHoverBoton(limpiar, Naranja1, Gris2);
	
	}

	//Efecto hover para el boton refenciado con los colores pasados por parametros
	private void efectoHoverBoton(JButton boton, Color punteroEncima, Color punteroFuera) 
	{
		boton.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				boton.setBackground(punteroEncima);
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				boton.setBackground(punteroFuera);
			}
		});
	}
	
	//METODOS AUXILIARES
	

	private void operarConUltimoResultado(String operador) 
	{
		calculadora.obtenerNumero(calculadora.getResultado());
		calculadora.obtenerOperador(operador);
		agregar(operador);
		sePresionoIgual = false;
	}
	
	//Agregar un string al final 
	private void agregar(String text) 
	{
		resultado.setText(operacion() + text);
	}
	
	//Reemplaza el ultimo caracter de la cadena por el string pasado como parametro
	private void reemplazarUltimoValor(String text) 
	{
		resultado.setText(operacion().substring(0, largo() - 1) + text);
	}
	
	//Borra el ultimo caracter de la cadena
	private void borrarUltimoValor() 
	{
		if(largo() == 1)
			resultado.setText("0");
		
		else if(numeroActual.equals("-")) 
		{
			numeroActual = "";
			reemplazarUltimoValor("");
		}
		
		else if(!numeroActual.equals("")) 
		{
			calculadora.obtenerNumero(Double.parseDouble(numeroActual));
			calculadora.borrar();
			numeroActual = numeroActual.substring(0, numeroActual.length() - 1);
			reemplazarUltimoValor("");	
		}
		
		else if(ultimoEsSigno()) 
		{
			calculadora.borrar();
			reemplazarUltimoValor("");
			numeroActual = ultimoNumero();
		}
		
	}
	
	//Devuelve un string con el numero que esta formado a partir del ultimo signo
	private String ultimoNumero() 
	{
		String ultimoNumero = "";
		
		for(int i=0; i < largo(); i++) 
		{
			if("+-*/".contains(Character.toString(operacion().charAt(i))))
				ultimoNumero = "";
	
			else
				ultimoNumero += operacion().charAt(i);
		}
		
		return ultimoNumero;
	}

	
	private void resetear() 
	{
		resultado.setText("0");
		numeroActual = "";
		sePresionoIgual = false;
	}
	
	//Dice si el numero es decimal
	private boolean esEntero(float numero) 
	{
		if (numero % 1 == 0) 
	        return true;
		return false;
	}
		
	//Dice si el ultimo caracter de la cadena es un '+', '-', '*', '/' 
	private boolean ultimoEsSigno() 
	{
		return ultimoCaracter() == '-' || ultimoCaracter() == '/' || ultimoCaracter() == '*' || ultimoCaracter() == '+';
	}
	
	//Dice si el ultimo numero de la cadena es un decimal
	public boolean ultimoNumeroEsDecimal()
	{
		boolean esDecimal = false;
		
		for(int i=0; i< largo(); i++)
		{
			if(operacion().charAt(i) == '.') 
				esDecimal = true;
			
			if(operacion().charAt(i) == '/' || operacion().charAt(i) == '*' || operacion().charAt(i) == '-' || operacion().charAt(i) == '+')
				esDecimal = false;
		}
		return esDecimal;
	}
	
	//Devuelve la cadena con la operacion
	public String operacion() 
	{
		return resultado.getText();
	}
	
	//Devuelve el largo de la operacion
	public int largo() 
	{
		return resultado.getText().length();
	}
	
	//Devuelve el ultimo caracter de la operacion
	public char ultimoCaracter() 
	{
		return resultado.getText().charAt(largo() - 1);
	}
}		
