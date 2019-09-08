package logica;

import java.util.ArrayList;

import javax.management.RuntimeErrorException;

public class Calculadora 
{
	private ArrayList<Double> numeros;
	private ArrayList<String> signos;
	
	private double resultado;
	
	///////////////CONSTRUCTOR///////////////
	public Calculadora()
	{
		numeros = new ArrayList<Double>();
		signos = new ArrayList<String>();
		resultado = 0;
	}
	
	public void obtenerNumero(double numero) 
	{
		if(numeros.size() > signos.size())                //Si no ingreso un signo despues de un numero, este ultimo es reemplazado por el nuevo
			numeros.set(numeros.size() - 1, numero);
		
		else
			numeros.add(numero);
	}
	
	public void obtenerOperador(String operador) 
	{
		if(!"+-*/".contains(operador))
			throw new IllegalArgumentException("Este metodo solo opera con +, -, *, /");
		
		if(numeros.size() == 0)
			return;
		
		if(ultimaVezSeIngresoSigno())
			reemplazarUltimoSigno(operador);
		
		else
			signos.add(operador);
	}
	
	public void calcular() 
	{
		if(numeros.size() == 0)
			throw new IllegalArgumentException("No hay numeros guardados para calcular");
		
		if(numeros.size() == signos.size()) 
			borrarUltimoSigno();
		
		resolverOperadoresPrimarios();
				
		//Resolvemos operadores + y -
		resultado = numeros.get(0);
		numeros.remove(0);		
		
		while(numeros.size() != 0) 
		{
			if(signos.get(0).equals("+")) 
				resultado += numeros.get(0);
				
			if(signos.get(0).equals("-"))
				resultado -= numeros.get(0);
			
			numeros.remove(0);
			signos.remove(0);
		}
		
	}

	//Calcula primero las multiplicacion y divisiones
	private void resolverOperadoresPrimarios() {
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
	}
	
	public void reset() 
	{
		resultado = 0;
		
		numeros.clear();
		
		signos.clear();
	}
	
	public void borrar()
	{
		if(numeros.size() == 0)
			return;
		
		if(numeros.size() == signos.size()) 
			borrarUltimoSigno();
		
		else
			borrarDigitoUltimoNumero();
		
	}
	
	private void borrarDigitoUltimoNumero() 
	{
		String ultimoNumero;
		
		if(ultimoNumero() % 1 == 0) //Chequeamos que sea entero para no que no borre el .0 
			ultimoNumero = String.valueOf((int) ultimoNumero());
		else
			ultimoNumero = String.valueOf(ultimoNumero());
		
		if(!ultimoNumero.substring(0, ultimoNumero.length() - 1).equals(""))
			numeros.set(numeros.size() - 1, Double.parseDouble(ultimoNumero.substring(0, ultimoNumero.length() - 1)));
				
		else
			numeros.remove(numeros.size() - 1);
	}
	
	private void borrarUltimoSigno() 
	{
		if(hayAlgunSigno())
			signos.remove(signos.size() - 1);
	}
	
	private double ultimoNumero() 
	{
		return numeros.get(numeros.size() - 1);
	}

	private boolean hayAlgunSigno() 
	{
		return cantSignos() > 0;
	}

	private int cantSignos() 
	{
		return signos.size();
	}
	
	private boolean ultimaVezSeIngresoSigno() 
	{
		return signos.size() == numeros.size();
	}
	
	private void reemplazarUltimoSigno(String operador) 
	{
		signos.set(signos.size() - 1, operador);
	}
	
	public float getResultado() 
	{
		return (float) resultado;
	}

	public String getUltimoSigno() 
	{
		return signos.get(signos.size() - 1);
	}	

}
