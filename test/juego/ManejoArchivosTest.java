package juego;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidades.Conexion;
import entidades.Configuracion;
import entidades.Endgame;
import entidades.Item;
import entidades.Lugar;
import entidades.Npc;
import entidades.Trigger;
import entidades.Ubicacion;

public class ManejoArchivosTest {
	
	ManejoArchivos m;

	@Before
	public void setup() {
		m = new ManejoArchivos("recursos/aventura.json");
	}
	
	@Test
	public void queLaConfiguracionSeaValida() {
		Configuracion c = m.getConfiguracion();
		Assert.assertNotNull(c);
		Assert.assertNotNull(c.getBienvenida());
		Assert.assertNotNull(c.getTitulo());
		Assert.assertNotNull(c.getEndgames());
		Assert.assertFalse(c.getBienvenida().isEmpty());
		Assert.assertFalse(c.getTitulo().isEmpty());
		Assert.assertFalse(c.getEndgames().isEmpty());
		
		for (Endgame endgame : c.getEndgames()) {
			Assert.assertNotNull(endgame);
			Assert.assertFalse(endgame.getCondicion().isEmpty());
			Assert.assertFalse(endgame.getAccion().isEmpty());
			Assert.assertFalse(endgame.getCosa().isEmpty());
			Assert.assertFalse(endgame.getDescripcion().isEmpty());
		}
	}
	
	@Test
	public void queLasUbicacionesSeanValidas() {
		List<Ubicacion> ubicaciones = m.getUbicaciones();
		Assert.assertNotNull(ubicaciones);
		Assert.assertFalse(ubicaciones.isEmpty());
		for (Ubicacion u : ubicaciones) {
			Assert.assertFalse(u.getNombre().isEmpty());
			Assert.assertFalse(u.getDescripcion().isEmpty());
			Assert.assertNotNull(u.getGenero());
			Assert.assertNotNull(u.getNumero());
			Assert.assertFalse(u.getLugares().isEmpty());
			Assert.assertFalse(u.getNpcs().isEmpty());
			Assert.assertFalse(u.getConexiones().isEmpty());
			
			for (Lugar l : u.getLugares()) {
				Assert.assertFalse(l.getNombre().isEmpty());
				Assert.assertFalse(l.getSegundoNombre().isEmpty());
				Assert.assertFalse(l.getDescripcion().isEmpty());
				Assert.assertNotNull(l.getGenero());
				Assert.assertNotNull(l.getNumero());
				Assert.assertNotNull(l.getItems()); //puede no tener items pero debe traer al menos una lista vacia
				//Assert.assertNotNull(l.getTriggers()); {//NOTA: agregar triggers en Lugar-----------------------
				
				for (Item i : l.getItems()) {
					Assert.assertFalse(i.getNombre().isEmpty());
					Assert.assertNotNull(i.getGenero());
					Assert.assertNotNull(i.getNumero());
					Assert.assertFalse(i.getAcciones().isEmpty());
					Assert.assertFalse(i.getEfectosSobre().isEmpty());
				}
				
				/*for (Trigger t : l.getTriggers()) {
					Assert.assertFalse(t.getType().isEmpty());
					Assert.assertFalse(t.getThing().isEmpty());
					Assert.assertFalse(t.getOn_trigger().isEmpty());
					Assert.assertFalse(t.getAfter_trigger().isEmpty());
				}*/
				
			}
			
			for (Npc npc : u.getNpcs()) {
				Assert.assertFalse(npc.getNombre().isEmpty());
				Assert.assertNotNull(npc.getGenero());
				Assert.assertNotNull(npc.getNumero());
				Assert.assertFalse(npc.getDescripcion().isEmpty());
				Assert.assertFalse(npc.getDialogo().isEmpty());
				Assert.assertFalse(npc.getTrigger().isEmpty());
				for (Trigger t : npc.getTrigger()) {
					Assert.assertFalse(t.getType().isEmpty());
					Assert.assertFalse(t.getThing().isEmpty());
					Assert.assertFalse(t.getOn_trigger().isEmpty());
					Assert.assertFalse(t.getAfter_trigger().isEmpty());
				}
			}
			
			for (Conexion c : u.getConexiones()) {
				System.out.println(u.getNombre() + " conexion " + c);
				Assert.assertNotNull(c.getDireccion());  //NOTA: La direccion siempre debe ser Este, Sur, ... ----------------------
				Assert.assertFalse(c.getUbicacionDestino().isEmpty());
				Assert.assertNotNull(c.getObstaculo());
			}
		}
	}
	
	@Test
	public void queElMapaDeUbicacionesFuncione() {
		Map<String, Ubicacion> ubis = m.getUbicacionesMap();
		Ubicacion aBuscar = m.getUbicaciones().get(0);
		Assert.assertNotNull(ubis.get(aBuscar.getNombre()));
		Assert.assertEquals(aBuscar, ubis.get(aBuscar.getNombre()));
	}
	
	@Test
	public void queElMapaDeItemsFuncione() {
		Map<String, Item> items = m.getItemsMap();
		Item aBuscar = m.getUbicaciones().get(0).getItems().get(0);
		Assert.assertNotNull(items.get(aBuscar.getNombre()));
		Assert.assertEquals(aBuscar, items.get(aBuscar.getNombre()));
	}
}
