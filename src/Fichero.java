import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
			imagenSalida.setValorMax(max);
			int[][] image = new int[height][width];
			for (int fila = 0; fila < height; ++fila) {
				for (int col = 0; col < width; ++col) {
					int value = scanner.nextInt();
					value = (int) Math.round(((double) value) / max * 255);
					image[fila][col] = value;
				}
			}
			imagenSalida.setMatrizImagen(image);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void escribirFichero(String ficheroSalida, Imagen imagenSalida) {
		try {
			BufferedWriter bw = null;
			if (!ficheroSalida.contentEquals(".pgm"))
				bw = new BufferedWriter(new FileWriter(ficheroSalida + ".pgm"));
			else
				bw = new BufferedWriter(new FileWriter(ficheroSalida));
			bw.write(imagenSalida.getTipo());
			bw.newLine();
			bw.write("#Imagen creada por Hugo Fernandez Visier");
			bw.newLine();
			bw.write(imagenSalida.getAncho() + " " + imagenSalida.getAlto());
			bw.newLine();
			bw.write(String.valueOf(imagenSalida.getValorMax()));

			bw.newLine();
			for (int i = 0; i < imagenSalida.getAlto(); i++) {
				for (int j = 0; j < imagenSalida.getAncho(); j++)
					bw.write(String.valueOf(imagenSalida.getMatrizImagen()[i][j]) + " ");
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean existeFichero(String nombreFichero) {
		if (new File(nombreFichero).exists() && !(new File(nombreFichero).isDirectory())) {
			return true;
		} else {
			System.out.println("el fichero no existe");
			return false;
		}
	}
}
