import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		Imagen imagenOriginal = new Imagen();
		Imagen imagenCopia=new Imagen();
		int[][] matrizTransformar = { { 221, 198, 149 }, { 205, 147, 173 }, { 149, 170, 222 } };
		
		// Filtro.mostrarFiltro();
		Filtro f = menu();
		Fichero.leerFichero("imagenes/totem.ascii.pgm", imagenOriginal);
		//imagenOriginal.mostrarMatriz();
		int[][] matrizCopia = new int[imagenOriginal.getAlto()][imagenOriginal.getAncho()];
		// se aplica de manerra concurrente, el filtro sobre la matriz que hemos
		// leido
		for (int i = 0; i < imagenOriginal.getAlto(); i++)
			for (int j = 0; j < imagenOriginal.getAncho(); j++){
				if((i>0&&i<imagenOriginal.getAlto()-1 )&&(j>0&&j<imagenOriginal.getAncho()-1))
					matrizCopia[i][j]=Filtro.aplicarFiltroPixel(imagenOriginal.getMatrizImagen(), i, j);
				else
					matrizCopia[i][j]=0;
			}
		System.out.println(matrizCopia[1][2]);
		imagenCopia.setMatrizImagen(matrizCopia);

		imagenCopia.mostrarMatriz();
		// se solicita al usuario el nombre del fichero de salida y se guarda la
		// imagen filtrado en ficho fichero con el
		// formato PGM antes descrito
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
