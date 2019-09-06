package interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.Border;

import logica.Calculadora;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GuiCalculadora 
{
	private JFrame frmCalculadora;
	private JTextField resultado;
	private JTextField seguimiento;
	
	private JButton boton_0, boton_1, boton_2, boton_3, boton_4, boton_5, boton_6, boton_7, boton_8,boton_9, boton_Punto,
					boton_Sum, boton_Rest, boton_Mult, boton_Div, boton_Igual, boton_Borrar, boton_Reset;
	
	private Color colorNum, colorOperadores;
	
	private Calculadora calculadora;
	
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
		calculadora = new Calculadora();
	}

	private void initialize() 
	{
		//Inicializacion
	
		sePresionoIgual = false;
		colorNum = new Color(38, 38, 38);
		colorOperadores = new Color(77, 77, 77);
		
		inicializarCalculadora();
		inicializarBotonesNum();
		inicializarBotonesOperadores();
		
		//Funciones
		boton_0.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor(boton_0.getText());
				
				if(sePresionoIgual) 
				{
					resultado.setText(boton_0.getText());
					sePresionoIgual = false;
				}
				else
				{
					if(!resultado.getText().equals("0")) 
					{
						resultado.setText(resultado.getText() + boton_0.getText());
						seguimiento.setText(seguimiento.getText() + "0");
					}
				}
			}
		});
		
		
		boton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{				
				calculadora.obtenerValor(boton_1.getText());
				
				agregarNumero(boton_1.getText());
			}
		});
		
		
		boton_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor(boton_2.getText());
		
				agregarNumero(boton_2.getText());	
			}
		});
		
		
		boton_3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor(boton_3.getText());
				
				agregarNumero(boton_3.getText());
			}
		});
		
		
		boton_4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor(boton_4.getText());
				
				agregarNumero(boton_4.getText());
			}
		});
		
		
		boton_5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor(boton_5.getText());
	
				agregarNumero(boton_5.getText());
			}
		});
		
		
		boton_6.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor(boton_6.getText());
			
				agregarNumero(boton_6.getText());
			}
		});
		
		
		boton_7.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor( boton_7.getText());
			
				agregarNumero(boton_7.getText());
			}
		});
		
		
		boton_8.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor(boton_8.getText());
				
				agregarNumero(boton_8.getText());
			}
		});
		
		
		boton_9.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor(boton_9.getText());
				
				agregarNumero(boton_9.getText());
			}
		});		
		
		
		boton_Mult.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				calculadora.obtenerValor(boton_Mult.getText());
				
				agregarOperador(boton_Mult.getText());
				
				sePresionoIgual = false;
			}
		});	
		
		
		boton_Sum.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{						
				calculadora.obtenerValor(boton_Sum.getText());
			
				agregarOperador(boton_Sum.getText());	
				
				sePresionoIgual = false;
			}
		});
				
		
		boton_Div.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor(boton_Div.getText());
				
				agregarOperador(boton_Div.getText());
				
				sePresionoIgual = false;
			}
		});
		
		
		boton_Rest.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor(boton_Rest.getText());
				
				if(resultado.getText().equals("0"))
				{
					resultado.setText(boton_Rest.getText());
					seguimiento.setText(boton_Rest.getText());
				}
				
				else if(resultado.getText().charAt(resultado.getText().length() - 1) == '+' || resultado.getText().charAt(resultado.getText().length() - 1) == '-' 
						|| resultado.getText().charAt(resultado.getText().length() - 1) == '.') 
					reemplazarSigno(boton_Rest.getText());
				
				else
				{
					resultado.setText(resultado.getText() + boton_Rest.getText());
					seguimiento.setText(seguimiento.getText() + boton_Rest.getText());
				}
				
				sePresionoIgual = false;
			}
		});
		
		
		boton_Igual.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				calculadora.obtenerValor(boton_Igual.getText());
				
				if(!esDecimal(calculadora.getResultado())) 
				{
					resultado.setText(String.valueOf((int)calculadora.getResultado()));
					
				}
				else
					resultado.setText(String.valueOf((float)calculadora.getResultado()));
				
				seguimiento.setText(seguimiento.getText() +  "=");
				seguimiento.setText(seguimiento.getText() +  String.valueOf((int)calculadora.getResultado()));
				
				sePresionoIgual = true;
			}
		});	
		
		
		boton_Reset.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				calculadora.obtenerValor(boton_Reset.getText());
				
				resultado.setText("0");
				seguimiento.setText("0");
			}
		});
		
		
		boton_Borrar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				calculadora.obtenerValor(boton_Borrar.getText());
				
				if(!sePresionoIgual)
				{
					if(resultado.getText().length() == 1)
						resultado.setText("0");
				
					else if (resultado.getText().length() > 0)
					{
						resultado.setText(resultado.getText().substring(0, resultado.getText().length() - 1));
					
						seguimiento.setText(seguimiento.getText().substring(0, seguimiento.getText().length() - 1));
					}
				}
			}
		});
		
		
		boton_Punto.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(!sePresionoIgual)
				{
					if(!ultimoEsSigno() && !ultimoNumeroEsDecimal()) //No permite que el numero contenga mas de un signo seguido
					{	
						calculadora.obtenerValor(boton_Punto.getText());				
						
						resultado.setText(resultado.getText() + boton_Punto.getText());
						seguimiento.setText(seguimiento.getText() + boton_Punto.getText());
					}
				}
				else
				{
					calculadora.obtenerValor(boton_Punto.getText());
					resultado.setText("0" + boton_Punto.getText());
					sePresionoIgual = false;
				}
						
			}
		});
	}

	//INICIALIZACION DE BOTONES
	
	private void inicializarCalculadora() 
	{
		frmCalculadora = new JFrame();
		frmCalculadora.setIconImage(Toolkit.getDefaultToolkit().getImage(GuiCalculadora.class.getResource("/interfaz/iconCalculadora.png")));
		frmCalculadora.getContentPane().setBackground(Color.BLACK);
		frmCalculadora.setBackground(Color.WHITE);
		frmCalculadora.setTitle("Calculadora");
		frmCalculadora.setBounds(100, 100, 416, 474);
		frmCalculadora.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCalculadora.getContentPane().setLayout(null);
		frmCalculadora.getContentPane().setBackground(new Color (13,13,13));
		
		resultado = new JTextField("0");
		resultado.setBackground(new Color(13,13,13));
		resultado.setForeground(new Color(204, 82, 0));
		resultado.setEditable(false);
		resultado.setFont(new Font("Tw Cen MT", Font.PLAIN, 50));
		resultado.setBorder(null);
		resultado.setBounds(10, 11, 378, 101);
		frmCalculadora.getContentPane().add(resultado);
		resultado.setColumns(10);
		
		seguimiento = new JTextField("0");
		seguimiento.setEditable(false);
		seguimiento.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		seguimiento.setBackground(new Color(166, 166, 166));
		seguimiento.setBounds(10, 379, 378, 45);
		frmCalculadora.getContentPane().add(seguimiento);
		seguimiento.setColumns(10);
	}

	private void inicializarBotonesNum() 
	{
		boton_0 = new JButton("0");
		efectoHoverBotonesNum(boton_0);
		boton_0.setForeground(new Color(255, 255, 255));
		boton_0.setBackground(colorNum);
		boton_0.setFont(new Font("Arial", Font.BOLD, 20));
		boton_0.setBorderPainted(false);
		boton_0.setFocusable(false);
		boton_0.setBounds(10, 315, 66, 53);
		frmCalculadora.getContentPane().add(boton_0);
	
		boton_1 = new JButton("1");
		efectoHoverBotonesNum(boton_1);
		boton_1.setForeground(new Color(255, 255, 255));
		boton_1.setBackground(colorNum);
		boton_1.setFont(new Font("Arial", Font.BOLD, 20));
		boton_1.setBorderPainted(false);
		boton_1.setFocusable(false);
		boton_1.setBounds(10, 251, 66, 53);
		frmCalculadora.getContentPane().add(boton_1);
	
		boton_2 = new JButton("2");
		efectoHoverBotonesNum(boton_2);
		boton_2.setForeground(new Color(255, 255, 255));
		boton_2.setBackground(colorNum);
		boton_2.setFont(new Font("Arial", Font.BOLD, 20));
		boton_2.setBorderPainted(false);
		boton_2.setFocusable(false);
		boton_2.setBounds(91, 251, 66, 53);
		frmCalculadora.getContentPane().add(boton_2);
		
		boton_3 = new JButton("3");
		efectoHoverBotonesNum(boton_3);
		boton_3.setForeground(new Color(255, 255, 255));
		boton_3.setBackground(colorNum);
		boton_3.setFont(new Font("Arial", Font.BOLD, 20));
		boton_3.setBorderPainted(false);
		boton_3.setFocusable(false);
		boton_3.setBounds(172, 251, 66, 53);
		frmCalculadora.getContentPane().add(boton_3);
		
		boton_4 = new JButton("4");
		efectoHoverBotonesNum(boton_4);
		boton_4.setForeground(new Color(255, 255, 255));
		boton_4.setBackground(colorNum);
		boton_4.setFont(new Font("Arial", Font.BOLD, 20));
		boton_4.setBorderPainted(false);
		boton_4.setFocusable(false);
		boton_4.setBounds(10, 187, 66, 53);
		frmCalculadora.getContentPane().add(boton_4);
	
		boton_5 = new JButton("5");
		efectoHoverBotonesNum(boton_5);
		boton_5.setForeground(new Color(255, 255, 255));
		boton_5.setBackground(colorNum);
		boton_5.setFont(new Font("Arial", Font.BOLD, 20));
		boton_5.setBorderPainted(false);
		boton_5.setFocusable(false);
		boton_5.setBounds(91, 187, 66, 53);
		frmCalculadora.getContentPane().add(boton_5);
		
		boton_6 = new JButton("6");
		efectoHoverBotonesNum(boton_6);
		boton_6.setForeground(new Color(255, 255, 255));
		boton_6.setBackground(colorNum);
		boton_6.setFont(new Font("Arial", Font.BOLD, 20));
		boton_6.setBorderPainted(false);
		boton_6.setFocusable(false);
		boton_6.setBounds(172, 187, 66, 53);
		frmCalculadora.getContentPane().add(boton_6);	
		
		boton_7 = new JButton("7");
		efectoHoverBotonesNum(boton_7);
		boton_7.setForeground(new Color(255, 255, 255));
		boton_7.setBackground(colorNum);
		boton_7.setFont(new Font("Arial", Font.BOLD, 20));
		boton_7.setBorderPainted(false);
		boton_7.setFocusable(false);
		boton_7.setBounds(10, 123, 66, 53);
		frmCalculadora.getContentPane().add(boton_7);
		
		boton_8 = new JButton("8");
		efectoHoverBotonesNum(boton_8);
		boton_8.setForeground(new Color(255, 255, 255));
		boton_8.setBackground(colorNum);
		boton_8.setFont(new Font("Arial", Font.BOLD, 20));
		boton_8.setBorderPainted(false);
		boton_8.setFocusable(false);
		boton_8.setBounds(91, 123, 66, 53);
		frmCalculadora.getContentPane().add(boton_8);
		
		boton_9 = new JButton("9");
		efectoHoverBotonesNum(boton_9);
		boton_9.setForeground(new Color(255, 255, 255));
		boton_9.setBackground(colorNum);
		boton_9.setFont(new Font("Arial", Font.BOLD, 20));
		boton_9.setBorderPainted(false);
		boton_9.setFocusable(false);
		boton_9.setBounds(172, 123, 66, 53);
		frmCalculadora.getContentPane().add(boton_9);
		
		boton_Punto = new JButton(".");
		efectoHoverBotonesNum(boton_Punto);
		boton_Punto.setForeground(new Color(255, 255, 255));
		boton_Punto.setBackground(colorNum);
		boton_Punto.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Punto.setBorderPainted(false);
		boton_Punto.setFocusable(false);
		boton_Punto.setBounds(91, 315, 66, 53);
		frmCalculadora.getContentPane().add(boton_Punto);
	}

	private void efectoHoverBotonesNum(JButton botonNum) 
	{
		botonNum.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				botonNum.setBackground(new Color(51, 51, 51));
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				botonNum.setBackground(new Color(38, 38, 38));
			}
		});
	}
	
	private void inicializarBotonesOperadores() 
	{
		boton_Sum = new JButton("+");
		boton_Sum.setBounds(248, 221, 66, 83);
		boton_Sum.setForeground(new Color(255, 255, 255));
		boton_Sum.setBackground(colorOperadores);
		boton_Sum.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Sum.setBorderPainted(false);
		boton_Sum.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Sum);
		efectoHoverBotonesOperadores(boton_Sum);
		
		boton_Rest = new JButton("-");
		boton_Rest.setBounds(324, 221, 66, 83);
		boton_Rest.setForeground(new Color(255, 255, 255));
		boton_Rest.setBackground(colorOperadores);
		boton_Rest.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Rest.setBorderPainted(false);
		boton_Rest.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Rest);
		efectoHoverBotonesOperadores(boton_Rest);
		
		boton_Div = new JButton("/");
		boton_Div.setBounds(322, 123, 66, 83);
		boton_Div.setForeground(new Color(255, 255, 255));
		boton_Div.setBackground(colorOperadores);
		boton_Div.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Div.setBorderPainted(false);
		boton_Div.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Div);
		efectoHoverBotonesOperadores(boton_Div);
		
		boton_Mult = new JButton("*");
		boton_Mult.setBounds(248, 123, 66, 83);
		boton_Mult.setForeground(new Color(255, 255, 255));
		boton_Mult.setBackground(colorOperadores);
		boton_Mult.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Mult.setBorderPainted(false);
		boton_Mult.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Mult);
		efectoHoverBotonesOperadores(boton_Mult);
		
		boton_Borrar = new JButton("<");
		boton_Borrar.setBounds(322, 315, 66, 53);
		boton_Borrar.setForeground(new Color(255, 255, 255));
		boton_Borrar.setBackground(colorOperadores);
		boton_Borrar.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Borrar.setBorderPainted(false);
		boton_Borrar.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Borrar);
		efectoHoverBotonesOperadores(boton_Borrar);
	
		boton_Reset = new JButton("C");
		boton_Reset.setBounds(248, 315, 66, 53);
		boton_Reset.setForeground(new Color(255, 255, 255));
		boton_Reset.setBackground(colorOperadores);
		boton_Reset.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Reset.setBorderPainted(false);
		boton_Reset.setFocusable(false);
		frmCalculadora.getContentPane().add(boton_Reset);
		efectoHoverBotonesOperadores(boton_Reset);
	
		boton_Igual = new JButton("=");
		boton_Igual.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				boton_Igual.setBackground(new Color(255, 133, 51));
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				boton_Igual.setBackground(new Color(204, 82, 0));
			}
		});
		boton_Igual.setForeground(new Color(255, 255, 255));
		boton_Igual.setBackground(new Color(204, 82, 0));
		boton_Igual.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Igual.setBounds(172, 315, 66, 53);
		boton_Igual.setFocusable(false);
		boton_Igual.setBorderPainted(false);
		frmCalculadora.getContentPane().add(boton_Igual);
	}

	private void efectoHoverBotonesOperadores(JButton boton) 
	{
		boton.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				boton.setBackground(new Color(102, 102, 102));
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				boton.setBackground(new Color(77, 77, 77));
			}
		});
	}
	
	
	//METODOS AUXILIARES
	
	//Dice si en la cadena hay un numero distinto de 0
	private boolean hayPrimerNumero() 
	{
		if(resultado.getText().equals("0"))
			return false;
		return true;
	}
	
	//Dice si el ultimo caracter de la cadena es un '+', '-', '*', '/' o un '.'
	private boolean ultimoEsSigno() 
	{
		if(resultado.getText().length() == 0)
			return false;
		
		char caracter = resultado.getText().charAt(resultado.getText().length() - 1);
		
		return caracter == '+' || caracter == '-' || caracter == '*' || caracter == '/' || caracter == '.';	
	}
	
	private void reemplazarSigno(String signo) 
	{
		resultado.setText(resultado.getText().substring(0, resultado.getText().length() - 1) + signo);
		seguimiento.setText(seguimiento.getText().substring(0, seguimiento.getText().length() - 1) + signo);
	}
	
	//Dice si un numero es decimal
	private boolean esDecimal(double numero) 
	{
		return !(numero % 1 == 0);
				
	}
	
	//Coloca el numero recibido al final 
	private void agregarNumero(String numero)
	{
		if(sePresionoIgual)//Chequeamos si por ultima vez se presiono el boton "="
		{
			resultado.setText(numero);
			sePresionoIgual = false;
		}
		
		else if(resultado.getText().equals("0")) 
		{	
			resultado.setText(numero);
			seguimiento.setText(numero);
		}
		
		else 
		{
			resultado.setText(resultado.getText() + numero);			
			seguimiento.setText(seguimiento.getText() + numero);
		}
	}
	
	//Coloca el signo recibido al final
	private void agregarOperador(String operador) 
	{
		if(hayPrimerNumero()) 
		{
			if(ultimoEsSigno())
				reemplazarSigno(operador);
			else 
			{
				resultado.setText(resultado.getText() + operador);
				seguimiento.setText(seguimiento.getText() + operador);	
			}
		}
	}
	
	//Dice si el ultimo numero de la cadena es un decimal
	public boolean ultimoNumeroEsDecimal()
	{
		boolean esDecimal = false;
		
		for(int i=0; i< resultado.getText().length(); i++)
		{
			if(resultado.getText().charAt(i) == '.')
				esDecimal = true;
			if(resultado.getText().charAt(i) == '/' || resultado.getText().charAt(i) == '*' || resultado.getText().charAt(i) == '-' || resultado.getText().charAt(i) == '+')
				esDecimal = false;
		}
		return esDecimal;
		
	}
}	