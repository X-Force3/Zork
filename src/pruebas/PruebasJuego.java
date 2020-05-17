package pruebas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidades.Aventura;
import juego.ManejoArchivos;


public class PruebasJuego {

	Aventura aventura;

	@Before
	public void cargarAventura() {
		aventura = ManejoArchivos.getAventura();
	}
	
	@Test
	public void queLaAventuraEsteCargada() {
		Assert.assertNotNull(aventura);
	}
	
	@Test
	public void describir() {
		System.out.println(aventura.getLocaciones().get(0).getGenero());
	}
}
