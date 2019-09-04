package logica;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculadoraTest {

	@Test
	public void testObtenerValor() 
	{
		Calculadora calculadora = new Calculadora();
		
		calculadora.obtenerValor("3");
		calculadora.obtenerValor("3");
		
		assertEquals("33",calculadora.getNumeroActual());
	}
	
	@Test
	public void sumar()
	{
		Calculadora calculadora = new Calculadora();
		
		calculadora.obtenerValor("33");
		calculadora.obtenerValor("+");
		calculadora.obtenerValor("2");
		calculadora.obtenerValor("=");

		assertTrue(35.0 == calculadora.getResultado());
	}

	@Test
	public void restar()
	{
		Calculadora calculadora = new Calculadora();
		
		calculadora.obtenerValor("3");
		calculadora.obtenerValor("-");
		calculadora.obtenerValor("2");
		calculadora.obtenerValor("=");
		
		assertTrue(1 == calculadora.getResultado());
	}
	
	@Test
	public void multiplicar()
	{
		Calculadora calculadora = new Calculadora();
		
		calculadora.obtenerValor("2");
		calculadora.obtenerValor("*");
		calculadora.obtenerValor("5");
		calculadora.obtenerValor("=");
		
		assertTrue(10 == calculadora.getResultado());
	}
	
	@Test
	public void dividir()
	{
		Calculadora calculadora = new Calculadora();
		
		calculadora.obtenerValor("16");
		calculadora.obtenerValor("/");
		calculadora.obtenerValor("2");
		calculadora.obtenerValor("=");
		
		assertTrue(8 == calculadora.getResultado());
	}
}
