package entidades;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;



public class ItemTest {

	Item item;
	Npc npc;
	List<String> acciones;
	List<String> efectosSobre;

	@Before
	public void setup() {
		acciones = new ArrayList<String>();
		efectosSobre = new ArrayList<String>();
		
		acciones.add("martilla");
		efectosSobre.add("Pedrito");
		item = new Item("Martillo", Genero.MALE, Numero.SINGULAR, acciones, efectosSobre);
		
	}

	@Test
	public void queSeUtilizaCorrectamente() {
		npc = new Npc("Pedrito", Genero.MALE, Numero.SINGULAR, "HOLIS", "Aguante el millo", null);
		assertTrue(item.realizarAccion(npc));
	}

	
	@Test
	public void seIntentaUtilizarEnObjetivoIncorrecto() {
		npc = new Npc("Messi", Genero.MALE, Numero.SINGULAR, "HOLIS", "Aguante el millo", null);
		assertFalse(item.realizarAccion(npc));
	}
}
