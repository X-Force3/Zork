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

	
	/**
	 * posibles finales:
	 *  - obtener un objeto
	 *  - llegar a un lugar
	 *  - llegar a un lugar con un objeto
	 *  - acciones
	 */
	
//	  "condition": "location",
//      "action": "move",
//      "thing": "taberna",
//      "description": "¡Enhorabuena! Llegaste a la taberna, donde te espera una noche de borrachera con Grog y otros colegas piratas."
//    
	
	
	
	public String ejecutarFinal() {
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

}
