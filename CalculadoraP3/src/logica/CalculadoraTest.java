package logica;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CalculadoraTest 
{	
	Calculadora calculadora;

	@Before
	public void iniCalculadora() 
	{
		calculadora = new Calculadora();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void valorInvalido()
	{
		calculadora.obtenerValor("k");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void valorConMasDeUnCaracter() 
	{
		calculadora.obtenerValor("1312");
	}
	
	@Test
	public void unSoloNumero() 
	{	
		calculadora.obtenerValor("3");
		calculadora.obtenerValor("4");
		calculadora.obtenerValor("6");
		calculadora.obtenerValor("=");
		
		assertTrue(346 == calculadora.getResultado());
	}
	
	@Test
	public void unSoloNumeroDecimal() 
	{
		calculadora.obtenerValor(".");
		calculadora.obtenerValor("4");
		calculadora.obtenerValor("6");
		calculadora.obtenerValor("=");
		
		assertTrue(0.46 == calculadora.getResultado());
	}
	
	@Test
	public void unSoloNumeroNegativo() 
	{
		calculadora.obtenerValor("-");
		calculadora.obtenerValor("9");
		calculadora.obtenerValor("9");
		calculadora.obtenerValor("=");
		
		assertTrue(-99 == calculadora.getResultado());
	}
	
	@Test
	public void sumar()
	{
		calculadora.obtenerValor("3");
		calculadora.obtenerValor("4");
		calculadora.obtenerValor("+");
		calculadora.obtenerValor("2");
		calculadora.obtenerValor("=");

		assertTrue(36 == calculadora.getResultado());
	}
	
	@Test
	public void sumaConNegativoAdelante() 
	{
		calculadora.obtenerValor("-");
		calculadora.obtenerValor("4");
		calculadora.obtenerValor("6");
		calculadora.obtenerValor("+");
		calculadora.obtenerValor("4");
		calculadora.obtenerValor("0");
		calculadora.obtenerValor("=");
	
		assertTrue(-6 == calculadora.getResultado());
	}
	
	@Test 
	public void sumarDecimales()
	{
		calculadora.obtenerValor("2");
		calculadora.obtenerValor(".");
		calculadora.obtenerValor("5");
		calculadora.obtenerValor("+");
		calculadora.obtenerValor("2");
		calculadora.obtenerValor(".");
		calculadora.obtenerValor("2");
		calculadora.obtenerValor("=");
		
		assertTrue((float)4.7 == calculadora.getResultado());
	}

	@Test
	public void restar()
	{
		calculadora.obtenerValor("3");
		calculadora.obtenerValor("-");
		calculadora.obtenerValor("2");
		calculadora.obtenerValor("=");
		
		assertTrue(1 == calculadora.getResultado());
	}
	
	@Test
	public void restarDecimales()
	{
		calculadora.obtenerValor("0.5");
		calculadora.obtenerValor("-");
		calculadora.obtenerValor("0.4");
		calculadora.obtenerValor("=");
		
		assertTrue( (float)0.1 == calculadora.getResultado());
	}
	
	
	@Test
	public void restaNegativos()
	{
		calculadora.obtenerValor("-5");
		calculadora.obtenerValor("+");
		calculadora.obtenerValor("-3");
		calculadora.obtenerValor("=");
		
		assertTrue(-8 == calculadora.getResultado());
	}
	
	@Test
	public void multiplicar()
	{
		calculadora.obtenerValor("2");
		calculadora.obtenerValor("*");
		calculadora.obtenerValor("5");
		calculadora.obtenerValor("=");
		
		assertTrue(10 == calculadora.getResultado());
	}
	
	@Test
	public void multiplicarDecimales()
	{
		calculadora.obtenerValor("2.5");
		calculadora.obtenerValor("*");
		calculadora.obtenerValor("2.5");
		calculadora.obtenerValor("=");
		
		assertTrue((float)6.25 == calculadora.getResultado());
	}
	
	@Test
	public void ordenDeOperaciones()
	{
		calculadora.obtenerValor("3");
		calculadora.obtenerValor("+");
		
		calculadora.obtenerValor("3");
		calculadora.obtenerValor("*");
		calculadora.obtenerValor("3");
	
		calculadora.obtenerValor("=");
		
		assertTrue(12 == calculadora.getResultado());
	}
	
	@Test
	public void dividir()
	{	
		calculadora.obtenerValor("16");
		calculadora.obtenerValor("/");
		calculadora.obtenerValor("2");
		calculadora.obtenerValor("=");
		
		assertTrue(8 == calculadora.getResultado());
	}
	 
	@Test
	public void borrarNumero()
	{	
		calculadora.obtenerValor("255");
		calculadora.obtenerValor("<");
		
		assertTrue(25  == Double.parseDouble(calculadora.getNumeroActual()));
	}
	
	@Test
	public void borrarSigno()
	{
		calculadora.obtenerValor("5");
		calculadora.obtenerValor("-");
		calculadora.obtenerValor("<");

		
		assertTrue(5 == Double.parseDouble(calculadora.getNumeroActual()));
	}
	
	@Test
	public void simplificar()
	{
		calculadora.obtenerValor("0");
		calculadora.obtenerValor(".");
		calculadora.obtenerValor("=");
		
		assertTrue(0 == calculadora.getResultado());
		
	}
	
	@Test
	public void simplificarPunto()
	{
		calculadora.obtenerValor(".");
		calculadora.obtenerValor("=");
		
		assertTrue(0 == calculadora.getResultado());
	}
	
	@Test
	public void resetear()
	{
		calculadora.obtenerValor("123");
		calculadora.obtenerValor("C");
		
		assertTrue(0 == calculadora.getResultado() );
	}
	
	@Test
	public void cambiarUltimoSigno()
	{
		calculadora.obtenerValor("3");
		calculadora.obtenerValor("+");
		calculadora.obtenerValor("-");
		
		assertEquals("-",calculadora.getUltimoSigno());
	
	}
		

}
