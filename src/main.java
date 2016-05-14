import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		Imagen imagenOriginal = new Imagen();
		System.out.println("introduzca la imagen que quieras convertir");
		String ficheroEntrada = new Scanner(System.in).nextLine();
		Fichero.leerFichero(ficheroEntrada, imagenOriginal);
		Filtro f = menu();

		int[][] matrizCopia = new int[imagenOriginal.getAlto()][imagenOriginal.getAncho()];
		// se aplica de manerra concurrente, el filtro sobre la matriz que hemos
		// leido
		long startTime = System.currentTimeMillis();

		for (int x = 1; x < imagenOriginal.getAlto() - 1; x++) {
			for (int y = 1; y < imagenOriginal.getAncho() - 1; y++) {
				Filtro.aplicarFiltroPixel(imagenOriginal.getMatrizImagen(), x, y, matrizCopia);
			}
		}
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
