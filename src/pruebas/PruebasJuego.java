package pruebas;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import entidades.*;
import juego.Constantes;
import juego.Juego;
import juego.ManejoArchivos;

public class PruebasJuego {

	/*
	 * @Test public void describirDondeEstoy() { List locaciones = new
	 * ArrayList<Locacion>(); locaciones.add(new Locacion("Muelle",
	 * Constantes.MASCULINO, Constantes.SINGULAR, )) Aventura aventura = new
	 * Aventura("Bienvenido",new Locacion(""), ); }
	 */

	Aventura aventura;
	List<Locacion> locaciones;
	List<Npc> npcs;
	List<Item> items;

	@Before
	public void traerInformacion() {
	//	aventura = ManejoArchivos.getAventuras().get(0);
		locaciones = ManejoArchivos.getLocaciones();
	//	npcs = ManejoArchivos.getNpcs();
	//	items = ManejoArchivos.getItems();
	}

	@Test
	public void describirAventura() {
		//System.out.println(aventura.getBienvenida());
		//Assert.assertEquals("Bienvenido", aventura.getBienvenida());
	}
	
	@Test
	public void describirLocacion() {
		System.out.println(locaciones.get(0));
	}
}
