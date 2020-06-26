package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import juego.ManejoArchivos;

public class Aventura {

	private Configuracion configuracion;
	private Map<String, Ubicacion> ubicaciones = new HashMap<String, Ubicacion>();//los mapas están cargados
	private Map<String, Item> items = new HashMap<String, Item>();
	private AnalizadorDeTexto analizador;
	private Protagonista protagonista;
	
	public Aventura(String pathAventura,String nombreJugador) {
		cargarAventura(pathAventura, nombreJugador);
	}

	private void cargarAventura(String pathAventura,String nombreJugador) {
		ManejoArchivos manejoArchivos = new ManejoArchivos(pathAventura);
		analizador = new AnalizadorDeTexto(manejoArchivos.getUbicacionesMap(),manejoArchivos.getItemsMap());
		ubicaciones = manejoArchivos.getUbicacionesMap();
		items = manejoArchivos.getItemsMap();
		configuracion = manejoArchivos.getConfiguracion();
		this.protagonista = new Protagonista(nombreJugador,manejoArchivos.getUbicaciones().get(0));
	}

	public Configuracion getConfiguracion() {
		return configuracion;
	}

	public AnalizadorDeTexto getAnalizador() {
		return this.analizador;
	}

	public Protagonista getProtagonista() {
		return this.protagonista;
	}

	public void comenzar() {
		String entrada;
		String salida;
		String descripcionEndgame;
		Conexion conexion;
		Item item;
		boolean fin = false;

//		this.describirContexto(); Juani: Si hubiera un while desde la línea 45 hasta la 76.. //Luz: pienso lo mismo
//		Cada vez que se ejecute el método "comenzar" el porgrama va a mostrar la descripción de la ubicación actual?
		System.out.println(this.configuracion.getBienvenida());
		System.out.println(describirUbicacionActual()); //funciona
		
		while(!fin) {
			entrada = analizador.recibirEntrada();

			if (this.quiereAgarrarItem(entrada) == true) {
				salida = this.protagonista.describirInventario();
				// habria que verificar condicion de endgame
			}

			else if ((conexion = this.quiereMoverseDeUbicacion(entrada)) != null) {
				salida = this.tratarObstaculo(conexion);
				// habria que verificar condicion de endgame
			}

			else if ((item = this.quiereRealizarAccionConItem(entrada)) != null) {
				salida = this.realizarAccionConItem(entrada, item);
				// no hace falta verificar condicion de endgame
			}

			else if (this.quiereVerAlrededor(entrada) == true) {
				salida = this.describirUbicacionActual();

			} else {
				salida = "No comprendí lo que quieres, intenta ser más preciso...";
			}

			descripcionEndgame = this.verificarEndgame(entrada);

			if (descripcionEndgame != "") {
				salida = descripcionEndgame;
				fin = true;
			}
			
			System.out.println(salida);
		}
	}
	

	public boolean quiereAgarrarItem(String entrada) {
		boolean condicion = false;
		Item objeto;
		objeto = analizador.contieneItem(entrada, this.protagonista.getUbicacionActual().getItems());
		if (objeto != null) {
			this.protagonista.añadirItem(objeto); // se añade al inventario
			this.protagonista.getUbicacionActual().eliminarItemUbicacion(objeto); // se quita del place
			condicion = true;
		}
		return condicion;
	}

	public Conexion quiereMoverseDeUbicacion(String entrada) {
		Conexion conexionDestino = analizador.contieneConexion(entrada,
				this.protagonista.getUbicacionActual().getConexiones());
		return conexionDestino;
	}

	public Item quiereRealizarAccionConItem(String entrada) {
		Item item = analizador.contieneItem(entrada, this.protagonista.getInventario());
		return item;
	}

	public boolean quiereVerAlrededor(String entrada) {
		boolean condicion = false;
		if (entrada.contains("ver alrededor") || entrada.contains("mirar alrededor")
				|| entrada.contains("describir lugar")) {
			condicion = true;
		}
		return condicion;
	}

	/**
	 * se fija que la conexion tenga obstaculo, si no lo tiene desplaza al
	 * protagonista si tiene, devuelve la descripcion del objeto que actua como
	 * obstaculo (lugar o npc) en caso que el obstaculo ya haya sido removido,
	 * desplaza al protagonista
	 */
	public String tratarObstaculo(Conexion conexion) {
		String salida;
		String obstaculo = conexion.getObstaculo();
		Npc obstaculoNpc;
		Lugar obstaculoLugar;
		if (obstaculo != null) {
			obstaculoNpc = analizador.contieneObstaculoNpc(obstaculo, this.protagonista.getUbicacionActual().getNpcs());
			if (obstaculoNpc != null) {
				salida = obstaculoNpc.getDescripcion();
			} else {
				obstaculoLugar = analizador.contieneObstaculoLugar(obstaculo,
						this.protagonista.getUbicacionActual().getLugares());
				if (obstaculoLugar != null) {
					salida = obstaculoLugar.getDescripcion();
				} else {
					this.protagonista.desplazarse(ubicaciones.get(conexion.getUbicacionDestino()));
					// System.out.println(this.protagonista.getUbicacionActual().getNombre());
					// salida = this.protagonista.getUbicacionActual().describirUbicacion();
					salida = this.describirUbicacionActual();
				}
			}
		} else {
			this.protagonista.desplazarse(ubicaciones.get(conexion.getUbicacionDestino()));
			salida = this.describirUbicacionActual();
		}
		return salida;
	}

	/**
	 * se fija si hay una accion con ese item, y si la hay busca si hay un NPC o un
	 * LUGAR en la entrada si encuentra, verifica y ejecuta el trigger
	 */
	public String realizarAccionConItem(String entrada, Item item) {
		Npc npc;
		Lugar lugar;
		String salida;
		String accion = analizador.contieneAccion(entrada, item.getAcciones());
		if (accion != null) {
			npc = analizador.contieneObstaculoNpc(entrada, this.protagonista.getUbicacionActual().getNpcs());
			if (npc != null) {
				salida = npc.verificarTrigger(item, this.protagonista);
				this.protagonista.eliminarItem(item);// Luego de que el protagonista utiliza el ítem, se elimina de su
														// inventario.
			} else {
				lugar = analizador.contieneObstaculoLugar(entrada, this.protagonista.getUbicacionActual().getLugares());
				if (lugar != null) {
					salida = lugar.verificarTrigger(item);
					this.protagonista.eliminarItem(item);// Luego de que el protagonista utiliza el ítem, se elimina de
															// su inventario.
				} else {
					salida = "No entiendo por qué quieres realizar eso..."; // Juani: Lo modifiqué porque me pareció mas
																			// preciso esto.
				}
			}
		} else {
			salida = "No entiendo qué acción quieres realizar con ese ítem...";// Juani: Idem.
		}
		return salida;
	}

	public String verificarEndgame(String entrada) {

		String salida = "";

		for (Endgame endgame : this.configuracion.getEndgames()) {

			if ((endgame.getCondicion().contentEquals("item")// Endgame de obtener un Item.
					&& endgame.verificarItemEndgame(this.analizador, this.protagonista))
					|| (endgame.getCondicion().contentEquals("ubicacion")// Endgame de llegar a una Ubicacion.
							&& endgame.verificarUbicacionEndgame(this.protagonista))
					|| (endgame.getCondicion().contentEquals("itemEnUbicacion")// Endgame de obtener un Item y llegar a una Ubicacion.
							&& endgame.verificarItemEndgame(this.analizador, this.protagonista)
							&& endgame.verificarUbicacionEndgame(this.protagonista))
					|| endgame.getCondicion().contentEquals("accion")// Endgame de realizar una acción con un Item.
							&& endgame.verificarItemEndgame(this.analizador, this.protagonista)
							&& endgame.verificarAccionEndgame(entrada)
					|| endgame.getCondicion().contentEquals("muerte")// Endgame de muerte del Protagonista.
							&& endgame.verificarVidaEndgame(this.protagonista))
				salida = endgame.ejecutarFinal();
		}

		return salida;
	}

	@Override
	public String toString() {
		return "Aventura \n[" + configuracion + ", \n" + ubicaciones + "]";
	}
	
	public String describirUbicacionActual() {
		return this.protagonista.getUbicacionActual().describirUbicacion(getUbicacionesConectadas(protagonista.getUbicacionActual().getConexiones()));
	}
	
	private List<Ubicacion> getUbicacionesConectadas(List<Conexion> conexiones) {
		List<Ubicacion> ubicacionesConectadas = new ArrayList<>();
		for(Conexion conexion : conexiones) {
			ubicacionesConectadas.add(ubicaciones.get(conexion.getUbicacionDestino()));
		}
		return ubicacionesConectadas;
	}
	
}