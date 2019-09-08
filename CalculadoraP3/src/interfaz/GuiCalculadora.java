package interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import logica.Calculadora;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GuiCalculadora 
{
	private JFrame frmCalculadora;
	private JTextField resultado;
	private JTextField seguimiento;
	
	private JButton boton_0, boton_1, boton_2, boton_3, boton_4, boton_5, boton_6, boton_7, boton_8,boton_9, boton_Punto,
					boton_Sum, boton_Rest, boton_Mult, boton_Div, boton_Igual, boton_Borrar, boton_Reset;
	
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
						if(ultimoEsSigno() || ultimoCaracter() == '.') 
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
		
		
		boton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				agregarNum(boton_1.getText());
			}
		});
		
		
		boton_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				agregarNum(boton_2.getText());
			}
		});
		
		
		boton_3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				agregarNum(boton_3.getText());
			}
		});
		
		
		boton_4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				agregarNum(boton_4.getText());
			}
		});
		
		
		boton_5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				agregarNum(boton_5.getText());
			}
		});
		
		
		boton_6.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				agregarNum(boton_6.getText());
			}
		});
		
		
		boton_7.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				agregarNum(boton_7.getText());
			}
		});
		
		
		boton_8.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				agregarNum(boton_8.getText());
			}
		});
		
		
		boton_9.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				agregarNum(boton_9.getText());
			}
		});		
		
		
		boton_Mult.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				agregarOperador(boton_Mult.getText());
			}
		});	
		
		
		boton_Sum.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				agregarOperador(boton_Sum.getText());
			}
		});
				
		
		boton_Div.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				agregarOperador(boton_Div.getText());
			}
		});
		
		
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
				{
					calculadora.obtenerNumero(calculadora.getResultado());
					calculadora.obtenerOperador(boton_Rest.getText());
					
					agregar(boton_Rest.getText());
					
					sePresionoIgual = false;
				}
			}
		});
		
		
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
				{
					borrarUltimoValor();
				}
			}
		});
		
		
		boton_Punto.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(ultimoCaracter() == '*' || ultimoCaracter() == '/' || ultimoCaracter() == '+' || ultimoCaracter() == '-')
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
		
		
	}
	
	protected void agregarOperador(String operador) 
	{
		if(!"+/*".contains(operador))
			throw new IllegalArgumentException("Este metodo solo agrega el +, * o / .");
		
		if(!sePresionoIgual)
		{
			if(ultimoCaracter() == '-') 
			{
				if(numeroActual.equals("-")) 
				{
					numeroActual = "";
					borrarUltimoValor();
				}
				else
				{
					calculadora.obtenerOperador(operador);
					reemplazarUltimoValor(operador);
				}
					
			}
		
			else if(ultimoEsSigno())
			{
				calculadora.obtenerOperador(operador);
				reemplazarUltimoValor(operador);
			}
			
			else if(ultimoCaracter() == '.') 
			{
				calculadora.obtenerNumero(Double.parseDouble(numeroActual));
				calculadora.obtenerOperador(operador);
				
				reemplazarUltimoValor(operador);
			}
			
			else
			{
				calculadora.obtenerNumero(Double.parseDouble(numeroActual));
				calculadora.obtenerOperador(operador);
				numeroActual = "";
	
				agregar(operador);
			}
		}
		else //Operamos con el ultimo resultado
		{
			calculadora.obtenerNumero(calculadora.getResultado());
			calculadora.obtenerOperador(operador);
			
			agregar(operador);
			
			sePresionoIgual = false;
		}
		
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
		frmCalculadora.setBounds(100, 100, 336, 380);
		frmCalculadora.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCalculadora.getContentPane().setLayout(null);
		frmCalculadora.getContentPane().setBackground(new Color (13,13,13));
		
		resultado = new JTextField("0");
		resultado.setBackground(new Color(13,13,13));
		resultado.setForeground(new Color(204, 82, 0));
		resultado.setEditable(false);
		resultado.setFont(new Font("Tw Cen MT", Font.PLAIN, 50));
		resultado.setBorder(null);
		resultado.setBounds(10, 11, 312, 54);
		frmCalculadora.getContentPane().add(resultado);
		resultado.setColumns(10);

		seguimiento = new JTextField("0");
		seguimiento.setEditable(false);
		seguimiento.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		seguimiento.setBackground(new Color(166, 166, 166));
		seguimiento.setBounds(10, 321, 312, 23);
		frmCalculadora.getContentPane().add(seguimiento);
		seguimiento.setColumns(10);
	}

	private void inicializarBotonesNum() 
	{
		boton_0 = new JButton("0");
		efectoHoverBoton(boton_0, colorNumHover, colorNum);
		boton_0.setForeground(new Color(255, 255, 255));
		boton_0.setBackground(colorNum);
		boton_0.setFont(new Font("Arial", Font.BOLD, 20));
		boton_0.setBorderPainted(false);
		boton_0.setFocusable(false);
		boton_0.setBounds(0, 256, 66, 54);
		frmCalculadora.getContentPane().add(boton_0);
	
		boton_1 = new JButton("1");
		efectoHoverBoton(boton_1, colorNumHover, colorNum);
		boton_1.setForeground(new Color(255, 255, 255));
		boton_1.setBackground(colorNum);
		boton_1.setFont(new Font("Arial", Font.BOLD, 20));
		boton_1.setBorderPainted(false);
		boton_1.setFocusable(false);
		boton_1.setBounds(0, 202, 66, 54);
		frmCalculadora.getContentPane().add(boton_1);
	
		boton_2 = new JButton("2");
		efectoHoverBoton(boton_2, colorNumHover, colorNum);
		boton_2.setForeground(new Color(255, 255, 255));
		boton_2.setBackground(colorNum);
		boton_2.setFont(new Font("Arial", Font.BOLD, 20));
		boton_2.setBorderPainted(false);
		boton_2.setFocusable(false);
		boton_2.setBounds(66, 202, 66, 54);
		frmCalculadora.getContentPane().add(boton_2);
		
		boton_3 = new JButton("3");
		efectoHoverBoton(boton_3, colorNumHover, colorNum);
		boton_3.setForeground(new Color(255, 255, 255));
		boton_3.setBackground(colorNum);
		boton_3.setFont(new Font("Arial", Font.BOLD, 20));
		boton_3.setBorderPainted(false);
		boton_3.setFocusable(false);
		boton_3.setBounds(132, 202, 66, 54);
		frmCalculadora.getContentPane().add(boton_3);
		
		boton_4 = new JButton("4");
		efectoHoverBoton(boton_4, colorNumHover, colorNum);
		boton_4.setForeground(new Color(255, 255, 255));
		boton_4.setBackground(colorNum);
		boton_4.setFont(new Font("Arial", Font.BOLD, 20));
		boton_4.setBorderPainted(false);
		boton_4.setFocusable(false);
		boton_4.setBounds(0, 148, 66, 54);
		frmCalculadora.getContentPane().add(boton_4);
	
		boton_5 = new JButton("5");
		efectoHoverBoton(boton_5, colorNumHover, colorNum);
		boton_5.setForeground(new Color(255, 255, 255));
		boton_5.setBackground(colorNum);
		boton_5.setFont(new Font("Arial", Font.BOLD, 20));
		boton_5.setBorderPainted(false);
		boton_5.setFocusable(false);
		boton_5.setBounds(66, 148, 66, 54);
		frmCalculadora.getContentPane().add(boton_5);
		
		boton_6 = new JButton("6");
		efectoHoverBoton(boton_6, colorNumHover, colorNum);
		boton_6.setForeground(new Color(255, 255, 255));
		boton_6.setBackground(colorNum);
		boton_6.setFont(new Font("Arial", Font.BOLD, 20));
		boton_6.setBorderPainted(false);
		boton_6.setFocusable(false);
		boton_6.setBounds(132, 148, 66, 54);
		frmCalculadora.getContentPane().add(boton_6);	
		
		boton_7 = new JButton("7");
		efectoHoverBoton(boton_7, colorNumHover, colorNum);
		boton_7.setForeground(new Color(255, 255, 255));
		boton_7.setBackground(colorNum);
		boton_7.setFont(new Font("Arial", Font.BOLD, 20));
		boton_7.setBorderPainted(false);
		boton_7.setFocusable(false);
		boton_7.setBounds(0, 94, 66, 54);
		frmCalculadora.getContentPane().add(boton_7);
		
		boton_8 = new JButton("8");
		efectoHoverBoton(boton_8, colorNumHover, colorNum);
		boton_8.setForeground(new Color(255, 255, 255));
		boton_8.setBackground(colorNum);
		boton_8.setFont(new Font("Arial", Font.BOLD, 20));
		boton_8.setBorderPainted(false);
		boton_8.setFocusable(false);
		boton_8.setBounds(66, 94, 66, 54);
		frmCalculadora.getContentPane().add(boton_8);
		
		boton_9 = new JButton("9");
		efectoHoverBoton(boton_9, colorNumHover, colorNum);
		boton_9.setForeground(new Color(255, 255, 255));
		boton_9.setBackground(colorNum);
		boton_9.setFont(new Font("Arial", Font.BOLD, 20));
		boton_9.setBorderPainted(false);
		boton_9.setFocusable(false);
		boton_9.setBounds(132, 94, 66, 54);
		frmCalculadora.getContentPane().add(boton_9);
		
		boton_Punto = new JButton(".");
		efectoHoverBoton(boton_Punto, colorNumHover, colorNum);
		boton_Punto.setForeground(new Color(255, 255, 255));
		boton_Punto.setBackground(colorNum);
		boton_Punto.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Punto.setBorderPainted(false);
		boton_Punto.setFocusable(false);
		boton_Punto.setBounds(66, 256, 66, 54);
		frmCalculadora.getContentPane().add(boton_Punto);
	}

	private void inicializarBotonesOperadores() 
	{
		boton_Sum = new JButton("+");
		boton_Sum.setBounds(198, 175, 66, 81);
		boton_Sum.setForeground(new Color(255, 255, 255));
		boton_Sum.setBackground(colorOperadores);
		boton_Sum.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Sum.setBorderPainted(false);
		boton_Sum.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Sum);
		efectoHoverBoton(boton_Sum, colorOperHover, colorOperadores);
		
		boton_Rest = new JButton("-");
		boton_Rest.setBounds(264, 175, 66, 81);
		boton_Rest.setForeground(new Color(255, 255, 255));
		boton_Rest.setBackground(colorOperadores);
		boton_Rest.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Rest.setBorderPainted(false);
		boton_Rest.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Rest);
		efectoHoverBoton(boton_Rest, colorOperHover, colorOperadores);
		
		boton_Div = new JButton("/");
		boton_Div.setBounds(264, 94, 66, 81);
		boton_Div.setForeground(new Color(255, 255, 255));
		boton_Div.setBackground(colorOperadores);
		boton_Div.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Div.setBorderPainted(false);
		boton_Div.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Div);
		efectoHoverBoton(boton_Div, colorOperHover, colorOperadores);
		
		boton_Mult = new JButton("*");
		boton_Mult.setBounds(198, 94, 66, 81);
		boton_Mult.setForeground(new Color(255, 255, 255));
		boton_Mult.setBackground(colorOperadores);
		boton_Mult.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Mult.setBorderPainted(false);
		boton_Mult.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Mult);
		efectoHoverBoton(boton_Mult, colorOperHover, colorOperadores);
		
		boton_Borrar = new JButton("<");
		boton_Borrar.setBounds(264, 256, 66, 54);
		boton_Borrar.setForeground(new Color(255, 255, 255));
		boton_Borrar.setBackground(colorOperadores);
		boton_Borrar.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Borrar.setBorderPainted(false);
		boton_Borrar.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Borrar);
		efectoHoverBoton(boton_Borrar, colorOperHover, colorOperadores);
	
		boton_Reset = new JButton("C");
		boton_Reset.setBounds(198, 256, 66, 54);
		boton_Reset.setForeground(new Color(255, 255, 255));
		boton_Reset.setBackground(colorOperadores);
		boton_Reset.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Reset.setBorderPainted(false);
		boton_Reset.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Reset);
		efectoHoverBoton(boton_Reset, colorOperHover, colorOperadores);
	
		boton_Igual = new JButton("=");
		efectoHoverBoton(boton_Igual, new Color(255,133,51), new Color(204,82,0));
		boton_Igual.setForeground(new Color(255, 255, 255));
		boton_Igual.setBackground(new Color(204, 82, 0));
		boton_Igual.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Igual.setBounds(132, 256, 66, 54);
		boton_Igual.setFocusable(false);
		boton_Igual.setBorderPainted(false);
		frmCalculadora.getContentPane().add(boton_Igual);
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
	
	//Agregar solo para numeros de 1 al 9
	private void agregarNum(String numero) 
	{
		if(!"123456789".contains(numero))
			throw new IllegalArgumentException("Este agregar solo es para numeros del 1 al 9");
			
		if(!sePresionoIgual) 
		{
			if(resultado.getText().equals("0") || numeroActual.equals("0"))
			{
				reemplazarUltimoValor(numero);
				numeroActual = numero;
			}
			else 
			{
				agregar(numero);
				numeroActual += numero;
			}
		}
		else 
		{
			resultado.setText(numero);
			numeroActual = numero;
			sePresionoIgual = false;
		}	
	}
	
	//Agregar en general
	private void agregar(String text) 
	{
		resultado.setText(resultado.getText() + text);
	}
	
	//Cambia el ultimo caracter de la cadena por el parametro 
	private void reemplazarUltimoValor(String text) 
	{
		resultado.setText(resultado.getText().substring(0, resultado.getText().length() - 1) + text);
	}
	
	//Borra el ultimo caracter de la cadena
	private void borrarUltimoValor() 
	{
		if(resultado.getText().length() == 1)
			resultado.setText("0");
		
		else if(!numeroActual.equals("")) 
		{
			calculadora.obtenerNumero(Double.parseDouble(numeroActual));
			calculadora.borrar();
			numeroActual = numeroActual.substring(0, numeroActual.length() - 1);
			resultado.setText(resultado.getText().substring(0, resultado.getText().length() - 1));
			
		}
		else if(ultimoEsSigno()) 
		{
			calculadora.borrar();
			resultado.setText(resultado.getText().substring(0, resultado.getText().length() - 1));
			numeroActual = ultimoNumero();
		}
		
	}
	
	//Devuelve un string con el numero que esta formado a partir del ultimo signo
	private String ultimoNumero() 
	{
		String ultimoNumero = "";
		
		for(int i=0; i < resultado.getText().length(); i++) 
		{
			if("+-*/".contains(Character.toString(resultado.getText().charAt(i)))) 
			{
				ultimoNumero = "";
			}
			else
				ultimoNumero += resultado.getText().charAt(i);
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
