package chainOfResponsability;

import entidades.AnalizadorDeTexto;
import entidades.Item;
import entidades.Lugar;
import entidades.Npc;
import entidades.Protagonista;

public class UtilizarItem implements RealizarAccion{

	private RealizarAccion r;
	private AnalizadorDeTexto analizador;
	private Protagonista protagonista;
	
	public UtilizarItem(AnalizadorDeTexto a, Protagonista p) {
		r = new AccionNoConocida();
		analizador = a;
		this.protagonista = p;
	}

	@Override
	public String realizarAccion(String entrada) {
		
		String salida;
		
		Item item = analizador.contieneItem(entrada, this.protagonista.getInventario());
		
		if(item == null) {
			salida = r.realizarAccion(entrada);
		} else {
			Npc npc;
			Lugar lugar;
			String accion = analizador.contieneAccion(entrada, item.getAcciones());
			if (accion != null) {
				npc = analizador.contieneObstaculoNpc(entrada, this.protagonista.getUbicacionActual().getNpcs());
				if (npc != null) {
					salida = npc.verificarTrigger(item, this.protagonista);
					this.protagonista.eliminarItem(item);// Luego de que el protagonista utiliza el �tem, se elimina de su
															// inventario.
				} else {
					lugar = analizador.contieneObstaculoLugar(entrada, this.protagonista.getUbicacionActual().getLugares());
					if (lugar != null) {
						salida = lugar.verificarTrigger(item);
						this.protagonista.eliminarItem(item);
					} else {
						salida = "No entiendo por qu� quieres realizar eso...";				
					}
				}
			} else {
				salida = "No entiendo qu� acci�n quieres realizar con ese �tem...";
			}
		}
		
		return salida;
	}

}
