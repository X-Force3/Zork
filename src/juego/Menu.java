package juego;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import entidades.Aventura;

public class Menu {

	private static final String RUTA = "recursos/";
	public static final String AVENTURA_1 = RUTA + "aventura.json";
	public static final String AVENTURA_2 = RUTA + "aventura2.json";
	public static final String AVENTURA_3 = RUTA + "aventura3.json";
	
	private Scanner scanner;
	private int opcion;
	private Map<Integer,String> nombreAventura; //Variables
	private String pathAventuraElegida;
	private String nombreJugador;
	
	public static void main(String[] args) {

		Menu menu = new Menu();
		menu.desplegarMenuOpciones();
		menu.solicitarNombreDeJugador();
		
		//Aventura aventura = new Aventura(menu.getPathAventuraElegida(), menu.getNombreJugador());*/
		
		Aventura aventura = new Aventura(Menu.AVENTURA_1, "Messi"); //Linea para ejecutar rápido
		aventura.comenzar();
		
	}
	
	public Menu() {
		this.scanner = new Scanner(System.in); 
		this.opcion = -1; //opción elegida del usuario
		this.nombreAventura = new HashMap<Integer,String>(); //Variables
		cargarMapaAventuras();
	}
	
	void desplegarMenuOpciones() {
		
		do{
			//Try catch para evitar que el programa termine si hay un error
			try{
				System.out.println("Seleccione la aventura:\n"
						+ "1.- " + this.nombreAventura.get(1)
						+ "\n2.- " + this.nombreAventura.get(2)
						+ "\n3.- " + this.nombreAventura.get(3)
						+ "\n0.- Salir\n");
				
				this.opcion = Integer.parseInt(this.scanner.nextLine()); 
				
			
				switch(this.opcion){
				case 1: 
					pathAventuraElegida = AVENTURA_1;
					System.out.println("selecciono la opcion 1\n");
					Aventura aventura = new Aventura(Menu.AVENTURA_1, "Messi"); //Linea para ejecutar rápido
					aventura.comenzar();
					break;
				case 2: 
					pathAventuraElegida = AVENTURA_2;
					System.out.println("selecciono la opcion 2");
					break;
				case 3: 
					pathAventuraElegida = AVENTURA_3;
					System.out.println ("selecciono la opcion 3");
					break;
				case 0: 
					System.out.println("Saliste del juego!");
					break;
				default:
					System.out.println("Opcion no existente, Intente nuevamente!");break;
				}
				
				System.out.println("\n"); 
				
			}catch(Exception e){
				System.out.println("Error! Ingrese un numero correcto de las opciones disponibles");
			}
		}while(!this.nombreAventura.containsKey(this.opcion));
		
	}
	
	public void solicitarNombreDeJugador() {
		do{
				System.out.println("Nombre de jugador:\n");
				nombreJugador = scanner.nextLine(); 
				System.out.println("\n"); 
			
		}while(nombreJugador == null || nombreJugador.isEmpty());
	}
	
	public static void cargarAventura(String patharchivo, String nombreAventura){
		System.out.println("Se cargo la aventura " + nombreAventura + "\n");
	}
	
	private void cargarMapaAventuras() {
		
		this.nombreAventura.put(1, "Aventura 1: La Isla Peligrosa");
		this.nombreAventura.put(2, "No disponible");
		this.nombreAventura.put(3, "No disponible");
		this.nombreAventura.put(0, "Salir");
	}

	public String getPathAventuraElegida() {
		return pathAventuraElegida;
	}

	public String getNombreJugador() {
		return nombreJugador;
	}
	
}