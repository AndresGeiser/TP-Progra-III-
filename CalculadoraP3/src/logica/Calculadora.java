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
		if(!esValido(valor))
			throw new IllegalArgumentException("La calculadora no puede operar con este valor - Lea la documentacion");
		
		if(valor.equals("C"))//RESETEAR
			resetear(); 
	
		else if (valor.equals("<"))//BORRAR
			borrarUltimoValor();
									
		else if(valor.equals("="))//CALCULAR 
			calcular();		
		
		else if(valor.equals("-"))
			agregarSignoMenos(valor);	
		
		
		else if(valor.equals("+") || valor.equals("/") || valor.equals("*"))		
			agregarOperador(valor);
		
		else if(valor.equals(".")) 
			armarDecimal(valor);
		
		else
		{
			if(resultado != 0)  //Vemos si por ultima vez se opero con el "="
				resultado = 0;  
			
			numeroActual += valor;
		
		}
		
		System.out.println(numeroActual);
	}


	private void armarDecimal(String valor) 
	{
		if(!actualEsDecimal()) //Para que no se guarde varios puntos seguidos
			numeroActual += ".";
	}
	
	private boolean actualEsDecimal() 
	{
		return numeroActual.contains(".");
	}

	private boolean esValido(String valor) 
	{
		if(valor.length() > 1) //Por si ingreso un valor con mas de un caracter
			return false;
		
		return "0123456789+-*/C<=.".contains(valor);
	}
	
	private void resetear()
	{	
		numeroActual = "";
		resultado = 0;
		
		numeros.clear();
		
		signos.clear();
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
			operar();
	}

	private void operar() 
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
		
	private void sumar( double num)
	{
		resultado += num;
	}
	
	private void restar(double num)
	{
		resultado -= num;
	}
	
	private void agregarSignoMenos(String valor) 
	{
		if(noHayDatos()) 				//Numero Negativo al comienzo
			numeroActual = valor;
		
		else
		{
			if(resultado != 0) 		 //Vemos si por ultima vez se opero con el "=" 
			{
				numeros.add(resultado);
				resultado = 0 ;
			}
			
			if(hayAlgunSigno()) 
			{
				if(actualEstaVacio() && (ultimoSigno().equals("*") || ultimoSigno().equals("/") || ultimoSigno().equals("+")))
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
	
	private void agregarOperador(String valor) 
	{
		if(resultado != 0)    //Vemos si por ultima vez se opero con el "="
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

	private void guardarDatos(String valor) 
	{
		signos.add(valor);
	
		if(!numeroActual.equals(""))
			numeros.add(Double.parseDouble(numeroActual));
		
		numeroActual = "";
	}
	
	private void reemplazarUltimoSigno(String valor) 
	{
		signos.set(cantSignos() - 1, valor);
	}
	
	public boolean noHayDatos() 
	{
		if(numeroActual.equals("0") && numeros.size() == 0 && cantSignos() == 0 && resultado == 0)
			return true;
		return false;
	}
	
	private boolean actualEsInvalido() 
	{
		return numeroActual.equals("-") || numeroActual.equals(".");
	}

	private boolean actualEstaVacio()
	{
		return numeroActual.equals("");
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
