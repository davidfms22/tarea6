/**
 * 
 */
package dm.uniandes.ASE;

/**
 * Esta clase contiene diferntes metetodos para realizar un integral con el
 * metodo numerico llamado regla de Simpson
 * 
 * @author David Martinez
 *
 */
public class Integral {

	// Atributos

	private Double lowerLimit;

	private Double upperLimit;

	private Integer dof;

	private Integer num_seg = 20;

	// Contructor
	public Integral(Double upperLimit, Integer dof) {
		this.lowerLimit = 0d;
		this.upperLimit = upperLimit;
		this.dof = dof;
	}

	// Metodos
	/**
	 * Metodo para acceder al valor de la propiedad lowerLimit.
	 * 
	 * @return valor que tiene la porpiedad lowerLimit.
	 */
	public Double getLowerLimit() {
		return lowerLimit;
	}

	/**
	 * Metodo para modificar el valor de la porpiedad lowerLimit.
	 * 
	 * @param lowerLimit:
	 *            El valor que que se desea asignar.
	 */
	public void setLowerLimit(Double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	/**
	 * Metodo para acceder al valor de la propiedad upperLimit.
	 * 
	 * @return valor que tiene la porpiedad upperLimit.
	 */
	public Double getUpperLimit() {
		return upperLimit;
	}

	/**
	 * Metodo para modificar el valor de la porpiedad upperLimit.
	 * 
	 * @param upperLimit:
	 *            El valor que que se desea asignar.
	 */
	public void setUpperLimit(Double upperLimit) {
		this.upperLimit = upperLimit;
	}

	/**
	 * Metodo para acceder al valor de la propiedad dof.
	 * 
	 * @return valor que tiene la porpiedad dof.
	 */
	public Integer getDof() {
		return dof;
	}

	/**
	 * Metodo para modificar el valor de la porpiedad dof.
	 * 
	 * @param dof:
	 *            El valor que que se desea asignar.
	 */
	public void setDof(Integer dof) {
		this.dof = dof;
	}

	/**
	 * Método que realiza el calculo total de la Integración Numérica.
	 * 
	 * @return valor de la integral.
	 */
	public Double calculate() {
		Double p = 0d;
		Double W = this.upperLimit / this.num_seg;
		
		Double numerator = Statistics.gammaFuntion((this.dof + 1) / 2.0);
		Double denominator = (Math.pow((dof * Math.PI), 0.5D) * Statistics.gammaFuntion((this.dof) / 2.0));
		Double term1 = numerator/denominator;

		for (int i = 0; i < this.num_seg + 1; i++) {
			Integer multiplier = 1;

			if (i != 0 && i != this.num_seg)
				multiplier = i % 2 == 0 ? 2 : 4;
			Double terms = calculateFunction(dof, i * W, term1);

			terms = terms * (W / 3) * multiplier;
			terms = Statistics.roundDown(terms, 5);
			p += terms;
		}
		p = Statistics.roundDown(p, 5);
		return p;
	}

	/**
	 * Este método realiza el calculo completo de la función F(x)
	 * 
	 * @param dof
	 *            - Grados de Libertad
	 * @param value
	 *            - valor de x en la función
	 * @return valor calculado
	 */
	public Double calculateFunction(int dof, Double value, Double term1) {
		Double result = 0d;
		result = term1 * (Math.pow(1 / (1 + ((Math.pow(value, 2)) / dof)), (dof + 1) / 2));
		return result;
	}


}
