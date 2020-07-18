package descripciones;

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
	public void queDescribaElLugarSinItems() {
		System.out.println(lugar.describirObjetosDisponibles());
		Assert.assertEquals("En el muelle no hay nada.", lugar.describirObjetosDisponibles());
	}

	@Test
	public void queDescribaElLugarConUnItem() {
		lugar.getItems().add(new Item("espejo", Genero.MALE, Numero.SINGULAR));

		System.out.println(lugar.describirObjetosDisponibles());
		Assert.assertEquals("En el muelle hay un espejo.", lugar.describirObjetosDisponibles());
	}

	@Test
	public void queDescribaElLugarConDosItems() {
		lugar.getItems().add(new Item("piedras", Genero.FEMALE, Numero.PLURAL));
		lugar.getItems().add(new Item("linterna", Genero.FEMALE, Numero.SINGULAR));
		System.out.println(lugar.describirObjetosDisponibles());
		Assert.assertEquals("En el muelle hay piedras y una linterna.", lugar.describirObjetosDisponibles());
	}

	@Test
	public void queDescribaElLugarConMuchosItems() {
		lugar.getItems().add(new Item("espejo", Genero.MALE, Numero.SINGULAR));
		lugar.getItems().add(new Item("martillo", Genero.MALE, Numero.SINGULAR));
		lugar.getItems().add(new Item("linterna", Genero.FEMALE, Numero.SINGULAR));
		System.out.println(lugar.describirObjetosDisponibles());
		Assert.assertEquals("En el muelle hay un espejo, un martillo y una linterna.",
				lugar.describirObjetosDisponibles());
	}
}
