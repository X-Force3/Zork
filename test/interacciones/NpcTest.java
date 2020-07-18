package interacciones;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidades.Genero;
import entidades.Item;
import entidades.Npc;
import entidades.Numero;
import entidades.Protagonista;
import entidades.Trigger;

public class NpcTest {

	private Npc npc;
	private List<Trigger> triggers;
	private Trigger trigger;
	private Item item;
	private List<String> acciones;
	private List<String> efectosSobre;
	private Protagonista protagonista;

	@Before
	public void setup() {
		this.triggers = new ArrayList<Trigger>();
		this.trigger = new Trigger("item", "rociador con cerveza de raiz",
				"- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía entusiasmado por tu ofrecimiento... sin embargo,"
						+ " cuando lo rociaste comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció, y las piernas inmediatamente echaron a correr.",
				"remove");
		this.triggers.add(this.trigger);

		this.npc = new Npc("pirata fantasma", Genero.MALE, Numero.SINGULAR,
				"- '¡No puedes pasar!' El pirata fantasma no te dejará pasar");
		this.npc.setDialogo("¡No hay nada que me digas que me haga cambiar de opinón!");
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
		
		protagonista = new Protagonista("Marcos", null);
	}
	
	@Test
	public void queRespondeCorrectamente() {
		Assert.assertEquals(this.npc.hablar(), "¡No hay nada que me digas que me haga cambiar de opinón!");
	}

	@Test
	public void queEsAfectadoPorUnItem() {
		String respuestaEsperada = "- '¡Me encanta la cerveza de raiz!' El pirata fantasma se veía entusiasmado por tu ofrecimiento... "
				+ "sin embargo, cuando lo rociaste comenzó a desintegrarse. La mitad de arriba de su cuerpo se desvaneció, y las piernas "
				+ "inmediatamente echaron a correr.";
		Assert.assertEquals(respuestaEsperada, this.npc.verificarTrigger(this.item, protagonista));
		Assert.assertEquals("borrado", this.npc.getNombre());
	}

	@Test
	public void queNoEsAfectadoPorUnItem() {
		this.item.setNombre("Este item no es un trigger del npc");
		String respuestaEsperada = "Eso no ha servido de nada...";
		Assert.assertEquals(respuestaEsperada, this.npc.verificarTrigger(this.item, protagonista));
		Assert.assertEquals("pirata fantasma", this.npc.getNombre());
	}
	
}
