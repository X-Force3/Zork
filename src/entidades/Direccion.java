package entidades;

import com.google.gson.annotations.SerializedName;

public enum Direccion {

	@SerializedName("norte")
	NORTE("norte"),

	@SerializedName("sur")
	SUR("sur"),

	@SerializedName("este")
	ESTE("este"),

	@SerializedName("oeste")
	OESTE("oeste"),

	@SerializedName("sureste")
	SURESTE("sureste"),

	@SerializedName("noreste")
	NORESTE("noreste");
	
	private final String nombre;

	Direccion(String nom) {
		nombre = nom;
	}

	public String getNombre() {
		return nombre;
	}
}
