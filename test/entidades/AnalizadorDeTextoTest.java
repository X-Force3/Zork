package entidades;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
		
		this.efectosItemsSuelo = new ArrayList<String>();
		this.efectosItemsSuelo.add("npcs");
		this.efectosItemsSuelo.add("item");
		this.efectosItemsSuelo.add("self");

		this.accionItemsSuelo = new ArrayList<String>();
		this.accionItemsSuelo.add("usar");

		this.barreta = new Item("barreta", Genero.FEMALE, Numero.SINGULAR, accionItemsSuelo, efectosItemsSuelo);
		this.espejo = new Item("espejo", Genero.MALE, Numero.SINGULAR, accionItemsSuelo, efectosItemsSuelo);
		this.rociadorCervezaRaiz = new Item("rociador con cerveza de ra�z", Genero.MALE, Numero.SINGULAR,
				accionItemsSuelo, efectosItemsSuelo);
		this.itemsSuelo = new ArrayList<Item>();
		this.itemsSuelo.add(barreta);
		this.itemsSuelo.add(rociadorCervezaRaiz);
		this.itemsSuelo.add(espejo);

		this.sueloMuelle = new Lugar("suelo", Genero.MALE, Numero.SINGULAR, itemsSuelo);
		this.lugaresMuelle = new ArrayList<Lugar>();
		this.lugaresMuelle.add(sueloMuelle);

		this.pirataFantasmaRociador = new Trigger("item", "rociador con cerveza de ra�z", null, null);
		this.triggersMuelle = new ArrayList<Trigger>();
		this.triggersMuelle.add(pirataFantasmaRociador);

		this.pirataFantasma = new Npc("pirata fantasma", Genero.MALE, Numero.SINGULAR,
				"- '�No puedes pasar!' El pirata fantasma no te dejar� pasar",
				"�No hay nada que me digas que me haga cambiar de opini�n!", triggersMuelle, true);
		this.npcsMuelle = new ArrayList<Npc>();
		this.npcsMuelle.add(pirataFantasma);

		this.taberna = new Ubicacion("taberna", Genero.FEMALE, Numero.SINGULAR, "Est�s en una taberna. ", null,
				null, null);

		this.surMuelle = new Conexion(Direccion.SUR, taberna, pirataFantasma);
		this.conexionesMuelle = new ArrayList<Conexion>();
		this.conexionesMuelle.add(surMuelle);

		this.muelle = new Ubicacion("muelle", Genero.MALE, Numero.SINGULAR, "Est�s en un muelle. ", lugaresMuelle,
				npcsMuelle, conexionesMuelle);

		Assert.assertEquals(
				"Est�s en un muelle. En el suelo hay una barreta, un rociador con cerveza de ra�z y un espejo. Hay un pirata fantasma. Al sur se puede ir hacia una taberna.",
				muelle.describirUbicacion());

	}
	
	
//	@Test
//	public void queRecibeEntrada() {
//		Assert.assertEquals("entrada esperada", analizador.recibirEntrada());
//	}
	
	
	
	@Test
	public void queDetectaItem() {	// ingresar %barreta%
		String entrada = analizador.recibirEntrada();
		Item itemDevuelto = analizador.contieneItem(entrada, this.itemsSuelo);
		Assert.assertEquals(this.barreta , itemDevuelto);
	}
	
	@Test
	public void queDetectaConexion() {	// ingresar %taberna%  o  %sur%
		String entrada = analizador.recibirEntrada();
		Conexion conexionDevuelta = analizador.contieneConexion(entrada, this.muelle.getConexiones());
		Assert.assertEquals(this.surMuelle, conexionDevuelta);
	}	

}

