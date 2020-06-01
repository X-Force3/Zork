package entidades;

import java.util.List;

public class Aventura {
	
	private Configuracion configuracion;
	private List<Ubicacion> ubicaciones;
	private AnalizadorDeTexto analizador;
	private Protagonista protagonista;
	
	public Aventura(Configuracion configuracion, List<Ubicacion> ubicaciones, String nombreJugador) {
		super();
		this.configuracion = configuracion;
		this.ubicaciones = ubicaciones;
		this.analizador = new AnalizadorDeTexto();
		this.protagonista = new Protagonista(nombreJugador, ubicaciones.get(0));
	}

	public Configuracion getConfiguracion() {
		return configuracion;
	}

	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	public void comenzar() {
		String entrada;
		
		this.describirContexto();
		entrada = analizador.recibirEntrada();
		
		if(this.quiereAgarrarItem(entrada) == true) {
			// habria que verificar condicion de endgame
			
		}
		
		else if(this.quiereMoverseDeUbicacion(entrada) == true) {
			// habria que verificar condicion de endgame
			
		}
		
		else if(this.quiereRealizarAccionConItem(entrada) == true) {
			
			
		}
		
		else if(true) { // quiere ver alrededor
			
			
		}
		else if(true) {	// algo mas?
			
			
		}
		else { // Syst.out no se entiende lo que dijiste
			
			
		}
			
		
		
		
	}
	
	public void describirContexto() {
		// mostrar por pantalla donde esta el personaje, places, conexiones, npcs		
	}
	
	public boolean quiereAgarrarItem(String entrada) {
		boolean condicion = false;
		Item objeto;
		objeto = analizador.contieneItem(entrada, this.protagonista.getUbicacionActual().getItems());
		// desarroyar getItems de ubicacion
		if(objeto != null) {
			this.protagonista.añadirItem(objeto); // se añade al inventario9
			this.ubicaciones.eliminarItem(objeto);	// se quita del place
			condicion = true;
		}
		return condicion;
	}
	
	public boolean quiereMoverseDeUbicacion(String entrada) {
		boolean condicion = false;
		Conexion conexion;
		Npc npc;
		conexion = analizador.contieneConexion(entrada, this.protagonista.getUbicacionActual().getConexiones());
		if(conexion != null) {
			npc = conexion.getObstaculo();	// podriamos fijarnos que tipo de obstaculo devuelve, si NPC o PLACE
			if(npc != null) {
				npc.getDialogo(); // ver donde printear esto
				
			}
			else {
				this.protagonista.desplazarse(conexion);
			}
			condicion = true;			
		}
		
		return condicion;
	}
	
	public boolean quiereRealizarAccionConItem(String entrada) {
		boolean condicion = false;
		Item item;
		String accion;
		Npc npc;
		Lugar lugar;
		objeto = analizador.contieneItem(entrada, this.protagonista.getInventario());
		
		if(item != null) {
			accion = analizador.contieneAccion(entrada, item.getAcciones());
			if(accion != null) {
				npc = analizador.contieneObstaculoNpc(entrada, this.protagonista.getUbicacionActual().getNpcs());
				if(npc != null) {
					npc.verificarTrigger(item);
					// codigo
					
				}
				else {
					lugar = analizador.contieneObstaculoLugar(entrada, this.protagonista.getUbicacionActual().getLugares());		
					if(lugar != null) {
						 lugar.verificarTrigger(item);	// implementar
						 // codigo
						
					}
					
				}
				
			}
		}
		
		return condicion;
	}
	
	
	
	
	
	
	
	
}