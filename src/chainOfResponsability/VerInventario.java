package chainOfResponsability;

import java.util.List;

import entidades.Protagonista;
import entidades.Item;
import entidades.AnalizadorDeTexto;

public class VerInventario implements RealizarAccion {

	private RealizarAccion r;
	private List<Item> itemsProtagonista;
	
	public VerInventario(AnalizadorDeTexto a, Protagonista p) {
		r = new AgarrarItem(a,p);
		itemsProtagonista = p.getInventario();
	}
	
	@Override
	public String realizarAccion(String entrada) {
		
		String salida = "En tu inventario hay:";
		
		if(entrada.equalsIgnoreCase("ver inventario") || entrada.equalsIgnoreCase("mirar inventario")) {
			
			if(itemsProtagonista.size() == 0) {
				salida = "No hay ningun item en tu inventario";
				
			} else {
				for(Item item : itemsProtagonista) {
					salida += "\n--> " + item.conjugarItem();
				}
			}
			
		} else {
			salida = r.realizarAccion(entrada);
		}
		
		return salida;
	}
		
}
