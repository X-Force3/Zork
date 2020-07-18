package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import componentes.InputTextListener;
import componentes.JuegoJFrame;
import javafx.util.Pair;
import juego.ManejoArchivos;
import juego.ArchivoTexto;

public class Aventura implements InputTextListener{

	private Configuracion configuracion;
	private Map<String, Ubicacion> ubicaciones = new HashMap<String, Ubicacion>();//los mapas est�n cargados
	private AnalizadorDeTexto analizador;
	private Protagonista protagonista;
	
	private JuegoJFrame ventanaJuego;
	private ArchivoTexto archivo;
	
	public Aventura(String pathAventura,String nombreJugador) {
		cargarAventura(pathAventura, nombreJugador);
	}

	public Aventura(Configuracion configuracion, Map<String, Ubicacion> ubicaciones, Protagonista protagonista) {
		this.configuracion = configuracion;
		this.ubicaciones = ubicaciones;
		this.protagonista = protagonista;
	}

	public void setAnalizador(AnalizadorDeTexto analizador) {
		this.analizador = analizador;
	}

	private void cargarAventura(String pathAventura,String nombreJugador) {
		ManejoArchivos manejoArchivos = new ManejoArchivos(pathAventura);
		analizador = new AnalizadorDeTexto();
		ubicaciones = manejoArchivos.getUbicacionesMap();
		configuracion = manejoArchivos.getConfiguracion();
		this.protagonista = new Protagonista(nombreJugador,manejoArchivos.getUbicaciones().get(0));
		this.archivo =  new ArchivoTexto("Historial.txt");
		
		ventanaJuego = new JuegoJFrame(this,configuracion.getTitulo());
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

//		this.describirContexto(); Juani: Si hubiera un while desde la l�nea 45 hasta la 76.. //Luz: pienso lo mismo
//		Cada vez que se ejecute el m�todo "comenzar" el porgrama va a mostrar la descripci�n de la ubicaci�n actual?
		
		//System.out.println(this.configuracion.getBienvenida() +" " +  protagonista.getNombre());
		//System.out.println(describirUbicacion()); //funciona
		
		String ini = this.configuracion.getBienvenida() +" " +  protagonista.getNombre() + ".\n" + describirUbicacion();
		ventanaJuego.setText(ini);
		ventanaJuego.setUbicacion(protagonista.getUbicacionActual());
		ventanaJuego.run();
		
		/*while(!fin) {
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
				salida = this.describirUbicacion();

			} else {
				salida = "No comprend� lo que quieres, intenta ser m�s preciso...";
			}

			descripcionEndgame = this.verificarEndgame(entrada);

			if (descripcionEndgame != "") {
				salida = descripcionEndgame;
				fin = true;
			}
			
			System.out.println(salida);
		}*/
	}
	

	public boolean quiereAgarrarItem(String entrada) {
		boolean condicion = false;
		Item objeto;
		objeto = analizador.contieneItem(entrada, this.protagonista.getUbicacionActual().getItems());
		if (objeto.getNombre() != " " && objeto.esItemDeInventario() && (entrada.contains("agarrar") ||
				entrada.contains("tomar") || entrada.contains("guardar"))) {
			ventanaJuego.actualizarItem(objeto.getNombre());
			this.protagonista.anadirItem(objeto); // se a�ade al inventario
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
	
	public Item quiereVerItem(String entrada) {
		Item item = analizador.contieneItem(entrada, this.getProtagonista().getUbicacionActual().getItems());
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
			if (obstaculoNpc.getNombre() != " ") {
				salida = obstaculoNpc.getDescripcion();
			} else {
				obstaculoLugar = analizador.contieneObstaculoLugar(obstaculo,
						this.protagonista.getUbicacionActual().getLugares());
				if (obstaculoLugar.getNombre() != " ") {
					salida = obstaculoLugar.getDescripcion();
				} else {
					this.protagonista.desplazarse(ubicaciones.get(conexion.getUbicacionDestino()));
					ventanaJuego.setUbicacion(protagonista.getUbicacionActual());
					// System.out.println(this.protagonista.getUbicacionActual().getNombre());
					// salida = this.protagonista.getUbicacionActual().describirUbicacion();
					salida = this.describirUbicacion();
				}
			}
		} else {
			this.protagonista.desplazarse(ubicaciones.get(conexion.getUbicacionDestino()));
			ventanaJuego.setUbicacion(protagonista.getUbicacionActual());
			salida = this.describirUbicacion();
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
		if (!accion.equals(" ") && !accion.equals("agarrar") && !accion.equals("tomar") && !accion.equals("guardar")) {
			npc = analizador.contieneObstaculoNpc(entrada, this.protagonista.getUbicacionActual().getNpcs());
			if (npc.getNombre() != " ") {
				String nombreAntes = npc.getNombre();
				Pair<String,Boolean> salidaYefecto = npc.verificarTrigger(item, this.protagonista);
				salida = salidaYefecto.getKey();
				if(salidaYefecto.getValue()) {
					ventanaJuego.actualizarNpc(nombreAntes);
				}
				this.protagonista.eliminarItem(item);// Luego de que el protagonista utiliza el �tem, se elimina de su
														// inventario.
			} else {
				lugar = analizador.contieneObstaculoLugar(entrada, this.protagonista.getUbicacionActual().getLugares());
				if (lugar.getNombre() != " ") {
					salida = lugar.verificarTrigger(item);
					this.protagonista.eliminarItem(item);
				}
				else if(analizador.afectaASiMismo(item.getEfectosSobre()) == true) {
					this.protagonista.aniadirVida();
					this.protagonista.eliminarItem(item);
					salida = "te curaste";
				}
				else {
					salida = "No entiendo por qu� quieres realizar eso...";
				}
			}
		} else {
			salida = "No entiendo qu� acci�n quieres realizar con ese �tem...";// Juani: Idem
		}
		
		return salida;
	}
	
	public String verItem(String entrada, Item item) {
		String salida;
		String accion = analizador.contieneAccion(entrada, item.getAcciones());
		
		if(!accion.equals(" ") && (accion.equals("mirar") || accion.equals("observar") || accion.equals("ver")))
			salida = item.getDescricpion();
		else
			salida = "No entiendo qu� acci�n quieres realizar con ese �tem...";
		
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
					|| endgame.getCondicion().contentEquals("accion")// Endgame de realizar una acci�n con un Item.
							&& endgame.verificarItemEndgame(this.analizador, this.protagonista)
							&& endgame.verificarAccionEndgame(entrada)
					|| endgame.getCondicion().contentEquals("muerte")// Endgame de muerte del Protagonista.
							&& endgame.verificarVidaEndgame(this.protagonista, entrada)) {
				salida = endgame.ejecutarFinal(this.protagonista);
				ventanaJuego.finalizar(protagonista.getNombre());
				
			}
		
		}

		return salida;
	}

	@Override
	public String toString() {
		return "Aventura \n[" + configuracion + ", \n" + ubicaciones + "]";
	}
	
	public String describirUbicacion() {
		return this.protagonista.getUbicacionActual().describirUbicacion(getUbicacionesConectadas(protagonista.getUbicacionActual().getConexiones()));
	}
	
	private List<Ubicacion> getUbicacionesConectadas(List<Conexion> conexiones) {
		List<Ubicacion> ubicacionesConectadas = new ArrayList<>();
		for(Conexion conexion : conexiones) {
			ubicacionesConectadas.add(ubicaciones.get(conexion.getUbicacionDestino()));
		}
		return ubicacionesConectadas;
	}
	
	@Override
	public void inputText(String newText) {
		String salida;
		String descripcionEndgame;
		Conexion conexion;
		Item item;

		if (this.quiereAgarrarItem(newText) == true) {
			salida = this.protagonista.describirInventario();
			// habria que verificar condicion de endgame
		}

		else if ((conexion = this.quiereMoverseDeUbicacion(newText)).getUbicacionDestino() != " ") {
			salida = this.tratarObstaculo(conexion);
			// habria que verificar condicion de endgame
		}

		else if ((item = this.quiereRealizarAccionConItem(newText)).getNombre() != " ") {
			salida = this.realizarAccionConItem(newText, item);
			// no hace falta verificar condicion de endgame
		}
		
		else if ((item = this.quiereVerItem(newText)).getNombre() != " ") {
			salida = this.verItem(newText, item);
		}
			// no hace falta verificar condicion de endgame
		

		else if (this.quiereVerInventario(newText) == true) {
			salida = this.protagonista.describirInventario();
		}
			
		else if (this.quiereVerAlrededor(newText) == true) {
			salida = this.describirUbicacion();
		}
		
		else {
			salida = "No comprendo lo que quieres, intenta ser m�s preciso...";
		}

		descripcionEndgame = this.verificarEndgame(newText);

		if (descripcionEndgame != "") {
			salida = descripcionEndgame + this.protagonista.getNombre() + "...";
			//fin = true;
		}
		
		this.archivo.escribirArchivoOut(newText, salida);
		ventanaJuego.setText(salida);
	}

	private boolean quiereVerInventario(String entrada) {
		boolean condicion = false;
		if (entrada.contains("ver inventario") || entrada.contains("mirar inventario")
				|| entrada.contains("describir inventario")) {
			condicion = true;
		}
		return condicion;
	}
	
}