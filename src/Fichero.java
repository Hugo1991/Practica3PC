import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author Hugo
 *
 */
public class Fichero {
	/**
	 * Recorre el fichero y almacena los valores almacenados en el objeto imagen
	 * 
	 * @param rutaImagen
	 * @param imagenSalida
	 */
	@SuppressWarnings("resource")
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
			// Recorre la matriz del fichero y lo almacena en la misma posicion
			// de la matriz
			for (int fila = 0; fila < height; ++fila) {
				for (int col = 0; col < width; ++col) {
					int value = scanner.nextInt();
					value = (int) Math.round(((double) value) / max * 255);
					image[fila][col] = value;
				}
			}
			imagenSalida.setMatrizImagen(image);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para escribir en un fichero, le pasamos el nombre del fichero, el
	 * objeto imagen que queremos almacenar en el fichero, y los milisegundos,
	 * ya que lo utilizare para almacenarlo en los comentarios de la imagen
	 * 
	 * @param ficheroSalida
	 * @param imagenSalida
	 * @param milis
	 */
	public static void escribirFichero(String ficheroSalida, Imagen imagenSalida, long milis) {
		try {
			BufferedWriter bw = null;
			if (!ficheroSalida.substring(ficheroSalida.length() - 3, ficheroSalida.length()).equals("pgm"))
				bw = new BufferedWriter(new FileWriter(ficheroSalida + ".pgm"));
			else
				bw = new BufferedWriter(new FileWriter(ficheroSalida));
			bw.write(imagenSalida.getTipo());
			bw.newLine();
			bw.write("#Imagen creada por Hugo Fernandez Visier, ha tardado " + milis + " milisegundos");
			bw.newLine();
			bw.write(imagenSalida.getAncho() + " " + imagenSalida.getAlto());
			bw.newLine();
			bw.write(String.valueOf(imagenSalida.getValorMax()));

			bw.newLine();
			// Escribe la matriz
			for (int i = 0; i < imagenSalida.getAlto(); i++) {
				for (int j = 0; j < imagenSalida.getAncho(); j++)
					bw.write(String.valueOf(imagenSalida.getMatrizImagen()[i][j]) + " ");
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Comprueba si existe un fichero o es una carpeta
	 * 
	 * @param nombreFichero
	 * @return
	 */
	public static boolean existeFichero(String nombreFichero) {
		if (new File(nombreFichero).exists() && !(new File(nombreFichero).isDirectory())) {
			return true;
		} else {
			System.out.println("el fichero no existe");
			return false;
		}
	}
}
