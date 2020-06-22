package entidades;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
//import org.junit.Test;

public class ItemTest {

	Item item;
	Npc npc;
	Lugar lugar;
	List<String> acciones;
	List<String> efectosSobre;

	@Before
	public void setup() {
		acciones = new ArrayList<String>();
		efectosSobre = new ArrayList<String>();

		acciones.add("Usar");
		efectosSobre.add("Thanos");
		efectosSobre.add("Suelo");
		efectosSobre.add("Clavo");
		item = new Item("Martillo Thor", Genero.MALE, Numero.SINGULAR, acciones, efectosSobre);

	}

//	@Test
//	public void seUtilizaCorrectamenteSobreNpc() {
//		npc = new Npc("Thanos", Genero.MALE, Numero.SINGULAR, "Thanos, monstruo malvado", "Hola, soy Thanos. ", null);
//		assertTrue(item.realizarAccion(npc, "Usar"));
//	}
//
//	@Test
//	public void seIntentaUtilizarEnNpcIncorrecto() {
//		npc = new Npc("Messi", Genero.MALE, Numero.SINGULAR, "Messi, futbolista", "Hola, soy Messi. ", null);
//		assertFalse(item.realizarAccion(npc, "Usar"));
//	}
//
//	@Test
//	public void seIntentaUtilizarEnNpcCorrectoPeroConAccionIncorrecta() {
//		npc = new Npc("Thanos", Genero.MALE, Numero.SINGULAR, "Thanos, monstruo malvado", "Hola, soy Thanos. ", null);
//		assertFalse(item.realizarAccion(npc, "Derretir"));
//	}
//
//	@Test
//	public void seUtilizaCorrectamenteSobreLugar() {
//		lugar = new Lugar("Suelo", Genero.MALE, Numero.SINGULAR, null, null, null);
//		assertTrue(item.realizarAccion(lugar, "Usar"));
//	}
//
//	@Test
//	public void seIntentaUtilizarEnLugarIncorrecto() {
//		lugar = new Lugar("Mesa", Genero.FEMALE, Numero.SINGULAR, null, null, null);
//		assertFalse(item.realizarAccion(lugar, "Usar"));
//	}
//
//	@Test
//	public void seIntentaUtilizarEnLugarCorrectoPeroConAccionIncorrecta() {
//		lugar = new Lugar("Suelo", Genero.MALE, Numero.SINGULAR, null, null, null);
//		assertFalse(item.realizarAccion(lugar, "Derretir"));
//	}
//	
// Juani: Los tests de Item quedan sin efecto porque el método "realizarAccion" es reemplazado por un método de la clase Aventura.
}
