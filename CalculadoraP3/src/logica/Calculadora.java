package logica;

import java.util.ArrayList;

public class Calculadora 
{
	private ArrayList<Double> numeros;
	private ArrayList<String> signos;
	
	private String numeroActual; //Numero que estara formando
	private double resultado;
	
	///////////////CONSTRUCTOR///////////////
	public Calculadora()
	{
		numeros = new ArrayList<Double>();
		signos = new ArrayList<String>();
		resultado = 0;
		numeroActual = "0";
	}
	
	
	public void obtenerValor(String valor) 
	{
		if(valor.equals("C"))//RESETEAR
			resetear(); 
	
		else if (valor.equals("<"))//BORRAR
			borrarUltimoValor();
									
		else if(valor.equals("="))//CALCULAR 
		{
			if(numeros.size() == 0 && numeroActual.length() == 0)//Si no se agrego ningun numero y no se estuvo formando uno retornamos.
				return;
			
			if(numeros.size() == 0 && numeroActual.length() > 0)//Si no se agrego ningun numero y se estuvo formando uno agregamos este ultimo.
				resultado = Double.parseDouble(numeroActual);
			
			else if(numeroActual.length() > 0) //Chequeamos que se estuviera formando un numero antes de agregarlo.
			{
				numeros.add(Double.parseDouble(numeroActual));
				numeroActual = "";
			}
			
			if(signos.size() > 0)
				calcular();
			
			System.out.println(resultado);
			
		}
		else if(valor.equals("+") || valor.equals("-") || valor.equals("/") || valor.equals("*"))
		{	
			if(signos.size() == 0 && numeroActual.length() == 0 && numeros.size() == 0)													
				return;
			
			if(numeroActual == "" && signos.size() > 0) 	//Si esta vacio entonces por ultima vez se agrego un signo
				signos.set(signos.size() - 1, valor);			//Entonces reemplazamos ese ultimo signo por el nuevo	
			else
			{
				signos.add(valor);
				
				if(numeroActual != "")
					numeros.add(Double.parseDouble(numeroActual));
				
				numeroActual = "";	
			}
		}
		else
		
			numeroActual += valor;
			
		System.out.println(numeroActual);
		
	}

	private void borrarUltimoValor() 
	{
		if(numeroActual.length() == 0)//Si no estaba formando un numero.
		{
			signos.remove(signos.size() - 1);								//Pasamos a borrar el ultimo signo ingresado
			numeroActual = String.valueOf(numeros.get(numeros.size() -1));	//y ponemos en "edicion" al ultimo numero ingresado.
			numeros.remove(numeros.size() - 1);								//Sacamos ese ultimo numero del array.
		}
		else
			numeroActual = numeroActual.substring(0, numeroActual.length() - 1); 			//Borramos el ultimo caracter del numero en "edicion".
	}
	
	private void calcular() 
	{	
		resultado = numeros.get(0);
		numeros.remove(0);
		
		while(numeros.size() != 0) 
		{
			if(signos.get(0).equals("+"))
				sumar(resultado, numeros.get(0));
			
			if(signos.get(0).equals("-"))
				restar(resultado, numeros.get(0));
			
			if(signos.get(0).equals("/"))
				dividir(resultado, numeros.get(0));
			
			if(signos.get(0).equals("*"))
				multiplicar(resultado, numeros.get(0));
			
			numeros.remove(0);
			signos.remove(0);
		}
		
		numeros.add(resultado);
	}
	
	private void resetear() //Funcion que resetea todos los datos de la calculadora
	{	
		numeroActual = "";
		resultado = 0;
		
		while(numeros.size() != 0)
			numeros.remove(0);
		
		while(signos.size() != 0)
			signos.remove(0);
	}	
	
	private void sumar(double num1, double num2)
	{
		resultado = num1 + num2;
	}
	
	private void restar(double num1, double num2)
	{
		resultado = num1 - num2;
	}
	
	private void dividir(double num1, double num2)
	{
		resultado = num1 / num2;
	}
	
	private void multiplicar(double num1, double num2)
	{
		resultado = num1 * num2;
	}
	
	public double getResultado() 
	{
		return resultado;
	}
	
	
}

