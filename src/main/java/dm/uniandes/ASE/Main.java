package dm.uniandes.ASE;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

/***********************************************************************************/
/*                                                                                 */
/* Nombre:         David Francisco Martinez Salcedo                                */
/* Fecha:          28/03/2016                                                      */
/* Descripción:    Programa para integrar numericamente una funcion                */
/*                 utilizando la regla de Simpson                                  */
/*                                                                                 */
/***********************************************************************************/

/**
 * Esta clase es la principal o raiz del programa, y desde esta se reliza toda
 * la logica en general por medio de las demas clases
 * 
 * @author David Martinez
 *
 */

public class Main {

	// Metodos

	/**
	 * Metodo principal del programa.
	 * 
	 * @param args:
	 *            lista de argumentos.
	 */
	public static void main(String[] args) {

		port(Integer.valueOf(System.getenv("PORT")));
		staticFileLocation("/public");

		get("/", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("A1", "");
			attributes.put("A2", "");
			attributes.put("A3", "");

			return new ModelAndView(attributes, "index.ftl");
		}, new FreeMarkerEngine());
		
		
		String a1 = doIntegral(1.1, 9).toString();
		String a2 = doIntegral(1.1812, 10).toString();
		String a3 = doIntegral(2.750, 30).toString();
		
		post("/calcular", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("A1", a1);
			attributes.put("A2", a2);
			attributes.put("A3", a3);
			return new ModelAndView(attributes, "result.ftl");
		}, new FreeMarkerEngine());

	}
	
	/**
	 * Realiza la integral, por medio de instancias de la 
	 * clase Integral
	 * 
	 * @param pX:
	 *            limite superior.
	 * @param pDof:
	 *            funcion a intengrar.
	 * @return result: resultado de la integral.
	 */
	public static Double doIntegral (Double pX, Integer pDof){
		Integral integral = new Integral(pX, pDof);
		return integral.calculate();
	}

}
