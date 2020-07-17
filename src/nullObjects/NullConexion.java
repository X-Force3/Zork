package nullObjects;

import java.util.List;

import entidades.Conexion;
import entidades.Direccion;
import entidades.Ubicacion;

public class NullConexion extends Conexion {

	public static String valor = "Nada";
	
	public NullConexion() {
		super(Direccion.SUR,valor,valor);
	}

	@Override
	public String describirConexiones(List<Ubicacion> ubicaciones) {
		return " Esta conexion no lleva a ningun lado";
	}
	
	@Override
	public String conjugarConexion(Ubicacion ubicacion) {
		return "";
	}
	
	@Override
	public String toString() {
		return "Conexion vacia";
	}
	
}
