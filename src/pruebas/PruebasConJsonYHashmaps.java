package pruebas;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidades.Aventura;
import entidades.Item;
import entidades.Ubicacion;
import juego.ManejoArchivosProbandoHashmaps;


public class PruebasConJsonYHashmaps {

	Aventura aventura;
	HashMap<String, Ubicacion> ubicacionesHashMap;
	HashMap<String, Item> itemsHashMap;

	@Before
	public void cargarAventura() {
		ManejoArchivosProbandoHashmaps manager = new ManejoArchivosProbandoHashmaps(ManejoArchivosProbandoHashmaps.RUTA_AVENTURA_1);
		aventura = manager.getAventura();
		ubicacionesHashMap = manager.construirHashmapUbicaciones();
		itemsHashMap = manager.construirHashmapItems();
	}
	
	@Test
	public void queLaAventuraEsteCargada() {
		Assert.assertNotNull(aventura);
		Assert.assertNotNull(ubicacionesHashMap);
		Assert.assertNotNull(itemsHashMap);
	}
	
	@Test
	public void probando() {
		System.out.println("Todos los items de la aventura: " + itemsHashMap.keySet() + "\n");
		System.out.println("Todas las ubicaciones de la aventura: " + ubicacionesHashMap.keySet() + "\n");
		System.out.println(ubicacionesHashMap.get("muelle"));
		System.out.println(itemsHashMap.get("espejo"));
		System.out.println(aventura.getConfiguracion().getBienvenida());
		System.out.println(ubicacionesHashMap.get("taberna").getNpcs());
	}
}
