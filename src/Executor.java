import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Executor implements Runnable{
	private int[][] matrizFuente;
	private int fila;
	private int col;
	private int[][] matrizDestino;

	public Executor(int[][] matrizFuente, int fila, int col, int[][] matrizDestino) {
		super();
		this.matrizFuente = matrizFuente;
		this.fila = fila;
		this.col = col;
		this.matrizDestino = matrizDestino;
	}

	@Override
	public void run() {
		Filtro.aplicarFiltroPixel(matrizFuente, fila, col, matrizDestino);
		
	}
}
