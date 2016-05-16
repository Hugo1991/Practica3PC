import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

/**
 * 
 * @author Hugo
 *
 *         Creamos un ExecutorService creando un ThreadPool con el numero maximo
 *         de procesadores disponibles, luego recorremos la matriz a la largo y
 *         ancho y ejecutamos la clase Executor pasandole los parametros matriz
 *         origen, fila, columna y la matriz auxiliar
 * 
 *         seguidamente nos ejecutará el algoritmo fork/join en el que creamos
 *         un ForkJoinPool y luego ejecutamos el objeto pasandole la clase
 *         Fork/join los parametros matriz origen, inicio fila, fin de fila,
 *         inicio columna, fin columna y matriz auxiliar
 * 
 */
public class Principal {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Imagen imagenOriginal = new Imagen();
		String ficheroEntrada;
		do {
			System.out.println("Introduzca la imagen que quieras convertir");
		} while (!Fichero.existeFichero(ficheroEntrada = new Scanner(System.in).nextLine()));
		Fichero.leerFichero(ficheroEntrada, imagenOriginal);
		menu();
		int[][] matrizCopia = new int[imagenOriginal.getAlto()][imagenOriginal.getAncho()];

		// EXECUTOR
		ExecutorService ejecutor = Executors.newFixedThreadPool((Runtime.getRuntime().availableProcessors()));
		long startTime = System.currentTimeMillis();
		for (int i = 1; i < imagenOriginal.getAlto() - 1; i++)
			for (int j = 1; j < imagenOriginal.getAncho() - 1; j++)
				ejecutor.execute(new Executor(imagenOriginal.getMatrizImagen(), i, j, matrizCopia));

		long endTime = System.currentTimeMillis();
		ejecutor.shutdown();
		while (ejecutor.isTerminated())
			System.out.println("Finalizando hilos...");
		Imagen imagenEditada = new Imagen(imagenOriginal.getTipo(), imagenOriginal.getAncho(), imagenOriginal.getAlto(),
				imagenOriginal.getValorMax());
		imagenEditada.setMatrizImagen(matrizCopia);
		System.out.println("Introduzca donde quiere guardar la imagen con el algoritmo Executor");
		String ficheroSalida = new Scanner(System.in).nextLine();
		Fichero.escribirFichero(ficheroSalida, imagenEditada, endTime - startTime);
		System.out.println("Ha terminado correctamente, el algoritmo tarda " + (endTime - startTime)
				+ " milisegundos con el algoritmo Executor.");

		// FORK/JOIN
		startTime = System.currentTimeMillis();
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(new ForkJoin(imagenOriginal.getMatrizImagen(), 1, imagenOriginal.getAlto() - 1, 1,
				imagenOriginal.getAncho() - 1, matrizCopia));
		endTime = System.currentTimeMillis();
		imagenEditada = new Imagen(imagenOriginal.getTipo(), imagenOriginal.getAncho(), imagenOriginal.getAlto(),
				imagenOriginal.getValorMax());
		imagenEditada.setMatrizImagen(matrizCopia);
		System.out.println("Introduzca donde quiere guardar la imagen con el algoritmo Fork/Join");
		ficheroSalida = new Scanner(System.in).nextLine();
		Fichero.escribirFichero(ficheroSalida, imagenEditada, endTime - startTime);
		System.out.println("Ha terminado correctamente, el algoritmo tarda " + (endTime - startTime)
				+ " milisegundos con el algoritmo FORK / JOIN.");
	}

	/**
	 * Muestra el menu y dependiendo de la opcion crea un filtro
	 */
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
