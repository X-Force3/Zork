package descripciones;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import entidades.Conexion;
import entidades.Direccion;
import entidades.Genero;
import entidades.Item;
import entidades.Lugar;
import entidades.Npc;
import entidades.Numero;
import entidades.Trigger;
import entidades.Ubicacion;

public class UbicacionTest {

	@Test
	public void mostrarDescripcionCompletaMuelle() {

		Item barreta = new Item("barreta", Genero.FEMALE, Numero.SINGULAR);
		Item espejo = new Item("espejo", Genero.MALE, Numero.SINGULAR);
		Item rociadorCervezaRaiz = new Item("rociador con cerveza de raíz", Genero.MALE, Numero.SINGULAR);
		List<Item> itemsSuelo = new ArrayList<Item>();
		itemsSuelo.add(barreta);
		itemsSuelo.add(rociadorCervezaRaiz);
		itemsSuelo.add(espejo);

		Trigger pirataFantasmaRociador = new Trigger("item", "rociador con cerveza de raíz", null, null);
		List<Trigger> triggersMuelle = new ArrayList<Trigger>();
		triggersMuelle.add(pirataFantasmaRociador);

		Lugar sueloMuelle = new Lugar("suelo", Genero.MALE, Numero.SINGULAR, null);
		List<Lugar> lugaresMuelle = new ArrayList<Lugar>();
		lugaresMuelle.add(sueloMuelle);
		sueloMuelle.setItems(itemsSuelo);

		Npc pirataFantasma = new Npc("pirata fantasma", Genero.MALE, Numero.SINGULAR,
				"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar");
		List<Npc> npcsMuelle = new ArrayList<Npc>();
		npcsMuelle.add(pirataFantasma);

		Ubicacion taberna = new Ubicacion("taberna", Genero.FEMALE, Numero.SINGULAR, "Estás en una taberna. ");

		Conexion surMuelle = new Conexion(Direccion.SUR, "taberna", "pirata fantasma");
		List<Conexion> conexionesMuelle = new ArrayList<Conexion>();
		conexionesMuelle.add(surMuelle);

		Ubicacion muelle = new Ubicacion("muelle", Genero.MALE, Numero.SINGULAR, "Estás en un muelle.");
		List<Ubicacion> listaDeUbicaciones = new ArrayList<Ubicacion>();
		listaDeUbicaciones.add(muelle);
		listaDeUbicaciones.add(taberna);
		muelle.setConexiones(conexionesMuelle);
		muelle.setLugares(lugaresMuelle);
		muelle.setNpcs(npcsMuelle);

		Assert.assertEquals(
				"Estás en un muelle. En el suelo hay una barreta, un rociador con cerveza de raíz y un espejo.\n"
						+ "Al sur se puede ir hacia un muelle.",
				muelle.describirUbicacion(listaDeUbicaciones));

	}

	@Test
	public void mostrarDescripcionCompletaAula() {

		List<String> efectosItemsMesas = new ArrayList<String>();
		efectosItemsMesas.add("npcs");
		efectosItemsMesas.add("item");
		efectosItemsMesas.add("self");

		List<String> accionesItemsMesas = new ArrayList<String>();
		accionesItemsMesas.add("entregar");

		Item lapiceras = new Item("lapiceras", Genero.FEMALE, Numero.PLURAL);
		Item hojas = new Item("hojas", Genero.FEMALE, Numero.PLURAL);
		Item trabajosPracticosIngenieria = new Item("trabajos prácticos de Ingeniería", Genero.MALE, Numero.PLURAL);
		List<Item> itemsMesas = new ArrayList<Item>();
		itemsMesas.add(lapiceras);
		itemsMesas.add(hojas);
		itemsMesas.add(trabajosPracticosIngenieria);

		Trigger profesorasTrabajosPracticos = new Trigger("item", "apuntes de materias de Ingeniería", null, null);
		List<Trigger> triggersAulas = new ArrayList<Trigger>();
		triggersAulas.add(profesorasTrabajosPracticos);

		Lugar mesasAulas = new Lugar("mesas", Genero.FEMALE, Numero.PLURAL, null);
		List<Lugar> lugaresAulas = new ArrayList<Lugar>();
		lugaresAulas.add(mesasAulas);
		mesasAulas.setItems(itemsMesas);
		
		Npc profesorasUniversitarias = new Npc("profesoras universitarias", Genero.FEMALE, Numero.PLURAL,
				"- '¡No puedes pasar!' Las profesoras no te aprobarán la materia si no entregas los trabajos prácticos");
		List<Npc> npcsAulas = new ArrayList<Npc>();
		npcsAulas.add(profesorasUniversitarias);

		Ubicacion pasillos = new Ubicacion("pasillos", Genero.MALE, Numero.PLURAL, "Estás en los pasillos de la UNLaM.");

		Conexion norteAulas = new Conexion(Direccion.NORTE, "pasillos", "profesoras universitarias");
		List<Conexion> conexionesAulas = new ArrayList<Conexion>();
		conexionesAulas.add(norteAulas);

		Ubicacion aulas = new Ubicacion("aulas", Genero.FEMALE, Numero.PLURAL, "Estás en las aulas de la UNLaM.");
		aulas.setConexiones(conexionesAulas);
		aulas.setLugares(lugaresAulas);
		aulas.setNpcs(npcsAulas);
		
		List<Ubicacion> listaDeUbicaciones = new ArrayList<Ubicacion>();
		listaDeUbicaciones.add(pasillos);
		listaDeUbicaciones.add(aulas);
		Assert.assertEquals(
				"Estás en las aulas de la UNLaM. En las mesas hay lapiceras, hojas y trabajos prácticos de Ingeniería.\n"
						+ "Al norte se puede ir hacia los pasillos.",
				aulas.describirUbicacion(listaDeUbicaciones));
	}
}
