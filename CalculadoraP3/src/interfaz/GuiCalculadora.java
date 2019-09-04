package interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import logica.Calculadora;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiCalculadora 
{
	private JFrame frmCalculadora;
	private JTextField resultado;
	private JTextField seguimiento;

	private Calculadora calculadora;
	
	
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
		
		frmCalculadora = new JFrame();
		frmCalculadora.setIconImage(Toolkit.getDefaultToolkit().getImage(GuiCalculadora.class.getResource("/interfaz/calculator.png")));
		frmCalculadora.getContentPane().setBackground(new Color(105, 105, 105));
		frmCalculadora.setBackground(Color.WHITE);
		frmCalculadora.setTitle("Calculadora");
		frmCalculadora.setBounds(100, 100, 416, 474);
		frmCalculadora.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCalculadora.getContentPane().setLayout(null);
		
		
		//Inicializacion
		inicializarResultado();
		inicializarSeguimiento();
		
		JButton boton_0 = inicializarBoton0();
		JButton boton_1 = inicializarBoton1();
		JButton boton_2 = inicializarBoton2();
		JButton boton_3 = inicializarBoton3();
		JButton boton_4 = inicializarBoton4();
		JButton boton_5 = inicializarBoton5();
		JButton boton_6 = inicializarBoton6();
		JButton boton_7 = inicializarBoton7();
		JButton boton_8 = inicializarBoton8();
		JButton boton_9 = inicializarBoton9();

		JButton boton_Mult = inicializarBotonMult();
		JButton boton_Div = inicializarBotonDiv();
		JButton boton_Sum = inicializarBotonSuma();
		JButton boton_Rest = inicializarBotonRest();
		JButton boton_Punto = inicializarBotonPunto();


		JButton boton_Igual = inicializarBotonIgual();
		JButton boton_Borrar = inicializarBotonBorrar();
		JButton boton_Reset = inicializarBotonReset();



		//Funciones
		
		boton_0.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor(boton_0.getText());
				
				if(seguimiento.getText().charAt(seguimiento.getText().length() - 1) == '=')
					resultado.setText(boton_0.getText());
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
				
				if(hayPrimerNumero()) 
				{
					if(haySigno()) 
						reemplazarSigno(boton_Mult.getText());
					else 
					{
						resultado.setText(resultado.getText() + boton_Mult.getText());
						seguimiento.setText(seguimiento.getText() + boton_Mult.getText());	
					}
				}
			}
		});	
		
		
		boton_Sum.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{						
				calculadora.obtenerValor(boton_Sum.getText());
			
				if(hayPrimerNumero()) 
				{
					if(haySigno())
						reemplazarSigno(boton_Sum.getText());
					else
					{	
						resultado.setText(resultado.getText() + boton_Sum.getText());
						seguimiento.setText(seguimiento.getText() + boton_Sum.getText());
					}
				}
				
			}
		});
				
		
		boton_Div.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor(boton_Div.getText());
				
				if(hayPrimerNumero()) 
				{
					if(haySigno())
						reemplazarSigno(boton_Div.getText());
					else 
					{
						resultado.setText(resultado.getText() + boton_Div.getText());
						seguimiento.setText(seguimiento.getText() + boton_Div.getText());	
					}
				}
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
				
				else if(resultado.getText().charAt(resultado.getText().length() - 1) == '+' || resultado.getText().charAt(resultado.getText().length() - 1) == '-' ) 
					reemplazarSigno(boton_Rest.getText());
				
				else
				{
					resultado.setText(resultado.getText() + boton_Rest.getText());
					seguimiento.setText(seguimiento.getText() + boton_Rest.getText());
				}			
			}
		});
		
		
		boton_Igual.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					calculadora.obtenerValor(boton_Igual.getText());
					
					if(!esDecimal(calculadora.getResultado())) 
					{
						resultado.setText(String.valueOf((int)calculadora.getResultado()));
					}
					else
						resultado.setText(String.valueOf(calculadora.getResultado()));
					
					seguimiento.setText(seguimiento.getText() +  "=");
				} 
				catch (Exception e) 
				{
					resultado.setText("Error de sintaxis");
				}
				
				
				
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
				
				if(seguimiento.getText().charAt(seguimiento.getText().length() - 1) != '=')
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
				if(seguimiento.getText().charAt(seguimiento.getText().length() - 1) != '=')
					if(!haySigno()) //No permite que el numero contenga mas de un numero seguido
					{	
						calculadora.obtenerValor(boton_Punto.getText());				

						resultado.setText(resultado.getText() + boton_Punto.getText());
						seguimiento.setText(seguimiento.getText() + boton_Punto.getText());	
					}
			}
		});
	}

	//Metodos de inicializacion y auxiliares
	
	private JButton inicializarBotonMult() {
		JButton boton_Mult = new JButton("*");
		boton_Mult.setBackground(new Color(211, 211, 211));
		boton_Mult.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Mult.setBounds(248, 123, 66, 83);
		frmCalculadora.getContentPane().add(boton_Mult);
		return boton_Mult;
	}

	private JButton inicializarBotonDiv() {
		JButton boton_Div = new JButton("/");
		boton_Div.setBackground(new Color(211, 211, 211));
	
		boton_Div.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Div.setBounds(322, 123, 66, 83);
		frmCalculadora.getContentPane().add(boton_Div);
		return boton_Div;
	}

	private JButton inicializarBotonSuma() {
		JButton boton_Sum = new JButton("+");
		boton_Sum.setBackground(new Color(211, 211, 211));
		boton_Sum.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Sum.setBounds(248, 221, 66, 83);
		frmCalculadora.getContentPane().add(boton_Sum);
		return boton_Sum;
	}

	private JButton inicializarBoton9() {
		JButton boton_9 = new JButton("9");
		boton_9.setBounds(172, 123, 66, 53);
		boton_9.setFont(new Font("Arial", Font.BOLD, 20));
		frmCalculadora.getContentPane().add(boton_9);
		return boton_9;
	}

	private JButton inicializarBoton8() {
		JButton boton_8 = new JButton("8");
		boton_8.setBounds(91, 123, 66, 53);
		boton_8.setFont(new Font("Arial", Font.BOLD, 20));
		frmCalculadora.getContentPane().add(boton_8);
		return boton_8;
	}

	private JButton inicializarBoton7() {
		JButton boton_7 = new JButton("7");
		boton_7.setBounds(10, 123, 66, 53);
		boton_7.setFont(new Font("Arial", Font.BOLD, 20));
		frmCalculadora.getContentPane().add(boton_7);
		return boton_7;
	}

	private JButton inicializarBoton6() {
		JButton boton_6 = new JButton("6");
		boton_6.setBounds(172, 187, 66, 53);
		boton_6.setFont(new Font("Arial", Font.BOLD, 20));
		frmCalculadora.getContentPane().add(boton_6);
		return boton_6;
	}

	private void inicializarResultado() {
		resultado = new JTextField("0");
		resultado.setEditable(false);
		resultado.setFont(new Font("Tw Cen MT", Font.PLAIN, 50));
		resultado.setBounds(10, 11, 378, 83);
		frmCalculadora.getContentPane().add(resultado);
		resultado.setColumns(10);
	}

	private JButton inicializarBoton0() {
		JButton boton_0 = new JButton("0");
		boton_0.setFont(new Font("Arial", Font.BOLD, 20));
		boton_0.setBounds(10, 315, 66, 53);
		frmCalculadora.getContentPane().add(boton_0);
		return boton_0;
	}

	private JButton inicializarBotonPunto() {
		JButton punto = new JButton(".");
		punto.setFont(new Font("Arial", Font.BOLD, 20));
		punto.setBounds(91, 315, 66, 53);
		frmCalculadora.getContentPane().add(punto);
		return punto;
	}

	private void inicializarSeguimiento() {
		seguimiento = new JTextField("0");
		seguimiento.setEditable(false);
		seguimiento.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		seguimiento.setBackground(SystemColor.inactiveCaption);
		seguimiento.setBounds(10, 379, 378, 45);
		frmCalculadora.getContentPane().add(seguimiento);
		seguimiento.setColumns(10);
	}

	private JButton inicializarBotonBorrar() {
		JButton boton_Borrar = new JButton("<");
		boton_Borrar.setBackground(new Color(211, 211, 211));
		boton_Borrar.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Borrar.setBounds(322, 315, 66, 53);
		frmCalculadora.getContentPane().add(boton_Borrar);
		return boton_Borrar;
	}

	private JButton inicializarBotonReset() {
		JButton boton_Reset = new JButton("C");
		boton_Reset.setBackground(new Color(211, 211, 211));
		boton_Reset.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Reset.setBounds(248, 315, 66, 53);
		frmCalculadora.getContentPane().add(boton_Reset);
		return boton_Reset;
	}

	private JButton inicializarBotonIgual() {
		JButton boton_Igual = new JButton("=");
		boton_Igual.setBackground(new Color(204, 153, 153));
		boton_Igual.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Igual.setBounds(172, 315, 66, 53);
		frmCalculadora.getContentPane().add(boton_Igual);
		return boton_Igual;
	}

	private JButton inicializarBotonRest() {
		JButton boton_Rest = new JButton("-");
		boton_Rest.setBackground(new Color(211, 211, 211));
		boton_Rest.setFont(new Font("Arial", Font.BOLD, 20));
		boton_Rest.setBounds(324, 221, 66, 83);
		frmCalculadora.getContentPane().add(boton_Rest);
		return boton_Rest;
	}

	private JButton inicializarBoton1() {
		JButton boton_1 = new JButton("1");
		boton_1.setFont(new Font("Arial", Font.BOLD, 20));
		boton_1.setBounds(10, 251, 66, 53);
		frmCalculadora.getContentPane().add(boton_1);
		return boton_1;
	}

	private JButton inicializarBoton2() {
		JButton boton_2 = new JButton("2");
		boton_2.setFont(new Font("Arial", Font.BOLD, 20));
		boton_2.setBounds(91, 251, 66, 53);
		frmCalculadora.getContentPane().add(boton_2);
		return boton_2;
	}

	private JButton inicializarBoton3() {
		JButton boton_3 = new JButton("3");
		boton_3.setBounds(172, 251, 66, 53);
		boton_3.setFont(new Font("Arial", Font.BOLD, 20));
		frmCalculadora.getContentPane().add(boton_3);
		return boton_3;
	}

	private JButton inicializarBoton4() {
		JButton boton_4 = new JButton("4");
		boton_4.setBounds(10, 187, 66, 53);
		boton_4.setFont(new Font("Arial", Font.BOLD, 20));
		frmCalculadora.getContentPane().add(boton_4);
		return boton_4;
	}

	private JButton inicializarBoton5()
	{
		JButton boton_5 = new JButton("5");
		boton_5.setBounds(91, 187, 66, 53);
		boton_5.setFont(new Font("Arial", Font.BOLD, 20));
		frmCalculadora.getContentPane().add(boton_5);
		return boton_5;
	}
	
	private boolean hayPrimerNumero() 
	{
		if(resultado.getText().equals("0"))
			return false;
		return true;
	}
	
	private boolean haySigno() 
	{
		if(resultado.getText().length() == 0)
			return false;
		
		char caracter = resultado.getText().charAt(resultado.getText().length() - 1);
		
		return caracter == '+' || caracter == '-' || caracter == '*' || caracter == '/' || caracter == '.';	
	}
	
	private boolean esDecimal(double numero) 
	{
		return !(numero % 1 == 0);
				
	}
	
	private void reemplazarSigno(String signo) 
	{
		resultado.setText(resultado.getText().substring(0, resultado.getText().length() - 1) + signo);
		seguimiento.setText(seguimiento.getText().substring(0, seguimiento.getText().length() - 1) + signo);
	}
	
	private void agregarNumero(String numero)
	{
		if(seguimiento.getText().charAt(seguimiento.getText().length() - 1) == '=')//Chequeamos si por ultima vez se presiono el boton "="
			resultado.setText(numero);
		
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
	
}	