package logica;

import java.util.ArrayList;

public class Calculadora 
{
	ArrayList<Integer> numeros;
	ArrayList<String> signos;
	
	String numeroActual; //Numero que estara formando
	int resultado;

	
	///////////////CONSTRUCTOR///////////////
	public Calculadora()
	{
		numeros = new ArrayList<Integer>();
		signos = new ArrayList<String>();
		resultado = 0;
		numeroActual = "";
	}
	
	
	public void obtenerValor(String c) 
	{
		if(c.equals("C"))//RESETEAR
			resetear(); 
	
		else if (c.equals("<"))//BORRAR
			borrarUltimoValor();
									
		else if(c.equals("="))//CALCULAR 
		{
			if(numeros.size() == 0 && numeroActual.length() == 0)//Si no se agrego ningun numero y no se estuvo formando uno retornamos.
				return;
			
			if(numeros.size() == 0 && numeroActual.length() > 0)//Si no se agrego ningun numero y se estuvo formando uno agregamos este ultimo.
			{
				numeros.add(Integer.parseInt(numeroActual));
				numeroActual = "";
			}
						
			if(numeroActual.length() != 0) //Chequeamos que se estuviera formando un numero antes de agregarlo.
				numeros.add(Integer.parseInt(numeroActual));
			
			calcular(); 
			numeroActual = "";

		}
		else if(c.equals("+") || c.equals("-") || c.equals("/") || c.equals("*"))
		{		
			if(numeroActual == "" && signos.size() > 0)	//Chequeamos si por ultima vez se estaba formando un numero o si se 
				signos.set(signos.size() - 1, c);	//agrego un signo, de ser esto ultimo, se lo reemplaza.	
			else 
				signos.add(c);
				
			if(signos.size() == 1 && numeroActual.length() == 0 && numeros.size() == 0)	//Si se agrego un signo sin que halla ningun
			{																		//numero se lo elimina.		
				signos.remove(0);
				return;
			}
			
			if(numeroActual != "")
				numeros.add(Integer.parseInt(numeroActual));
			
			numeroActual = "";
		}
		else 
			numeroActual = numeroActual + c;
		
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
	
	private void sumar(int num1, int num2)
	{
		resultado = num1 + num2;
	}
	
	private void restar(int num1, int num2)
	{
		resultado = num1 - num2;
	}
	
	private void dividir( int num1, int num2)
	{
		resultado = num1 / num2;
	}
	
	private void multiplicar (int num1, int num2)
	{
		resultado = num1 * num2;
	}
	
	public int getResultado() 
	{
		return resultado;
	}
	
	
}

