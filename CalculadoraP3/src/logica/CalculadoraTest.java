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
		calculadora.obtenerOperador("1312");
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
		
		assertTrue(1450 == calculadora.getResultado());
	}
	
	
	@Test
	public void unSoloNumeroDecimal() 
	{
		calculadora.obtenerNumero(.46);
		calculadora.calcular();
		
		assertTrue((float)0.46 == calculadora.getResultado());
	}
	
	@Test
	public void unSoloNumeroNegativo() 
	{
		calculadora.obtenerNumero(-99);
		calculadora.calcular();
		
		assertTrue(-99 == calculadora.getResultado());
	}
	

	
	@Test
	public void sumaConNegativoAdelante() 
	{
		calculadora.obtenerNumero(-25);
		calculadora.obtenerOperador("+");
		calculadora.obtenerNumero(20);
		calculadora.calcular();
	
		assertTrue(-5 == calculadora.getResultado());
	}
	
	@Test 
	public void sumarDecimales()
	{
		calculadora.obtenerNumero(2.5);
		calculadora.obtenerOperador("+");
		calculadora.obtenerNumero(2.2);
		calculadora.calcular();
		
		assertTrue((float)4.7 == calculadora.getResultado());
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
	public void restarDecimales()
	{
		calculadora.obtenerNumero(0.5);
		calculadora.obtenerOperador("-");
		calculadora.obtenerNumero(0.4);

		calculadora.calcular();
		
		assertTrue( (float)0.1 == calculadora.getResultado());
	}
	
	
	@Test
	public void restaNegativos()
	{
		calculadora.obtenerNumero(-5);
		calculadora.obtenerOperador("+");
		calculadora.obtenerNumero(-3);
		calculadora.calcular();
		
		assertTrue(-8 == calculadora.getResultado());
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
	public void multiplicarDecimales()
	{
		calculadora.obtenerNumero(2.5);
		calculadora.obtenerOperador("*");
		calculadora.obtenerNumero(2.5);
		calculadora.calcular();
		
		assertTrue((float)6.25 == calculadora.getResultado());
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
		
		assertTrue(12 == calculadora.getResultado());
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
	public void borrarNumero()
	{	
		calculadora.obtenerNumero(255);
		calculadora.borrar();
		calculadora.calcular();
		
		assertTrue(25  == calculadora.getResultado());
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class) //Se testea que el ultimo signo se elimine del array
	public void borrarSigno()
	{
		calculadora.obtenerNumero(5);
		calculadora.obtenerOperador("-");
		calculadora.borrar();
		calculadora.getUltimoSigno();

	}
	
	@Test
	public void simplificar()
	{
		calculadora.obtenerNumero(0.);
		calculadora.calcular();
		
		assertTrue(0 == calculadora.getResultado());
	}
	
	@Test
	public void resetear()
	{
		calculadora.obtenerNumero(123);
		calculadora.reset();
		
		assertTrue(0 == calculadora.getResultado() );
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
