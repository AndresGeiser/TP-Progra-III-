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

public class GuiCalculadora 
{
	private JFrame frmCalculadora;
	private JTextField resultado;
	
	private JScrollPane scrollPaneHistorial;
	private JPanel panelHistorial;
	
	private JButton boton_0, boton_1, boton_2, boton_3, boton_4, boton_5, boton_6, boton_7, boton_8, boton_9, boton_Punto,
					boton_Sum, boton_Rest, boton_Mult, boton_Div, boton_Igual, boton_Borrar, boton_Reset, verHistorial, guardar;

	private Color colorNum, colorOperadores, colorNumHover, colorOperHover;
	
	private Calculadora calculadora;
	
	private String numeroActual;
	
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
		sePresionoIgual = false;
		
		colorNum = new Color(38, 38, 38);
		colorNumHover = new Color(51,51,51);
		colorOperadores = new Color(77, 77, 77);
		colorOperHover = new Color(102, 102, 102);
		
		inicializarCalculadora();
		inicializarBotonesNum();
		inicializarBotonesOperadores();
		
		//Funciones
		boton_0.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!sePresionoIgual)
				{
					if(!resultado.getText().equals("0")) 
					{
						if(!ultimoNumero().equals("0")) 
						{
							numeroActual += boton_0.getText();
							agregar(boton_0.getText());
						}
						
						else if(ultimoNumeroEsDecimal()) 
						{
							numeroActual += boton_0.getText();
							agregar(boton_0.getText());
						}
						
					}
				}
				else 
				{
					resultado.setText(boton_0.getText());
					numeroActual = boton_0.getText();
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
					if(resultado.getText().equals("0") || numeroActual.equals("0"))
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
		
		boton_1.addActionListener(accion1Al9);
		boton_2.addActionListener(accion1Al9);
		boton_3.addActionListener(accion1Al9);
		boton_4.addActionListener(accion1Al9);
		boton_5.addActionListener(accion1Al9);
		boton_6.addActionListener(accion1Al9);
		boton_7.addActionListener(accion1Al9);
		boton_8.addActionListener(accion1Al9);
		boton_9.addActionListener(accion1Al9);
		
		boton_Rest.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				if(!sePresionoIgual)
				{
					if(resultado.getText().equals("0")) 
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
				if(verHistorial.getText().equals(">>>")) 
				{
					frmCalculadora.setSize(500, 339);
					verHistorial.setText("<<<");
					verHistorial.setToolTipText("Ocultar historial");
				}
				else 
				{
					frmCalculadora.setSize(336, 339);
					verHistorial.setText(">>>");
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
					boton.setToolTipText(resultado.getText());
					boton.setForeground(new Color(255, 255, 255));
					boton.setBackground(colorNum);
					boton.setFont(new Font("Arial", Font.BOLD, 10));
					boton.setFocusable(false);
					boton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) 
						{
							if(!sePresionoIgual)
							{
								numeroActual += boton.getText();
								agregar(boton.getText());
							}
							
						}
					});
					panelHistorial.add(boton);
					panelHistorial.updateUI();
				}
			}
		});
		
		
	}

	//INICIALIZACION DE BOTONES
	private void inicializarCalculadora() 
	{
		frmCalculadora = new JFrame();
		frmCalculadora.setResizable(false);
		frmCalculadora.setIconImage(Toolkit.getDefaultToolkit().getImage(GuiCalculadora.class.getResource("/interfaz/iconCalculadora.png")));
		frmCalculadora.getContentPane().setBackground(Color.BLACK);
		frmCalculadora.setBackground(Color.WHITE);
		frmCalculadora.setTitle("Calculadora");
		frmCalculadora.setBounds(100, 100, 500, 335);
		frmCalculadora.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCalculadora.getContentPane().setLayout(null);
		frmCalculadora.getContentPane().setBackground(new Color (13,13,13));
		
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
		scrollPaneHistorial.setBounds(343, 73, 141, 227);
		scrollPaneHistorial.getVerticalScrollBar().setBackground(colorOperadores);
		scrollPaneHistorial.getVerticalScrollBar().setUI(new BasicScrollBarUI()
		{
			@Override
				public void configureScrollBarColors()
				{
					this.thumbColor = colorNum;
				}
			
			});
		
		frmCalculadora.getContentPane().add(scrollPaneHistorial);
		
		
		panelHistorial = new JPanel();
		panelHistorial.setBackground(colorNum);
		scrollPaneHistorial.setViewportView(panelHistorial);
		panelHistorial.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		
		

	}

	private void inicializarBotonesNum() 
	{		
		boton_0 = new JButton("0");
		boton_0.setForeground(new Color(255, 255, 255));
		boton_0.setBackground(colorNum);
		boton_0.setFont(new Font("Arial", Font.BOLD, 20));
		boton_0.setBorderPainted(false);
		boton_0.setFocusable(false);
		boton_0.setBounds(0, 256, 66, 54);
		frmCalculadora.getContentPane().add(boton_0);
	
		boton_1 = new JButton("1");
		boton_1.setForeground(new Color(255, 255, 255));
		boton_1.setBackground(colorNum);
		boton_1.setFont(new Font("Arial", Font.BOLD, 20));
		boton_1.setBorderPainted(false);
		boton_1.setFocusable(false);
		boton_1.setBounds(0, 202, 66, 54);
		frmCalculadora.getContentPane().add(boton_1);
	
		boton_2 = new JButton("2");
		boton_2.setForeground(new Color(255, 255, 255));
		boton_2.setBackground(colorNum);
		boton_2.setFont(new Font("Arial", Font.BOLD, 20));
		boton_2.setBorderPainted(false);
		boton_2.setFocusable(false);
		boton_2.setBounds(66, 202, 66, 54);
		frmCalculadora.getContentPane().add(boton_2);
		
		boton_3 = new JButton("3");
		boton_3.setForeground(new Color(255, 255, 255));
		boton_3.setBackground(colorNum);
		boton_3.setFont(new Font("Arial", Font.BOLD, 20));
		boton_3.setBorderPainted(false);
		boton_3.setFocusable(false);
		boton_3.setBounds(132, 202, 66, 54);
		frmCalculadora.getContentPane().add(boton_3);
		
		boton_4 = new JButton("4");
		boton_4.setForeground(new Color(255, 255, 255));
		boton_4.setBackground(colorNum);
		boton_4.setFont(new Font("Arial", Font.BOLD, 20));
		boton_4.setBorderPainted(false);
		boton_4.setFocusable(false);
		boton_4.setBounds(0, 148, 66, 54);
		frmCalculadora.getContentPane().add(boton_4);
	
		boton_5 = new JButton("5");
		boton_5.setForeground(new Color(255, 255, 255));
		boton_5.setBackground(colorNum);
		boton_5.setFont(new Font("Arial", Font.BOLD, 20));
		boton_5.setBorderPainted(false);
		boton_5.setFocusable(false);
		boton_5.setBounds(66, 148, 66, 54);
		frmCalculadora.getContentPane().add(boton_5);
		
		boton_6 = new JButton("6");
		boton_6.setForeground(new Color(255, 255, 255));
		boton_6.setBackground(colorNum);
		boton_6.setFont(new Font("Arial", Font.BOLD, 20));
		boton_6.setBorderPainted(false);
		boton_6.setFocusable(false);
		boton_6.setBounds(132, 148, 66, 54);
		frmCalculadora.getContentPane().add(boton_6);	
		
		boton_7 = new JButton("7");
		boton_7.setBorderPainted(false);
		boton_7.setForeground(new Color(255, 255, 255));
		boton_7.setBackground(colorNum);
		boton_7.setFont(new Font("Arial", Font.BOLD, 20));
		boton_7.setFocusable(false);
		boton_7.setBounds(0, 94, 66, 54);
		frmCalculadora.getContentPane().add(boton_7);
		
		boton_8 = new JButton("8");
		boton_8.setForeground(new Color(255, 255, 255));
		boton_8.setBackground(colorNum);
		boton_8.setFont(new Font("Arial", Font.BOLD, 20));
		boton_8.setBorderPainted(false);
		boton_8.setFocusable(false);
		boton_8.setBounds(66, 94, 66, 54);
		frmCalculadora.getContentPane().add(boton_8);
		
		boton_9 = new JButton("9");
		boton_9.setForeground(new Color(255, 255, 255));
		boton_9.setBackground(colorNum);
		boton_9.setFont(new Font("Arial", Font.BOLD, 20));
		boton_9.setBorderPainted(false);
		boton_9.setFocusable(false);
		boton_9.setBounds(132, 94, 66, 54);
		frmCalculadora.getContentPane().add(boton_9);
		
		boton_Punto = new JButton(".");
		boton_Punto.setForeground(new Color(255, 255, 255));
		boton_Punto.setBackground(colorNum);
		boton_Punto.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Punto.setBorderPainted(false);
		boton_Punto.setFocusable(false);
		boton_Punto.setBounds(66, 256, 66, 54);
		frmCalculadora.getContentPane().add(boton_Punto);
		
		efectoHoverBoton(boton_0, colorNumHover, colorNum);
		efectoHoverBoton(boton_1, colorNumHover, colorNum);
		efectoHoverBoton(boton_2, colorNumHover, colorNum);
		efectoHoverBoton(boton_3, colorNumHover, colorNum);
		efectoHoverBoton(boton_4, colorNumHover, colorNum);
		efectoHoverBoton(boton_5, colorNumHover, colorNum);
		efectoHoverBoton(boton_6, colorNumHover, colorNum);
		efectoHoverBoton(boton_7, colorNumHover, colorNum);
		efectoHoverBoton(boton_8, colorNumHover, colorNum);
		efectoHoverBoton(boton_9, colorNumHover, colorNum);
		efectoHoverBoton(boton_Punto, colorNumHover, colorNum);
	}

	private void inicializarBotonesOperadores() 
	{
		boton_Sum = new JButton("+");
		boton_Sum.setBorderPainted(false);
		boton_Sum.setBounds(198, 175, 66, 81);
		boton_Sum.setForeground(new Color(255, 255, 255));
		boton_Sum.setBackground(colorOperadores);
		boton_Sum.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Sum.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Sum);
	
		boton_Rest = new JButton("-");
		boton_Rest.setBounds(264, 175, 66, 81);
		boton_Rest.setForeground(new Color(255, 255, 255));
		boton_Rest.setBackground(colorOperadores);
		boton_Rest.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Rest.setBorderPainted(false);
		boton_Rest.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Rest);
		
		boton_Div = new JButton("/");
		boton_Div.setBounds(264, 94, 66, 81);
		boton_Div.setForeground(new Color(255, 255, 255));
		boton_Div.setBackground(colorOperadores);
		boton_Div.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Div.setBorderPainted(false);
		boton_Div.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Div);
		
		boton_Mult = new JButton("*");
		boton_Mult.setBounds(198, 94, 66, 81);
		boton_Mult.setForeground(new Color(255, 255, 255));
		boton_Mult.setBackground(colorOperadores);
		boton_Mult.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Mult.setBorderPainted(false);
		boton_Mult.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Mult);
		
		boton_Borrar = new JButton("<");
		boton_Borrar.setBounds(264, 256, 66, 54);
		boton_Borrar.setForeground(new Color(255, 255, 255));
		boton_Borrar.setBackground(colorOperadores);
		boton_Borrar.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Borrar.setBorderPainted(false);
		boton_Borrar.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Borrar);
	
		boton_Reset = new JButton("C");
		boton_Reset.setBounds(198, 256, 66, 54);
		boton_Reset.setForeground(new Color(255, 255, 255));
		boton_Reset.setBackground(colorOperadores);
		boton_Reset.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Reset.setBorderPainted(false);
		boton_Reset.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Reset);
	
		boton_Igual = new JButton("=");
		efectoHoverBoton(boton_Igual, new Color(255,133,51), new Color(204,82,0));
		boton_Igual.setForeground(new Color(255, 255, 255));
		boton_Igual.setBackground(new Color(204, 82, 0));
		boton_Igual.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Igual.setBounds(132, 256, 66, 54);
		boton_Igual.setFocusable(false);
		boton_Igual.setBorderPainted(false);
		frmCalculadora.getContentPane().add(boton_Igual);
		
		verHistorial = new JButton(">>>");
		verHistorial.setToolTipText("Mostrar historial");
		efectoHoverBoton(verHistorial, colorNumHover, colorNum);
		verHistorial.setForeground(new Color(255, 255, 255));
		verHistorial.setBackground(colorNum);
		verHistorial.setFont(new Font("Arial", Font.BOLD, 20));
		verHistorial.setBorderPainted(false);
		verHistorial.setFocusable(false);
		verHistorial.setBounds(198, 71, 132, 23);
		frmCalculadora.getContentPane().add(verHistorial);
		
		guardar = new JButton("Guardar");
		efectoHoverBoton(guardar, colorOperHover, colorOperadores);
		guardar.setForeground(Color.WHITE);
		guardar.setFont(new Font("Arial", Font.BOLD, 10));
		guardar.setFocusable(false);
		guardar.setBorderPainted(false);
		guardar.setBackground(colorOperadores);
		guardar.setBounds(0, 71, 198, 23);
		frmCalculadora.getContentPane().add(guardar);
		
		efectoHoverBoton(boton_Sum, colorOperHover, colorOperadores);
		efectoHoverBoton(boton_Rest, colorOperHover, colorOperadores);
		efectoHoverBoton(boton_Div, colorOperHover, colorOperadores);
		efectoHoverBoton(boton_Mult, colorOperHover, colorOperadores);
		efectoHoverBoton(boton_Borrar, colorOperHover, colorOperadores);
		efectoHoverBoton(boton_Reset, colorOperHover, colorOperadores);
		efectoHoverBoton(boton_Igual, new Color(255,133,51), new Color(204,82,0));
		
		JButton limpiar = new JButton("Limpiar");
		limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panelHistorial.removeAll();
				panelHistorial.updateUI();
			}
		});
		efectoHoverBoton(limpiar, colorNumHover, colorNum);
		limpiar.setToolTipText("Mostrar historial");
		limpiar.setForeground(Color.WHITE);
		limpiar.setFont(new Font("Arial", Font.BOLD, 10));
		limpiar.setFocusable(false);
		limpiar.setBorderPainted(false);
		limpiar.setBackground(new Color(38, 38, 38));
		limpiar.setBounds(343, 39, 141, 23);
		frmCalculadora.getContentPane().add(limpiar);
		
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
		if(resultado.getText().length() == 1)
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
