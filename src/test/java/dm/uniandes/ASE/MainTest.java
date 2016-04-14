package dm.uniandes.ASE;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class MainTest {
	
	/**
	 * Metodo principal Prueba 1.
	 */
	@Test
	public void test1() {
		Double resultado = Main.getUpperLimit(0.20, 6);
		assertEquals("La Integral deberia ser 0.55338", 0.55338, resultado, 1e-2);
	}

	/**
	 * Metodo principal Prueba 2.
	 */
	@Test
	public void test2() {
		Double resultado = Main.getUpperLimit(0.45, 15);
		assertEquals("La Integral deberia ser 1.75305", 1.75305, resultado, 1e-2);
	}
	
	/**
	 * Metodo principal Prueba 3.
	 */
	@Test
	public void test3() {
		Double resultado = Main.getUpperLimit(0.495 , 4);
		assertEquals("La Integral deberia ser 4.60409", 4.60409, resultado, 1e-2);
	}

}
