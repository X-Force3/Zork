package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chainOfResponsability.MoverseDeUbicacion;
import juego.ManejoArchivos;

public class Aventura {

	private Configuracion configuracion;
	private Map<String, Ubicacion> ubicaciones = new HashMap<String, Ubicacion>();//los mapas est�n cargados
	private AnalizadorDeTexto analizador;
	private Protagonista protagonista;
	
	public Aventura(String pathAventura,String nombreJugador) {
		cargarAventura(pathAventura, nombreJugador);
	}

	public Aventura(Configuracion configuracion, Map<String, Ubicacion> ubicaciones, Protagonista protagonista) {
		this.configuracion = configuracion;
		this.ubicaciones = ubicaciones;
		this.protagonista = protagonista;
	}

	private void cargarAventura(String pathAventura,String nombreJugador) {
		ManejoArchivos manejoArchivos = new ManejoArchivos(pathAventura);
		analizador = new AnalizadorDeTexto();
		ubicaciones = manejoArchivos.getUbicacionesMap();
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
		///patron chain of Responsability
		MoverseDeUbicacion accion = new MoverseDeUbicacion(analizador, protagonista, ubicaciones);
		
		boolean fin = false;

//		this.describirContexto(); Juani: Si hubiera un while desde la l�nea 45 hasta la 76.. //Luz: pienso lo mismo
//		Cada vez que se ejecute el m�todo "comenzar" el porgrama va a mostrar la descripci�n de la ubicaci�n actual?
		System.out.println(this.configuracion.getBienvenida() +" " +  protagonista.getNombre());
		System.out.println(describirUbicacion()); //funciona
		
		while(!fin) {
			entrada = analizador.recibirEntrada();

			salida = accion.realizarAccion(entrada);

			descripcionEndgame = this.verificarEndgame(entrada);

			if (descripcionEndgame != "") {
				salida = descripcionEndgame;
				fin = true;
			}
			
			System.out.println(salida);
		}
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
							&& endgame.verificarVidaEndgame(this.protagonista))
				salida = endgame.ejecutarFinal();
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
	
}