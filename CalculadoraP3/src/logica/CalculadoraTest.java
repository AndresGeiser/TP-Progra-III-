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
	public void calcularSinNumeros() 
	{
		calculadora.calcular();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void obtenerOperadorInvalido()
	{
		calculadora.obtenerOperador("{");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void valorConMasDeUnCaracter() 
	{
		calculadora.obtenerOperador("dl");
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
	
	@Test
	public void obtenerOperadorSuma() 
	{
		calculadora.obtenerNumero(1000);
		calculadora.obtenerOperador("+");
		calculadora.obtenerNumero(450);
		calculadora.calcular();
		
		assertTrue(1000 + 450 == calculadora.getResultado());
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
	public void obtenerOperadorMultiplicacion() 
	{
		calculadora.obtenerNumero(7);
		calculadora.obtenerOperador("*");
		calculadora.obtenerNumero(429);
		calculadora.calcular();
		
		assertTrue(7 * 429 == calculadora.getResultado());
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
	public void ordenDeOperaciones()
	{
		calculadora.obtenerNumero(3);
		calculadora.obtenerOperador("+");
		calculadora.obtenerNumero(3);
		calculadora.obtenerOperador("*");
		calculadora.obtenerNumero(3);
	
		calculadora.calcular();
		
		assertTrue( 3 + 3 * 3 == calculadora.getResultado());
	}
	

	 
	@Test
	public void borrarNumero()
	{	
		calculadora.obtenerNumero(255);
		calculadora.borrar();
		calculadora.calcular();
		
		assertTrue(25  == calculadora.getResultado());
	}
	
	@Test
	public void borrarSigno()
	{
		calculadora.obtenerNumero(5);
		calculadora.obtenerOperador("-");
		calculadora.borrar();
		calculadora.obtenerNumero(6);
		calculadora.calcular();
		
		assertTrue(6 == calculadora.getResultado());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void resetear()
	{
		calculadora.obtenerNumero(45);
		calculadora.obtenerOperador("+");
		calculadora.obtenerNumero(5.28);
		calculadora.obtenerOperador("*");
		calculadora.obtenerNumero(24);
		calculadora.reset();
		calculadora.calcular();
		
	}
	
	@Test
	public void obtenerOperadorYReemplazarUltimoSigno() 
	{
		calculadora.obtenerNumero(33);
		calculadora.obtenerOperador("-");
		calculadora.obtenerOperador("*");
		calculadora.obtenerOperador("+");
		calculadora.obtenerNumero(2);
		calculadora.calcular();
		
		assertTrue(33 + 2 == calculadora.getResultado());
	}

}
