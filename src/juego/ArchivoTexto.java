package juego;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivoTexto {

	private String rutaArchivo;
		
	public ArchivoTexto(String rutaArchivo) {
		
		this.rutaArchivo = rutaArchivo;
		
		try {
			BufferedWriter bufferEscritura = new BufferedWriter(new FileWriter(this.rutaArchivo));
			bufferEscritura.close();
		} catch (IOException excepcion) {
			excepcion.printStackTrace();
		}
	}

	
	public void escribirArchivoOut(String entrada, String salida) {

		try {
			BufferedWriter bufferEscritura = new BufferedWriter(new FileWriter(this.rutaArchivo, true));

			bufferEscritura.write(entrada + "\n" + salida + "\n");
			bufferEscritura.close();
		} catch (IOException excepcion) {
			excepcion.printStackTrace();
		}
	}
}
