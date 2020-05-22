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
	OESTE("oeste");

	// constructor
	Direccion(String string) {

	}

	public String getNombre() {
		if (this == SUR)
			return "sur";
		else if (this == NORTE)
			return "norte";
		else if (this == OESTE)
			return "oeste";
		else
			return "este";
	}
}
