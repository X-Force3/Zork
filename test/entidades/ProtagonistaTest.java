package entidades;

import static org.junit.Assert.assertEquals;

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
		Conexion c = new Conexion(Direccion.OESTE,"Barco Pirata","Minas explosivas");
		
		Assert.assertEquals(protagonista.desplazarse(c), true);
	}

	@Test
	public void queUtilizaCorrectamenteUnItem() {
		List<String> acciones = new ArrayList<String>();
		List<String> efectosSobre = new ArrayList<String>();
		acciones.add("Usar");
		efectosSobre.add("Asustarse");
		
		Item item = new Item("Espejo",Genero.MALE,Numero.SINGULAR,acciones, efectosSobre);
			
		protagonista.añadirItem(item);
		protagonista.utilizarItem(item);///si lo uso, el item queda descartado de su inventario
		Assert.assertEquals(true, protagonista.añadirItem(item)); ///si lo añadio, quiere decir que no lo tenia en su inventario 
	}

	@Test
	public void queSeDesplazaCorrectamenteHaciaUnaUbicacion() {
		List<Lugar> lugares =new ArrayList<Lugar>();
		List<Conexion> conexiones = new ArrayList<Conexion>();
		
		conexiones.add(new Conexion(Direccion.ESTE,"Muelle",""));
		conexiones.add(new Conexion(Direccion.SUR,"Casa",""));
		conexiones.add(new Conexion(Direccion.OESTE,"Barco Pirata","Minas explosivas"));
		
		lugares.add(new Lugar());
		
		Ubicacion u = new Ubicacion("Pantano",Genero.MALE,Numero.SINGULAR,lugares, null, conexiones);
		
		Assert.assertEquals(protagonista.desplazarse(u), true);
	}
	
	@Test
	public void queHableSegunElDiagologoCorrespondiente() {
		String dialogo = "Presentacion";
		
		Assert.assertEquals(protagonista.hablar(dialogo), "Hola! Mi nombre es Nahuel");
	}
	
}
