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

		assertEquals("35.0",Double.toString(calculadora.getResultado()));
	}

}
