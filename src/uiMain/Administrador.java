package uiMain;

import java.util.*;
import gestorAplicacion.gestion.*;
import gestorAplicacion.personas.*;
import baseDatos.Serializador;

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

		// Serializador.DeserializarAsignaturas();
		// Serializador.DeserializarBecas();
		// Serializador.DeserializarFacultades();
		// Serializador.DeserializarProfesores();
		// Serializador.DeserializarEstudiantes();

		// Declaracion asignaturas para testeos
		//
		Asignatura discretas = new Asignatura("Discretas", 4, null, null, null);
		Asignatura vectorial = new Asignatura("Vectorial", 4, null, null, null);
		Asignatura diferencial = new Asignatura("Diferencial", 4, null, null, null);
		Asignatura introduccion = new Asignatura("introduccion", 2, null, null, null);
		Asignatura lineal = new Asignatura("Lineal", 4, null, null, null);
		Asignatura fundamentosProg = new Asignatura("Fund. Prog", 4, null, null, LineasEnfasis.SISTEMAS);
		Asignatura estad1 = new Asignatura("Estad. I", 4, null, null, null);
		Asignatura estad2 = new Asignatura("Estad. II", 2, null, null, LineasEnfasis.SISTEMAS);
		Asignatura sistemasOperativos = new Asignatura("Sistemas Operativos", 3, null, null, LineasEnfasis.SISTEMAS);
		Asignatura ingenieriaSoftware = new Asignatura("Ingenieria de Software", 4, null, null, LineasEnfasis.SISTEMAS);
		Asignatura pOO = new Asignatura("Programacion Orientada a Objetos", 4, null, null, LineasEnfasis.SISTEMAS);

		// Declaracion prerrequisitos para testing de recomendacion
		//
		ArrayList<Asignatura> requisitosEstad2 = new ArrayList<Asignatura>();
		requisitosEstad2.add(diferencial);
		requisitosEstad2.add(estad1);

		ArrayList<Asignatura> requisitosPOO = new ArrayList<Asignatura>();
		requisitosPOO.add(discretas);
		requisitosPOO.add(fundamentosProg);

		// Declaracion Hashmaps de asignaturas con notas para estudiantes
		//
		HashMap<Asignatura, Float> asignaturasE1 = new HashMap<Asignatura, Float>();
		asignaturasE1.put(discretas, 1f);
		asignaturasE1.put(lineal, 1f);

		HashMap<Asignatura, Float> asignaturasE2 = new HashMap<Asignatura, Float>();
		asignaturasE2.put(vectorial, 3f);
		asignaturasE2.put(diferencial, 4f);
		asignaturasE2.put(lineal, 4.5f);

		HashMap<Asignatura, Float> asignaturasE3 = new HashMap<Asignatura, Float>();
		asignaturasE3.put(ingenieriaSoftware, 5f);
		asignaturasE3.put(sistemasOperativos, 4f);
		asignaturasE3.put(pOO, 4.5f);

		HashMap<Asignatura, Float> asignaturasE4 = new HashMap<Asignatura, Float>();
		asignaturasE4.put(estad1, 3f);
		asignaturasE4.put(ingenieriaSoftware, 3f);
		asignaturasE4.put(lineal, 4.5f);

		// Declaracion estudiantes para los tests
		//
		Estudiante estudiante1 = new Estudiante(10000, "Juan camilo", 20, asignaturasE1, 4, 1, LineasEnfasis.SISTEMAS,
				new ArrayList<Asignatura>());
		ArrayList<Asignatura> asignaturasE1keys = new ArrayList<Asignatura>(asignaturasE1.keySet());
		estudiante1.setAsignaturasAprobadas(asignaturasE1keys);

		Estudiante estudiante2 = new Estudiante(10001, "Juan Diego", 20, asignaturasE2, 4, 1, LineasEnfasis.IA,
				new ArrayList<Asignatura>());
		ArrayList<Asignatura> asignaturasE2keys = new ArrayList<Asignatura>(asignaturasE2.keySet());
		estudiante2.setAsignaturasAprobadas(asignaturasE2keys);

		Estudiante estudiante3 = new Estudiante(10002, "Andres", 21, asignaturasE3, 4, 1, LineasEnfasis.SISTEMAS,
				new ArrayList<Asignatura>());
		ArrayList<Asignatura> asignaturasE3keys = new ArrayList<Asignatura>(asignaturasE3.keySet());
		estudiante2.setAsignaturasAprobadas(asignaturasE3keys);

		Estudiante estudiante4 = new Estudiante(10004, "Rocio", 18, asignaturasE4, 4, 1, LineasEnfasis.SISTEMAS,
				new ArrayList<Asignatura>());
		ArrayList<Asignatura> asignaturasE4keys = new ArrayList<Asignatura>(asignaturasE4.keySet());
		estudiante2.setAsignaturasAprobadas(asignaturasE4keys);

		// Declaracion facultades
		//
		ArrayList<Estudiante> estudiantesMinas = new ArrayList<Estudiante>(Arrays.asList(estudiante1, estudiante2));
		ArrayList<Estudiante> estudiantesCiencias = new ArrayList<Estudiante>(Arrays.asList(estudiante3, estudiante4));

		Facultad minas = new Facultad("Minas", new ArrayList<Profesor>(), estudiantesMinas);
		Facultad ciencias = new Facultad("Ciencias", new ArrayList<Profesor>(), estudiantesCiencias);

		// Declaracion Becas

		HashMap<Facultad, Integer> cuposPorFacultad1 = new HashMap<Facultad, Integer>();
		cuposPorFacultad1.put(minas, 1);
		cuposPorFacultad1.put(ciencias, 2);

		Beca becaEstudiantil1 = new Beca("Componente Excelencia",3.2f, cuposPorFacultad1, false);

		for (Estudiante e: Estudiante.getListaEstudiantes()) {
			System.out.println(e.getNombre());
		}
		Collections.sort(Estudiante.getListaEstudiantes());
		Collections.sort(Profesor.getListaProfesores());
		for (Estudiante e: Estudiante.getListaEstudiantes()) {
			System.out.println(e.getNombre());
		}
		
		int opcion;
		do {

			System.out.println("¿Que operacion desea realizar?");
			System.out.println(" 1. Recomendar Asignaturas");
			System.out.println(" 2. Calidad Estudiante");
			System.out.println(" 3. Información acerca de Becas");
			System.out.println(" 4.");
			System.out.println(" 5.");
			System.out.println(" 6. Salir del Sistema");
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
				// Serializador.SerializarAsignaturas();
				// Serializador.SerializarBecas();
				// Serializador.SerializarFacultades();
				// Serializador.SerializarProfesores();
				Serializador.SerializarEstudiantes();
				break;
			}
		} while (opcion != 6);
	}

	private static void funcionalidad1() {
		for (Estudiante e : Estudiante.getListaEstudiantes()) {
			System.out.println(e.RecomendarAsignaturas());
		}
	}

	private static void funcionalidad2() {
		for (Estudiante e : Estudiante.getListaEstudiantes()) {
			System.out.println(e.posicionSemestre(e));
		}
	}

	private static void funcionalidad3() {
		System.out.println("\n \n \n"); //TODO Limpiar la consola
		System.out.println("¿Sobre qué beca desea obtener información?");

		int contador = 0;
		int becaElegida;

		do {
			for (Beca b : Beca.getListaBecas()) {
				System.out.println(contador + ". " + b.getNombre());
				contador++;
			}
			System.out.println(contador + ". Salir del programa");

			System.out.println("Digite una opción: ");
			becaElegida = (int) readLong();


		} while (becaElegida < Beca.getListaBecas().size());
	
		System.out.println("\n");

		do {
			System.out.println(""); //TODO Limpiar la consola

			System.out.println("Digite una opción: ");
			becaElegida = (int) readLong();


		} while (becaElegida < Beca.getListaBecas().size());
	
		System.out.println(Beca.listaBecas.get(becaElegida).getBeneficiarios());


	
	}

	private static void funcionalidad4() {

	}

	private static void funcionalidad5() {

	}
}