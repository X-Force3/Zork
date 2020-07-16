package descripciones;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidades.Genero;
import entidades.Item;
import entidades.Npc;
import entidades.Numero;
import entidades.Trigger;

public class NpcTest {

	private Npc npc;
	private List<Trigger> triggers;
	private Trigger trigger;
	private Item item;
	private List<String> acciones;
	private List<String> efectosSobre;

	@Before
	public void setup() {
		this.triggers = new ArrayList<Trigger>();
		this.trigger = new Trigger("item", "ociador con cerveza de raiz",
				"- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía entusiasmado por tu ofrecimiento... sin embargo,"
						+ " cuando lo rociaste comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció, y las piernas inmediatamente echaron a correr.",
				"remove");
		this.triggers.add(this.trigger);

		this.npc = new Npc("pirata fantasma", Genero.MALE, Numero.SINGULAR,
				"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar");
		this.npc.setTriggers(triggers);

		acciones = new ArrayList<String>();
		efectosSobre = new ArrayList<String>();

		acciones.add("Usar");
		efectosSobre.add("Thanos");
		efectosSobre.add("Suelo");
		efectosSobre.add("Clavo");
		item = new Item("rociador con cerveza de raiz", Genero.MALE, Numero.SINGULAR);
		item.setAcciones(acciones);
		item.setEfectosSobre(efectosSobre);
	}

	@Test
	public void queSePresenteCorrectamente() {
		Assert.assertEquals(this.npc.presentarse(), "- '¡No puedes pasar!' El pirata fantasma no te dejará pasar");
	}

}
