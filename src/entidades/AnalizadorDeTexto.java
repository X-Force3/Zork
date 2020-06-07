package entidades;

import java.util.List;
import java.util.Scanner;


public class AnalizadorDeTexto {
	private Scanner scanner;

	public AnalizadorDeTexto() {
	    scanner = new Scanner(System.in);  // Create a Scanner object
//	    String userName = myObj.nextLine();  // Read user input
	}
	
	public String recibirEntrada() {
		return scanner.nextLine();
	}
	
	
	// devuelve el item si esta dentro de la cadena
	public Item contieneItem(String entrada, List<Item> items) {
		for(Item elemento : items) {
			if(entrada.contains(elemento.getNombre())) {
				return elemento;
			}
		}
		return null;
	}
	
	// devuelve la conexion si la ubicacion dentro de la misma esta dentro de la cadena
	public Conexion contieneConexion(String entrada, List<Conexion> conexiones) {
		for(Conexion elemento : conexiones) {
			if(entrada.contains(elemento.getUbicacionDestino().getNombre()) || entrada.contains(elemento.getDireccion().getNombre())) {
				return elemento;		
			}
		}
		return null;
	}
	
	public Npc contieneObstaculoNpc(String entrada, List<Npc> npcs) {	// deberia ser la lista de npcs de la ubicacion actual
		for(Npc elemento : npcs) {
			if(entrada.contains(elemento.getNombre())) {
				return elemento;		
			}
		}
		return null;
	}	
	
	public String contieneAccion(String entrada, List<String> acciones) {
		for(String accion : acciones) {
			if(entrada.contains(accion)) {
				return accion;
			}
		}
		return null;		
	}
	
	public Lugar contieneObstaculoLugar(String entrada, List<Lugar> lugares) {
		for(Lugar elemento : lugares) {
			if(entrada.contains(elemento.getNombre())) {
				return elemento;		
			}
		}
		return null;
	}
	
	

}
