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
		 Serializador.DeserializarAsignaturas();
		 Serializador.DeserializarBecas();
		 Serializador.DeserializarFacultades();
		 Serializador.DeserializarProfesores();
		 Serializador.DeserializarEstudiantes();

		// Declaracion asignaturas para testeos
		//

		int opcion;
		do {
			System.out.println("\n--- Menu Inicial ---\n");
			System.out.println("¿Que operacion desea realizar?");
			System.out.println(" 1. Recomendar Asignaturas");
			System.out.println(" 2. Calidad Estudiante");
			System.out.println(" 3. Información de Becas");
			System.out.println(" 4. Informacion de Subsidios Estudiantiles");
			System.out.println(" 5. Consultar posición de un estudiante");
			System.out.println(" 6. Consultar vinculados");
			System.out.println(" 7. Salir del Sistema");
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
				funcionalidad6();
				break;
			case 7:
				Serializador.SerializarAsignaturas();
				Serializador.SerializarBecas();
				Serializador.SerializarFacultades();
				Serializador.SerializarProfesores();
				Serializador.SerializarEstudiantes();
				System.exit(0);
				break;
			}
		} while (opcion != 7);
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
							System.out.println('\n' + "Posición " +
									Estudiante.posicionEstudiante(estudianteConsultado, "semestre") +
									" entre estudiantes del semestre " + estudianteConsultado.getSemestre() + '\n');
							break;

						case 2:
							if(Estudiante.posicionEstudiante(estudianteConsultado, "facultad").equals("NN")){
								System.out.println("El estudiante no pertenece a ninguna facultad" + '\n');
							}
							else{
								System.out.println('\n' + "Posición " +
										Estudiante.posicionEstudiante(estudianteConsultado, "facultad") +
										" entre estudiantes de la facultad " + estudianteConsultado.getFacultad().getNombre() + '\n');
							}
							break;
						case 3:
							if(Estudiante.posicionEstudiante(estudianteConsultado, "lineaEnfasis").equals("NN")){
								System.out.println("El estudiante no pertenece a ninguna línea de enfásis" + '\n');
							}
							else{
								System.out.println('\n' + "Posición " +
										Estudiante.posicionEstudiante(estudianteConsultado, "lineaEnfasis") +
										" entre estudiantes de la línea de enfásis " + estudianteConsultado.getLineaEnfasis() + '\n');
							}
							break;
					}

				}
				int terminacion = terminarPrograma();
				switch (terminacion) {
					case 1:
						System.out.println(espaciado);
						return;
					case 0:
						System.exit(0);
				}
			}
		} while (seleccion > 2 || seleccion < 1);
	}

	private static void funcionalidad6(){
		// Funcionalidad consulta de vinculados
		int seleccion = 1;

		do {
			System.out.println(espaciado);
			System.out.println("\n--- Menu Consulta Vinculados ---\n");

			if (seleccion != 1) {
				System.out.println("--- Ingrese una opción valida ---\n");
			}

			System.out.println("¿Que información desea obtener acerca del estudiante?");
			System.out.println("1. Consultar los estudiantes vinculados");
			System.out.println("2. Consultar los profesores vinculados");
			System.out.println("3. Regresar al Inicio");

			System.out.println("Digite una opción: ");
			seleccion = (int) readLong();
			if (seleccion == 3) {
				return;
			}

		} while (seleccion > 3 || seleccion < 1);

		switch (seleccion) {
			case 1:
				System.out.println(espaciado);
				Estudiante.vistaGeneralEstudiantes();
				break;

			case 2:
				System.out.println(espaciado);
				Profesor.vistaGeneralProfesores();
				break;
		}
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