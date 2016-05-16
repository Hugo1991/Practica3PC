/**
 * 
 * @author Hugo
 * 
 *         Clase para aplicar el filtro sobre una posicion de la matriz.
 */
public class Filtro {
	private final static int[][] matriz = new int[3][3];

	public Filtro(String matrizEntera) {
		crearFiltro(matrizEntera);
	}

	/**
	 * metodo que utilizaremos en el constructor
	 * 
	 * @param matrizEntera
	 */
	private static void crearFiltro(String matrizEntera) {
		String[] fila = matrizEntera.split(",");
		for (int i = 0; i < matriz.length; i++) {
			String[] numero = fila[i].split("\\s+");
			for (int j = 0; j < matriz.length; j++) {
				matriz[i][j] = Integer.parseInt(numero[j]);
			}
		}
	}

	/**
	 * Muestra el filtro
	 */
	public static void mostrarFiltro() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Metodo para aplicar el filtro en la posicion x e y de la matriz auxiliar.
	 * 
	 * @param imagen
	 * @param x
	 * @param y
	 * @param imagenDestino
	 */
	public static void aplicarFiltroPixel(int[][] imagen, int x, int y, int[][] imagenDestino) {
		int suma = 0;
		int posFiltroX = 0;
		// Recorrido de filas
		for (int fila = x - 1; fila <= x + 1; fila++) {
			int posFiltroY = 0;
			// Recorrido de columnas
			for (int col = y - 1; col <= y + 1; col++) {
				// multiplicamos la posicion de la imagen por la posicion de la
				// matriz y lo almacenamos en suma
				suma += (imagen[fila][col] * matriz[posFiltroX][posFiltroY]);
				posFiltroY++;
			}
			posFiltroX++;
		}
		// Cogemos el maximo valor de los positivos o negativos
		suma /= getValorAbsoluto();
		// asignamos el valor absoluto a la posicion x,y de la matriz destino
		imagenDestino[x][y] = Math.abs(suma);
	}

	/**
	 * Recorre la matriz del filtro, suma los numeros positivos y negativos, y
	 * coge el maximo valor sobre los numeros positivos o el valor absoluto de
	 * los negativos
	 * 
	 * @return el valor absoluto
	 */
	private static int getValorAbsoluto() {
		int sumaPos = 0;
		int sumaNeg = 0;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[i][j] < 0)
					sumaNeg += matriz[i][j];
				else
					sumaPos += matriz[i][j];

			}
		}
		sumaNeg = Math.abs(sumaNeg);
		return Math.max(sumaPos, sumaNeg);
	}
}
