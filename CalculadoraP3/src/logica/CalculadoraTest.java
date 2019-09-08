package logica;

import static org.junit.Assert.assertTrue;

import javax.management.RuntimeErrorException;

import org.junit.Before;
import org.junit.Test;

public class CalculadoraTest 
{
	private Calculadora calculadora;

	@Before
	public void iniCalculadora() 
	{
		calculadora = new Calculadora();
	}
	
	@Test
	public void obtenerNumero() 
	{
		calculadora.obtenerNumero(15);
		calculadora.calcular();
		
		assertTrue(15 == calculadora.getResultado());
	}
	
	@Test
	public void reemplazarUltimoNumero() 
	{
		calculadora.obtenerNumero(57);
		calculadora.obtenerNumero(105);
		calculadora.calcular();
		
		assertTrue(105 == calculadora.getResultado());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void obtenerOperadorInvalido()
	{
		calculadora.obtenerOperador("{");
	}
	
	@Test
	public void obtenerOperadorSuma() 
	{
		calculadora.obtenerNumero(1005);
		calculadora.obtenerOperador("+");
		calculadora.obtenerNumero(450);
		calculadora.calcular();
		
		assertTrue(1005 + 450 == calculadora.getResultado());
	}
	
	@Test
	public void obtenerOperadorResta() 
	{
		calculadora.obtenerNumero(708);
		calculadora.obtenerOperador("-");
		calculadora.obtenerNumero(480);
		calculadora.calcular();
		
		assertTrue(708 - 480 == calculadora.getResultado());
	}
	
	@Test
	public void obtenerOperadorDivision() 
	{
		calculadora.obtenerNumero(390);
		calculadora.obtenerOperador("/");
		calculadora.obtenerNumero(6);
		calculadora.calcular();
		
		assertTrue(390 / 6 == calculadora.getResultado());
	}
	
	@Test
	public void obtenerOperadorMultiplicacion() 
	{
		calculadora.obtenerNumero(7);
		calculadora.obtenerOperador("*");
		calculadora.obtenerNumero(429);
		calculadora.calcular();
		
		assertTrue(7 * 429 == calculadora.getResultado());
	}
	
	@Test
	public void obtenerOperadorYReemplazar() 
	{
		calculadora.obtenerNumero(33);
		calculadora.obtenerOperador("-");
		calculadora.obtenerOperador("*");
		calculadora.obtenerOperador("+");
		calculadora.obtenerNumero(2);
		calculadora.calcular();
		
		assertTrue(33 + 2 == calculadora.getResultado());
	}
	
	@Test(expected = RuntimeErrorException.class)
	public void calcularSinNumeros() 
	{
		calculadora.calcular();
	}
	
}
