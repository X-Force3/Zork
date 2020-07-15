package chainOfResponsability;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entidades.AnalizadorDeTexto;
import entidades.Conexion;
import entidades.Protagonista;
import entidades.Ubicacion;

public class VerAlrededor implements RealizarAccion {

	private RealizarAccion r;
	private Map<String, Ubicacion> ubicaciones;
	private Protagonista protagonista;

	public VerAlrededor(AnalizadorDeTexto a, Protagonista p, Map<String, Ubicacion> ubicaciones) {
		r = new VerInventario(a,p);
		protagonista = p;
		this.ubicaciones = ubicaciones;
	}

	@Override
	public String realizarAccion(String entrada) {
		
		String salida;
		
		if (entrada.contains("ver alrededor") || entrada.contains("mirar alrededor")
				|| entrada.contains("describir lugar")) {
			
			salida = describirUbicacion();
		} else {
			salida = r.realizarAccion(entrada);
		}
		
		return salida;
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
