/**
 * 
 * @author Hugo
 *
 */
public class Imagen {
	private String tipo;
	private int ancho, alto;
	private int valorMax;
	private int[][] matrizImagen;

	/**
	 * Constructor vacio, inicializamos los valores
	 */
	public Imagen() {
		setTipo(null);
		setAncho(0);
		setAlto(0);
		this.matrizImagen = new int[alto][ancho];

	}

	public Imagen(String tipo, int ancho, int alto, int valorMax) {
		super();
		setTipo(tipo);
		setAncho(ancho);
		setAlto(alto);
		setValorMax(valorMax);
	}

	public int getValorMax() {
		return valorMax;
	}

	public void setValorMax(int valorMax) {
		this.valorMax = valorMax;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the ancho
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * @param ancho
	 *            the ancho to set
	 */
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	/**
	 * @return the alto
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * @param alto
	 *            the alto to set
	 */
	public void setAlto(int alto) {
		this.alto = alto;
	}

	/**
	 * @return the matrizImagen
	 */
	public int[][] getMatrizImagen() {
		return matrizImagen;
	}

	/**
	 * @param matrizImagen
	 *            the matrizIzmagen to set
	 */
	public void setMatrizImagen(int[][] matrizImagen) {
		this.matrizImagen = matrizImagen;
	}

	/**
	 * Metodo para mostrar la matriz
	 */
	public void mostrarMatriz() {
		for (int i = 0; i < getAlto(); i++) {
			for (int j = 0; j < getAncho(); j++) {
				System.out.print(matrizImagen[i][j] + " ");
			}
			System.out.println();
		}
	}
}
