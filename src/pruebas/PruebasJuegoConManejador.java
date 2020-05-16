package pruebas;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import entidades.*;
import juego.Constantes;
import juego.Juego;
import juego.ManejoArchivos;

public class PruebasJuegoConManejador {

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
		//List<Aventura> aventuras = ManejoArchivos.getAventuras();
		//System.out.println(aventuras.get(0).getClass());
		//aventura = aventuras.get(0);
		locaciones = ManejoArchivos.getLocaciones();
		npcs = ManejoArchivos.getNpcs();
		items = ManejoArchivos.getItems();
	}
	
	@Test 
	public void hayInformacion() {
		//Assert.assertNotNull(aventura);
		/*Assert.assertNotNull(locaciones);
		Assert.assertNotNull(npcs);
		Assert.assertNotNull(items);
		System.out.println(locaciones.get(0));
		System.out.println(npcs.get(0));
		System.out.println(items.get(0));*/
	}

	/*@Test
	public void describirAventura() {
		//System.out.println(aventura.getBienvenida());
		//Assert.assertEquals("Bienvenido", aventura.getBienvenida());
	}*/
	
	@Test
	public void describirLocacion() {
		System.out.println(locaciones.get(0).getGenero());
		System.out.println(npcs.get(0));
		System.out.println(items.get(0));
	}
}
