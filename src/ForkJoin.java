import java.util.concurrent.RecursiveAction;

/**
 * 
 * @author Hugo
 * 
 *         Clase Fork/join con el constructor, el metodo Compute y el metodo
 *         para calcular el caso base
 */
public class ForkJoin extends RecursiveAction {
	private static final long serialVersionUID = 1L;
	private int[][] matrizFuente;
	private int filaInicio;
	private int filaFin;
	private int colInicio;
	private int colFin;
	private int[][] matrizDestino;

	/**
	 * Constructor
	 * 
	 * @param matrizFuente
	 * @param filaInicio
	 * @param filaFin
	 * @param colInicio
	 * @param colFin
	 * @param matrizDestino
	 */
	public ForkJoin(int[][] matrizFuente, int filaInicio, int filaFin, int colInicio, int colFin,
			int[][] matrizDestino) {
		this.matrizFuente = matrizFuente;
		this.filaInicio = filaInicio;
		this.filaFin = filaFin;
		this.colInicio = colInicio;
		this.colFin = colFin;
		this.matrizDestino = matrizDestino;
	}

	/**
	 * recorremos el array y aplicamos el filtro
	 */
	public void resolverDirectamente() {
		for (int x = filaInicio; x < filaFin; x++)
			for (int y = colInicio; y < colFin; y++)
				Filtro.aplicarFiltroPixel(matrizFuente, x, y, matrizDestino);
	}

	/**
	 * calculamos el caso base, en el que si la columna tiene una longitud menor
	 * de 4, calcula el caso base, si tiene una longitud mayor o igual, divide
	 * las filas y las columnas enre 2, crea 4 trabajadores, divide 3 trabajos,
	 * ejecuta 1, y luego une los otros 3 que hemos dividido
	 */
	@Override
	protected void compute() {
		if ((colFin - colInicio < 4)) {
			resolverDirectamente();
			return;
		}
		int filaMedio = (filaFin + filaInicio) / 2;
		int colMedio = (colFin + colInicio) / 2;
		ForkJoin f1 = new ForkJoin(matrizFuente, filaInicio, filaMedio, colInicio, colMedio, matrizDestino);
		ForkJoin f2 = new ForkJoin(matrizFuente, filaInicio, filaMedio, colMedio, colFin, matrizDestino);
		ForkJoin f3 = new ForkJoin(matrizFuente, filaMedio, filaFin, colInicio, colMedio, matrizDestino);
		ForkJoin f4 = new ForkJoin(matrizFuente, filaMedio, filaFin, colMedio, colFin, matrizDestino);
		f1.fork();
		f2.fork();
		f3.fork();
		f4.compute();
		f1.join();
		f2.join();
		f3.join();
	}
}
