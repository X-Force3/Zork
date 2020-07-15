package chainOfResponsability;

public class AccionNoConocida implements RealizarAccion {
	
	@Override
	public String realizarAccion(String entrada) {
		
		return "No comprend� lo que quieres, intenta ser m�s preciso...";
	}

}
