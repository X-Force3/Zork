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
		String salida;
		Conexion conexion = new Conexion(null, null, null);	// tuve que iniciarlizarlo porque sino tira errores mas adelante
		Item item = new Item(null, null, null, null, null); // same
		
		this.describirContexto();
		entrada = analizador.recibirEntrada();
		
		if(this.quiereAgarrarItem(entrada) == true) {
			salida = this.protagonista.describirInventario();	// implementar
			// habria que verificar condicion de endgame
		}
		
		else if(this.quiereMoverseDeUbicacion(entrada, conexion) == true) {
			salida = this.tratarObstaculo(conexion);
			// habria que verificar condicion de endgame	
		}
		
		else if(this.quiereRealizarAccionConItem(entrada, item) == true) {
			salida = this.realizarAccionConItem(entrada, item);
			//no hace falta verificar condicion de endgame
		}
		
		else if(this.quiereVerAlrededor(entrada) == true) {
			salida = this.protagonista.getUbicacionActual().describirUbicacion();
			
		}
		else {
			salida = "no se entendió lo que dijiste";
		}
		
		System.out.println(salida);
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
			this.protagonista.getUbicacionActual().eliminarItem(objeto);	// se quita del place
			condicion = true;
		}
		return condicion;
	}
	
	public boolean quiereMoverseDeUbicacion(String entrada, Conexion conexionDestino) {
		boolean condicion = false;
		conexionDestino = analizador.contieneConexion(entrada, this.protagonista.getUbicacionActual().getConexiones());
		if(conexionDestino != null) {
			condicion = true;
		}
		return condicion;
	}
	
	public boolean quiereRealizarAccionConItem(String entrada, Item item) {
		boolean condicion = false;
		item = analizador.contieneItem(entrada, this.protagonista.getInventario());
		if(item != null) {
			condicion = true;
		}
		return condicion;
	}
	
	
	public boolean quiereVerAlrededor(String entrada) {
		boolean condicion = false;
		if(entrada.contains("ver alrededor") || entrada.contains("mirar alrededor") || entrada.contains("describir lugar")) {
			condicion = true;
		}
		return condicion;
	}
	
	/**
	 * se fija que la conexion tenga obstaculo, si no lo tiene desplaza al protagonista
	 * si tiene, devuelve la descripcion del objeto que actua como obstaculo (lugar o npc)
	 * en caso que el obstaculo ya haya sido removido, desplaza al protagonista
	 */
	private String tratarObstaculo(Conexion conexion) {
		String salida;
		String obstaculo = conexion.getObstaculo();
		Npc obstaculoNpc;
		Lugar obstaculoLugar;
		if(obstaculo != null) {
			obstaculoNpc = analizador.contieneObstaculoNpc(obstaculo, this.protagonista.getUbicacionActual().getNpcs());
			if(obstaculoNpc != null) {
				salida = obstaculoNpc.getDescripcion();
			}
			else {
				obstaculoLugar = analizador.contieneObstaculoLugar(obstaculo, this.protagonista.getUbicacionActual().getLugares());
				if(obstaculoLugar != null) {
					salida = obstaculoLugar.getDescripcion();
				}
				else {
					this.protagonista.desplazarse(conexion);
					salida = this.protagonista.getUbicacionActual().describirUbicacion();
				}
			}
		}
		else {
			this.protagonista.desplazarse(conexion);
			salida = this.protagonista.getUbicacionActual().describirUbicacion();
		}
		return salida;
	}
	
	/**
	 * se fija si hay una accion con ese item, y si la hay busca si hay un NPC o un LUGAR en la entrada
	 * si encuentra, verifica y ejecuta el trigger
	 */
	private String realizarAccionConItem(String entrada, Item item) {
		Npc npc;
		Lugar lugar;
		String salida;
		String accion =  analizador.contieneAccion(entrada, item.getAcciones());
		if(accion != null) {
			npc = analizador.contieneObstaculoNpc(entrada, this.protagonista.getUbicacionActual().getNpcs());
			if(npc != null) {
				salida = npc.verificarTrigger(item);
			}
			else {
				lugar = analizador.contieneObstaculoLugar(entrada, this.protagonista.getUbicacionActual().getLugares());		
				if(lugar != null) {
					 salida = lugar.verificarTrigger(item);
				}
				else {
					salida = "no entiendo donde quieres realizar esa accion con ese item"; // u otra cosa
				}
			}
		}
		else {
			salida = "no entiendo que quieres hacer con ese item";
		}
		return salida;
	}
	
	
	
	
}