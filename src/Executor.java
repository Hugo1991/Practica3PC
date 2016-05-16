/**
 * 
 * @author Hugo
 * Clase Executor con el constructor y el metodo RUN
 */
public class Executor implements Runnable{
	private int[][] matrizFuente;
	private int fila;
	private int col;
	private int[][] matrizDestino;

	/**
	 * Constructor
	 * @param matrizFuente
	 * @param fila
	 * @param col
	 * @param matrizDestino
	 */
	public Executor(int[][] matrizFuente, int fila, int col, int[][] matrizDestino) {
		super();
		this.matrizFuente = matrizFuente;
		this.fila = fila;
		this.col = col;
		this.matrizDestino = matrizDestino;
	}

	/**
	 * Aplica el filtro al pixel que le pasamos al constructor
	 */
	@Override
	public void run() {
		Filtro.aplicarFiltroPixel(matrizFuente, fila, col, matrizDestino);
		
	}
}
