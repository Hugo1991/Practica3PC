import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Fichero {
		public static void leerFichero(String rutaImagen, Imagen imagenSalida) {
		try {
			Scanner scanner = new Scanner(new File(rutaImagen));
			String linea = scanner.nextLine();
			imagenSalida.setTipo(linea);

			while (!scanner.hasNextInt()) {
				scanner.nextLine();
			}

			int width = scanner.nextInt();
			int height = scanner.nextInt();
			imagenSalida.setAncho(width);
			imagenSalida.setAlto(height);
			int max = scanner.nextInt();
			int[][] image = new int[height][width];
			for (int i = 0; i < height; ++i) {
				for (int j = 0; j < width; ++j) {
					int value = scanner.nextInt();
					value = (int) Math.round(((double) value) / max * 255);
					image[i][j] = value;
				}
			}
			imagenSalida.setMatrizImagen(image);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
