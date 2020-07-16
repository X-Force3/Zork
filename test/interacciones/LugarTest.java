package interacciones;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidades.Genero;
import entidades.Item;
import entidades.Lugar;
import entidades.Numero;

public class LugarTest {
	
	Lugar lugar;

	@Before
	public void setup() {
		lugar = new Lugar("muelle", Genero.MALE, Numero.SINGULAR, null);
		ArrayList<Item> items = new ArrayList<Item>();
		lugar.setItems(items);
	}
	
	@Test
	public void queNoEsteDisponibleUnObjetoAlAgarrarlo() {

		Item piedras = new Item("piedras", Genero.FEMALE, Numero.PLURAL);

		lugar.getItems().add(piedras);
		lugar.getItems()
				.add(new Item("linterna", Genero.FEMALE, Numero.SINGULAR));

		lugar.eliminarItemLugar(piedras);

		Assert.assertEquals("En el muelle hay una linterna.", lugar.describirObjetosDisponibles());
	}
}
