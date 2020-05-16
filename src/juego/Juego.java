package juego;

import java.util.List;

import entidades.Aventura;
import entidades.Item;
import entidades.Ubicacion;
import entidades.Npc;

public class Juego {
	
	private List<Aventura> aventuras;
	private List<Ubicacion> locaciones;
	private List<Item> items;
	private List<Npc> npcs;
	
	public Juego(List<Aventura> aventuras, List<Ubicacion> locaciones, List<Item> items, List<Npc> npcs) {
		super();
		this.aventuras = aventuras;
		this.locaciones = locaciones;
		this.items = items;
		this.npcs = npcs;
	}

	@Override
	public String toString() {
		return "Juego [aventuras=" + aventuras + ", locaciones=" + locaciones + ", items=" + items + ", npcs=" + npcs
				+ "]";
	}
	
}
