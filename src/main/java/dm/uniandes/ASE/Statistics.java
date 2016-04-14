package dm.uniandes.ASE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Esta clase contiene diferntes metetodos para realizar diferentes calculos
 * estadisticos
 * 
 * @author David Martinez
 *
 */
public final class Statistics {

	// Metodos
	/**
	 * Calculo del promedio de todos los datos que estan en la lista de datos.
	 * 
	 * @param pData:
	 *            lista de datos.
	 * @return result: promedio de la lista de datos.
	 */
	public static Double mean(List<Double> pData) {
		Double result = new Double(0.00);
		result = (sumatoria(pData) / (pData.size()));

		return result;
	}

	/**
	 * Calculo de la desviacion estandar de todos los datos que estan en la
	 * lista de datos.
	 * 
	 * @param pData:
	 *            lista de datos.
	 * @return result: desviacion estandar de la lista de datos.
	 */
	public static Double calculateStandardDeviation(List<Double> pData) {
		Double result = new Double(0.00);
		Double sum = 0.00;
		Double localMean = mean(pData);

		for (int i = 0; i < pData.size(); i++) {
			Double resta = pData.get(i) - (localMean);
			Double raiz = Math.pow(resta, 2);
			sum = sum + raiz;
		}

		result = Math.sqrt(sum / (pData.size() - 1));
		return result;
	}

	/**
	 * Calculo de la regrsion lineal de todos los datos que estan en las listas
	 * de datos.
	 * 
	 * @param pX:
	 *            lista de datos X.
	 * @param pY:
	 *            lista de datos Y.
	 * @return result: regresion lineal de las listas de datos.
	 */
	public static HashMap<String, Double> linearRegression(List<Double> pX, List<Double> pY) {
		int n = pX.size();
		Double xAvg = mean(pX);
		Double yAvg = mean(pY);
		Double xSum = sumatoria(pX);
		Double ySum = sumatoria(pY);
		ArrayList<Double> pXY = new ArrayList<Double>();
		for (int i = 0; i < n; i++) {
			Double num = pX.get(i) * pY.get(i);
			pXY.add(num);
		}
		Double xySum = sumatoria(pXY);
		Double xxSum = sumatoria(squaring(pX));
		Double yySum = sumatoria(squaring(pY));

		Double beta1 = (xySum - (n * xAvg * yAvg)) / (xxSum - n * xAvg * xAvg);
		Double beta0 = yAvg - beta1 * xAvg;
		Double up = (n * xySum) - (xSum * ySum);
		Double down = Math.sqrt(((n * xxSum) - Math.pow(xSum, 2)) * ((n * yySum) - Math.pow(ySum, 2)));
		Double rXY = up / down;
		Double r2 = rXY * rXY;
		Double yK = beta0 + (beta1 * 386);

		HashMap<String, Double> result = new HashMap<String, Double>();
		result.put("beta1", beta1);
		result.put("beta0", beta0);
		result.put("rXY", rXY);
		result.put("r2", r2);
		result.put("yK", yK);

		return result;
	}

	/**
	 * Calculo de la sumatoria de todos los datos que estan en la lista de
	 * datos.
	 * 
	 * @param pData:
	 *            lista de datos.
	 * @return result: desviacion estandar de la lista de datos.
	 */
	public static Double sumatoria(List<Double> pData) {
		Double result = 0d;
		for (int i = 0; i < pData.size(); i++) {
			result += pData.get(i);
		}
		return result;
	}

	/**
	 * Recorre todos los elementos de una lista y los eleva al cuadrado
	 * 
	 * @param pData:
	 *            lista de datos.
	 * @return result: lista de datos elevados al cuadrado.
	 */
	public static List<Double> squaring(List<Double> pData) {
		for (int i = 0; i < pData.size(); i++) {
			Double sqrt = pData.get(i) * pData.get(i);
			pData.set(i, sqrt);
		}
		return pData;
	}

	/**
	 * metdodo para deficinir los digitos de precicion de una lista de numeros
	 * dobles
	 * 
	 * @param pNumber:
	 *            lista de numeros.
	 * @param pDigit:
	 *            cantidad de digitos de precision.
	 * @return result: lista de datos ajustados con los digitos definidos.
	 */
	public static List<Double> roundDownList(List<Double> pNumber, int pDigit) {
		ArrayList<Double> result = new ArrayList<Double>();
		int decimal = (int) Math.pow(10, pDigit);
		for (int i = 0; i < pNumber.size(); i++) {
			Double num = Math.rint(pNumber.get(i) * decimal) / decimal;
			result.add(num);
		}

		return result;
	}

	/**
	 * Recorre todos los elementos de una lista y los iguala a el valor de su
	 * logaritmo
	 * 
	 * @param pData:
	 *            lista de datos.
	 * @return result: lista de datos igualados a su logaritmo.
	 */
	public static List<Double> logaritList(List<Double> pData) {
		ArrayList<Double> result = new ArrayList<Double>();
		Double log = 0d;
		for (int i = 0; i < pData.size(); i++) {
			log = Math.log(pData.get(i));
			result.add(log);
		}
		return result;
	}

	/**
	 * Calcula la varianza de los datos que se pasan en la lista de datos
	 * 
	 * @param pData:
	 *            lista de datos.
	 * @return result: valor de la varianza calculada.
	 */
	public static Double variance(List<Double> pData) {
		Double result = new Double(0.00);
		Double avg = new Double(0.00);
		Double sum = new Double(0.00);
		Double n = new Double(0.00);
		n = (double) pData.size();
		ArrayList<Double> loglist = (ArrayList<Double>) logaritList(pData);
		avg = mean(loglist);

		for (int i = 0; i < n; i++) {
			loglist.set(i, (loglist.get(i) - avg));
		}
		sum = sumatoria(squaring(loglist));
		result = sum / (n - 1);
		return result;
	}

	/**
	 * Recorre todos los elementos de una lista y los iguala a el valor de su
	 * antilogaritmo
	 * 
	 * @param pData:
	 *            lista de datos.
	 * @return result: lista de datos igualados a su antilogaritmo.
	 */
	public static List<Double> antiLogaritList(List<Double> pData) {
		ArrayList<Double> result = new ArrayList<Double>();
		Double exp = 0d;
		for (int i = 0; i < pData.size(); i++) {
			exp = Math.exp(pData.get(i));
			result.add(exp);
		}
		return result;
	}

	/**
	 * Realiza la funcion gamma para un valor de x dado
	 * 
	 * @param pX:
	 *            funcion.
	 * @return result: resultado de la funcion gamma.
	 */
	public static Double gammaFuntion(Double pX) {
		if (pX % 1 == 0.0) {
			return factorial(pX - 1.0);
		} else {
			return fractionalFactorial(pX - 1.0);
		}
	}
	
	public static Double factorial(Double pX){ 
		if(pX==0){ 
			return 1.0; 
		} else{ 
			return factorial(pX-1.0)*pX; 
			} 
	}
	
	public static Double fractionalFactorial(Double pX){ 
		if(pX==0.50){ 
			return (Double) 0.50*(Math.sqrt(Math.PI)); 
		} else{ 
			return fractionalFactorial(pX-1.0)*(pX); 
			} 
	}
	
	/**
	 * metdodo para deficinir los digitos de precicion de una lista de numeros
	 * dobles
	 * 
	 * @param pNumber:
	 *            lista de numeros.
	 * @param pDigit:
	 *            cantidad de digitos de precision.
	 * @return result: lista de datos ajustados con los digitos definidos.
	 */
	public static Double roundDown(Double pNumber, int pDigit) {
		Double result = 0d;
		int decimal = (int) Math.pow(10, pDigit);
		result = Math.rint(pNumber * decimal) / decimal;

		return result;
	}

}
