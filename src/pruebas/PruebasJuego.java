package pruebas;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import juego.Juego;


public class PruebasJuego {

	Juego juego;

	@Before
	public void leerJson() {
		Gson gson = new Gson();
		try {
			JsonReader reader = new JsonReader(new FileReader("recursos/juego.json"));
			juego = gson.fromJson(reader,Juego.class);
			System.out.println(juego);
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			Assert.assertNotNull(juego);
		}
	}
	
	@Test
	public void asdf() {
		Assert.assertNotNull(juego);
		String genero = juego.getLocaciones().get(0).getGenero();
		System.out.println("\n" + genero);
	}
}
