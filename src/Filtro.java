public class Filtro{
	private final static int[][] matriz = new int[3][3];

	public Filtro(String matrizEntera){
		crearFiltro(matrizEntera);
	}

	private static void crearFiltro(String matrizEntera){
		String[] fila = matrizEntera.split(",");
		for(int i = 0;i < matriz.length;i++){
			String[] numero = fila[i].split("\\s+");
			for(int j = 0;j < matriz.length;j++){
				matriz[i][j] = Integer.parseInt(numero[j]);
			}
		}
	}

	public static void mostrarFiltro(){
		for(int i = 0;i < matriz.length;i++){
			for(int j = 0;j < matriz.length;j++){
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void aplicarFiltroImagen(int[][] imagen,int x,int y,int[][] imagenCopia){
		int suma = 0;
		for(int fila = x - 1;fila <= x + 1;fila++){
			for(int col = y - 1;col <= y + 1;col++){
				suma += (imagen[fila][col] * matriz[fila][col]);
			}
		}
		if(suma < 0){
			suma *= -1;
		}
		if(suma > 255){
			suma /= getValorAbsoluto();
		}
		System.out.println(suma);
		imagenCopia[x][y] = suma;
	}

	private static int getValorAbsoluto(){
		int sumaPos = 0;
		int sumaNeg = 0;
		for(int i = 0;i < matriz.length;i++){
			for(int j = 0;j < matriz.length;j++){
				if(matriz[i][j] < 0){
					sumaNeg += matriz[i][j];
				}else{
					sumaPos += matriz[i][j];
				}
			}
		}
		return Math.max(sumaPos,sumaNeg);
	}
}
