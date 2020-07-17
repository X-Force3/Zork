package entidades;

public class Endgame {

	private String condicion;
	private String accion;
	private String cosa;
	private String ubicacion;
	private String descripcion;

	public Endgame(String condicion, String accion, String cosa, String descripcion, String ubicacion) {
		this.condicion = condicion;
		this.accion = accion;
		this.cosa = cosa;
		this.ubicacion = ubicacion;
		this.descripcion = descripcion;
	}

	public boolean verificarItemEndgame(AnalizadorDeTexto analizador, Protagonista protagonista) {

		Item item = analizador.contieneItem(this.getCosa(), protagonista.getInventario());
		if (item.getNombre() != " ")
			return true;
		return false;
	}

	public boolean verificarUbicacionEndgame(Protagonista protagonista) {

		if ((protagonista.getUbicacionActual().getNombre()).equals(this.getUbicacion()))
			return true;
		return false;
	}

	public boolean verificarAccionEndgame(String entrada) {

		if (entrada.contains(this.getAccion()))
			return true;
		return false;
	}    
	
	public boolean verificarVidaEndgame(Protagonista protagonista, String entrada) {

		if(entrada.contains(this.getAccion()) && entrada.contains(this.getCosa())) {
			protagonista.morir();
			return true;
		}
		return false;
	}  
	
	public String ejecutarFinal(Protagonista protagonista) {
		return this.descripcion;	
	}

	public String getCondicion() {
		return condicion;
	}

	public String getAccion() {
		return accion;
	}

	public String getCosa() {
		return cosa;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}


	@Override
	public String toString() {
		return "Endgame [condicion=" + condicion + ", accion=" + accion + ", cosa=" + cosa + ", descripcion=" + descripcion + "]";
	}

}
