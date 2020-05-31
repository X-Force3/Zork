package entidades;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProtagonistaTest {

	Protagonista protagonista;

	Ubicacion ubicacion;
	Ubicacion taberna;
	Item item;
	Npc pirata;
	
	@Before
	public void setup() {
		List<Lugar> lugares =new ArrayList<Lugar>();

		List<String> efectosItemsSuelo = new ArrayList<String>();
		efectosItemsSuelo.add("npcs");
		efectosItemsSuelo.add("item");
		efectosItemsSuelo.add("self");

		List<String> accionItemsSuelo = new ArrayList<String>();
		accionItemsSuelo.add("usar");

		Item barreta = new Item("barreta", Genero.FEMALE, Numero.SINGULAR, accionItemsSuelo, efectosItemsSuelo);
		Item espejo = new Item("espejo", Genero.MALE, Numero.SINGULAR, accionItemsSuelo, efectosItemsSuelo);
		Item rociadorCervezaRaiz = new Item("rociador con cerveza de raíz", Genero.MALE, Numero.SINGULAR,
		accionItemsSuelo, efectosItemsSuelo);
		List<Item> itemsSuelo = new ArrayList<Item>();
		itemsSuelo.add(barreta);
		itemsSuelo.add(rociadorCervezaRaiz);
		itemsSuelo.add(espejo);
		///creado lista de items, se crea el lugar
		lugares.add(new Lugar("Casa",Genero.FEMALE,Numero.SINGULAR,itemsSuelo));
		///lugares HECHO - Falta conexiones

		//Para crear una conexion -->
		taberna = new Ubicacion("taberna", Genero.FEMALE, Numero.SINGULAR,"Taberna",lugares, null, null);
		Conexion surMuelle = new Conexion(Direccion.SUR, taberna, new Npc("Messi", Genero.MALE, Numero.SINGULAR, "Messi, futbolista", "Hola, soy messi", null,false));
		List<Conexion> conexionesMuelle = new ArrayList<Conexion>();
		conexionesMuelle.add(surMuelle);
		///se creo una lista de conexiones donde tiene una ubicacion destino a "Taberna"
		//En este caso, se crea la ubicacion "Casa" con los mismos parametros
		ubicacion = new Ubicacion("Casa",Genero.FEMALE,Numero.SINGULAR,"Casa",lugares, null, conexionesMuelle);
		
		///inicializo item
		List<String> acciones = new ArrayList<String>();
		List<String> efectosSobre = new ArrayList<String>();
		acciones.add("Usar");
		efectosSobre.add("Asustarse");
		
		item = new Item("Espejo",Genero.MALE,Numero.SINGULAR,acciones, efectosSobre);
		///inicializo pirata
		Trigger pirataFantasmaRociador = new Trigger("item", "rociador con cerveza de raíz", null, null);
		List<Trigger> triggersMuelle = new ArrayList<Trigger>();
		triggersMuelle.add(pirataFantasmaRociador);

		protagonista = new Protagonista("Nahuel", ubicacion);
		
		pirata = new Npc("pirata fantasma", Genero.MALE, Numero.SINGULAR,
		"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar",
		"¡No hay nada que me digas que me haga cambiar de opinión!", triggersMuelle, false);
	}
	
	@Test
	public void queRecogeCorrectamenteUnItem() {
		
		List<String> acciones = new ArrayList<String>();
		List<String> efectosSobre = new ArrayList<String>();
		acciones.add("Usar");
		efectosSobre.add("Asustarse");
		
		Item item = new Item("Espejo",Genero.MALE,Numero.SINGULAR,acciones, efectosSobre);
			
		protagonista.añadirItem(item);
		
		Assert.assertFalse(protagonista.añadirItem(item));///si devuelve falso, quiere decir que ya lo tiene en el inventario
		
	}
	
	@Test
	public void queSeDesplazaCorrectamenteHaciaUnaConexion() {
		
		Conexion c = new Conexion(Direccion.SUR, taberna, new Npc("Messi", Genero.MALE, Numero.SINGULAR, "Messi, futbolista", "Hola, soy messi", null,false));
		
		Assert.assertTrue(protagonista.desplazarse(c));
	}
	
	@Test
	public void queUtilizaCorrectamenteUnItem() {
			
		List<String> acciones = new ArrayList<String>();
		List<String> efectosSobre = new ArrayList<String>();
		acciones.add("Usar");
		efectosSobre.add("Asustarse");
		
		Item item = new Item("Espejo",Genero.MALE,Numero.SINGULAR,acciones, efectosSobre);
		
		protagonista.añadirItem(item);
		protagonista.utilizarItem(item, pirata, item.getAcciones().get(0));///si lo uso, el item queda descartado de su inventario
		Assert.assertEquals(true, protagonista.añadirItem(item)); ///si lo añadio, quiere decir que no lo tenia en su inventario 
	}

	@Test
	public void queHableSegunElDiagologoCorrespondiente() {
		Npc messi = new Npc("Messi", Genero.MALE, Numero.SINGULAR, "Messi, futbolista", "Hola, soy messi", null,false);
		
		Assert.assertEquals(protagonista.hablar(messi.getDialogo()), "Hola messi!");
	}
	
}
