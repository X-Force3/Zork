package juego;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import entidades.Aventura;

public class Menu {

	private static final String RUTA = "recursos/";
	private static final String AVENTURA_1 = RUTA + "aventura.json";
	private static final String AVENTURA_2 = RUTA + "aventura2.json";
	private static final String AVENTURA_3 = RUTA + "aventura3.json";
	
	private Scanner scanner;
	private int opcion;
	private Map<Integer,String> nombreAventura; //Variables
	ManejoArchivos manejoArchivos;
	
	public static void main(String[] args) {
		
		Menu menu = new Menu();
	
		menu.cargarMapaAventuras();
		menu.desplegarMenuOpciones();
		
		

	}
	
	public Menu() {
	
		this.scanner = new Scanner(System.in); 
		this.opcion = -1; //opci�n elegida del usuario
		this.nombreAventura = new HashMap<Integer,String>(); //Variables
		
	}
	
	private void desplegarMenuOpciones() {
		
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
					manejoArchivos = new ManejoArchivos(AVENTURA_1);
					System.out.println("selecciono la opcion 1");
					break;
				case 2: 
					manejoArchivos = new ManejoArchivos(AVENTURA_2);
					System.out.println("selecciono la opcion 2");
					break;
				case 3: 
					manejoArchivos = new ManejoArchivos(AVENTURA_3);
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
	
	
	public static void cargarAventura(String patharchivo, String nombreaventura){
		
		System.out.println("Se cargo la aventura " + nombreaventura);
		
		
		System.out.println("\n"); 
	}
	
	private void cargarMapaAventuras() {
		
		this.nombreAventura.put(1, "Taberna");
		this.nombreAventura.put(2, "Discoteca");
		this.nombreAventura.put(3, "La isla");
		this.nombreAventura.put(0, "Salir");
	}
	
}