package uiMain;

import java.util.*;
import gestorAplicacion.gestion.*;
import gestorAplicacion.personas.*;

public class Administrador {

	static Scanner sc = new Scanner(System.in);

	static long readLong() {
		return sc.nextLong();
	}

	static String readln() {
		sc.nextLine();
		return sc.nextLine();
	}
	

	public static void main(String args[]) {
		// Departamento dpto = new Departamento (/' noffbre Archivo '/);
		int opcion;
		do {
			System.out.println("¿Que operacion desea realizar?");
			System.out.println(" 1.");
			System.out.println(" 2.");
			System.out.println(" 3.");
			System.out.println(" 4.");
			System.out.println(" 5.");
			System.out.println(" 6.");
			System.out.print("Teclee opcion: ");
			opcion = (int) readLong();

			switch (opcion) {
			case 1:
				funcionalidad1();
				break;
			case 2:
				funcionalidad2();
				break;
			case 3:
				funcionalidad3();
				break;
			case 4:
				funcionalidad4();
				break;
			case 5:
				funcionalidad5();
				break;
			case 6:
				break;
			}
		} while (opcion != 6);
	}

	private static void funcionalidad1() {

	}

	private static void funcionalidad2() {

	}

	private static void funcionalidad3() {

	}

	private static void funcionalidad4() {

	}

	private static void funcionalidad5() {

	}
}