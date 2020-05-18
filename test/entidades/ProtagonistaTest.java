package entidades;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProtagonistaTest {

	Protagonista protagonista = new Protagonista("Nahuel");

	@Before
	public void setup() {
		
	}

	@Test
	public void queRecogeCorrectamenteUnItem() {
		
		List<String> acciones = new ArrayList<String>();
		List<String> efectosSobre = new ArrayList<String>();
		acciones.add("Usar");
		efectosSobre.add("Asustarse");
		
		Item item = new Item("Espejo",Genero.MALE,Numero.SINGULAR,acciones, efectosSobre);
			
			protagonista.añadirItem(item);
			
			Assert.assertEquals(false, protagonista.añadirItem(item));///si devuelve falso, quiere decir que ya lo tiene en el inventario
			
	}

	@Test
	public void queSeDesplazaCorrectamenteHaciaUnaConexion() {

	}

	@Test
	public void queUtilizaCorrectamenteUnItem() {

	}

	@Test
	public void queSeDesplazaCorrectamenteHaciaUnaUbicacion() {

	}
}
