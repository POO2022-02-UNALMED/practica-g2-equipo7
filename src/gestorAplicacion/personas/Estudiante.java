package gestorAplicacion.personas;

import java.io.Serializable;
import java.util.*;
import gestorAplicacion.gestion.*;
import gestorAplicacion.personas.*;

public class Estudiante extends Persona implements Serializable, Comparable<Estudiante> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Asignatura, Float> asignaturasInscritas;
	private float promedio;
	private int semestre;
	private LineasEnfasis lineaEnfasis;
	private ArrayList<Asignatura> asignaturasAprobadas;
	private Facultad facultad;
	private static ArrayList<Estudiante> listaEstudiantes = new ArrayList<Estudiante>();
	private int estrato;
	private float ingresosFamiliares;
	private boolean colegioPublico;
	private int numeroFamiliares;

	public Estudiante(int documento, String nombre, int edad, HashMap<Asignatura, Float> asignaturasInscritas,
			float promedio, int semestre, LineasEnfasis lineaEnfasis, ArrayList<Asignatura> asignaturasAprobadas,
			int estrato, float ingresosFamiliares, boolean colegioPublico, int numeroFamiliares) {
		super(documento, nombre, edad);
		this.asignaturasInscritas = asignaturasInscritas;
		this.promedio = this.calcularPromedio();
		this.semestre = semestre;
		this.lineaEnfasis = lineaEnfasis;
		this.asignaturasAprobadas = asignaturasAprobadas;
		this.facultad = null;
		this.estrato = estrato;
		this.ingresosFamiliares = ingresosFamiliares;
		this.colegioPublico = colegioPublico;
		this.numeroFamiliares = numeroFamiliares;
		Estudiante.listaEstudiantes.add(this);
	}

	public Facultad getFacultad() {
		return facultad;
	}

	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}


	public float calcularPromedio() {
		float promedio = 0;
		int creditosInscritos = 0;

		if (this.asignaturasInscritas.size() == 0) {
			return 3f;
		}

		for (Asignatura informacionAsignatura : asignaturasInscritas.keySet()) {

			float nota = asignaturasInscritas.get(informacionAsignatura);
			int creditos = informacionAsignatura.getCreditos();

			creditosInscritos += creditos;
			promedio += creditos * nota;
		}

		promedio = promedio / creditosInscritos;
		return promedio;
	}

	public CalidadEstudiante calidadEstudiante() {
		return CalidadEstudiante.ObtenerCalidadEstudiante(this.promedio);
	}

	// Funcionalidad posición de estudiante
	public static String posicionEstudiante(Estudiante estudiante, String Dominio) {
		int posEstudiante = 1;

		switch (Dominio) {

			case "semestre":
				for (Estudiante e : Estudiante.getListaEstudiantes()) {
					if (e.getSemestre() == estudiante.getSemestre()) {
						if (e.getPromedio() > estudiante.getPromedio()) {
							posEstudiante += 1;
						}
					}
				}
				return String.valueOf(posEstudiante);

			case "facultad":
				for (Facultad f : Facultad.getListaFacultades()) {
					if (f.getEstudiantes().contains(estudiante)) {
						for (Estudiante e : f.getEstudiantes()) {
							if (e.getPromedio() > estudiante.getPromedio()) {
								posEstudiante += 1;
							}
							if(e.getPromedio() == estudiante.getPromedio()){
								if(e.getSemestre() > estudiante.getSemestre()){
									posEstudiante += 1;
								}
							}
						}
						return String.valueOf(posEstudiante);
					}
				}
				return "NN";

			case "lineaEnfasis":
				for (Facultad f : Facultad.getListaFacultades()) {
					if (f.getEstudiantes().contains(estudiante)) {
						for (Estudiante e : f.getEstudiantes()) {
							if (e.getLineaEnfasis() == estudiante.getLineaEnfasis()) {
								if (e.getPromedio() > estudiante.getPromedio()) {
									posEstudiante += 1;
								}
								if(e.getPromedio() == estudiante.getPromedio()){
									if(e.getSemestre() > estudiante.getSemestre()){
										posEstudiante += 1;
									}
								}
							}
						}
						return String.valueOf(posEstudiante);
					}
				}
				return "NN";

			default:
				return "Ingrese un rango de busqueda válido";
		}
	}

	// Funcionalidad Recomendacion de asignaturas por linea de enfasis
	public ArrayList<Asignatura> RecomendarAsignaturas() {
		ArrayList<Asignatura> listaRecomendar = new ArrayList<Asignatura>();
		ArrayList<Asignatura> listaEnfasis = new ArrayList<Asignatura>();
		ArrayList<Asignatura> listado = Asignatura.getListaAsignaturas();

		for (Asignatura i : listado) {
			if (lineaEnfasis != null && i.getLineaEnfasis() != null) {
				if (i.getLineaEnfasis().equals(lineaEnfasis) && !asignaturasAprobadas.contains(i)
						&& !asignaturasInscritas.containsKey(i)) {
					listaEnfasis.add(i);
				}
			}
		}

		for (Asignatura i : listaEnfasis) {
			if (i.getPrerrequisitos() != null) {
				if (asignaturasAprobadas.containsAll(i.getPrerrequisitos())) {
					listaRecomendar.add(i);
				}
			}

			else {
				listaRecomendar.add(i);
			}
		}

		return listaRecomendar;
	}

	// Funcionalidad Recomendacion de asignaturas Basicas
	public ArrayList<Asignatura> RecomendarAsignaturasBasicas() {
		ArrayList<Asignatura> listaRecomendar = new ArrayList<Asignatura>();
		ArrayList<Asignatura> listado = Asignatura.getListaAsignaturas();
		ArrayList<Asignatura> listadoBasicas = new ArrayList<Asignatura>();

		for (Asignatura e : listado) {
			if (e.getLineaEnfasis() == LineasEnfasis.BASICAS && !asignaturasAprobadas.contains(e)
					&& !asignaturasInscritas.containsKey(e)) {
				listadoBasicas.add(e);
			}
		}

		for (Asignatura e : listadoBasicas) {
			if (e.getPrerrequisitos() != null) {
				if (asignaturasAprobadas.containsAll(e.getPrerrequisitos())) {
					listaRecomendar.add(e);
				}
			}

			else {
				listaRecomendar.add(e);
			}
		}

		return listaRecomendar;
	}

	public static void vistaGeneralEstudiantes(){
		System.out.print("------------------------------------------------------------------------");
		System.out.println("------------------------------------------------------------------------");
		System.out.printf("%15s %15s %24s %24s %18s %13s %13s %13s",
				"NOMBRE", "DOCUMENTO", "BECA", "SUBSIDIO",
				"CALIDAD", "PSEMESTRE", "PFACULTAD", "PLINEA");
		System.out.print("\n------------------------------------------------------------------------");
		System.out.println("------------------------------------------------------------------------");
		for (Estudiante e: listaEstudiantes) {
			String beca = "No aplica";
			String subsidio = "No aplica";

			for(Beca b: Beca.getListaBecas()) {
				if (b.getBeneficiarios().contains(e)) {
					beca = b.getNombre();
					break;
				}
			}
			for(Subsidio s: Subsidio.getListaSubsidio()){
				if(s.getBeneficiarios().contains(e)){
					subsidio = s.getNombre();
				}
			}

			System.out.printf("%15s %15d %24s %24s %18s %13s %13s %13s",
					e.getNombre(),
					e.getDocumento(),
					beca,
					subsidio,
					e.calidadEstudiante(),
					posicionEstudiante(e, "semestre"),
					posicionEstudiante(e,"facultad"),
					posicionEstudiante(e,"lineaEnfasis"));
			System.out.println();
		}
		System.out.print("------------------------------------------------------------------------");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\nPSEMESTRE: Posición del estudiante dentro de su semestre");
		System.out.println("PFACULTAD: Posición del estudiante dentro de su facultad");
		System.out.println("PLINEA: Posición del estudiante dentro de su línea de énfasis\n");
	}

	public HashMap<Asignatura, Float> getAsignaturasInscritas() {
		return asignaturasInscritas;
	}

	public void setAsignaturasInscritas(HashMap<Asignatura, Float> asignaturasInscritas) {
		this.asignaturasInscritas = asignaturasInscritas;
	}

	public float getPromedio() {
		return promedio;
	}

	public void setPromedio(float promedio) {
		this.promedio = promedio;
	}

	public int getSemestre() {
		return this.semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public LineasEnfasis getLineaEnfasis() {
		return lineaEnfasis;
	}

	public void setLineaEnfasis(LineasEnfasis lineaEnfasis) {
		this.lineaEnfasis = lineaEnfasis;
	}

	public ArrayList<Asignatura> getAsignaturasAprobadas() {
		return asignaturasAprobadas;
	}

	public void setAsignaturasAprobadas(ArrayList<Asignatura> asignaturasAprobadas) {
		this.asignaturasAprobadas = asignaturasAprobadas;
	}

	public static ArrayList<Estudiante> getListaEstudiantes() {
		return listaEstudiantes;
	}

	public static void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
		Estudiante.listaEstudiantes = listaEstudiantes;
	}

	@Override
	public int compareTo(Estudiante o) {
		return (int) ((o.getPromedio() * 1000) - (this.getPromedio() * 1000));
	}

	public int getEstrato() {
		return estrato;
	}

	public void setEstrato(int estrato) {
		this.estrato = estrato;
	}

	public float getIngresosFamiliares() {
		return ingresosFamiliares;
	}

	public void setIngresosFamiliares(float ingresosFamiliares) {
		this.ingresosFamiliares = ingresosFamiliares;
	}

	public boolean isColegioPublico() {
		return colegioPublico;
	}

	public void setColegioPublico(boolean colegioPublico) {
		this.colegioPublico = colegioPublico;
	}

	public int getNumeroFamiliares() {
		return numeroFamiliares;
	}

	public void setNumeroFamiliares(int numeroFamiliares) {
		this.numeroFamiliares = numeroFamiliares;
	}

	@Override
	public String toString() {
		return "Nombre del estudiante: " + this.getNombre() +
				"\nPromedio: " + this.promedio +
				"\nSemestre: " + this.semestre +
				"\nLínea de Enfásis: " + this.lineaEnfasis +
				"\nFacultad: " + this.facultad.getNombre();
	}
}
