package chainOfResponsability;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entidades.AnalizadorDeTexto;
import entidades.Conexion;
import entidades.Lugar;
import entidades.Npc;
import entidades.Protagonista;
import entidades.Ubicacion;
import nullObjects.NullConexion;

public class MoverseDeUbicacion implements RealizarAccion{

	private RealizarAccion r;
	private AnalizadorDeTexto analizador;
	private Protagonista protagonista;
	private Map<String, Ubicacion> ubicaciones;
	
	public MoverseDeUbicacion(AnalizadorDeTexto a, Protagonista p, Map<String, Ubicacion> ubicaciones) {
		r = new VerAlrededor(a,p,ubicaciones);
		analizador = a;
		protagonista = p;
		this.ubicaciones = ubicaciones;
	}
	@Override
	public String realizarAccion(String entrada) {
		
		String salida;
		
		Conexion conexionDestino = analizador.contieneConexion(entrada,
				this.protagonista.getUbicacionActual().getConexiones());
		
		if(conexionDestino.getObstaculo() == NullConexion.valor) {
			salida = r.realizarAccion(entrada);
		} else {
			String obstaculo = conexionDestino.getObstaculo();
			Npc obstaculoNpc;
			Lugar obstaculoLugar;
			if (obstaculo != null) {
				obstaculoNpc = analizador.contieneObstaculoNpc(obstaculo, this.protagonista.getUbicacionActual().getNpcs());
				if (obstaculoNpc != null) {
					salida = obstaculoNpc.getDescripcion() + "\n<" +obstaculoNpc.getNombre().toUpperCase() +">: " + obstaculoNpc.getDialogo();
				} else {
					obstaculoLugar = analizador.contieneObstaculoLugar(obstaculo,
							this.protagonista.getUbicacionActual().getLugares());
					if (obstaculoLugar != null) {
						salida = obstaculoLugar.getDescripcion();
					} else {
						this.protagonista.desplazarse(ubicaciones.get(conexionDestino.getUbicacionDestino()));
						// System.out.println(this.protagonista.getUbicacionActual().getNombre());
						// salida = this.protagonista.getUbicacionActual().describirUbicacion();
						salida = this.describirUbicacion();
					}
				}
			} else {
				this.protagonista.desplazarse(ubicaciones.get(conexionDestino.getUbicacionDestino()));
				salida = this.describirUbicacion();
			}
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
