package entidades;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConexionTest {

	Conexion conexion;
	Npc npc;
	Ubicacion ubicacionInicial;
	Ubicacion ubicacionDestino;

	@Before
	public void setup() {
		npc = new Npc("Saku", Genero.FEMALE, Numero.SINGULAR, "Una señorita con un tapado rojo. ", " ", null);
		ubicacionDestino = new Ubicacion("Tienda electrónica", Genero.MALE, Numero.SINGULAR, null, null, null, null);
		conexion = new Conexion(Direccion.NORTE, "Tienda electrónica", "Saku");

		List<Npc> listaNpc = new ArrayList<Npc>();
		listaNpc.add(npc);
		List<Conexion> listaConexiones = new ArrayList<Conexion>();
		listaConexiones.add(conexion);
		ubicacionInicial = new Ubicacion("Bar cyberpunk", Genero.MALE, Numero.SINGULAR, null, null, listaNpc,
				listaConexiones);

	}

	@Test
	public void queConectaCorrectamenteHaciaOtraUbicacion() {

		Assert.assertEquals(conexion.getUbicacionDestino(), ubicacionDestino.getNombre());

	}

	@Test
	public void queEsUnaConexionDeUnaUbicacionOriginaria() {

		Assert.assertTrue(ubicacionInicial.getConexiones().contains(conexion));
	}

}
