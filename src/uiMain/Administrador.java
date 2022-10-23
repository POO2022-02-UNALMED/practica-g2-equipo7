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

	private final static String espaciado = "\n \n \n";

	public static void main(String args[]) {
		// Departamento dpto = new Departamento (/' noffbre Archivo '/);

		// NO BORRAR COMENTARIOS
		// Serializador.DeserializarAsignaturas();
		// Serializador.DeserializarBecas();
		// Serializador.DeserializarFacultades();
		// Serializador.DeserializarProfesores();
		// Serializador.DeserializarEstudiantes();

		// Declaracion asignaturas para testeos
		//

		Asignatura estad1 = new Asignatura("Estad. I", 4, null, null, LineasEnfasis.ESTADISTICA);
		Asignatura estad2 = new Asignatura("Estad. II", 4, null, null, LineasEnfasis.ESTADISTICA);
		Asignatura analisisDatos = new Asignatura("Analisis de datos", 4, null, null, LineasEnfasis.ESTADISTICA);
		Asignatura estadDescriptiva = new Asignatura("Estad. Descriptiva", 2, null, null, LineasEnfasis.ESTADISTICA);

		Asignatura diferencial = new Asignatura("Diferencial", 4, null, null, LineasEnfasis.BASICAS);
		Asignatura fisica1 = new Asignatura("Fisica I", 4, null, null, LineasEnfasis.BASICAS);
		Asignatura introduccion = new Asignatura("introduccion", 2, null, null, LineasEnfasis.BASICAS);
		Asignatura lineal = new Asignatura("Algebra Lineal", 4, null, null, LineasEnfasis.BASICAS);
		Asignatura investigacionOp = new Asignatura("Invetigacion de Operaciones", 3, null, null,
				LineasEnfasis.BASICAS);

		Asignatura fundamentosProg = new Asignatura("Fund. Prog", 3, null, null, LineasEnfasis.SISTEMAS);
		Asignatura sistemasOperativos = new Asignatura("Sistemas Operativos", 3, null, null, LineasEnfasis.SISTEMAS);
		Asignatura ingenieriaSoftware = new Asignatura("Ingenieria de Software", 3, null, null, LineasEnfasis.SISTEMAS);
		Asignatura inteligenciaArtificial = new Asignatura("Ingenieria de Requisitos", 5, null, null,
				LineasEnfasis.SISTEMAS);
		Asignatura pOO = new Asignatura("Programacion Orientada a Objetos", 4, null, null, LineasEnfasis.SISTEMAS);

		Asignatura contabilidad = new Asignatura("Contabilidad", 3, null, null, LineasEnfasis.ADMINISTRACION);
		Asignatura gestion = new Asignatura("Gestion de proyectos", 3, null, null, LineasEnfasis.ADMINISTRACION);
		Asignatura econometria = new Asignatura("Econometría", 4, null, null, LineasEnfasis.ADMINISTRACION);
		Asignatura derecho = new Asignatura("Derecho Empresarial", 4, null, null, LineasEnfasis.ADMINISTRACION);

		Asignatura bioMolecular = new Asignatura("Biologia molecular", 4, null, null, LineasEnfasis.BIOLOGIA);
		Asignatura bioCelular = new Asignatura("Biologia celular", 4, null, null, LineasEnfasis.BIOLOGIA);
		Asignatura bioquimica = new Asignatura("Bioquimica", 4, null, null, LineasEnfasis.BIOLOGIA);

		// Declaracion prerrequisitos para testing de recomendacion
		//
		ArrayList<Asignatura> requisitosEstad2 = new ArrayList<Asignatura>();
		requisitosEstad2.add(diferencial);
		requisitosEstad2.add(estad1);

		estad2.setPrerrequisitos(requisitosEstad2);

		ArrayList<Asignatura> requisitosDescriptiva = new ArrayList<Asignatura>();
		requisitosDescriptiva.add(analisisDatos);
		requisitosDescriptiva.add(estad1);

		estadDescriptiva.setPrerrequisitos(requisitosDescriptiva);

		ArrayList<Asignatura> requisitosPOO = new ArrayList<Asignatura>();
		requisitosPOO.add(ingenieriaSoftware);
		requisitosPOO.add(fundamentosProg);

		pOO.setPrerrequisitos(requisitosPOO);

		ArrayList<Asignatura> requisitosIA = new ArrayList<Asignatura>();
		requisitosIA.add(ingenieriaSoftware);
		requisitosIA.add(pOO);
		requisitosIA.add(lineal);
		requisitosIA.add(diferencial);

		inteligenciaArtificial.setPrerrequisitos(requisitosIA);

		ArrayList<Asignatura> requisitosEconometria = new ArrayList<Asignatura>();
		requisitosEconometria.add(diferencial);
		requisitosEconometria.add(contabilidad);
		requisitosEconometria.add(gestion);

		econometria.setPrerrequisitos(requisitosEconometria);

		ArrayList<Asignatura> requisitosBioMolecular = new ArrayList<Asignatura>();
		requisitosBioMolecular.add(bioCelular);
		requisitosBioMolecular.add(bioquimica);

		bioMolecular.setPrerrequisitos(requisitosBioMolecular);

		// Declaracion Hashmaps de asignaturas con notas para estudiantes
		//
		HashMap<Asignatura, Float> asignaturasE1 = new HashMap<Asignatura, Float>();
		asignaturasE1.put(fundamentosProg, 1f);
		asignaturasE1.put(lineal, 1f);
		asignaturasE1.put(diferencial, 0.5f);
		asignaturasE1.put(ingenieriaSoftware, 4.2f);

		HashMap<Asignatura, Float> asignaturasE2 = new HashMap<Asignatura, Float>();
		asignaturasE2.put(fundamentosProg, 4.5f);
		asignaturasE2.put(fisica1, 3.4f);
		asignaturasE2.put(diferencial, 4.5f);
		asignaturasE2.put(ingenieriaSoftware, 4.2f);

		HashMap<Asignatura, Float> asignaturasE3 = new HashMap<Asignatura, Float>();
		asignaturasE3.put(sistemasOperativos, 5f);
		asignaturasE3.put(diferencial, 4.1f);
		asignaturasE3.put(pOO, 4.5f);
		asignaturasE3.put(lineal, 4f);

		HashMap<Asignatura, Float> asignaturasE4 = new HashMap<Asignatura, Float>();
		asignaturasE4.put(estad1, 3f);
		asignaturasE4.put(ingenieriaSoftware, 3f);
		asignaturasE4.put(diferencial, 4.5f);

		HashMap<Asignatura, Float> asignaturasE5 = new HashMap<Asignatura, Float>();
		asignaturasE5.put(contabilidad, 5f);
		asignaturasE5.put(diferencial, 5f);
		asignaturasE5.put(gestion, 4.5f);
		asignaturasE5.put(lineal, 4.9f);

		HashMap<Asignatura, Float> asignaturasE6 = new HashMap<Asignatura, Float>();
		asignaturasE6.put(contabilidad, 3.2f);
		asignaturasE6.put(diferencial, 3.7f);
		asignaturasE6.put(gestion, 4.5f);
		asignaturasE6.put(lineal, 3.4f);

		HashMap<Asignatura, Float> asignaturasE7 = new HashMap<Asignatura, Float>();
		asignaturasE7.put(bioMolecular, 5f);
		asignaturasE7.put(bioquimica, 4.1f);
		asignaturasE7.put(fisica1, 4.5f);
		asignaturasE7.put(lineal, 4f);

		HashMap<Asignatura, Float> asignaturasE8 = new HashMap<Asignatura, Float>();
		asignaturasE8.put(bioquimica, 4.7f);
		asignaturasE8.put(diferencial, 5f);
		asignaturasE8.put(investigacionOp, 4.1f);
		asignaturasE8.put(lineal, 5f);
		asignaturasE8.put(introduccion, 3f);

		HashMap<Asignatura, Float> asignaturasE9 = new HashMap<Asignatura, Float>();
		asignaturasE9.put(estad1, 5f);
		asignaturasE9.put(fisica1, 4.1f);
		asignaturasE9.put(diferencial, 4.5f);
		asignaturasE9.put(lineal, 4f);

		HashMap<Asignatura, Float> asignaturasE10 = new HashMap<Asignatura, Float>();
		asignaturasE10.put(estad1, 4.8f);
		asignaturasE10.put(analisisDatos, 4f);
		asignaturasE10.put(investigacionOp, 3.1f);
		asignaturasE10.put(lineal, 3f);
		asignaturasE10.put(introduccion, 5f);

		// Declaracion estudiantes para los tests
		//
		Estudiante estudiante1 = new Estudiante(10001, "Juan camilo", 20, asignaturasE1, 4, 1, LineasEnfasis.SISTEMAS,
				new ArrayList<Asignatura>(), 3, 890000, false, 1);
		ArrayList<Asignatura> asignaturasE1keys = new ArrayList<Asignatura>(asignaturasE1.keySet());
		estudiante1.setAsignaturasAprobadas(asignaturasE1keys);

		Estudiante estudiante2 = new Estudiante(10002, "Juan Diego", 20, asignaturasE2, 4, 1, LineasEnfasis.SISTEMAS,
				new ArrayList<Asignatura>(), 4, 2880000, true, 4);
		ArrayList<Asignatura> asignaturasE2keys = new ArrayList<Asignatura>(asignaturasE2.keySet());
		estudiante2.setAsignaturasAprobadas(asignaturasE2keys);

		Estudiante estudiante3 = new Estudiante(10003, "Andres", 21, asignaturasE3, 4, 1, LineasEnfasis.SISTEMAS,
				new ArrayList<Asignatura>(), 2, 3000000, true, 5);
		ArrayList<Asignatura> asignaturasE3keys = new ArrayList<Asignatura>(asignaturasE3.keySet());
		estudiante3.setAsignaturasAprobadas(asignaturasE3keys);

		Estudiante estudiante4 = new Estudiante(10004, "Rocio", 18, asignaturasE4, 4, 1, LineasEnfasis.SISTEMAS,
				new ArrayList<Asignatura>(), 5, 10000000, false, 4);
		ArrayList<Asignatura> asignaturasE4keys = new ArrayList<Asignatura>(asignaturasE4.keySet());
		estudiante4.setAsignaturasAprobadas(asignaturasE4keys);

		Estudiante estudiante5 = new Estudiante(10005, "Diana", 20, asignaturasE5, 4, 1, LineasEnfasis.ADMINISTRACION,
				new ArrayList<Asignatura>(), 2, 5100000, false, 4);
		ArrayList<Asignatura> asignaturasE5keys = new ArrayList<Asignatura>(asignaturasE5.keySet());
		estudiante5.setAsignaturasAprobadas(asignaturasE5keys);

		Estudiante estudiante6 = new Estudiante(10006, "Claudia", 20, asignaturasE6, 4, 1, LineasEnfasis.ADMINISTRACION,
				new ArrayList<Asignatura>(), 4, 2300000, true, 5);
		ArrayList<Asignatura> asignaturasE6keys = new ArrayList<Asignatura>(asignaturasE6.keySet());
		estudiante6.setAsignaturasAprobadas(asignaturasE6keys);

		Estudiante estudiante7 = new Estudiante(10007, "Brandon", 21, asignaturasE7, 4, 1, LineasEnfasis.BIOLOGIA,
				new ArrayList<Asignatura>(), 1, 3000000, true, 3);
		ArrayList<Asignatura> asignaturasE7keys = new ArrayList<Asignatura>(asignaturasE7.keySet());
		estudiante7.setAsignaturasAprobadas(asignaturasE7keys);

		Estudiante estudiante8 = new Estudiante(10008, "William", 18, asignaturasE8, 4, 1, LineasEnfasis.BIOLOGIA,
				new ArrayList<Asignatura>(), 5, 10700000, false, 3);
		ArrayList<Asignatura> asignaturasE8keys = new ArrayList<Asignatura>(asignaturasE8.keySet());
		estudiante8.setAsignaturasAprobadas(asignaturasE8keys);

		Estudiante estudiante9 = new Estudiante(10009, "Ana", 21, asignaturasE9, 4, 1, LineasEnfasis.ESTADISTICA,
				new ArrayList<Asignatura>(), 3, 6000000, true, 5);
		ArrayList<Asignatura> asignaturasE9keys = new ArrayList<Asignatura>(asignaturasE9.keySet());
		estudiante9.setAsignaturasAprobadas(asignaturasE9keys);

		Estudiante estudiante10 = new Estudiante(10010, "Antonia", 18, asignaturasE10, 4, 1, LineasEnfasis.BIOLOGIA,
				new ArrayList<Asignatura>(), 4, 8000000, true, 6);
		ArrayList<Asignatura> asignaturasE10keys = new ArrayList<Asignatura>(asignaturasE10.keySet());
		estudiante10.setAsignaturasAprobadas(asignaturasE10keys);

		for (Estudiante e : Estudiante.getListaEstudiantes()) {
			System.out.println(e.getNombre());
			System.out.println(Subsidio.calcularIES(e));
		}

		Subsidio.sortByIES();
		System.out.println("----------");
		for (Estudiante e : Estudiante.getListaEstudiantes()) {
			System.out.println(e.getNombre());
			System.out.println(Subsidio.calcularIES(e));
		}

		// Declaracion Profesores

		HashMap<Asignatura, Float> asignaturasP1 = new HashMap<Asignatura, Float>();
		asignaturasP1.put(fundamentosProg, 4.2f);
		asignaturasP1.put(ingenieriaSoftware, 4.1f);

		Profesor profesor1 = new Profesor(20001, "Antonio", 0, asignaturasP1, null, null);

		HashMap<Asignatura, Float> asignaturasP2 = new HashMap<Asignatura, Float>();
		asignaturasP2.put(fundamentosProg, 4.5f);
		asignaturasP2.put(ingenieriaSoftware, 4.9f);

		Profesor profesor2 = new Profesor(20002, "Carlos", 0, asignaturasP2, null, null);

		HashMap<Asignatura, Float> asignaturasP3 = new HashMap<Asignatura, Float>();
		asignaturasP3.put(sistemasOperativos, 5f);
		asignaturasP3.put(ingenieriaSoftware, 4.8f);
		asignaturasP3.put(inteligenciaArtificial, 4f);
		asignaturasP3.put(lineal, 4.5f);

		Profesor profesor3 = new Profesor(20003, "Julieta", 0, asignaturasP3, null, null);

		HashMap<Asignatura, Float> asignaturasP4 = new HashMap<Asignatura, Float>();
		asignaturasP4.put(inteligenciaArtificial, 4f);
		asignaturasP4.put(ingenieriaSoftware, 3f);
		asignaturasP4.put(pOO, 4f);
		asignaturasP4.put(estad1, 4.7f);
		asignaturasP4.put(estad2, 3f);

		Profesor profesor4 = new Profesor(20004, "Michael", 0, asignaturasP4, null, null);

		HashMap<Asignatura, Float> asignaturasP5 = new HashMap<Asignatura, Float>();
		asignaturasP5.put(analisisDatos, 4.8f);
		asignaturasP5.put(estad2, 4.3f);
		asignaturasP5.put(estad1, 4.5f);
		asignaturasP5.put(estadDescriptiva, 4.9f);
		asignaturasP5.put(investigacionOp, 3.7f);

		Profesor profesor5 = new Profesor(20005, "Maria Camila", 0, asignaturasP5, null, null);

		HashMap<Asignatura, Float> asignaturasP6 = new HashMap<Asignatura, Float>();
		asignaturasP6.put(bioMolecular, 4.5f);
		asignaturasP6.put(bioCelular, 5f);
		asignaturasP6.put(estadDescriptiva, 3.8f);
		asignaturasP6.put(estad1, 3.4f);
		Profesor profesor6 = new Profesor(20006, "Carlos", 0, asignaturasP6, null, null);

		HashMap<Asignatura, Float> asignaturasP7 = new HashMap<Asignatura, Float>();
		asignaturasP7.put(bioMolecular, 3.5f);
		asignaturasP7.put(bioCelular, 3.9f);
		asignaturasP7.put(lineal, 4.3f);
		asignaturasP7.put(fisica1, 3.9f);
		Profesor profesor7 = new Profesor(20007, "Adriana", 0, asignaturasP7, null, null);

		HashMap<Asignatura, Float> asignaturasP8 = new HashMap<Asignatura, Float>();
		asignaturasP8.put(contabilidad, 5f);
		asignaturasP8.put(gestion, 4.3f);
		asignaturasP8.put(lineal, 4f);
		asignaturasP8.put(investigacionOp, 5f);
		Profesor profesor8 = new Profesor(20008, "Mirabel", 0, asignaturasP8, null, null);

		// Declaracion Profesores de Asignaturas
		ArrayList<Profesor> profesorEstad1 = new ArrayList<Profesor>();
		profesorEstad1.add(profesor4);
		profesorEstad1.add(profesor5);
		profesorEstad1.add(profesor6);
		estad1.setProfesor(profesorEstad1);

		ArrayList<Profesor> profesorEstad2 = new ArrayList<Profesor>();
		profesorEstad2.add(profesor4);
		profesorEstad2.add(profesor5);
		estad2.setProfesor(profesorEstad2);

		ArrayList<Profesor> profesorAnalisisDatos = new ArrayList<Profesor>();
		profesorAnalisisDatos.add(profesor5);
		analisisDatos.setProfesor(profesorAnalisisDatos);

		ArrayList<Profesor> profesorEstadDescriptiva = new ArrayList<Profesor>();
		profesorEstadDescriptiva.add(profesor5);
		profesorEstadDescriptiva.add(profesor6);
		estadDescriptiva.setProfesor(profesorEstadDescriptiva);

		ArrayList<Profesor> profesorDiferencial = new ArrayList<Profesor>();
		diferencial.setProfesor(profesorDiferencial);

		ArrayList<Profesor> profesorFisica1 = new ArrayList<Profesor>();
		profesorFisica1.add(profesor7);
		fisica1.setProfesor(profesorFisica1);

		ArrayList<Profesor> profesorIntroduccion = new ArrayList<Profesor>();
		introduccion.setProfesor(profesorIntroduccion);

		ArrayList<Profesor> profesorLineal = new ArrayList<Profesor>();
		profesorLineal.add(profesor8);
		profesorLineal.add(profesor3);
		lineal.setProfesor(profesorLineal);

		ArrayList<Profesor> profesorInvestigacionOp = new ArrayList<Profesor>();
		profesorInvestigacionOp.add(profesor8);
		profesorInvestigacionOp.add(profesor5);
		investigacionOp.setProfesor(profesorInvestigacionOp);

		ArrayList<Profesor> profesorFundamentosProg = new ArrayList<Profesor>();
		profesorFundamentosProg.add(profesor2);
		profesorFundamentosProg.add(profesor1);
		fundamentosProg.setProfesor(profesorFundamentosProg);

		ArrayList<Profesor> profesorSistemasOperativos = new ArrayList<Profesor>();
		profesorSistemasOperativos.add(profesor3);
		sistemasOperativos.setProfesor(profesorSistemasOperativos);

		ArrayList<Profesor> profesorIngenieriaSoftware = new ArrayList<Profesor>();
		profesorIngenieriaSoftware.add(profesor1);
		profesorIngenieriaSoftware.add(profesor2);
		profesorIngenieriaSoftware.add(profesor3);
		profesorIngenieriaSoftware.add(profesor4);
		ingenieriaSoftware.setProfesor(profesorIngenieriaSoftware);

		ArrayList<Profesor> profesorIA = new ArrayList<Profesor>();
		profesorIA.add(profesor4);
		profesorIA.add(profesor3);
		inteligenciaArtificial.setProfesor(profesorIA);

		ArrayList<Profesor> profesorPOO = new ArrayList<Profesor>();
		profesorPOO.add(profesor4);
		pOO.setProfesor(profesorPOO);

		ArrayList<Profesor> profesorContabilidad = new ArrayList<Profesor>();
		profesorContabilidad.add(profesor8);
		contabilidad.setProfesor(profesorContabilidad);

		ArrayList<Profesor> profesorGestion = new ArrayList<Profesor>();
		profesorGestion.add(profesor8);
		gestion.setProfesor(profesorGestion);

		ArrayList<Profesor> profesorEconometria = new ArrayList<Profesor>();
		econometria.setProfesor(profesorEconometria);

		ArrayList<Profesor> profesorDerecho = new ArrayList<Profesor>();
		derecho.setProfesor(profesorDerecho);

		ArrayList<Profesor> profesorBioMolecular = new ArrayList<Profesor>();
		profesorBioMolecular.add(profesor7);
		profesorBioMolecular.add(profesor6);
		bioMolecular.setProfesor(profesorBioMolecular);

		ArrayList<Profesor> profesorBioCelular = new ArrayList<Profesor>();
		profesorBioCelular.add(profesor7);
		profesorBioCelular.add(profesor6);
		bioCelular.setProfesor(profesorBioCelular);

		ArrayList<Profesor> profesorBioQuimica = new ArrayList<Profesor>();
		bioquimica.setProfesor(profesorBioQuimica);

		// Declaracion facultades

		ArrayList<Estudiante> estudiantesMinas = new ArrayList<Estudiante>(
				Arrays.asList(estudiante1, estudiante2, estudiante3, estudiante4, estudiante5, estudiante6));
		ArrayList<Estudiante> estudiantesCiencias = new ArrayList<Estudiante>(
				Arrays.asList(estudiante7, estudiante8, estudiante9, estudiante10));
		ArrayList<Profesor> profesoresMinas = new ArrayList<Profesor>(
				Arrays.asList(profesor1, profesor2, profesor3, profesor4));
		ArrayList<Profesor> profesoresCiencias = new ArrayList<Profesor>(
				Arrays.asList(profesor5, profesor6, profesor7, profesor8));

		Facultad minas = new Facultad("Minas", profesoresMinas, estudiantesMinas);
		Facultad ciencias = new Facultad("Ciencias", profesoresCiencias, estudiantesCiencias);

		// Declaracion Becas

		HashMap<Facultad, Integer> cuposPorFacultad1 = new HashMap<Facultad, Integer>();
		cuposPorFacultad1.put(minas, 5);
		cuposPorFacultad1.put(ciencias, 2);
		HashMap<Facultad, Integer> cuposPorFacultad2 = new HashMap<Facultad, Integer>();
		cuposPorFacultad2.put(minas, 3);
		cuposPorFacultad2.put(ciencias, 2);
		HashMap<Facultad, Integer> cuposPorFacultad3 = new HashMap<Facultad, Integer>();
		cuposPorFacultad3.put(minas, 1);
		cuposPorFacultad3.put(ciencias, 1);
		HashMap<Facultad, Integer> cuposPorFacultad4 = new HashMap<Facultad, Integer>();
		cuposPorFacultad4.put(minas, 6);
		cuposPorFacultad4.put(ciencias, 3);

		Beca becaEstudiantil1 = new Beca("Componente Excelencia", 4f, cuposPorFacultad1, false);
		Beca becaEstudiantil2 = new Beca("Beca Pedro Nel", 4.5f, cuposPorFacultad2, true);
		Beca becaEstudiantil3 = new Beca("Estudiante Excepcional", 4.7f, cuposPorFacultad3, false);
		Beca becaEstudiantil4 = new Beca("Docente Destacado", 4f, cuposPorFacultad4, false);

		Subsidio subsidio1 = new Subsidio("Subsidio Alimentario", 25, 5);
		Subsidio subsidio2 = new Subsidio("Subsidio de Transporte", 40, 5);
		Subsidio subsidio3 = new Subsidio("Subsidio de hospedaje", 0, 5);

		int opcion;
		do {
			System.out.println("\n--- Menu Inicial ---\n");
			System.out.println("¿Que operacion desea realizar?");
			System.out.println(" 1. Recomendar Asignaturas");
			System.out.println(" 2. Calidad Estudiante");
			System.out.println(" 3. Información de Becas");
			System.out.println(" 4. Informacion de Subsidios Estudiantiles");
			System.out.println(" 5. Consultar posición de un estudiante");
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
				Serializador.SerializarAsignaturas();
				Serializador.SerializarBecas();
				Serializador.SerializarFacultades();
				Serializador.SerializarProfesores();
				Serializador.SerializarEstudiantes();
				System.exit(0);
				break;
			}
		} while (opcion != 6);
	}

	private static int terminarPrograma() {

		int subOpcion = 0;
		do {
			System.out.println("-Ingrese 1 para Regresar al Inicio");
			System.out.println("-Ingrese 0 para Salir del programa.");
			System.out.println("Digite una opción: ");
			subOpcion = (int) readLong();

		} while (subOpcion > 1 || subOpcion < 0);
		return subOpcion;
	}

	private static void funcionalidad1() {
		int seleccion = 1;

		do {
			System.out.println(espaciado);
			System.out.println("\n--- Menu Recomendar Asignaturas ---\n");
			if (seleccion != 1) {
				System.out.println("--- Ingrese una opción valida ---\n");
			}

			System.out.println("¿Con cual criterio desea filtrar la recomendacion de asignatura?");
			System.out.println("1. Asignatura por linea de enfasis");
			System.out.println("2. Asignaturas basicas");
			System.out.println("3. Regresar al Inicio");

			System.out.println("Digite una opción: ");
			seleccion = (int) readLong();
			if (seleccion == 3) {
				return;
			}
		} while (seleccion > 3 || seleccion < 1);

		switch (seleccion) {
		case 1:
			int subOpcion = 1;
			do {
				System.out.println(espaciado);
				System.out.println("--- Menu Recomendar Asignaturas ---");
				System.out.println("Citerio elegido: Linea de enfasis\n");
				if (subOpcion != 1) {
					System.out.println("--- Ingrese una opción valida ---\n");
				}

				System.out.println("¿Que Recomendacion desea consultar?");
				System.out.println(" 1. Recomendar asignatura a un estudiante");
				System.out.println(" 2. Recomendacion de asignatura de todos los estudiantes");
				System.out.println(" 3. Regresar al Inicio");

				System.out.println("Digite una opción: ");
				subOpcion = (int) readLong();
				if (subOpcion == 3) {
					System.out.println(espaciado);
					return;
				}
			} while (subOpcion > 3 || subOpcion < 1);

			switch (subOpcion) {
			case 1:
				int subOpcion1 = 0;
				int documento = 0;
				Estudiante vinculadoConsultado = null;
				boolean condicional = false;
				ArrayList<Asignatura> recomendacion = null;

				do {
					System.out.println(espaciado);
					System.out.println("--- Menu Recomendar Asignaturas ---");
					System.out.println("Citerio elegido: Linea de enfasis");
					System.out.println("Opcion Elegida: Recomendar al Estudiante\n");

					System.out.println("-Ingrese el documento del Estudiante.");
					System.out.println("-Ingrese 0 para salir.");
					System.out.println("Digite una opción: ");

					documento = (int) readLong();
					if (documento == 0) {
						System.out.println(espaciado);
						return;
					}
					for (Estudiante e : Estudiante.getListaEstudiantes()) {
						if (documento == e.getDocumento()) {
							condicional = true;
							vinculadoConsultado = e;
						}
					}
					if (condicional == false) {
						System.out.println("\n--- Documento Invalido ---");
					}

					else {
						recomendacion = vinculadoConsultado.RecomendarAsignaturas();
						if (!recomendacion.isEmpty()) {
							System.out.println("\nLas asignaturas recomendadas para " + vinculadoConsultado.getNombre()
									+ " en su linea de enfasis son:");
							for (Asignatura i : recomendacion) {
								System.out.println("- " + i.getNombre());
							}
						} else {
							System.out.println("No hay ninguna asignatura que te recomienda de tu linea de enfasis");
						}
					}
				} while(!condicional);
				subOpcion1 = 1;
				do {
					if (subOpcion1 != 1) {
						System.out.println("--- Ingrese una opción valida ---\n");
					}

					System.out.println("\n ¿Deseas consultar los profesores de las asignaturas recomendadas?\n");
					System.out.println("-Ingrese 1 para consultar los profesores");
					System.out.println("-Ingrese 2 para salir");
					
					System.out.println("Digite una opción: ");
					subOpcion1 = (int) readLong();

					if (subOpcion1 == 2) {
						int terminacion = terminarPrograma();
						switch (terminacion) {
						case 1:
							System.out.println(espaciado);
							return;
						case 0:
							System.exit(0);
						}
					} if(subOpcion1 == 1) {
						for (Asignatura i : recomendacion) {
							ArrayList<Profesor> profesores = i.getProfesor();
							if(!profesores.isEmpty()) {
								System.out.println("\nLos profesores de " + i.getNombre() + " son:");
								for(Profesor e: profesores) {
									System.out.println(e.getNombre());
								}
							}
							else {
								System.out.println("\n-Actualmente no hay ningun profesor que dicte " + i.getNombre());
							}
						}
						
						System.out.println("");
						int terminacion = terminarPrograma();
						switch (terminacion) {
						case 1:
							System.out.println(espaciado);
							return;
						case 0:
							System.exit(0);
						}
					}
					
				} while(subOpcion1> 2 || subOpcion1 < 1);
			case 2:
				System.out.println(espaciado);
				System.out.println("--- Menu Recomendar Asignaturas ---");
				System.out.println("Citerio elegido: Linea de enfasis");
				System.out.println("Opcion Elegida: Recomendar al Estudiante\n");

				Formatter fmt = new Formatter();
				fmt.format("%-20s %s\n", "Nombre Estudiante", "Asignaturas Recomendadas");
				fmt.format("%s\n","----------------------------------------------------------------");

				for(Estudiante e: Estudiante.getListaEstudiantes()) {
					ArrayList<String> listadoRecomendar = new ArrayList<String>();
					for(Asignatura i: e.RecomendarAsignaturas()) {
						listadoRecomendar.add(i.getNombre());
					}
					String lista = String.join(", ", listadoRecomendar);
					fmt.format("%-20s %s\n", e.getNombre(), lista);
				}
				System.out.println(fmt);

				int terminacion = terminarPrograma();
				switch (terminacion) {
				case 1:
					System.out.println(espaciado);
					return;
				case 0:
					System.exit(0);
				}
			}

		case 2:
			int subOpcion2 = 0;
			do {
				System.out.println(espaciado);
				System.out.println("--- Menu Recomendar Asignaturas ---");
				System.out.println("Citerio elegido: Basicas\n");
				if (subOpcion2 != 0) {
					System.out.println("--- Ingrese una opción valida ---\n");
				}

				System.out.println("¿Que Recomendacion desea consultar?");
				System.out.println(" 1. Recomendar asignatura a un estudiante");
				System.out.println(" 2. Recomendacion de asignatura de todos los estudiantes");
				System.out.println(" 3. Regresar al Inicio");

				System.out.println("Digite una opción: ");
				subOpcion2 = (int) readLong();
				if (subOpcion2 == 3) {
					System.out.println(espaciado);
					return;
				}
			} while (subOpcion2 > 3 || subOpcion2 < 1);

			switch (subOpcion2) {
			case 1:
				int subOpcion21 = 0;
				int documento2 = 0;
				Estudiante vinculadoConsultado2 = null;
				boolean condicional2 = false;
				ArrayList<Asignatura> recomendacion2 = null;

				do {
					System.out.println(espaciado);
					System.out.println("--- Menu Recomendar Asignaturas ---");
					System.out.println("Citerio elegido: Basicas");
					System.out.println("Opcion Elegida: Recomendar al Estudiante\n");

					System.out.println("-Ingrese el documento del Estudiante.");
					System.out.println("-Ingrese 0 para salir.");
					System.out.println("Digite una opción: ");

					documento2 = (int) readLong();
					if (documento2 == 0) {
						System.out.println(espaciado);
						return;
					}
					for (Estudiante e : Estudiante.getListaEstudiantes()) {
						if (documento2 == e.getDocumento()) {
							condicional2 = true;
							vinculadoConsultado2 = e;
						}
					}
					if (condicional2 == false) {
						System.out.println("\n--- Documento Invalido ---");
					}

					else {
						recomendacion2 = vinculadoConsultado2.RecomendarAsignaturasBasicas();
						if (!recomendacion2.isEmpty()) {
							System.out.println("\nLas asignaturas Basicas recomendadas para "
									+ vinculadoConsultado2.getNombre() + " son:");
							for (Asignatura i : recomendacion2) {
								System.out.println(i.getNombre());
							}
						} else {
							System.out.println("No hay ninguna asignatura Basica que te recomiende");
						}
					}
				} while(!condicional2);
				subOpcion21 = 1;
				do {
					if (subOpcion21 != 1) {
						System.out.println("--- Ingrese una opción valida ---\n");
					}

					System.out.println("\n ¿Deseas consultar los profesores de las asignaturas recomendadas?\n");
					System.out.println("-Ingrese 1 para consultar los profesores");
					System.out.println("-Ingrese 2 para salir");

					System.out.println("Digite una opción: ");
					subOpcion21 = (int) readLong();

					if (subOpcion21 == 2) {
						int terminacion = terminarPrograma();
						switch (terminacion) {
						case 1:
							System.out.println(espaciado);
							return;
						case 0:
							System.exit(0);
						}
					}
					if (subOpcion21 == 1) {
						for(Asignatura i: recomendacion2) {
							ArrayList<Profesor> profesores = i.getProfesor();
							if(!profesores.isEmpty()) {
								System.out.println("\nLos profesores de " + i.getNombre() + " son:");
								for(Profesor e: profesores) {
									System.out.println(e.getNombre());
								}
							}
							else {
								System.out.println("\n-Actualmente no hay ningun profesor que dicte " + i.getNombre());
							}
						}
						
						System.out.println("");
						int terminacion = terminarPrograma();
						switch (terminacion) {
						case 1:
							System.out.println(espaciado);
							return;
						case 0:
							System.exit(0);
						}
					}
					
				} while(subOpcion21 > 2 || subOpcion21 < 1);
			case 2:
				System.out.println(espaciado);
				System.out.println("--- Menu Recomendar Asignaturas ---");
				System.out.println("Citerio elegido: Basicas");
				System.out.println("Opcion Elegida: Recomendar al Estudiante\n");

				Formatter fmt = new Formatter();
				fmt.format("%-20s %s\n", "Nombre Estudiante", "Asignaturas Recomendadas");
				fmt.format("%s\n","----------------------------------------------------------------");

				for(Estudiante e: Estudiante.getListaEstudiantes()) {
					ArrayList<String> listadoRecomendar = new ArrayList<String>();
					for(Asignatura i: e.RecomendarAsignaturasBasicas()) {
						listadoRecomendar.add(i.getNombre());
					}
					String lista = String.join(", ", listadoRecomendar);
					fmt.format("%-20s %s\n", e.getNombre(), lista);
				}
				System.out.println(fmt);

				int terminacion = terminarPrograma();
				switch (terminacion) {
				case 1:
					System.out.println(espaciado);
					return;
				case 0:
					System.exit(0);
				}
			}
		}
	}

	private static void funcionalidad2() {
		// Funcionalidad Calidad de estudiantes
		int seleccion = 1;

		do {
			System.out.println(espaciado);
			System.out.println("\n--- Menu Calidad de Estudiante ---\n");
			if (seleccion != 1) {
				System.out.println("--- Ingrese una opción valida ---\n");
			}

			System.out.println("¿Que información desea obtener acerca del estudiante?");
			System.out.println("1. Consultar la calidad de un estudiante por documento");
			System.out.println("2. Consultar la calidad de los estudiantes inscritos a una facultad");
			System.out.println("3. Consultar la calidad de los estudiantes inscritos a una linea de enfasis");
			System.out.println("4. Regresar al Inicio");

			System.out.println("Digite una opción: ");
			seleccion = (int) readLong();
			if (seleccion == 4) {
				return;
			}

		} while (seleccion > 4 || seleccion < 1);

		switch (seleccion){
			case 1:
				int documento = 0;
				Estudiante estudianteConsultado = null;
				boolean estudianteExiste = false;

				do {
					System.out.println(espaciado);
					System.out.println("--- Menu Calidad de Estudiante ---");
					System.out.println("Opcion Elegida: Consultar la calidad de un estudiante por documento\n");

					System.out.println("-Ingrese el documento del Estudiante.");
					System.out.println("-Ingrese 0 para salir.");
					System.out.println("Digite una opción: ");

					documento = (int) readLong();
					if (documento == 0) {
						System.out.println(espaciado);
						return;
					}
					for (Estudiante e : Estudiante.getListaEstudiantes()) {
						if (documento == e.getDocumento()) {
							estudianteExiste = true;
							estudianteConsultado = e;
						}
					}
					if (estudianteExiste == false) {
						System.out.println("\n--- Documento Invalido ---");
					} else {
						System.out.println("\nEl estudiante con el documento " + documento + " tiene una calidad " + estudianteConsultado.calidadEstudiante());
						System.out.println("");
						int terminacion = terminarPrograma();
						switch (terminacion) {
						case 1:
							System.out.println(espaciado);
							return;
						case 0:
							System.exit(0);
						}
					}
				} while(!estudianteExiste);

			case 2:
				int subOpcion = 0;
				do {
					System.out.println(espaciado);
					System.out.println("--- Menu Calidad de Estudiante ---");
					System.out.println("Opcion Elegida: Consultar la calidad de los estudiantes inscritos a una facultad\n");

					int contador = 1;

					System.out.println("-Seleccione la facultad de la que desea obtener información.");
					for (Facultad facultad : Facultad.getListaFacultades()){
						System.out.println(contador + ". " + facultad.getNombre());
						contador++;
					}
					System.out.println(contador + ". Regresar al Inicio");

					System.out.println("Digite una opción: ");
					subOpcion = (int) readLong();
					if (subOpcion == contador) {
						return;
					}
				}   while (subOpcion > Facultad.getListaFacultades().size() + 1 || subOpcion < 1);

				Facultad facultadElegida = Facultad.getListaFacultades().get(subOpcion - 1);

				Formatter fmt = new Formatter();
				fmt.format("%15s %15s %15s\n", "Nombre", "Calidad", "Facultad");
				for (Estudiante e : facultadElegida.getEstudiantes()){
					fmt.format("%14s %14s %17s\n", e.getNombre(), e.calidadEstudiante(),
							facultadElegida.getNombre());
				}
				System.out.println(espaciado);
				System.out.println("--- Menu Calidad de Estudiante ---");
				System.out.println("Opcion Elegida: Consultar la calidad de los estudiantes inscritos a una facultad");
				System.out.println("Facultad Elegida: " + facultadElegida.getNombre() + "\n");

				System.out.println(fmt);
				int terminacion = terminarPrograma();
				switch (terminacion) {
					case 1:
						System.out.println(espaciado);
						return;
					case 0:
						System.exit(0);
				}

			case 3:
				int subOpcion1 = 0;
				do {
					System.out.println(espaciado);
					System.out.println("--- Menu Calidad de Estudiante ---");
					System.out.println("Opcion Elegida: Consultar la calidad de los estudiantes inscritos a una linea de enfasis\n");

					int contador = 1;

					System.out.println("-Seleccione la linea de enfasis de la que desea obtener información.");
					for (LineasEnfasis le : LineasEnfasis.values()){
						System.out.println(contador + ". " + le);
						contador++;
					}
					System.out.println(contador + ". Regresar al Inicio");

					System.out.println("Digite una opción: ");
					subOpcion1 = (int) readLong();
					if (subOpcion1 == contador) {
						return;
					}
				}  while (subOpcion1 > LineasEnfasis.values().length || subOpcion1 < 0);

				LineasEnfasis lineaElegida = LineasEnfasis.values()[subOpcion1 - 1];

				fmt = new Formatter();
				fmt.format("%15s %15s %15s\n", "Nombre", "Calidad", "Facultad");

				for (Estudiante e : Estudiante.getListaEstudiantes()){
					if(e.getLineaEnfasis().equals(lineaElegida)){
						fmt.format("%14s %14s %17s\n", e.getNombre(), e.calidadEstudiante(),
							lineaElegida.toString());
					}
				}



				System.out.println(espaciado);
				System.out.println("--- Menu Calidad de Estudiante ---");
				System.out.println("Opcion Elegida: Consultar la calidad de los estudiantes inscritos a una facultad");
				System.out.println("Linea de Enfasis Elegida: " + lineaElegida.toString() + "\n");

				System.out.println(fmt);
				terminacion = terminarPrograma();
				switch (terminacion) {
					case 1:
						System.out.println(espaciado);
						return;
					case 0:
						System.exit(0);
				}
		}
	}

	/**
	 * 
	 */
	/**
	 * 
	 */
	private static void funcionalidad3() {
		// Funcionalidad Becas.
		int seleccion = 1;

		do {
			System.out.println(espaciado);
			System.out.println("\n--- Menu Becas ---\n");
			if (seleccion != 1) {
				System.out.println("--- Ingrese una opción valida ---\n");
			}
			int contador = 1;

			System.out.println("¿Sobre qué beca desea obtener información?");
			for (Beca b : Beca.getListaBecas()) {
				System.out.println(contador + ". " + b.getNombre());
				contador++;
			}
			System.out.println(contador + ". Regresar al Inicio");

			System.out.println("Digite una opción: ");
			seleccion = (int) readLong();
			if (seleccion == contador) {
				return;
			}

		} while (seleccion > Beca.getListaBecas().size() + 1 || seleccion < 1);

		Beca BecaElegida = Beca.getListaBecas().get(seleccion - 1);
		int subOpcion = 0;

		do {
			System.out.println(espaciado);
			System.out.println("--- Menu Becas ---");
			System.out.println("Beca Elegida: " + BecaElegida.getNombre() + "\n");

			if (subOpcion != 0) {
				System.out.println("\n--- Ingrese una opción valida ---\n");
			}
			System.out.println("¿Qué información desea acerca de la beca?");
			System.out.println(" 1. Consultar elegibilidad de un vinculado");
			System.out.println(" 2. Tabla de vinculados elegibles");
			System.out.println(" 3. Información asociada");
			System.out.println(" 4. Regresar al Inicio");
			System.out.println("Digite una opción: ");

			subOpcion = (int) readLong();
			if (subOpcion == 4) {
				System.out.println(espaciado);
				return;
			}

		} while (subOpcion > 4 || subOpcion < 1);

		switch (subOpcion) {
		case 1:
			int documento = 0;
			Persona vinculadoConsultado = null;
			boolean condicional = false;
			int contadorLocal = 0;
			do {
				System.out.println(espaciado);
				System.out.println("--- Menu Becas ---");
				System.out.println("Beca Elegida: " + BecaElegida.getNombre());
				System.out.println("Opcion Elegida: Elegibilidad de vinculado\n");

				if (contadorLocal != 0) {
					System.out.println("--- Documento Invalido ---\n");
				}

				System.out.println("-Ingrese el documento del vinculado.");
				System.out.println("-Ingrese 0 para salir.");
				System.out.println("Digite una opción: ");

				documento = (int) readLong();
				if (documento == 0) {
					System.out.println(espaciado);
					return;
				}
				contadorLocal++;
				for (Estudiante e : Estudiante.getListaEstudiantes()) {
					if (documento == e.getDocumento()) {
						condicional = true;
						vinculadoConsultado = e;
					}

				}
				for (Profesor p : Profesor.getListaProfesores()) {
					if (documento == p.getDocumento()) {
						condicional = true;
						vinculadoConsultado = p;
					}
				}

			} while (!condicional);

			boolean condicional2 = BecaElegida.getBeneficiarios().contains(vinculadoConsultado);
			System.out.println(espaciado);
			System.out.println("--- Menu Becas ---");
			System.out.println("Beca Elegida: " + BecaElegida.getNombre());
			System.out.println("Opcion Elegida: Elegibilidad de vinculado\n");
			if (vinculadoConsultado instanceof Estudiante && BecaElegida.isProfesoral()) {
				System.out.println("Esta es una beca profesoral\nel documento ingresado es de un Estudiante");
			} else if (vinculadoConsultado instanceof Profesor && !BecaElegida.isProfesoral()) {
				System.out.println("Esta es una beca estudiantil\nel documento ingresado es de un Profesor");
			}
			if (condicional2) {
				System.out.println("El vinculado SI es Elegible para la beca\n");
			} else {
				System.out.println("El vinculado NO es Elegible para la beca\n");
			}

			int terminacion = terminarPrograma();
			switch (terminacion) {
			case 1:
				System.out.println(espaciado);
				return;
			case 0:
				System.exit(0);

			}

		case 2:
			subOpcion = 0;
			Formatter fmt = new Formatter();
			fmt.format("%15s %15s %15s\n", "Nombre vinculado", "Promedio", "Facultad");

			do {
				if (subOpcion != 0) {
					System.out.println("--- Opcion invalida ---\n");
				}

				System.out.println(espaciado);
				System.out.println("--- Menu Becas ---");
				System.out.println("Beca Elegida: " + BecaElegida.getNombre());
				System.out.println("Opcion Elegida: Ver vinculado elegibles\n");

				System.out.println("1. Ver vinculados elegibles por facultad");
				System.out.println("2. Ver vinculados elegibles por Linea de Enfasis");
				System.out.println("3. Ver todos los vinculados elegibles");
				System.out.println("4. Regresar al Inicio");
				System.out.println("Digite una opción: ");
				subOpcion = (int) readLong();
				if (subOpcion == 4) {
					return;
				}

			} while (subOpcion > 3 || subOpcion < 0);

			switch (subOpcion) {
			case 1:
				fmt = new Formatter();
				fmt.format("%15s %15s %15s\n", "Nombre vinculado", "Promedio", "Facultad");

				int subOpcion1 = 0;

				do {
					if (subOpcion1 != 0) {
						System.out.println("--- Opcion invalida ---\n");
					}

					System.out.println(espaciado);
					System.out.println("--- Menu Becas ---");
					System.out.println("Beca Elegida: " + BecaElegida.getNombre());
					System.out.println("Opcion Elegida: Ver vinculado elegibles");
					System.out.println("Filtro : Por facultad \n");

					int contador = 1;
					System.out.println("Facultades Disponibles :");

					for (Facultad f : BecaElegida.getCuposPorFacultad().keySet()) {
						System.out.println(contador + ". " + f.getNombre());
						contador++;
					}
					System.out.println(contador + ". Regresar al Inicio");

					System.out.println("Digite una opción: ");
					subOpcion1 = (int) readLong();
					if (subOpcion1 == contador) {
						return;
					}
				} while (subOpcion1 > BecaElegida.getCuposPorFacultad().keySet().size() + 1 || subOpcion1 < 0);

				List<Facultad> list = new ArrayList<Facultad>(BecaElegida.getCuposPorFacultad().keySet());
				Facultad facultadElegida = list.get(subOpcion1 - 1);

				for (Persona p : BecaElegida.getBeneficiarios()) {
					if (p.getFacultad().equals(facultadElegida)) {
						fmt.format("%14s %14s %17s\n", p.getNombre(), String.format("%.2f", p.calcularPromedio()),
								p.getFacultad().getNombre());
					}
				}
				System.out.println(espaciado);
				System.out.println("--- Menu Becas ---");
				System.out.println("Beca Elegida: " + BecaElegida.getNombre());
				System.out.println("Opcion Elegida: Ver vinculado elegibles");
				System.out.println("Filtro : Por facultad " + facultadElegida.getNombre() + "\n");

				System.out.println(fmt);

				terminacion = terminarPrograma();
				switch (terminacion) {
				case 1:
					System.out.println(espaciado);
					return;
				case 0:
					System.exit(0);
				}

			case 2:

				fmt = new Formatter();
				fmt.format("%15s %15s %15s\n", "Nombre vinculado", "Promedio", "Facultad");
				if (BecaElegida.isProfesoral()) {
					System.out.println("\n\n--- Opcion no valida para profesores ---\n\n");

					terminacion = terminarPrograma();
					switch (terminacion) {
					case 1:
						System.out.println(espaciado);
						return;
					case 0:
						System.exit(0);
					}
					return;
				}

				int subOpcion2 = 0;

				do {
					if (subOpcion2 != 0) {
						System.out.println("--- Opcion invalida ---\n");
					}

					System.out.println(espaciado);
					System.out.println("--- Menu Becas ---");
					System.out.println("Beca Elegida: " + BecaElegida.getNombre());
					System.out.println("Opcion Elegida: Ver vinculado elegibles");
					System.out.println("Filtro : Por Linea de Enfasis \n");

					int contador = 1;
					for (LineasEnfasis le : LineasEnfasis.values()) {
						System.out.println(contador + ". " + le);
						contador++;
					}
					System.out.println(contador + ". Regresar al Inicio");
					System.out.println("Digite una opción: ");
					subOpcion2 = (int) readLong();
					if (subOpcion == contador) {
						return;
					}

				} while (subOpcion2 > LineasEnfasis.values().length || subOpcion2 < 0);

				LineasEnfasis lineaElegida = LineasEnfasis.values()[subOpcion2 - 1];

				for (Persona p : BecaElegida.getBeneficiarios()) {
					if (p.getLineaEnfasis().equals(lineaElegida)) {
						fmt.format("%14s %14s %17s\n", p.getNombre(), String.format("%.2f", p.calcularPromedio()),
								p.getFacultad().getNombre());
					}
				}
				System.out.println(espaciado);
				System.out.println("--- Menu Becas ---");
				System.out.println("Beca Elegida: " + BecaElegida.getNombre());
				System.out.println("Opcion Elegida: Ver vinculado elegibles");
				System.out.println("Filtro : Linea de Enfasis " + lineaElegida + "\n");

				System.out.println(fmt);

				terminacion = terminarPrograma();
				switch (terminacion) {
				case 1:
					System.out.println(espaciado);
					return;
				case 0:
					System.exit(0);
				}

			case 3:

				System.out.println(espaciado);
				System.out.println("--- Menu Becas ---");
				System.out.println("Beca Elegida: " + BecaElegida.getNombre());
				System.out.println("Opcion Elegida: Ver vinculado elegibles");
				System.out.println("Filtro : Ninguno \n");
				fmt = new Formatter();
				fmt.format("%15s %15s %15s\n", "Nombre vinculado", "Promedio", "Facultad");

				for (Persona p : BecaElegida.getBeneficiarios()) {
					fmt.format("%14s %14s %17s\n", p.getNombre(), String.format("%.2f", p.calcularPromedio()),
							p.getFacultad().getNombre());
				}
				System.out.println(fmt);

				terminacion = terminarPrograma();
				switch (terminacion) {
				case 1:
					System.out.println(espaciado);
					return;
				case 0:
					System.exit(0);
				}
			}
		case 3:
			String dirigidoA;
			if (BecaElegida.isProfesoral()) {
				dirigidoA = "Profesores";
			} else {
				dirigidoA = "Estudiantes";
			}
			System.out.println(espaciado);
			System.out.println("--- Menu Becas ---");
			System.out.println("Beca Elegida: " + BecaElegida.getNombre());
			System.out.println("Opcion Elegida: Informacion general\n");
			System.out.println("Criterio de aptitud: " + BecaElegida.getCriterio());
			System.out.println("Esta beca está destinada a : " + dirigidoA);
			System.out.println("Cantidad de beneficiarios: " + BecaElegida.getBeneficiarios().size());
			System.out.println("Cupos sobrantes: " + BecaElegida.cuposSobrantes());
			System.out.println("Promedio académico beneficiarios: "
					+ String.format("%.2f", BecaElegida.promedioBeneficiarios()) + "\n");
			terminacion = terminarPrograma();
			switch (terminacion) {
			case 1:
				System.out.println(espaciado);
				return;
			case 0:
				System.exit(0);
			}
		}

	}

	private static void funcionalidad4() {
		int seleccion = 1;

		do {
			System.out.println(espaciado);
			System.out.println("\n--- Menu Subsidios ---\n");
			if (seleccion != 1) {
				System.out.println("--- Ingrese una opción valida ---\n");
			}
			int contador = 1;

			System.out.println("¿Sobre qué Subsidio desea obtener información?");
			for (Subsidio s : Subsidio.getListaSubsidio()) {
				System.out.println(contador + ". " + s.getNombre());
				contador++;
			}
			System.out.println(contador + ". Regresar al Inicio");

			System.out.println("Digite una opción: ");
			seleccion = (int) readLong();
			if (seleccion == contador) {
				return;
			}

		} while (seleccion > Subsidio.getListaSubsidio().size() + 1 || seleccion < 1);

		Subsidio subsidioElegido = Subsidio.getListaSubsidio().get(seleccion - 1);
		int subOpcion = 0;

		do {
			System.out.println(espaciado);
			System.out.println("--- Menu Subsidios ---");
			System.out.println("Subsidio elegido: " + subsidioElegido.getNombre() + "\n");

			if (subOpcion != 0) {
				System.out.println("\n--- Ingrese una opción valida ---\n");
			}
			System.out.println("¿Qué información desea acerca del subsidio?");
			System.out.println(" 1. Consultar elegibilidad de un vinculado");
			System.out.println(" 2. Tabla de vinculados elegibles");
			System.out.println(" 3. Información asociada");
			System.out.println(" 4. Regresar al Inicio");
			System.out.println("Digite una opción: ");

			subOpcion = (int) readLong();
			if (subOpcion == 4) {
				System.out.println(espaciado);
				return;
			}

		} while (subOpcion > 4 || subOpcion < 1);

		switch (subOpcion) {
		case 1:
			int documento = 0;
			Persona vinculadoConsultado = null;
			boolean condicional = false;
			int contadorLocal = 0;
			do {
				System.out.println(espaciado);
				System.out.println("--- Menu Subsidios ---");
				System.out.println("Subsidio elegido: " + subsidioElegido.getNombre() + "\n");
				System.out.println("Opcion Elegida: Elegibilidad de vinculado\n");

				if (contadorLocal != 0) {
					System.out.println("--- Documento Invalido ---\n");
				}

				System.out.println("-Ingrese el documento del vinculado.");
				System.out.println("-Ingrese 0 para salir.");
				System.out.println("Digite una opción: ");

				documento = (int) readLong();
				if (documento == 0) {
					System.out.println(espaciado);
					return;
				}
				contadorLocal++;
				for (Estudiante e : Estudiante.getListaEstudiantes()) {
					if (documento == e.getDocumento()) {
						condicional = true;
						vinculadoConsultado = e;
					}

				}

			} while (!condicional);

			boolean condicional2 = subsidioElegido.getBeneficiarios().contains(vinculadoConsultado);
			System.out.println(espaciado);
			System.out.println("--- Menu Subsidios ---");
			System.out.println("Subsidio elegido: " + subsidioElegido.getNombre() + "\n");
			System.out.println("Opcion Elegida: Elegibilidad de vinculado\n");
			if (condicional2) {
				System.out.println("El vinculado SI es Elegible para el Subsidio\n");
			} else {
				System.out.println("El vinculado NO es Elegible para el Subsidio\n");
			}

			int terminacion = terminarPrograma();
			switch (terminacion) {
			case 1:
				System.out.println(espaciado);
				return;
			case 0:
				System.exit(0);

			}

		case 2:
			subOpcion = 0;
			Formatter fmt = new Formatter();
			fmt.format("%15s %15s %15s\n", "Nombre vinculado", "IES", "Facultad");

			do {
				if (subOpcion != 0) {
					System.out.println("--- Opcion invalida ---\n");
				}

				System.out.println(espaciado);
				System.out.println("--- Menu Subsidios ---");
				System.out.println("Subsidio elegido: " + subsidioElegido.getNombre() + "\n");
				System.out.println("Opcion Elegida: Ver vinculados elegibles\n");

				System.out.println("1. Ver vinculados elegibles por facultad");
				System.out.println("2. Ver vinculados elegibles por Linea de Enfasis");
				System.out.println("3. Ver todos los vinculados elegibles");
				System.out.println("4. Regresar al Inicio");
				System.out.println("Digite una opción: ");
				subOpcion = (int) readLong();
				if (subOpcion == 4) {
					return;
				}

			} while (subOpcion > 3 || subOpcion < 0);

			switch (subOpcion) {
			case 1:
				fmt = new Formatter();
				fmt.format("%15s %15s %15s\n", "Nombre vinculado", "IES", "Facultad");

				int subOpcion1 = 0;

				do {
					if (subOpcion1 != 0) {
						System.out.println("--- Opcion invalida ---\n");
					}

					System.out.println(espaciado);
					System.out.println("--- Menu Subsidios ---");
					System.out.println("Subsidio elegido: " + subsidioElegido.getNombre() + "\n");
					System.out.println("Opcion Elegida: Ver vinculados elegibles\n");
					System.out.println("Filtro : Por facultad \n");

					int contador = 1;
					System.out.println("Facultades Disponibles :");

					for (Facultad f : Facultad.getListaFacultades()) {
						System.out.println(contador + ". " + f.getNombre());
						contador++;
					}
					System.out.println(contador + ". Regresar al Inicio");

					System.out.println("Digite una opción: ");
					subOpcion1 = (int) readLong();
					if (subOpcion1 == contador) {
						return;
					}
				} while (subOpcion1 > Facultad.getListaFacultades().size() + 1 || subOpcion1 < 0);

				List<Facultad> list = new ArrayList<Facultad>(Facultad.getListaFacultades());
				Facultad facultadElegida = list.get(subOpcion1 - 1);

				for (Estudiante p : subsidioElegido.getBeneficiarios()) {
					if (p.getFacultad().equals(facultadElegida)) {
						fmt.format("%14s %14s %17s\n", p.getNombre(), String.format("%.2f", Subsidio.calcularIES(p)),
								p.getFacultad().getNombre());
					}
				}
				System.out.println(espaciado);
				System.out.println("--- Menu Subsidios ---");
				System.out.println("Subsidio elegido: " + subsidioElegido.getNombre() + "\n");
				System.out.println("Opcion Elegida: Ver vinculados elegibles\n");
				System.out.println("Filtro : Por facultad " + facultadElegida.getNombre() + "\n");

				System.out.println(fmt);

				terminacion = terminarPrograma();
				switch (terminacion) {
				case 1:
					System.out.println(espaciado);
					return;
				case 0:
					System.exit(0);
				}

			case 2:

				fmt = new Formatter();
				fmt.format("%15s %15s %15s\n", "Nombre vinculado", "IES", "Facultad");

				int subOpcion2 = 0;

				do {
					if (subOpcion2 != 0) {
						System.out.println("--- Opcion invalida ---\n");
					}

					System.out.println(espaciado);
					System.out.println("--- Menu Subsidios ---");
					System.out.println("Subsidio elegido: " + subsidioElegido.getNombre() + "\n");
					System.out.println("Opcion Elegida: Ver vinculados elegibles\n");
					System.out.println("Filtro : Por Linea de Enfasis \n");

					int contador = 1;
					for (LineasEnfasis le : LineasEnfasis.values()) {
						System.out.println(contador + ". " + le);
						contador++;
					}
					System.out.println(contador + ". Regresar al Inicio");
					System.out.println("Digite una opción: ");
					subOpcion2 = (int) readLong();
					if (subOpcion == contador) {
						return;
					}

				} while (subOpcion2 > LineasEnfasis.values().length + 1 || subOpcion2 < 0);

				LineasEnfasis lineaElegida = LineasEnfasis.values()[subOpcion2 - 1];

				for (Estudiante p : subsidioElegido.getBeneficiarios()) {
					if (p.getLineaEnfasis().equals(lineaElegida)) {
						fmt.format("%14s %14s %17s\n", p.getNombre(), String.format("%.2f", Subsidio.calcularIES(p)),
								p.getFacultad().getNombre());
					}
				}

				System.out.println(espaciado);
				System.out.println("--- Menu Subsidios ---");
				System.out.println("Subsidio elegido: " + subsidioElegido.getNombre() + "\n");
				System.out.println("Opcion Elegida: Ver vinculados elegibles\n");
				System.out.println("Filtro : Linea de Enfasis " + lineaElegida + "\n");

				System.out.println(fmt);

				terminacion = terminarPrograma();
				switch (terminacion) {
				case 1:
					System.out.println(espaciado);
					return;
				case 0:
					System.exit(0);
				}

			case 3:
				System.out.println(espaciado);
				System.out.println("--- Menu Subsidios ---");
				System.out.println("Subsidio elegido: " + subsidioElegido.getNombre() + "\n");
				System.out.println("Opcion Elegida: Ver vinculados elegibles\n");
				System.out.println("Filtro : Ninguno \n");
				fmt = new Formatter();
				fmt.format("%15s %15s %15s\n", "Nombre vinculado", "IES", "Facultad");

				for (Estudiante p : subsidioElegido.getBeneficiarios()) {
					fmt.format("%14s %14s %17s\n", p.getNombre(), String.format("%.2f", Subsidio.calcularIES(p)),
							p.getFacultad().getNombre());
				}
				System.out.println(fmt);

				terminacion = terminarPrograma();
				switch (terminacion) {
				case 1:
					System.out.println(espaciado);
					return;
				case 0:
					System.exit(0);
				}
			}
		case 3:
			String dirigidoA = "Estudiantes";
			System.out.println(espaciado);
			System.out.println("--- Menu Subsidios ---");
			System.out.println("Subsidio elegido: " + subsidioElegido.getNombre() + "\n");
			System.out.println("Opcion Elegida: Informacion general\n");
			System.out.println("Criterio de aptitud: IES menor que " + subsidioElegido.getCondicion());
			System.out.println("Este subsidio está destinado a : " + dirigidoA);
			System.out.println("Cantidad de beneficiarios: " + subsidioElegido.getBeneficiarios().size());
			System.out.println("Cupos sobrantes: " + subsidioElegido.cuposSobrantes());
			System.out.println("Promedio académico beneficiarios: "
					+ String.format("%.2f", subsidioElegido.promedioBeneficiarios()) + "\n");
			terminacion = terminarPrograma();
			switch (terminacion) {
			case 1:
				System.out.println(espaciado);
				return;
			case 0:
				System.exit(0);
			}
		}

	}

	private static void funcionalidad5() {
		// Funcionalidad Posición de estudiantes
		int seleccion = 1;

		do {
			System.out.println(espaciado);
			System.out.println("--- Menu Posición de Estudiante ---");

			if (seleccion != 1) {
				System.out.println("--- Ingrese una opción valida ---\n");
			}

			System.out.println("Seleccione una opción");
			System.out.println("1. Ingresar el documento del Estudiante.");
			System.out.println("2. Regresar al Inicio.");
			System.out.print("Digite una opción: ");

			seleccion = (int) readLong();

			if (seleccion == 2) {
				System.out.println(espaciado);
				return;
			}
			else if (seleccion == 1) {
				System.out.print("Ingrese el documento del Estudiante: ");
				int documento = (int) readLong();
				Estudiante estudianteConsultado = null;

				for (Estudiante e : Estudiante.getListaEstudiantes()) {
					if (documento == e.getDocumento()) {
						estudianteConsultado = e;
					}
				}

				if(estudianteConsultado == null){
					System.out.println("Documento no encontrado");
					funcionalidad5();
				}

				else {
					int seleccion2 = 1;

					do {
						System.out.println(espaciado);
						System.out.println("\n--- Menu Posición de Estudiante ---\n");
						System.out.println("Documento válido\n");
						System.out.println(estudianteConsultado + "\n");
						if (seleccion2 != 1) {
							System.out.println("--- Ingrese una opción valida ---\n");
						}

						System.out.println("¿Que información desea obtener acerca del estudiante?");
						System.out.println("1. Consultar la posición del estudiante en su semestre");
						System.out.println("2. Consultar la posición del estudiante en su facultad");
						System.out.println("3. Consultar la posición del estudiante en su línea de enfasis");
						System.out.println("4. Regresar al Inicio");

						System.out.print("Digite una opción: ");
						seleccion2 = (int) readLong();
						if (seleccion2 == 4) {
							return;
						}

					} while (seleccion2 > 4 || seleccion2 < 1);

					switch (seleccion2){
						case 1:
							System.out.println('\n' + Estudiante.posicionEstudiante(estudianteConsultado, "semestre"));
							break;

						case 2:
							System.out.println('\n' + Estudiante.posicionEstudiante(estudianteConsultado, "facultad"));
							break;

						case 3:
							System.out.println('\n' + Estudiante.posicionEstudiante(estudianteConsultado, "lineaEnfasis"));
							break;
					}

					funcionalidad5();
				}
			}
		} while (seleccion > 2 || seleccion < 1);
	}
}