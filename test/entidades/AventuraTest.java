package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	Map<String, Ubicacion> ubicaciones;
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
	Trigger triggerCuboHieloCambiaNombre;
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
		accionItemsSuelo.add("derretir");
		
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
		pirataFantasmaRociador = new Trigger("item", "rociador con cerveza de raiz","- '�Me encanta la cerveza de raiz!' El pirata fantasma se ve�a entusiasmado por tu ofrecimiento... sin embargo,"
				+ " cuando lo rociaste comenz� a desintegrarse. La mitad de arriba de su cuerpo se desvaneci�, y las piernas inmediatamente echaron a correr.",
		"remove");
		triggersPirataFantasma.add(pirataFantasmaRociador);
		
		triggerCuboHielo = new Trigger("item", "espejo", "El hielo se est� derritiendo!", "remove");
		triggerCuboHieloCambiaNombre = new Trigger("item", "barreta", "El hielo se est� derritiendo!", "cambiar nombre");
		triggersMuelle = new ArrayList<Trigger>();
		triggersMuelle.add(triggerCuboHielo);
		triggersMuelle.add(triggerCuboHieloCambiaNombre);

		sueloMuelle = new Lugar("suelo", null, Genero.MALE, Numero.SINGULAR, itemsSuelo, null, null);
		cuboHielo = new Lugar("cubo de hielo", "charco de agua", Genero.MALE, Numero.SINGULAR, null, triggersMuelle, "No puedes pasar! El cubo de hielo te impide el paso...");
		lugaresMuelle = new ArrayList<Lugar>();
		lugaresMuelle.add(sueloMuelle);
		lugaresMuelle.add(cuboHielo);

		pirataFantasma = new Npc("pirata fantasma", Genero.MALE, Numero.SINGULAR,
				"- '�No puedes pasar!' El pirata fantasma no te dejar� pasar",
				"�No hay nada que me digas que me haga cambiar de opini�n!", triggersPirataFantasma);
		npcsMuelle = new ArrayList<Npc>();
		npcsMuelle.add(pirataFantasma);

		muelle = new Ubicacion("muelle", Genero.MALE, Numero.SINGULAR, "Est�s en un muelle.", lugaresMuelle,
				npcsMuelle, conexionesMuelle);
		taberna = new Ubicacion("taberna", Genero.FEMALE, Numero.SINGULAR, "Est�s en una taberna.", null,
				null, null);
		playa = new Ubicacion("playa", Genero.FEMALE, Numero.SINGULAR, "Est�s en la playa.", null,
				null, null);
		
		surMuelle = new Conexion(Direccion.SUR, "taberna", "pirata fantasma");
		esteMuelle = new Conexion(Direccion.ESTE, "playa", "cubo de hielo");
		conexionesMuelle = new ArrayList<Conexion>();
		conexionesMuelle.add(surMuelle);
		conexionesMuelle.add(esteMuelle);

		ubicaciones = new HashMap<String, Ubicacion>();
		ubicaciones.put(muelle.getNombre(), muelle);
		ubicaciones.put(taberna.getNombre(), taberna);
		ubicaciones.put(playa.getNombre(), playa);
		
		aventura = new Aventura(null, ubicaciones, new Protagonista("X-Force", muelle));
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
			salida = aventura.getProtagonista().describirInventario();
		}
		Assert.assertEquals("En tu inventario hay un rociador con cerveza de raiz.", salida);
		// agregar que no este mas en el lugar
	}
	
	@Test
	public void agarraSegundoItemCorrectamente() {
		this.agarraItemCorrectamente();	//se ejecuta otra vez el test
		//aventura.describirContexto();
		String entrada = "agarrar espejo";
		String salida = null;
		if (aventura.quiereAgarrarItem(entrada) == true) {
			salida = aventura.getProtagonista().describirInventario();
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
		Assert.assertEquals("Est�s en una taberna.", salida);
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
			salida = "No entend� lo que me dijiste...";
		}
		Assert.assertEquals("No entend� lo que me dijiste...", salida);
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
		Assert.assertEquals("- '�No puedes pasar!' El pirata fantasma no te dejar� pasar", salida);
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
		Assert.assertEquals("No puedes pasar! El cubo de hielo te impide el paso...", salida);
	}
	
	@Test
	public void activaTriggerDeNpcCorrectamente() {
		this.agarraItemCorrectamente();

		String entrada = "usar el rociador con cerveza de raiz en el pirata fantasma";
		String salida = null;
		Item item;
		if ((item = aventura.quiereRealizarAccionConItem(entrada)) != null) {
			salida = aventura.realizarAccionConItem(entrada, item);
		}
		
		Assert.assertEquals("- '�Me encanta la cerveza de raiz!' El pirata fantasma se ve�a entusiasmado por tu ofrecimiento... sin embargo, cuando lo rociaste comenz� a desintegrarse. La mitad de arriba de su cuerpo se desvaneci�, y las piernas inmediatamente echaron a correr.", salida);
		Assert.assertEquals("Est�s en un muelle. En el suelo hay una barreta y un espejo. Hay un cubo de hielo. Al sur se puede ir hacia una taberna. Al este se puede ir hacia una playa.", aventura.describirUbicacion());
	}
	
	@Test
	public void activaTriggerDeLugarCorrectamente() {
		this.agarraSegundoItemCorrectamente();

		String entrada = "derretir el cubo de hielo con el espejo";
		String salida = null;
		Item item;
		if ((item = aventura.quiereRealizarAccionConItem(entrada)) != null) {
			salida = aventura.realizarAccionConItem(entrada, item);
		}

		Assert.assertEquals("El hielo se est� derritiendo!", salida);
		Assert.assertEquals("Est�s en un muelle. En el suelo hay una barreta. Hay un pirata fantasma. Al sur se puede ir hacia una taberna. Al este se puede ir hacia una playa.", aventura.describirUbicacion());
	}
	
	@Test
	public void noActivaTriggerDeLugarCombinacionIncorrecta() {
		this.agarraItemCorrectamente();

		String entrada = "usar rociador con cerveza de raiz en el cubo de hielo";
		String salida = null;
		Item item;
		if ((item = aventura.quiereRealizarAccionConItem(entrada)) != null) {
			salida = aventura.realizarAccionConItem(entrada, item);
		}

		Assert.assertEquals("Eso no ha dado ning�n resultado...", salida);
		Assert.assertEquals("Est�s en un muelle. En el suelo hay una barreta y un espejo. Hay un cubo de hielo. Hay un pirata fantasma. Al sur se puede ir hacia una taberna. Al este se puede ir hacia una playa.", aventura.describirUbicacion());
	}
	
	@Test
	public void noActivaTriggerDeNpcCombinacionIncorrecta() {
		this.agarraSegundoItemCorrectamente();

		String entrada = "apuntar con el espejo al pirata fantasma";
		String salida = null;
		Item item;
		if ((item = aventura.quiereRealizarAccionConItem(entrada)) != null) {
			salida = aventura.realizarAccionConItem(entrada, item);
		}

		Assert.assertEquals("Eso no ha servido de nada...", salida);
		Assert.assertEquals("Est�s en un muelle. En el suelo hay una barreta. Hay un cubo de hielo. Hay un pirata fantasma. Al sur se puede ir hacia una taberna. Al este se puede ir hacia una playa.", aventura.describirUbicacion());
	}
	
	@Test
	public void noActivaTriggerDeLugarItemIncorrecto() {
		this.agarraItemCorrectamente();

		String entrada = "usar el rociador con cerveza de raiz en el cubo de hielo";
		String salida = null;
		Item item;
		if ((item = aventura.quiereRealizarAccionConItem(entrada)) != null) {
			salida = aventura.realizarAccionConItem(entrada, item);
		}

		Assert.assertEquals("Eso no ha dado ning�n resultado...", salida);
		Assert.assertEquals("Est�s en un muelle. En el suelo hay una barreta y un espejo. Hay un cubo de hielo. Hay un pirata fantasma. Al sur se puede ir hacia una taberna. Al este se puede ir hacia una playa.", aventura.describirUbicacion());
	}
	
	@Test
	public void noActivaTriggerDeNpcItemIncorrecto() {
		this.agarraSegundoItemCorrectamente();

		String entrada = "usar el espejo con el pirata fantasma";
		String salida = null;
		Item item;
		if ((item = aventura.quiereRealizarAccionConItem(entrada)) != null) {
			salida = aventura.realizarAccionConItem(entrada, item);
		}

		Assert.assertEquals("Eso no ha servido de nada...", salida);
		Assert.assertEquals("Est�s en un muelle. En el suelo hay una barreta. Hay un cubo de hielo. Hay un pirata fantasma. Al sur se puede ir hacia una taberna. Al este se puede ir hacia una playa.", aventura.describirUbicacion());
	}
	
	@Test
	public void noActivaTriggerDeLugarAccionIncorrecta() {
		this.agarraSegundoItemCorrectamente();

		String entrada = "golpear el cubo de hielo con el espejo";
		String salida = null;
		Item item;
		if ((item = aventura.quiereRealizarAccionConItem(entrada)) != null) {
			salida = aventura.realizarAccionConItem(entrada, item);
		}

		Assert.assertEquals("No entiendo qu� acci�n quieres realizar con ese �tem...", salida);
		Assert.assertEquals("Est�s en un muelle. En el suelo hay una barreta. Hay un cubo de hielo. Hay un pirata fantasma. Al sur se puede ir hacia una taberna. Al este se puede ir hacia una playa.", aventura.describirUbicacion());
	}
	
	@Test
	public void noActivaTriggerDeNpcAccionIncorrecta() {
		this.agarraItemCorrectamente();

		String entrada = "golpear al pirata fantasma con el rociador con cerveza de raiz";
		String salida = null;
		Item item;
		if ((item = aventura.quiereRealizarAccionConItem(entrada)) != null) {
			salida = aventura.realizarAccionConItem(entrada, item);
		}

		Assert.assertEquals("No entiendo qu� acci�n quieres realizar con ese �tem...", salida);
		Assert.assertEquals("Est�s en un muelle. En el suelo hay una barreta y un espejo. Hay un cubo de hielo. Hay un pirata fantasma. Al sur se puede ir hacia una taberna. Al este se puede ir hacia una playa.", aventura.describirUbicacion());
	}

	@Test
	public void noActivaTriggerNpcEsIncorrecto() {
		this.agarraItemCorrectamente();
		
		String entrada = "usar rociador con cerveza de raiz en Lionel Messi";
		String salida = null;
		Item item;
		if ((item = aventura.quiereRealizarAccionConItem(entrada)) != null) {
			salida = aventura.realizarAccionConItem(entrada, item);
		}
		
		Assert.assertEquals("No entiendo por qu� quieres realizar eso...", salida);
		Assert.assertEquals("Est�s en un muelle. En el suelo hay una barreta y un espejo. Hay un cubo de hielo. Hay un pirata fantasma. Al sur se puede ir hacia una taberna. Al este se puede ir hacia una playa.", aventura.describirUbicacion());
	}
	
	@Test
	public void noActivaTriggerLugarEsIncorrecto() {
		this.agarraSegundoItemCorrectamente();

		String entrada = "derretir la pelota con el espejo";
		String salida = null;
		Item item;
		if ((item = aventura.quiereRealizarAccionConItem(entrada)) != null) {
			salida = aventura.realizarAccionConItem(entrada, item);
		}
		
		Assert.assertEquals("No entiendo por qu� quieres realizar eso...", salida);
		Assert.assertEquals("Est�s en un muelle. En el suelo hay una barreta. Hay un cubo de hielo. Hay un pirata fantasma. Al sur se puede ir hacia una taberna. Al este se puede ir hacia una playa.", aventura.describirUbicacion());
	}
	
	@Test
	public void cambiaNombreDeLugarCorrectamente() {
		setup();
		System.out.println(aventura.describirUbicacion());
		String entrada = "agarrar barreta";
		String salida = null;
		if (aventura.quiereAgarrarItem(entrada) == true) {
			salida = aventura.getProtagonista().describirInventario();
		}
		Assert.assertEquals("En tu inventario hay una barreta.", salida);

		entrada = "derretir el cubo de hielo con la barreta";
		salida = null;
		Item item;
		if ((item = aventura.quiereRealizarAccionConItem(entrada)) != null) {
			salida = aventura.realizarAccionConItem(entrada, item);
		}
		System.out.println(salida);
		System.out.println(aventura.describirUbicacion());
		
		Assert.assertEquals("El hielo se est� derritiendo!", salida);
		Assert.assertEquals("Est�s en un muelle. En el suelo hay un rociador con cerveza de raiz y un espejo. Hay un charco de agua. "
				+ "Hay un pirata fantasma. Al sur se puede ir hacia una taberna."
				+ " Al este se puede ir hacia una playa.", aventura.describirUbicacion());
	}
	
}
