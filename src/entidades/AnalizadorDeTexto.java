package entidades;

import java.util.List;
import java.util.Scanner;

public class AnalizadorDeTexto {
	private Scanner scanner;

	public AnalizadorDeTexto() {
		scanner = new Scanner(System.in);
	}

	public String recibirEntrada() {
		return scanner.nextLine();
	}

	// devuelve el item si esta dentro de la cadena
	public Item contieneItem(String entrada, List<Item> items) {
		for (Item elemento : items) {
			if (entrada.contains(elemento.getNombre())) {
				return elemento;
			}
		}
		Item itemVacio = new Item();
		
		return itemVacio;
	}

	// devuelve la conexion si la ubicacion dentro de la misma esta dentro de la
	// cadena
	public Conexion contieneConexion(String entrada, List<Conexion> conexiones) {
		for (Conexion elemento : conexiones) {
			if (entrada.contains(elemento.getUbicacionDestino())
					|| entrada.contains(elemento.getDireccion().getNombre())) {
				return elemento;
			}
		}
		Conexion conexionVacia = new Conexion();
		
		return conexionVacia;
	}

	public Npc contieneObstaculoNpc(String entrada, List<Npc> npcs) { // deberia ser la lista de npcs de la ubicacion																	// actual
		for (Npc elemento : npcs) {
			if (entrada.contains(elemento.getNombre())) {
				return elemento;
			}
		}
		Npc npcVacio = new Npc();
		
		return npcVacio;
	}

	public String contieneAccion(String entrada, List<String> acciones) {
		for (String accion : acciones) {
			if (entrada.contains(accion)) {
				return accion;
			}
		}
		String accionVacia = " ";
		return accionVacia;
	}

	public Lugar contieneObstaculoLugar(String entrada, List<Lugar> lugares) {
		for (Lugar elemento : lugares) {
			if (entrada.contains(elemento.getNombre())) {
				return elemento;
			}
		}
		Lugar lugarVacio = new Lugar();
		
		return lugarVacio;
	}
	
	public Ubicacion devolverObjetoUbicacion(String entrada, List<Ubicacion> ubicaciones) {
		for (Ubicacion elemento : ubicaciones) {
			if (entrada.contains(elemento.getNombre())) {
				return elemento;
			}
		}
		Ubicacion ubicacionVacia = new Ubicacion();
		
		return ubicacionVacia;
	}
	
	public boolean afectaASiMismo(List<String> efectosSobre) {
		for(String elemento : efectosSobre) {
			if(elemento.equals("self"))
				return true;			
		}
		return false;
	}
}
