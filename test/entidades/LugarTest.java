package entidades;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LugarTest {

	Lugar lugar;

	@Before
	public void setup() {
		ArrayList<Item> items = new ArrayList<Item>();
		lugar = new Lugar("muelle", null, Genero.MALE, Numero.SINGULAR, items, null, null);
	}

	@Test
	public void queDescribaElLugarSinItems() {
		System.out.println(lugar.describirObjetosDisponibles());
		Assert.assertEquals("En el muelle no hay nada.", lugar.describirObjetosDisponibles());
	}

	@Test
	public void queDescribaElLugarConUnItem() {
		lugar.getItems()
				.add(new Item("espejo", Genero.MALE, Numero.SINGULAR, Arrays.asList("usar"), Arrays.asList("npc")));
		System.out.println(lugar.describirObjetosDisponibles());
		Assert.assertEquals("En el muelle hay un espejo.", lugar.describirObjetosDisponibles());
	}

	@Test
	public void queDescribaElLugarConDosItems() {
		lugar.getItems()
				.add(new Item("piedras", Genero.FEMALE, Numero.PLURAL, Arrays.asList("usar"), Arrays.asList("npc")));
		lugar.getItems()
				.add(new Item("linterna", Genero.FEMALE, Numero.SINGULAR, Arrays.asList("usar"), Arrays.asList("npc")));
		System.out.println(lugar.describirObjetosDisponibles());
		Assert.assertEquals("En el muelle hay piedras y una linterna.", lugar.describirObjetosDisponibles());
	}

	@Test
	public void queDescribaElLugarConMuchosItems() {
		lugar.getItems()
				.add(new Item("espejo", Genero.MALE, Numero.SINGULAR, Arrays.asList("usar"), Arrays.asList("npc")));
		lugar.getItems()
				.add(new Item("martillo", Genero.MALE, Numero.SINGULAR, Arrays.asList("usar"), Arrays.asList("npc")));
		lugar.getItems()
				.add(new Item("linterna", Genero.FEMALE, Numero.SINGULAR, Arrays.asList("usar"), Arrays.asList("npc")));
		System.out.println(lugar.describirObjetosDisponibles());
		Assert.assertEquals("En el muelle hay un espejo, un martillo y una linterna.",
				lugar.describirObjetosDisponibles());
	}

	/// Se probara que un objeto disponible en el place, no siga disponible una vez
	/// haya sido recogido
	@Test
	public void queNoEsteDisponibleUnObjetoAlAgarrarlo() {

		Item piedras = new Item("piedras", Genero.FEMALE, Numero.PLURAL, Arrays.asList("usar"), Arrays.asList("npc"));

		lugar.getItems().add(piedras);
		lugar.getItems()
				.add(new Item("linterna", Genero.FEMALE, Numero.SINGULAR, Arrays.asList("usar"), Arrays.asList("npc")));

		lugar.eliminarItemLugar(piedras);

		Assert.assertEquals("En el muelle hay una linterna.", lugar.describirObjetosDisponibles());
	}

}
