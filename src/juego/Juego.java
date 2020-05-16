package juego;

import java.util.List;

import entidades.Aventura;
import entidades.Item;
import entidades.Locacion;
import entidades.Npc;

public class Juego {
	
	private List<Aventura> aventuras;
	private List<Locacion> locaciones;
	private List<Item> items;
	private List<Npc> npcs;
	
	public Juego(List<Aventura> aventuras, List<Locacion> locaciones, List<Item> items, List<Npc> npcs) {
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

	public List<Aventura> getAventuras() {
		return aventuras;
	}

	public void setAventuras(List<Aventura> aventuras) {
		this.aventuras = aventuras;
	}

	public List<Locacion> getLocaciones() {
		return locaciones;
	}

	public void setLocaciones(List<Locacion> locaciones) {
		this.locaciones = locaciones;
	}
	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Npc> getNpcs() {
		return npcs;
	}

	public void setNpcs(List<Npc> npcs) {
		this.npcs = npcs;
	}
	
	
}
