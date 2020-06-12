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
	Ubicacion playa;
	
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
			List<Trigger> triggersPirataFantasma;
				Trigger pirataFantasmaRociador;
	
	/**
	* atributos agregadosa la historia original
	*/
	Lugar cuboHielo;
	Conexion esteMuelle;
	Trigger triggerCuboHielo;
	List<Trigger> triggersMuelle;
	List<String> accionesEspejo;
	
	
	@Before
	public void setup() {
		
		efectosItemsSuelo = new ArrayList<String>();
		efectosItemsSuelo.add("npcs");
		efectosItemsSuelo.add("item");
		efectosItemsSuelo.add("self");

		accionItemsSuelo = new ArrayList<String>();
		accionItemsSuelo.add("usar");
		
		accionesEspejo = new ArrayList<String>();
		accionesEspejo.add("derretir");
		accionesEspejo.add("usar");
		accionesEspejo.add("apuntar");

		barreta = new Item("barreta", Genero.FEMALE, Numero.SINGULAR, accionItemsSuelo, efectosItemsSuelo);
		espejo = new Item("espejo", Genero.MALE, Numero.SINGULAR, accionesEspejo, efectosItemsSuelo);
		rociadorCervezaRaiz = new Item("rociador con cerveza de raiz", Genero.MALE, Numero.SINGULAR,
				accionItemsSuelo, efectosItemsSuelo);
		itemsSuelo = new ArrayList<Item>();
		itemsSuelo.add(barreta);
		itemsSuelo.add(rociadorCervezaRaiz);
		itemsSuelo.add(espejo);


		triggersPirataFantasma = new ArrayList<Trigger>();
		pirataFantasmaRociador = new Trigger("item", "rociador con cerveza de raiz","- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía entusiasmado por tu ofrecimiento... sin embargo,"
				+ " cuando lo rociaste comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció, y las piernas inmediatamente echaron a correr.",
		"remove");
		triggersPirataFantasma.add(pirataFantasmaRociador);
		
		triggerCuboHielo = new Trigger("item", "espejo", "el hielo se esta derritiendo!", "remove");
		triggersMuelle = new ArrayList<Trigger>();
		triggersMuelle.add(triggerCuboHielo);

		sueloMuelle = new Lugar("suelo", Genero.MALE, Numero.SINGULAR, itemsSuelo, null, null);
		cuboHielo = new Lugar("cubo de hielo", Genero.MALE, Numero.SINGULAR, null, triggersMuelle, "no puedes pasar, el cubo de hielo te impide el paso!");
		lugaresMuelle = new ArrayList<Lugar>();
		lugaresMuelle.add(sueloMuelle);
		lugaresMuelle.add(cuboHielo);

		pirataFantasma = new Npc("pirata fantasma", Genero.MALE, Numero.SINGULAR,
				"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar",
				"¡No hay nada que me digas que me haga cambiar de opinión!", triggersPirataFantasma);
		npcsMuelle = new ArrayList<Npc>();
		npcsMuelle.add(pirataFantasma);

		surMuelle = new Conexion(Direccion.SUR, taberna, "pirata fantasma");
		esteMuelle = new Conexion(Direccion.ESTE, playa, "cubo de hielo");
		conexionesMuelle = new ArrayList<Conexion>();
		conexionesMuelle.add(surMuelle);
		conexionesMuelle.add(esteMuelle);

		muelle = new Ubicacion("muelle", Genero.MALE, Numero.SINGULAR, "Estás en un muelle.", lugaresMuelle,
				npcsMuelle, conexionesMuelle);
		taberna = new Ubicacion("taberna", Genero.FEMALE, Numero.SINGULAR, "Estás en una taberna.", null,
				null, null);
		playa = new Ubicacion("playa", Genero.FEMALE, Numero.SINGULAR, "estas en la playa.", null,
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
	public void agarraItemCorrectamente() {
		setup();
		//aventura.describirContexto();
		String entrada = "agarrar rociador con cerveza de raiz";
		String salida = null;
		if (aventura.quiereAgarrarItem(entrada) == true) {
			salida = aventura.getPortagonista().describirInventario();
		}
		Assert.assertEquals("En tu inventario hay un rociador con cerveza de raiz.", salida);
	}
	
	@Test
	public void agarraSegundoItemCorrectamente() {
		this.agarraItemCorrectamente();	//se ejecuta otra vez el test
		//aventura.describirContexto();
		String entrada = "agarrar espejo";
		String salida = null;
		if (aventura.quiereAgarrarItem(entrada) == true) {
			salida = aventura.getPortagonista().describirInventario();
		}
		Assert.assertEquals("En tu inventario hay un rociador con cerveza de raiz y un espejo.", salida);
	}
	
	@Test
	public void cambiaDeUbicacionCorrectamente() {
		this.activaTriggerDeNpcCorrectamente();
		String entrada = "ir hacia la taberna";
		String salida = null;
		Conexion conexion;
		if ((conexion = aventura.quiereMoverseDeUbicacion(entrada)) != null) {
			salida = aventura.tratarObstaculo(conexion);
		}
		//System.out.println(salida);
		Assert.assertEquals("Estás en una taberna.", salida);
	}
	
	@Test
	public void quiereMoverseAUbicacionNoExistente() {
		setup();
		String entrada = "ir hacia el norte";
		String salida = null;
		Conexion conexion;
		if ((conexion = aventura.quiereMoverseDeUbicacion(entrada)) != null) {
			salida = aventura.tratarObstaculo(conexion);
		}
		else {
			salida = "No entendí lo que me dijiste...";
		}
		Assert.assertEquals("No entendí lo que me dijiste...", salida);
	}
	
	@Test
	public void elObstaculoNpcLeImpideCambiarDeUbicacion() {
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
	public void elObstaculoLugarLeImpideCambiarDeUbicacion() {
		setup();
		String entrada = "ir hacia el este";
		String salida = null;
		Conexion conexion;
		if ((conexion = aventura.quiereMoverseDeUbicacion(entrada)) != null) {
			salida = aventura.tratarObstaculo(conexion);
		}
		Assert.assertEquals("no puedes pasar, el cubo de hielo te impide el paso!", salida);
	}
	
	@Test
	public void activaTriggerDeNpcCorrectamente() {
		this.agarraItemCorrectamente();
		String entrada = "usar rociador con cerveza de raiz en pirata fantasma";
		String salida = null;
		Item item;
		if ((item = aventura.quiereRealizarAccionConItem(entrada)) != null) {
			salida = aventura.realizarAccionConItem(entrada, item);
		}
		Assert.assertEquals("- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía entusiasmado por tu ofrecimiento... sin embargo, cuando lo rociaste comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció, y las piernas inmediatamente echaron a correr.", salida);
		Assert.assertEquals("Estás en un muelle. En el suelo hay una barreta y un espejo. hay un cubo de hielo. Al sur se puede ir hacia una taberna. Al este se puede ir hacia una playa.", aventura.getPortagonista().getUbicacionActual().describirUbicacion2());
	}
	
	@Test
	public void activaTriggerDeLugarCorrectamente() {
		this.agarraSegundoItemCorrectamente();
		//System.out.println(aventura.getPortagonista().getUbicacionActual().describirUbicacion2());
		String entrada = "derretir cubo de hielo con el espejo";
		String salida = null;
		Item item;
		if ((item = aventura.quiereRealizarAccionConItem(entrada)) != null) {
			salida = aventura.realizarAccionConItem(entrada, item);
		}
		//System.out.println(salida);
		//System.out.println(aventura.getPortagonista().getUbicacionActual().describirUbicacion2());
		Assert.assertEquals("el hielo se esta derritiendo!", salida);
		Assert.assertEquals("Estás en un muelle. En el suelo hay una barreta. Hay un pirata fantasma. Al sur se puede ir hacia una taberna. Al este se puede ir hacia una playa.", aventura.getPortagonista().getUbicacionActual().describirUbicacion2());
	}

}
