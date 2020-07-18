package interacciones;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidades.AnalizadorDeTexto;
import entidades.Conexion;
import entidades.Direccion;
import entidades.Genero;
import entidades.Item;
import entidades.Lugar;
import entidades.Npc;
import entidades.Numero;
import entidades.Trigger;
import entidades.Ubicacion;

public class AnalizadorDeTextoTest {

	public AnalizadorDeTexto analizador;

	List<String> efectosItemsSuelo;
	Item barreta;
	Item espejo;
	Item rociadorCervezaRaiz;
	List<Item> itemsSuelo;

	List<String> accionItemsSuelo;

	Lugar sueloMuelle;
	List<Lugar> lugaresMuelle;

	Trigger pirataFantasmaRociador;
	List<Trigger> triggersMuelle;

	Npc pirataFantasma;
	List<Npc> npcsMuelle;

	Ubicacion taberna;
	Conexion surMuelle;
	Ubicacion muelle;
	List<Conexion> conexionesMuelle;

	@Before
	public void setup() {
		this.analizador = new AnalizadorDeTexto();

		this.accionItemsSuelo = new ArrayList<String>();
		this.accionItemsSuelo.add("usar");

		this.barreta = new Item("barreta", Genero.FEMALE, Numero.SINGULAR);
		this.espejo = new Item("espejo", Genero.MALE, Numero.SINGULAR);
		this.rociadorCervezaRaiz = new Item("rociador con cerveza de raíz", Genero.MALE, Numero.SINGULAR);
		this.barreta.setAcciones(accionItemsSuelo);
		this.espejo.setAcciones(accionItemsSuelo);
		this.rociadorCervezaRaiz.setAcciones(accionItemsSuelo);

		this.itemsSuelo = new ArrayList<Item>();
		this.itemsSuelo.add(barreta);
		this.itemsSuelo.add(rociadorCervezaRaiz);
		this.itemsSuelo.add(espejo);

		this.sueloMuelle = new Lugar("suelo", Genero.MALE, Numero.SINGULAR, null);
		this.sueloMuelle.setItems(itemsSuelo);
		this.lugaresMuelle = new ArrayList<Lugar>();
		this.lugaresMuelle.add(sueloMuelle);

		this.pirataFantasmaRociador = new Trigger("item", "rociador con cerveza de raíz", null, null);
		this.triggersMuelle = new ArrayList<Trigger>();
		this.triggersMuelle.add(pirataFantasmaRociador);

		this.pirataFantasma = new Npc("pirata fantasma", Genero.MALE, Numero.SINGULAR,
				"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar");
		this.npcsMuelle = new ArrayList<Npc>();
		this.npcsMuelle.add(pirataFantasma);

		this.taberna = new Ubicacion("taberna", Genero.FEMALE, Numero.SINGULAR, "Estás en una taberna. ");

		this.surMuelle = new Conexion(Direccion.SUR, "taberna", "pirata fantasma");
		this.conexionesMuelle = new ArrayList<Conexion>();
		this.conexionesMuelle.add(surMuelle);

		this.muelle = new Ubicacion("muelle", Genero.MALE, Numero.SINGULAR, "Estás en un muelle.");
		this.muelle.setConexiones(conexionesMuelle);
		this.muelle.setLugares(lugaresMuelle);
		this.muelle.setNpcs(npcsMuelle);
		List<Ubicacion> listaDeUbicaciones = new ArrayList<Ubicacion>();
		listaDeUbicaciones.add(muelle);
		listaDeUbicaciones.add(taberna);
	}

	@Test
	public void queDetectaItem() { // ingresar %barreta%

		String entrada = "barreta";
		Item itemDevuelto = analizador.contieneItem(entrada, this.itemsSuelo);
		Assert.assertEquals(this.barreta, itemDevuelto);
	}

	@Test
	public void queDetectaConexionPuntoCardinal() { // ingresar %taberna% o %sur%

		String entrada = "sur";
		Conexion conexionDevuelta = analizador.contieneConexion(entrada, this.muelle.getConexiones());
		Assert.assertEquals(this.surMuelle, conexionDevuelta);
	}

	@Test
	public void queDetectaConexionUbicacion() { // ingresar %taberna% o %sur%

		String entrada = "taberna";
		Conexion conexionDevuelta = analizador.contieneConexion(entrada, this.muelle.getConexiones());
		Assert.assertEquals(this.surMuelle, conexionDevuelta);
	}

	@Test
	public void queDetectaAccion() {

		String entrada = "usar";
		String accionDevuelta = analizador.contieneAccion(entrada, this.accionItemsSuelo);
		Assert.assertEquals(entrada, accionDevuelta);
	}

	@Test
	public void queDetectaNpc() {

		String entrada = "pirata fantasma";
		Npc npcDevuelto = analizador.contieneObstaculoNpc(entrada, npcsMuelle);
		Assert.assertEquals(entrada, npcDevuelto.getNombre());
	}

	@Test
	public void queDetectaLugar() {

		String entrada = "suelo";
		Lugar lugarDevuelto = analizador.contieneObstaculoLugar(entrada, lugaresMuelle);
		Assert.assertEquals(entrada, lugarDevuelto.getNombre());
	}
}
