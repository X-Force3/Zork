package entidades;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class UbicacionTest {

	@Test
	public void mostrarDescripcionCompletaMuelle() {

		List<String> efectosItemsSuelo = new ArrayList<String>();
		efectosItemsSuelo.add("npcs");
		efectosItemsSuelo.add("item");
		efectosItemsSuelo.add("self");

		List<String> accionItemsSuelo = new ArrayList<String>();
		accionItemsSuelo.add("usar");

		Item barreta = new Item("barreta", Genero.FEMALE, Numero.SINGULAR, accionItemsSuelo, efectosItemsSuelo);
		Item espejo = new Item("espejo", Genero.MALE, Numero.SINGULAR, accionItemsSuelo, efectosItemsSuelo);
		Item rociadorCervezaRaiz = new Item("rociador con cerveza de raíz", Genero.MALE, Numero.SINGULAR,
				accionItemsSuelo, efectosItemsSuelo);
		List<Item> itemsSuelo = new ArrayList<Item>();
		itemsSuelo.add(barreta);
		itemsSuelo.add(rociadorCervezaRaiz);
		itemsSuelo.add(espejo);

		Trigger pirataFantasmaRociador = new Trigger("item", "rociador con cerveza de raíz", null, null);
		List<Trigger> triggersMuelle = new ArrayList<Trigger>();
		triggersMuelle.add(pirataFantasmaRociador);

		Lugar sueloMuelle = new Lugar("suelo", Genero.MALE, Numero.SINGULAR, itemsSuelo, triggersMuelle, null);
		List<Lugar> lugaresMuelle = new ArrayList<Lugar>();
		lugaresMuelle.add(sueloMuelle);

		Npc pirataFantasma = new Npc("pirata fantasma", Genero.MALE, Numero.SINGULAR,
				"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar",
				"¡No hay nada que me digas que me haga cambiar de opinión!", triggersMuelle);
		List<Npc> npcsMuelle = new ArrayList<Npc>();
		npcsMuelle.add(pirataFantasma);

		Ubicacion taberna = new Ubicacion("taberna", Genero.FEMALE, Numero.SINGULAR, "Estás en una taberna. ", null,
				null, null);

		Conexion surMuelle = new Conexion(Direccion.SUR, taberna, "pirata fantasma");
		List<Conexion> conexionesMuelle = new ArrayList<Conexion>();
		conexionesMuelle.add(surMuelle);

		Ubicacion muelle = new Ubicacion("muelle", Genero.MALE, Numero.SINGULAR, "Estás en un muelle. ", lugaresMuelle,
				npcsMuelle, conexionesMuelle);

		Assert.assertEquals(
				"Estás en un muelle. En el suelo hay una barreta, un rociador con cerveza de raíz y un espejo. Hay un pirata fantasma. Al sur se puede ir hacia una taberna.",
				muelle.describirUbicacion());
	}

	@Test
	public void mostrarDescripcionCompletaAula() {

		List<String> efectosItemsMesas = new ArrayList<String>();
		efectosItemsMesas.add("npcs");
		efectosItemsMesas.add("item");
		efectosItemsMesas.add("self");

		List<String> accionesItemsMesas = new ArrayList<String>();
		accionesItemsMesas.add("entregar");

		Item lapiceras = new Item("lapiceras", Genero.FEMALE, Numero.PLURAL, accionesItemsMesas, efectosItemsMesas);
		Item hojas = new Item("hojas", Genero.FEMALE, Numero.PLURAL, accionesItemsMesas, efectosItemsMesas);
		Item trabajosPracticosIngenieria = new Item("trabajos prácticos de Ingeniería", Genero.MALE, Numero.PLURAL,
				accionesItemsMesas, efectosItemsMesas);
		List<Item> itemsMesas = new ArrayList<Item>();
		itemsMesas.add(lapiceras);
		itemsMesas.add(hojas);
		itemsMesas.add(trabajosPracticosIngenieria);

		Trigger profesorasTrabajosPracticos = new Trigger("item", "apuntes de materias de Ingeniería", null, null);
		List<Trigger> triggersAulas = new ArrayList<Trigger>();
		triggersAulas.add(profesorasTrabajosPracticos);

		Lugar mesasAulas = new Lugar("mesas", Genero.FEMALE, Numero.PLURAL, itemsMesas, triggersAulas, null);
		List<Lugar> lugaresAulas = new ArrayList<Lugar>();
		lugaresAulas.add(mesasAulas);

		Npc profesorasUniversitarias = new Npc("profesoras universitarias", Genero.FEMALE, Numero.PLURAL,
				"- '¡No puedes pasar!' Las profesoras no te aprobarán la materia si no entregas los trabajos prácticos",
				"¡No hay nada que nos digas que nos haga cambiar de opinión!", triggersAulas);
		List<Npc> npcsAulas = new ArrayList<Npc>();
		npcsAulas.add(profesorasUniversitarias);

		Ubicacion pasillos = new Ubicacion("pasillos", Genero.MALE, Numero.PLURAL,
				"Estás en los pasillos de la UNLaM. ", null, null, null);

		Conexion norteAulas = new Conexion(Direccion.NORTE, pasillos, "profesoras universitarias");
		List<Conexion> conexionesAulas = new ArrayList<Conexion>();
		conexionesAulas.add(norteAulas);

		Ubicacion aulas = new Ubicacion("aulas", Genero.FEMALE, Numero.PLURAL, "Estás en las aulas de la UNLaM. ",
				lugaresAulas, npcsAulas, conexionesAulas);

		Assert.assertEquals(
				"Estás en las aulas de la UNLaM. En las mesas hay lapiceras, hojas y trabajos prácticos de Ingeniería. Hay profesoras universitarias. Al norte se puede ir hacia los pasillos.",
				aulas.describirUbicacion());
	}

}
