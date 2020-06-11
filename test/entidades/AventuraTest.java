package entidades;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class AventuraTest {
	Aventura aventura;
	/**
	 * atributos de aventura
	 */
	Protagonista protagonista;
	Configuracion configuracion;
	List<Ubicacion> ubicacionesAventura;
	AnalizadorDeTexto analizador;
	
	/**
	 * atributos de ubicacionesAventura
	 */
	Ubicacion muelle;
	Ubicacion taberna;
	List<Lugar> lugaresMuelle;
		Lugar sueloMuelle;
	List<Item> itemsSuelo;
		Item barreta;
		Item espejo;
		Item rociadorCervezaRaiz;
		List<String> efectosItemsSuelo;
		List<String> accionItemsSuelo;
	List<Conexion> conexionesMuelle;
		Conexion surMuelle;
	List<Npc> npcsMuelle;
		Npc pirataFantasma;
			List<Trigger> triggersMuelle;
				Trigger pirataFantasmaRociador;
	/**
	* atributos agregados
	*/
				
				

	@Before
	public void setup() {
		
		efectosItemsSuelo = new ArrayList<String>();
		efectosItemsSuelo.add("npcs");
		efectosItemsSuelo.add("item");
		efectosItemsSuelo.add("self");

		accionItemsSuelo = new ArrayList<String>();
		accionItemsSuelo.add("usar");

		barreta = new Item("barreta", Genero.FEMALE, Numero.SINGULAR, accionItemsSuelo, efectosItemsSuelo);
		espejo = new Item("espejo", Genero.MALE, Numero.SINGULAR, accionItemsSuelo, efectosItemsSuelo);
		rociadorCervezaRaiz = new Item("rociador con cerveza de raiz", Genero.MALE, Numero.SINGULAR,
				accionItemsSuelo, efectosItemsSuelo);
		itemsSuelo = new ArrayList<Item>();
		itemsSuelo.add(barreta);
		itemsSuelo.add(rociadorCervezaRaiz);
		itemsSuelo.add(espejo);

		pirataFantasmaRociador = new Trigger("item", "rociador con cerveza de raiz","- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía entusiasmado por tu ofrecimiento... sin embargo,"
				+ " cuando lo rociaste comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció, y las piernas inmediatamente echaron a correr.",
		"remove");
		triggersMuelle = new ArrayList<Trigger>();
		triggersMuelle.add(pirataFantasmaRociador);

		sueloMuelle = new Lugar("suelo", Genero.MALE, Numero.SINGULAR, itemsSuelo, triggersMuelle, null);
		lugaresMuelle = new ArrayList<Lugar>();
		lugaresMuelle.add(sueloMuelle);

		pirataFantasma = new Npc("pirata fantasma", Genero.MALE, Numero.SINGULAR,
				"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar",
				"¡No hay nada que me digas que me haga cambiar de opinión!", triggersMuelle);
		npcsMuelle = new ArrayList<Npc>();
		npcsMuelle.add(pirataFantasma);

		surMuelle = new Conexion(Direccion.SUR, taberna, "pirata fantasma");
		conexionesMuelle = new ArrayList<Conexion>();
		conexionesMuelle.add(surMuelle);

		muelle = new Ubicacion("muelle", Genero.MALE, Numero.SINGULAR, "Estás en un muelle. ", lugaresMuelle,
				npcsMuelle, conexionesMuelle);
		taberna = new Ubicacion("taberna", Genero.FEMALE, Numero.SINGULAR, "Estás en una taberna. ", null,
				null, null);
		
		ubicacionesAventura = new ArrayList<Ubicacion>();
		ubicacionesAventura.add(muelle);	//ubicacion inicial
		ubicacionesAventura.add(taberna);
		aventura = new Aventura(null, ubicacionesAventura, "X-Force3");
	}

	@Test
	public void queDaLaBienvenidaEnLaUbicacionCorrecta() {
		
	}
	
	@Test
	public void queAgarraItemCorrectamente() {
		setup();
		//aventura.describirContexto();
		String entrada = "agarrar rociador con cerveza de raiz y un espejo";
		String salida = null;
		if (aventura.quiereAgarrarItem(entrada) == true) {
			salida = aventura.getPortagonista().describirInventario();
		}
		Assert.assertEquals("En tu inventario hay un rociador con cerveza de raiz.", salida);
	}
	
	@Test
	public void queAgarraSegundoItemCorrectamente() {
		setup();
		this.queAgarraItemCorrectamente();	//se ejecuta otra vez el test
		//aventura.describirContexto();
		String entrada = "agarrar espejo";
		String salida = null;
		if (aventura.quiereAgarrarItem(entrada) == true) {
			salida = aventura.getPortagonista().describirInventario();
		}
		Assert.assertEquals("En tu inventario hay un rociador con cerveza de raiz y un espejo.", salida);
	}
	
	@Test
	public void queCambiaDeUbicacionCorrectamente() {
		setup();
		this.ActivaTriggerDeNpcCorrectamente();
		String entrada = "ir hacia la taberna";
		String salida = null;
		Conexion conexion;
		if ((conexion = aventura.quiereMoverseDeUbicacion(entrada)) != null) {
			salida = aventura.tratarObstaculo(conexion);
		}
		System.out.println(salida);
		// Assert.assertEquals("- '¡No puedes pasar!' El pirata fantasma no te dejará pasar", salida);
	}
	
	@Test
	public void queQuiereMoverseAUbicacionNoExistente() {
		
	}
	
	@Test
	public void queElObstaculoNpcLeImpideCambiarDeUbicacion() {
		
	}
	
	@Test
	public void queElObstaculoLugarLeImpideCambiarDeUbicacion() {
		setup();
		String entrada = "ir hacia el sur";
		String salida = null;
		Conexion conexion;
		if ((conexion = aventura.quiereMoverseDeUbicacion(entrada)) != null) {
			salida = aventura.tratarObstaculo(conexion);
		}
		Assert.assertEquals("- '¡No puedes pasar!' El pirata fantasma no te dejará pasar", salida);
	}
	
	@Test
	public void ActivaTriggerDeNpcCorrectamente() {
		this.queAgarraItemCorrectamente();
		String entrada = "usar rociador con cerveza de raiz en pirata fantasma";
		String salida = null;
		Item item;
		if ((item = aventura.quiereRealizarAccionConItem(entrada)) != null) {
			salida = aventura.realizarAccionConItem(entrada, item);
		}
		Assert.assertEquals("- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía entusiasmado por tu ofrecimiento... sin embargo, cuando lo rociaste comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció, y las piernas inmediatamente echaron a correr.", salida);
		Assert.assertEquals("Estás en un muelle. En el suelo hay una barreta y un espejo. Al sur se puede ir hacia una taberna.", aventura.getPortagonista().getUbicacionActual().describirUbicacion());
	}
	
	@Test
	public void ActivaTriggerDeLugarCorrectamente() {
		
	}

}
