package interacciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidades.*;

public class EndgameTest {

	Aventura aventura;

	Protagonista protagonista;
	Configuracion configuracionAventura;
	AnalizadorDeTexto analizador;

	Endgame itemEnUbicacion;
	Endgame item;
	Endgame ubicacion;
	Endgame accion;
	Endgame muerte;
	List<Endgame> endgamesAventura;

	Ubicacion muelle;
	Ubicacion taberna;
	Ubicacion playa;
	Map<String, Ubicacion> ubicaciones;

	Lugar sueloMuelle;
	List<Lugar> lugaresMuelle;

	Item barreta;
	Item espejo;
	Item rociadorCervezaRaiz;
	List<Item> itemsSuelo;

	List<String> efectosItemsSuelo;
	List<String> accionesItemsSuelo;

	Conexion surMuelle;
	Conexion esteMuelle;
	List<Conexion> conexionesMuelle;

	@Before
	public void setup() {

		efectosItemsSuelo = new ArrayList<String>();
		efectosItemsSuelo.add("item");

		accionesItemsSuelo = new ArrayList<String>();
		accionesItemsSuelo.add("usar");
		accionesItemsSuelo.add("agarrar");

		barreta = new Item("barreta", Genero.FEMALE, Numero.SINGULAR);
		espejo = new Item("espejo", Genero.MALE, Numero.SINGULAR);
		rociadorCervezaRaiz = new Item("rociador con cerveza de raiz", Genero.MALE, Numero.SINGULAR);
		barreta.setAcciones(accionesItemsSuelo);
		espejo.setAcciones(accionesItemsSuelo);
		rociadorCervezaRaiz.setAcciones(accionesItemsSuelo);

		itemsSuelo = new ArrayList<Item>();
		itemsSuelo.add(barreta);
		itemsSuelo.add(rociadorCervezaRaiz);
		itemsSuelo.add(espejo);

		sueloMuelle = new Lugar("suelo", Genero.MALE, Numero.SINGULAR, null);
		sueloMuelle.setItems(itemsSuelo);
		
		lugaresMuelle = new ArrayList<Lugar>();
		lugaresMuelle.add(sueloMuelle);

		taberna = new Ubicacion("taberna", Genero.FEMALE, Numero.SINGULAR, "Estás en una taberna.");
		playa = new Ubicacion("playa", Genero.FEMALE, Numero.SINGULAR, "Estás en la playa.");
		taberna.setConexiones(new ArrayList<Conexion>());
		playa.setConexiones(new ArrayList<Conexion>());

		surMuelle = new Conexion(Direccion.SUR, "taberna", null);
		esteMuelle = new Conexion(Direccion.ESTE, "playa", null);
		
		conexionesMuelle = new ArrayList<Conexion>();
		conexionesMuelle.add(surMuelle);
		conexionesMuelle.add(esteMuelle);
		
		muelle = new Ubicacion("muelle", Genero.MALE, Numero.SINGULAR, "Estás en un muelle.");
		muelle.setConexiones(conexionesMuelle);
		muelle.setLugares(lugaresMuelle);
		
		ubicaciones = new HashMap<String, Ubicacion>();
		ubicaciones.put(muelle.getNombre(), muelle);
		ubicaciones.put(taberna.getNombre(), taberna);
		ubicaciones.put(playa.getNombre(), playa);

		itemEnUbicacion = new Endgame("itemEnUbicacion", "", barreta.getNombre(),
				"¡Felicitaciones! Has ganado el juego, lograste llegar a la taberna con la barreta...",
				taberna.getNombre());
		item = new Endgame("item", "", espejo.getNombre(),
				"¡Felicitaciones! Has ganado el juego, lograste conseguir el espejo...", "");
		ubicacion = new Endgame("ubicacion", "", "",
				"¡Felicitaciones! Has ganado el juego, lograste llegar a la playa...", playa.getNombre());
		accion = new Endgame("accion", "beber", rociadorCervezaRaiz.getNombre(),
				"¡Felicitaciones! Has ganado el juego, bebiste la cerveza de raíz...", "");
		muerte = new Endgame("muerte", "", "",
				"El animal te atacó causándote heridas mortales. Has muerto...", "");

		endgamesAventura = new ArrayList<Endgame>();
		endgamesAventura.add(item);
		endgamesAventura.add(itemEnUbicacion);
		endgamesAventura.add(accion);
		endgamesAventura.add(ubicacion);
		endgamesAventura.add(muerte);
		
		configuracionAventura = new Configuracion(null, null, endgamesAventura);
		aventura = new Aventura(configuracionAventura, ubicaciones, new Protagonista("X-Force", muelle));
		aventura.setAnalizador(new AnalizadorDeTexto());
	}

	@Test
	public void ejecutarEndgameItem() {
		
		String entrada = "agarrar el espejo";

		aventura.quiereAgarrarItem(entrada);

		Assert.assertEquals("¡Felicitaciones! Has ganado el juego, lograste conseguir el espejo...",
				aventura.verificarEndgame(entrada));
	}
	
	@Test
	public void ejecutarEndgameItemEnUbicacion() {

		String entrada = "agarrar la barreta";
		aventura.quiereAgarrarItem(entrada);
		
		entrada = "ir a la taberna";
		aventura.tratarObstaculo(aventura.quiereMoverseDeUbicacion(entrada));

		Assert.assertEquals("¡Felicitaciones! Has ganado el juego, lograste llegar a la taberna con la barreta...",
				aventura.verificarEndgame(entrada));
	}
	
	@Test
	public void ejecutarEndgameEnUbicacion() {

		String entrada = "ir a la playa";
		aventura.tratarObstaculo(aventura.quiereMoverseDeUbicacion(entrada));

		Assert.assertEquals("¡Felicitaciones! Has ganado el juego, lograste llegar a la playa...",
				aventura.verificarEndgame(entrada));
	}
	
	@Test
	public void ejecutarEndgameAccion() {

		String entrada = "agarrar el rociador con cerveza de raiz";
		aventura.quiereAgarrarItem(entrada);
		
		entrada = "beber el rociador con cerveza de raiz";
		aventura.realizarAccionConItem(entrada, aventura.quiereRealizarAccionConItem(entrada));

		Assert.assertEquals("¡Felicitaciones! Has ganado el juego, bebiste la cerveza de raíz...",
				aventura.verificarEndgame(entrada));
	}
	
	@Test
	public void ejecutarEndgameMuerte() {

		String entrada = "random";
		aventura.getProtagonista().morir();

		Assert.assertEquals("El animal te atacó causándote heridas mortales. Has muerto...",
				aventura.verificarEndgame(entrada));
	}
}

