public class Imagen{
	String tipo;
	int ancho,alto;
	int[][] matrizImagen;

	/**
	 * @return the tipo
	 */
	public String getTipo(){
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo){
		this.tipo = tipo;
	}

	/**
	 * @return the ancho
	 */
	public int getAncho(){
		return ancho;
	}

	/**
	 * @param ancho the ancho to set
	 */
	public void setAncho(int ancho){
		this.ancho = ancho;
	}

	/**
	 * @return the alto
	 */
	public int getAlto(){
		return alto;
	}

	/**
	 * @param alto the alto to set
	 */
	public void setAlto(int alto){
		this.alto = alto;
	}

	/**
	 * @return the matrizImagen
	 */
	public int[][] getMatrizImagen(){
		return matrizImagen;
	}

	/**
	 * @param matrizImagen the matrizImagen to set
	 */
	public void setMatrizImagen(int[][] matrizImagen){
		this.matrizImagen = matrizImagen;
	}

	public void mostrarMatriz(){
		for(int i = 0;i < 18071;i++){
			for(int j = 0;j < 17;j++){
				System.out.print(getMatrizImagen()[i][j] + " ");
			}
			System.out.println();
		}
	}
}
