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

		assertTrue(35 == calculadora.getResultado());
	}
	
	@Test 
	public void sumarDecimales()
	{
		Calculadora calculadora = new Calculadora();
		
		calculadora.obtenerValor("2.5");
		calculadora.obtenerValor("+");
		calculadora.obtenerValor("2.2");
		calculadora.obtenerValor("=");
		
		assertTrue((float)4.7 == calculadora.getResultado());
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
	public void restarDecimales()
	{
		Calculadora calculadora = new Calculadora();
		
		calculadora.obtenerValor("0.5");
		calculadora.obtenerValor("-");
		calculadora.obtenerValor("0.4");
		calculadora.obtenerValor("=");
		
		assertTrue( (float)0.1 == calculadora.getResultado());
	}
	
	
	@Test
	public void restaNegativos()
	{
		Calculadora calculadora = new Calculadora();
		calculadora.obtenerValor("-5");
		calculadora.obtenerValor("+");
		calculadora.obtenerValor("-3");
		calculadora.obtenerValor("=");
		
		assertTrue(-8 == calculadora.getResultado());
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
	 
	@Test
	public void borrarElementos()
	{
		Calculadora calculadora = new Calculadora();
		
		calculadora.obtenerValor("255");
		calculadora.obtenerValor("<");
		
		assertTrue(25  == Double.parseDouble(calculadora.getNumeroActual()));
	}
	
	@Test
	public void simplificar()
	{
		Calculadora calculadora = new Calculadora();
		
		calculadora.obtenerValor("0");
		calculadora.obtenerValor(".");
		calculadora.obtenerValor("=");
		
		assertTrue(0 == calculadora.getResultado());
		
	}
	
	@Test
	public void simplificarPunto()
	{
		Calculadora calculadora = new Calculadora();
		
		calculadora.obtenerValor(".");
		calculadora.obtenerValor("=");
		
		assertTrue(0 == calculadora.getResultado());
	}
	
	@Test
	public void resetear()
	{
		Calculadora calculadora = new Calculadora();
		
		calculadora.obtenerValor("123");
		calculadora.obtenerValor("C");
		
		assertTrue(0 == calculadora.getResultado() );
	}
	
	@Test
	public void cambiarUltimoSigno()
	{
		Calculadora calculadora = new Calculadora();
		
		calculadora.obtenerValor("3");
		calculadora.obtenerValor("+");
		calculadora.obtenerValor("-");
		
		assertEquals("-",calculadora.getUltimoSigno());
	
	}
	

}

