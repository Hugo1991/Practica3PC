import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Fichero{
	public static void leerFichero(String rutaImagen,Imagen imagenSalida){
		FileReader f;
		int[][] matrizImagen = null;
		int i = 0;
		try{
			f = new FileReader(rutaImagen);
			BufferedReader b = new BufferedReader(f);
			String linea = b.readLine();
			imagenSalida.setTipo(linea);
			linea = b.readLine();
			while(linea.startsWith("#")){
				linea = b.readLine();
			}
			imagenSalida.setAncho(Integer.parseInt(linea.split("\\s+")[0]));
			imagenSalida.setAlto(Integer.parseInt(linea.split("\\s+")[1]));
			linea = b.readLine();
			while((linea = b.readLine()) != null){
				String[] numeros = (linea.trim().split("\\s+"));
				if(matrizImagen == null){
					matrizImagen = new int[18075][numeros.length];
				}
				for(int j = 0;j < numeros.length;j++){
					matrizImagen[i][j] = Integer.parseInt(numeros[j].trim());
				}
				i++;
			}
			imagenSalida.setMatrizImagen(matrizImagen);
		}catch(FileNotFoundException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException npe){
			System.out.println("error leyendo la fila");
			npe.printStackTrace();
		}catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
