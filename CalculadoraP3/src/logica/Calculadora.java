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
		numeroActual = "";
	}
	
	
	public void obtenerValor(String valor) 
	{
		if(valor.equals("C"))//RESETEAR
			resetear(); 
	
		else if (valor.equals("<"))//BORRAR
			borrarUltimoValor();
									
		else if(valor.equals("="))//CALCULAR 
		{
			if(noHayDatos())//Si no se agrego ningun numero y no se estuvo formando uno retornamos.
				return;
			
			if(numeros.size() == 0 && !actualEstaVacio() )//Si no se agrego ningun numero y se estuvo formando uno agregamos este ultimo.
			{	
				if(!actualEsResta())
					resultado = Double.parseDouble(numeroActual);
				
				numeroActual = "";
			}
			
			if(!actualEstaVacio() && !actualEsResta()) //Chequeamos que se estuviera formando un numero antes de agregarlo.
			{
				numeros.add(Double.parseDouble(numeroActual));
				numeroActual = "";
			}
			
			if(hayAlgunSigno())
				calcular();		
		}
		
		else if(valor.equals("-"))
		{
			if(noHayDatos())
				numeroActual += valor;
			
			else
			{
				if(resultado != 0) 
				{
					numeros.add(resultado);
					resultado = 0 ;
				}
				
				if(hayAlgunSigno()) 
				{
					if(actualEstaVacio() && ultimoSignoEs("*") || ultimoSignoEs("/")) //Permite operar con las operaciones entre negativos
						numeroActual += valor;
						
					else if(actualEstaVacio() && ultimoSignoEs("+"))                      //Permite cambiar el signo a un numero positivo
						reemplazarUltimoSigno(valor);
					
					else
						guardarDatos(valor);
				}
				else
					if(!actualEsResta())
						guardarDatos(valor);
		
			}	
		}
		
		else if(valor.equals("+") || valor.equals("/") || valor.equals("*"))
		{			
			if(noHayDatos())													
				return;
			
			if(resultado != 0)
			{
				numeros.add(resultado);
				resultado = 0 ;
			}
			
			if(actualEstaVacio() && hayAlgunSigno()) //Si esta vacio quiere decir que por ultima vez se agrego un signo
				reemplazarUltimoSigno(valor);			//Entonces reemplazamos ese ultimo signo por el nuevo	
			else 
			{
				if(!actualEsResta())
					guardarDatos(valor);
				else
					numeroActual = "";
			}
		}
		
		else
		{
			if(resultado != 0)
				resultado = 0;
			
			
			numeroActual += valor;
			
			System.out.println(numeroActual);
		}
	}


	private boolean actualEsResta() 
	{
		return numeroActual.equals("-");
	}

	private boolean hayAlgunSigno() 
	{
		return cantSignos() > 0;
	}

	private int cantSignos() 
	{
		return signos.size();
	}

	private boolean ultimoSignoEs(String signo)
	{
		return signos.get(cantSignos() -1).equals(signo);
	}
	
	
	private boolean actualEstaVacio()
	{
		return numeroActual.equals("");
	}
	
	public boolean noHayDatos() 
	{
		if(actualEstaVacio() && numeros.size() == 0 && cantSignos() == 0 && resultado == 0)
			return true;
		return false;
	}

	private void borrarUltimoValor() 
	{
		if(noHayDatos())
			return;
		
		if(actualEstaVacio())//Si no estaba formando un numero.
		{
			if(hayAlgunSigno()) 
			{
				signos.remove(cantSignos() - 1);								//Pasamos a borrar el ultimo signo ingresado
				numeroActual = String.valueOf(numeros.get(numeros.size() -1));	//y ponemos en "edicion" al ultimo numero ingresado.			
				numeros.remove(numeros.size() - 1);								//Sacamos ese ultimo numero del array.
			}
		}
		else
			numeroActual = numeroActual.substring(0, numeroActual.length() - 1); 		//Borramos el ultimo caracter del numero en "edicion".
		
		System.out.println(numeroActual);
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
		
		if(signos.size() > 0)
			signos.remove(0);
	}
	
	private void resetear() //Funcion que resetea todos los datos de la calculadora
	{	
		numeroActual = "";
		resultado = 0;
		
		while(numeros.size() != 0)
			numeros.remove(0);
		
		while(cantSignos() != 0)
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
	
	private void reemplazarUltimoSigno(String valor) 
	{
		signos.set(cantSignos() - 1, valor);
	}
	
	private void guardarDatos(String valor) 
	{
		signos.add(valor);
		
		if(!actualEstaVacio())
			numeros.add(Double.parseDouble(numeroActual));
		
		numeroActual = "";
	}
	
	public double getResultado() 
	{
		return resultado;
	}

	public String getNumeroActual() 
	{
		return numeroActual;
	}	
}	