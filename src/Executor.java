/**
 * 
 * @author Hugo Clase Executor con el constructor y el metodo RUN
 */
public class Executor implements Runnable {
	private int[][] matrizFuente;
	private int fila;
	private int[][] matrizDestino;

	/**
	 * Constructor
	 * 
	 * @param matrizFuente
	 * @param fila
	 * @param col
	 * @param matrizDestino
	 */
	public Executor(int[][] matrizFuente, int fila, int[][] matrizDestino) {
		super();
		this.matrizFuente = matrizFuente;
		this.fila = fila;
		this.matrizDestino = matrizDestino;
	}

	/**
	 * Aplica el filtro al pixel que le pasamos al constructor
	 */
	@Override
	public void run() {
		for (int j = 1; j < matrizFuente[0].length - 1; j++)
			Filtro.aplicarFiltroPixel(matrizFuente, fila, j, matrizDestino);

	}
}
