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

	public static void aplicarFiltroPixel(int[][] imagen,int x,int y, int [][]imagenDestino){
		int suma = 0;
		int posFiltroX=0;
		for(int fila = x - 1;fila <= x + 1;fila++){
			int posFiltroY=0;
			for(int col = y - 1;col <= y + 1;col++){
				suma += (imagen[fila][col] * matriz[posFiltroX][posFiltroY]);
				posFiltroY++;
			}
			posFiltroX++;
		}
		
		if(suma > 255){
			suma /= Math.abs(getValorAbsoluto());
		}
		imagenDestino[x][y]=suma;
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
		return Math.max(sumaPos,Math.round(sumaNeg));
	}
}
