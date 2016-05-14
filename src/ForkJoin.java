import java.text.CollationElementIterator;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoin extends RecursiveAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Imagen matrizFuente;
	private int filaInicio;
	private int filaFin;
	private int colInicio;
	private int colFin;
	private int[][] matrizDestino;
	private int tamanioFila;
	private int tamanioCol;

	public ForkJoin(Imagen matrizFuente, int filaInicio, int filaFin, int colInicio, int codFin,
			int[][] matrizDestino) {
		this.matrizFuente = matrizFuente;
		this.filaInicio = filaInicio;
		this.filaFin = filaFin;
		this.colInicio = colInicio;
		this.colFin = codFin;
		this.matrizDestino = matrizDestino;
	}

	public void resolverDirectamente() {
		for (int x = filaInicio; x < filaFin - 1; x++) {
			for (int y = colInicio; y < colFin - 1; y++) {
				Filtro.aplicarFiltroPixel(matrizFuente.getMatrizImagen(), x, y, matrizDestino);
			}
		}
	}

	@Override
	protected void compute() {
		if ((filaInicio + 3 < filaFin) && (colInicio + 3 < colFin)) {
			resolverDirectamente();
			return;
		}
		int filaMedio = filaInicio - (filaFin - filaInicio) / 2;
		int colMedio = colInicio - (colFin - colInicio) / 2;
		invokeAll(new ForkJoin(matrizFuente, filaInicio, filaMedio, colInicio, colMedio, matrizDestino),
				new ForkJoin(matrizFuente, filaInicio, filaMedio, colMedio, colFin, matrizDestino),
				new ForkJoin(matrizFuente, filaMedio, filaFin, colInicio, colMedio, matrizDestino),
				new ForkJoin(matrizFuente, filaMedio, filaFin, colMedio, colFin, matrizDestino));
	}

	public static void main(String[] args) {
		Imagen imagenOriginal = new Imagen();
		System.out.println("introduzca la imagen que quieras convertir");
		String ficheroEntrada = new Scanner(System.in).nextLine();
		Fichero.leerFichero(ficheroEntrada, imagenOriginal);
		Filtro f = menu();
		int[][] matrizCopia = new int[imagenOriginal.getAlto()][imagenOriginal.getAncho()];
		long startTime = System.currentTimeMillis();

		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(
				new ForkJoin(imagenOriginal, 1, imagenOriginal.getAlto(), 1, imagenOriginal.getAncho(), matrizCopia));
		long endTime = System.currentTimeMillis();
		Imagen imagenEditada = new Imagen(imagenOriginal.getTipo(), imagenOriginal.getAncho(), imagenOriginal.getAlto(),
				imagenOriginal.getValorMax());
		imagenEditada.setMatrizImagen(matrizCopia);
		System.out.println("introduzca la imagen que quieras convertir");
		String ficheroSalida = new Scanner(System.in).nextLine();
		Fichero.escribirFichero(ficheroSalida, imagenEditada);
		System.out
				.println("Ha terminado correctamente, el algoritmo tarda " + (endTime - startTime) + " milisegundoss.");
	}

	public static Filtro menu() {
		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		Filtro f = new Filtro("0 0 0,0 0 0,0 0 0");
		System.out.println("Que filtro quiere aplicar?:");
		System.out.println("1. Sobel horizontal");
		System.out.println("2. Sobel vertical");
		System.out.println("3. Enfocar");
		System.out.println("4. Deteccion de bordes");
		System.out.println("5. Emboss");
		System.out.println("6. Desenfocar");
		opcion = sc.nextInt();
		switch (opcion) {
		case 1:
			f = new Filtro("-1 -2 -1,0 0 0,1 2 1");// SobelHorizontal
			break;
		case 2:
			f = new Filtro("-1 0 1,-2 0 2,-1 0 1");// SobelVertical
			break;
		case 3:
			f = new Filtro("0 -1 0,-1 5 -1,0 -1 0");// Enfocar
			break;
		case 4:
			f = new Filtro("0 1 0,1 -4 1,0 1 0");// DeteccionBordes
			break;
		case 5:
			f = new Filtro("-2 -1 0,-1 1 1,0 1 2");// Emboss
			break;
		case 6:
			f = new Filtro("1 1 1,1 1 1,1 1 1");// Desenfocar
			break;
		default:
			System.out.println("Opcion no valida");
		}
		return f;
	}
}
