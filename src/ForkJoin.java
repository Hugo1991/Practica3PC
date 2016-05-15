import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoin extends RecursiveAction {
	private static final long serialVersionUID = 1L;
	private Imagen matrizFuente;
	private int filaInicio;
	private int filaFin;
	private int colInicio;
	private int colFin;
	private int[][] matrizDestino;

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
		for (int x = filaInicio; x < filaFin - 1; x++)
			for (int y = colInicio; y < colFin - 1; y++)
				Filtro.aplicarFiltroPixel(matrizFuente.getMatrizImagen(), x, y, matrizDestino);
	}

	@Override
	protected void compute() {
		if ((filaFin - filaInicio <= 3) && (colFin - colInicio <= 3)) {
			resolverDirectamente();
			return;
		}
		
		int filaMedio = filaInicio;
		int colMedio = colInicio;
		filaMedio = (filaFin + filaInicio) / 2;
		colMedio = (colFin + colInicio) / 2;
		ForkJoin f1 = new ForkJoin(matrizFuente, filaInicio, filaMedio+1, colInicio, colMedio+1, matrizDestino);
		ForkJoin f2 = new ForkJoin(matrizFuente, filaInicio, filaMedio+1, colMedio-1, colFin, matrizDestino);
		ForkJoin f3 = new ForkJoin(matrizFuente, filaMedio-1, filaFin, colInicio, colMedio+1, matrizDestino);
		ForkJoin f4 = new ForkJoin(matrizFuente, filaMedio-1, filaFin, colMedio-1, colFin, matrizDestino);
		f1.fork();
		f2.fork();
		f3.fork();
		f4.compute();
		f1.join();
		f2.join();
		f3.join();
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Imagen imagenOriginal = new Imagen();
		String ficheroEntrada;
		do {
			System.out.println("introduzca la imagen que quieras convertir");
		} while (!Fichero.existeFichero(ficheroEntrada = new Scanner(System.in).nextLine()));
		Fichero.leerFichero(ficheroEntrada, imagenOriginal);
		menu();
		int[][] matrizCopia = new int[imagenOriginal.getAlto()][imagenOriginal.getAncho()];
		long startTime = System.currentTimeMillis();
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(
				new ForkJoin(imagenOriginal, 1, imagenOriginal.getAlto(), 1, imagenOriginal.getAncho(), matrizCopia));
		long endTime = System.currentTimeMillis();
		Imagen imagenEditada = new Imagen(imagenOriginal.getTipo(), imagenOriginal.getAncho(), imagenOriginal.getAlto(),
				imagenOriginal.getValorMax());
		imagenEditada.setMatrizImagen(matrizCopia);
		System.out.println("introduzca donde quiere guardar la imagen");
		String ficheroSalida = new Scanner(System.in).nextLine();
		Fichero.escribirFichero(ficheroSalida, imagenEditada);
		System.out
				.println("Ha terminado correctamente, el algoritmo tarda " + (endTime - startTime) + " milisegundoss.");
	}

	@SuppressWarnings("resource")
	public static void menu() {
		int opcion;
		do {
			new Filtro("0 0 0,0 0 0,0 0 0");
			System.out.println("Que filtro quiere aplicar?:");
			System.out.println("1. Sobel horizontal");
			System.out.println("2. Sobel vertical");
			System.out.println("3. Enfocar");
			System.out.println("4. Deteccion de bordes");
			System.out.println("5. Emboss");
			System.out.println("6. Desenfocar");
			opcion = new Scanner(System.in).nextInt();
			switch (opcion) {
			case 1:
				new Filtro("-1 -2 -1,0 0 0,1 2 1");// SobelHorizontal
				break;
			case 2:
				new Filtro("-1 0 1,-2 0 2,-1 0 1");// SobelVertical
				break;
			case 3:
				new Filtro("0 -1 0,-1 5 -1,0 -1 0");// Enfocar
				break;
			case 4:
				new Filtro("0 1 0,1 -4 1,0 1 0");// DeteccionBordes
				break;
			case 5:
				new Filtro("-2 -1 0,-1 1 1,0 1 2");// Emboss
				break;
			case 6:
				new Filtro("1 1 1,1 1 1,1 1 1");// Desenfocar
				break;
			default:
				System.out.println("Opcion no valida");
			}
		} while ((opcion < 1) || (opcion > 6));
	}
}
