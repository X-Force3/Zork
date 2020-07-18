package interacciones;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidades.*;

public class ProtagonistaTest {

	Protagonista protagonista;
	Aventura aventura;
	Ubicacion ubicacion;
	Ubicacion taberna;
	Item item;
	Npc pirata;

	@Before
	public void setup() {
		List<Lugar> lugares = new ArrayList<Lugar>();

		List<String> efectosItemsSuelo = new ArrayList<String>();
		efectosItemsSuelo.add("npcs");
		efectosItemsSuelo.add("item");
		efectosItemsSuelo.add("self");

		List<String> accionItemsSuelo = new ArrayList<String>();
		accionItemsSuelo.add("usar");

		Item barreta = new Item("barreta", Genero.FEMALE, Numero.SINGULAR);
		Item espejo = new Item("espejo", Genero.MALE, Numero.SINGULAR);
		Item rociadorCervezaRaiz = new Item("rociador con cerveza de raiz", Genero.MALE, Numero.SINGULAR);
		barreta.setAcciones(accionItemsSuelo);
		espejo.setAcciones(accionItemsSuelo);
		rociadorCervezaRaiz.setAcciones(accionItemsSuelo);
		
		List<Item> itemsSuelo = new ArrayList<Item>();
		itemsSuelo.add(barreta);
		itemsSuelo.add(rociadorCervezaRaiz);
		itemsSuelo.add(espejo);
		Lugar lugar = new Lugar("Casa", Genero.FEMALE, Numero.SINGULAR, null);
		lugar.setItems(itemsSuelo);
		lugares.add(lugar);



		taberna = new Ubicacion("taberna", Genero.FEMALE, Numero.SINGULAR, "Taberna");
		taberna.setLugares(lugares);
		Conexion surMuelle = new Conexion(Direccion.SUR, "taberna", "Messi");
		List<Conexion> conexionesMuelle = new ArrayList<Conexion>();
		conexionesMuelle.add(surMuelle);
		ubicacion = new Ubicacion("Casa", Genero.FEMALE, Numero.SINGULAR, "Casa");
		ubicacion.setConexiones(conexionesMuelle);
		ubicacion.setLugares(lugares);

		List<String> acciones = new ArrayList<String>();
		List<String> efectosSobre = new ArrayList<String>();
		acciones.add("Usar");
		efectosSobre.add("Asustarse");

		item = new Item("Espejo", Genero.MALE, Numero.SINGULAR);
		item.setAcciones(acciones);
		Trigger pirataFantasmaRociador = new Trigger("item", "rociador con cerveza de raiz", null, null);
		List<Trigger> triggersMuelle = new ArrayList<Trigger>();
		triggersMuelle.add(pirataFantasmaRociador);

		protagonista = new Protagonista("Nahuel", ubicacion);

		pirata = new Npc("pirata fantasma", Genero.MALE, Numero.SINGULAR,
				"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar");
		pirata.setTriggers(triggersMuelle);
		pirata.setDialogo("¡No hay nada que me digas que me haga cambiar de opinión!");

	}

	@Test
	public void queNoRecogeUnItemDosVeces() {

		List<String> acciones = new ArrayList<String>();
		List<String> efectosSobre = new ArrayList<String>();
		acciones.add("Usar");
		efectosSobre.add("Asustarse");

		Item item = new Item("Espejo", Genero.MALE, Numero.SINGULAR);
		item.setAcciones(acciones);

		protagonista.anadirItem(item);

		Assert.assertFalse(protagonista.anadirItem(item));
	}

	@Test
	public void queRecogeUnItemDeManeraCorrecta() {

		List<String> acciones = new ArrayList<String>();
		List<String> efectosSobre = new ArrayList<String>();
		acciones.add("Usar");
		efectosSobre.add("Asustarse");

		Item item = new Item("Espejo", Genero.MALE, Numero.SINGULAR);
		item.setAcciones(acciones);

		Assert.assertTrue(protagonista.anadirItem(item));
	}

	@Test
	public void queHableSegunElDiagologoCorrespondiente() {
		Npc messi = new Npc("Messi", Genero.MALE, Numero.SINGULAR, "Messi, futbolista. ");
		messi.setDialogo("Hola, soy Messi.");

		Assert.assertEquals(protagonista.hablar(messi.getDialogo()), "¡Hola Messi!");
	}

}
