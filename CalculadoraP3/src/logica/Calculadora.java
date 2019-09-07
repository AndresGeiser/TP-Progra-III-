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
				
				if(!actualEsInvalido())
					resultado = Double.parseDouble(numeroActual);
				
				numeroActual = "";
				System.out.println(resultado);
			}
			
			if(!actualEstaVacio() && !actualEsInvalido()) //Chequeamos que se estuviera formando un numero antes de agregarlo.
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
					if(actualEstaVacio() && ultimoSigno().equals("*") || ultimoSigno().equals("/") || ultimoSigno().equals("+"))
					{
						if(!actualEsInvalido())
							numeroActual += valor;
						
						System.out.println(numeroActual);
					}
					else
						if(!actualEsInvalido())
							guardarDatos(valor);
				}
				else
				{
					if(!actualEsInvalido())
						guardarDatos(valor);
					
				}
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
				if(!actualEsInvalido())
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


	private boolean actualEsInvalido() 
	{
		return numeroActual.equals("-") || numeroActual.equals(".");
	}

	private boolean hayAlgunSigno() 
	{
		return cantSignos() > 0;
	}

	private int cantSignos() 
	{
		return signos.size();
	}

	private String ultimoSigno()
	{
		return signos.get(cantSignos() -1);
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
		if(signos.size() == numeros.size())//Sacamos el ultimo signo por si ingresan una cantidad de signos igual al de numeros para evitar errores de calculos en el futuro
			signos.remove(signos.size() - 1);
		
		//Resolvemos operadores primarios * y /
		for(int i=0; i < signos.size(); i++)
		{			
			if(signos.get(i).equals("*")) 
			{
				numeros.set(i, numeros.get(i) * numeros.get(i+1));
				numeros.remove(i+1);
				signos.remove(i);
				i--;
			}
			
			else if(signos.get(i).equals("/"))
			{
				numeros.set(i, numeros.get(i) / numeros.get(i+1));
				numeros.remove(i+1);
				signos.remove(i);
				i--;
			}	
		}
		
		//Resolvemos operadores + y -
		resultado = numeros.get(0);
		numeros.remove(0);		
		
		while(numeros.size() != 0) 
		{
			if(signos.get(0).equals("+")) 
				sumar(numeros.get(0));
				
			if(signos.get(0).equals("-"))
				restar(numeros.get(0));
			
			numeros.remove(0);
			signos.remove(0);
		}
		
	}
	
	private void resetear() //Funcion que resetea todos los datos de la calculadora
	{	
		numeroActual = "";
		resultado = 0;
		
		numeros.clear();
		
		signos.clear();
	}	
	
	private void sumar( double num)
	{
		resultado += num;
	}
	
	private void restar(double num)
	{
		resultado -= num;
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
	
	public float getResultado() 
	{
		return (float) resultado;
	}

	public String getNumeroActual() 
	{
		return numeroActual;
	}	
	
	public String getUltimoSigno()
	{
		return signos.get(signos.size() - 1);
	}
}
