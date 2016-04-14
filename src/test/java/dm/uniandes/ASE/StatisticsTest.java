package dm.uniandes.ASE;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple StatisticsTest.
 */
public class StatisticsTest {

	public ArrayList<Double> lista;
	public ArrayList<Double> result1;
	public ArrayList<Double> result2;

	/**
	 * Prepara el escenario antes de ejecutar las pruebas estadisticas
	 */
	@Before
	public void antesDelTest() {

		this.lista = new ArrayList<Double>();
		lista.add(new Double(2));
		lista.add(new Double(4));
		lista.add(new Double(5));
		lista.add(new Double(2));

	}

	/**
	 * Metodo prueba Sumatoria.
	 */
	@Test
	public void testSumatoria() {

		Double resultado = Statistics.sumatoria(lista);
		assertEquals("La sumatoria deberia ser 13", new Double(13), resultado, 1e-4);
	}

	/**
	 * Metodo prueba Media de un grupo de datos.
	 */
	@Test
	public void testMean() {

		Double resultado = Statistics.mean(lista);
		assertEquals("La media deberia ser 3.25", new Double(3.25), resultado, 1e-4);
	}

	/**
	 * Metodo prueba Varianza.
	 */
	@Test
	public void testVariance() {

		Double resultado = Statistics.variance(lista);
		assertEquals("La varianza deberia ser 0.2241", new Double(0.2241), resultado, 1e-4);
	}
	
	/**
	 * Metodo prueba1 Gamma.
	 */
	@Test
	public void testGamma1() {

		Double resultado = Statistics.gammaFuntion(5.0);
		assertEquals("La varianza deberia ser 24", 24.0, resultado, 1e-4);
	}
	
	/**
	 * Metodo prueba2 Gamma.
	 */
	@Test
	public void testGamma2() {

		Double resultado = Statistics.gammaFuntion(4.5);
		assertEquals("La varianza deberia ser 11.63", 11.63173, resultado, 1e-4);
	}

}
