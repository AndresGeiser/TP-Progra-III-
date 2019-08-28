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
	private JTextField prueba;

	private Calculadora calculadora;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiCalculadora window = new GuiCalculadora();
					window.frmCalculadora.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GuiCalculadora() {
		initialize();
		calculadora = new Calculadora();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmCalculadora = new JFrame();
		frmCalculadora.setIconImage(Toolkit.getDefaultToolkit().getImage(GuiCalculadora.class.getResource("/interfaz/calculator.png")));
		frmCalculadora.getContentPane().setBackground(SystemColor.activeCaption);
		frmCalculadora.setBackground(Color.WHITE);
		frmCalculadora.setTitle("Calculadora");
		frmCalculadora.setBounds(100, 100, 416, 474);
		frmCalculadora.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCalculadora.getContentPane().setLayout(null);
		
		resultado = new JTextField();
		resultado.setEditable(false);
		resultado.setFont(new Font("Tw Cen MT", Font.PLAIN, 50));
		resultado.setBounds(10, 11, 378, 83);
		frmCalculadora.getContentPane().add(resultado);
		resultado.setColumns(10);
		
		JButton boton_0 = new JButton("0");
		boton_0.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		boton_0.setBounds(10, 315, 66, 53);
		frmCalculadora.getContentPane().add(boton_0);
		
		boton_0.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor(boton_0.getText());
				
				resultado.setText(resultado.getText() + boton_0.getText());
				prueba.setText(prueba.getText() + "0");
			}
		});
		
		JButton boton_1 = new JButton("1");
		boton_1.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		boton_1.setBounds(10, 251, 66, 53);
		frmCalculadora.getContentPane().add(boton_1);
		
		boton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{				
				calculadora.obtenerValor(boton_1.getText());
				
				resultado.setText(resultado.getText() + boton_1.getText());			
				prueba.setText(prueba.getText() + "1");
			}
		});
		
		JButton boton_2 = new JButton("2");
		boton_2.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		boton_2.setBounds(91, 251, 66, 53);
		frmCalculadora.getContentPane().add(boton_2);
		
		boton_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor(boton_2.getText());
				
				resultado.setText(resultado.getText() + boton_2.getText());			
				prueba.setText(prueba.getText() + "2");
			}
		});
		
		JButton boton_3 = new JButton("3");
		boton_3.setBounds(172, 251, 66, 53);
		boton_3.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		frmCalculadora.getContentPane().add(boton_3);
		
		boton_3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor(boton_3.getText());
				
				resultado.setText(resultado.getText() + boton_3.getText());	
				prueba.setText(prueba.getText() + "3");
			}
		});
		
		JButton boton_4 = new JButton("4");
		boton_4.setBounds(10, 187, 66, 53);
		boton_4.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		frmCalculadora.getContentPane().add(boton_4);
		
		boton_4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor(boton_4.getText());
				
				resultado.setText(resultado.getText() + boton_4.getText());
				prueba.setText(prueba.getText() + "4");

			}
		});
		
		JButton boton_5 = new JButton("5");
		boton_5.setBounds(91, 187, 66, 53);
		boton_5.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		frmCalculadora.getContentPane().add(boton_5);
		
		boton_5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor(boton_5.getText());
				
				resultado.setText(resultado.getText() + boton_5.getText());
				prueba.setText(prueba.getText() + "5");

			}
		});
		
		JButton boton_6 = new JButton("6");
		boton_6.setBounds(172, 187, 66, 53);
		boton_6.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		frmCalculadora.getContentPane().add(boton_6);
		
		boton_6.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor(boton_6.getText());
				
				resultado.setText(resultado.getText() + boton_6.getText());
				prueba.setText(prueba.getText() + "6");

			}
		});
		
		
		JButton boton_7 = new JButton("7");
		boton_7.setBounds(10, 123, 66, 53);
		boton_7.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		frmCalculadora.getContentPane().add(boton_7);
		
		boton_7.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor(boton_7.getText());
				
				resultado.setText(resultado.getText() + boton_7.getText());
				prueba.setText(prueba.getText() + "7");
			}
		});
		
		JButton boton_8 = new JButton("8");
		boton_8.setBounds(91, 123, 66, 53);
		boton_8.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		frmCalculadora.getContentPane().add(boton_8);
		
		boton_8.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor(boton_8.getText());
				
				resultado.setText(resultado.getText() + boton_8.getText());
				prueba.setText(prueba.getText() + "8");

			}
		});
		
		JButton boton_9 = new JButton("9");
		boton_9.setBounds(172, 123, 66, 53);
		boton_9.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		frmCalculadora.getContentPane().add(boton_9);
		
		boton_9.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor(boton_9.getText());
				
				resultado.setText(resultado.getText() + boton_9.getText());
				prueba.setText(prueba.getText() + "9");
			}
		});		
		
		JButton boton_Mult = new JButton("*");
		boton_Mult.setFont(new Font("Tahoma", Font.PLAIN, 20));
		boton_Mult.setBounds(248, 123, 66, 83);
		frmCalculadora.getContentPane().add(boton_Mult);
		
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
						prueba.setText(prueba.getText() + boton_Mult.getText());	
					}
				}
			}
		});	
		
		JButton boton_Sum = new JButton("+");
		boton_Sum.setFont(new Font("Tahoma", Font.PLAIN, 20));
		boton_Sum.setBounds(248, 221, 66, 83);
		frmCalculadora.getContentPane().add(boton_Sum);
		
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
						prueba.setText(prueba.getText() + boton_Sum.getText());
					}
				}
				
			}
		});
				
		
		JButton boton_Div = new JButton("/");
	
		boton_Div.setFont(new Font("Tahoma", Font.PLAIN, 20));
		boton_Div.setBounds(322, 123, 66, 83);
		frmCalculadora.getContentPane().add(boton_Div);
		
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
						prueba.setText(prueba.getText() + boton_Div.getText());	
					}
				}
			}
		});
		
		JButton boton_Rest = new JButton("-");
		
		boton_Rest.setFont(new Font("Tahoma", Font.PLAIN, 20));
		boton_Rest.setBounds(324, 221, 66, 83);
		frmCalculadora.getContentPane().add(boton_Rest);
		
		boton_Rest.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				calculadora.obtenerValor(boton_Rest.getText());
				
				if(hayPrimerNumero()) 
				{
					if(haySigno())
						reemplazarSigno(boton_Rest.getText());
					else 
					{
						resultado.setText(resultado.getText() + boton_Rest.getText());
						prueba.setText(prueba.getText() + boton_Rest.getText());
					}
				}	
			}
		});
		
		JButton boton_Igual = new JButton("=");
		boton_Igual.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		boton_Igual.setBounds(91, 315, 147, 53);
		frmCalculadora.getContentPane().add(boton_Igual);
		
		boton_Igual.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				calculadora.obtenerValor(boton_Igual.getText());
				
				resultado.setText(String.valueOf(calculadora.getResultado()));
			}
		});	
		
		JButton boton_Reset = new JButton("C");
		boton_Reset.setFont(new Font("Tahoma", Font.PLAIN, 20));
		boton_Reset.setBounds(248, 315, 66, 53);
		frmCalculadora.getContentPane().add(boton_Reset);
		
		boton_Reset.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				calculadora.obtenerValor(boton_Reset.getText());
				
				resultado.setText("");
				prueba.setText("");
			}
		});
		
		
		JButton boton_Borrar = new JButton("<");
		boton_Borrar.setBounds(322, 315, 66, 53);
		frmCalculadora.getContentPane().add(boton_Borrar);
		
		boton_Borrar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				calculadora.obtenerValor(boton_Borrar.getText());
				
				resultado.setText(resultado.getText().substring(0, resultado.getText().length() - 1));
				
			}
		});
		
		
		prueba = new JTextField();
		prueba.setEditable(false);
		prueba.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		prueba.setBackground(SystemColor.inactiveCaption);
		prueba.setBounds(10, 379, 378, 45);
		frmCalculadora.getContentPane().add(prueba);
		prueba.setColumns(10);
	
	}
	
	public boolean hayPrimerNumero() 
	{
		if(resultado.getText().length() > 0)
			return true;
		return false;
	}
	
	public boolean haySigno() 
	{
		if(resultado.getText().length() == 0)
			return false;
		
		char caracter = resultado.getText().charAt(resultado.getText().length() - 1);
		
		if(caracter == '+' || caracter == '-' || caracter == '*' || caracter == '/') 
			return true;
		
		return false;
	}
	
	
	public void reemplazarSigno(String signo) 
	{
		resultado.setText(resultado.getText().substring(0, resultado.getText().length() - 1) + signo);
	}
}
