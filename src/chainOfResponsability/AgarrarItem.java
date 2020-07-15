package chainOfResponsability;

import entidades.AnalizadorDeTexto;
import entidades.Item;
import entidades.Protagonista;

public class AgarrarItem implements RealizarAccion{

	private RealizarAccion r;
	private AnalizadorDeTexto analizador;
	private Protagonista protagonista;
	
	public AgarrarItem(AnalizadorDeTexto a, Protagonista p) {
		r = new UtilizarItem(a,p);
		analizador = a;
		protagonista = p;
	}

	@Override
	public String realizarAccion(String entrada) {
		String salida;
		Item objeto;
		
		objeto = analizador.contieneItem(entrada, this.protagonista.getUbicacionActual().getItems());
		if (objeto != null && objeto.esItemDeInventario() && (entrada.contains("agarrar") ||
			entrada.contains("tomar") || entrada.contains("guardar"))) {
			this.protagonista.añadirItem(objeto); // se a�ade al inventario
			this.protagonista.getUbicacionActual().eliminarItemUbicacion(objeto); // se quita del place
			
			///
			salida = this.protagonista.describirInventario();
			
		} else {
			salida = r.realizarAccion(entrada);
		}
		return salida;
	}

}
