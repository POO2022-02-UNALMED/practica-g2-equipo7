package gestorAplicacion.gestion;

import java.io.Serializable;
import java.util.*;
import gestorAplicacion.gestion.*;
import gestorAplicacion.personas.*;

public class Beca implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final float criterio;
	private int cupos;
	private ArrayList<Persona> beneficiarios;
	private HashMap<Facultad, Integer> cuposPorFacultad;
	public static ArrayList<Beca> listaBecas = new ArrayList<Beca>();
	private boolean isProfesoral;
	private String nombre;

	public Beca(String nombre, float criterio,
			HashMap<Facultad, Integer> cuposPorFacultad, boolean isProfesoral) {
		super();
		this.nombre = nombre;
		this.criterio = criterio;
		this.cuposPorFacultad = cuposPorFacultad;
		this.isProfesoral = isProfesoral;
		this.beneficiarios = new ArrayList<Persona>();
		this.setBeneficiarios();
		Beca.listaBecas.add(this);

	}

	private boolean Elegibilidad(Persona beneficiario) {
		float promedio = beneficiario.calcularPromedio();
		boolean result = (promedio >= criterio);
		return result;
	}

	public void setBeneficiarios() {

		HashMap<Facultad, Integer> cuposCopy = new HashMap<>(cuposPorFacultad);
		Collections.sort(Estudiante.getListaEstudiantes());
		Collections.sort(Profesor.getListaProfesores());

		for (Facultad facultad : cuposCopy.keySet()) {

			if (isProfesoral) {
				for (Profesor profesor : facultad.getProfesoresVinculados()) {
					boolean result = Elegibilidad(profesor);
					boolean cuposDisponibles = cuposCopy.get(facultad) > 0;
					if (result && cuposDisponibles) {
						beneficiarios.add(profesor);
						cuposCopy.put(facultad, cuposCopy.get(facultad) - 1);
					} else {
					}
				}

			} else {
				for (Estudiante estudiante : facultad.getEstudiantes()) {
					boolean result = Elegibilidad(estudiante);
					boolean cuposDisponibles = cuposCopy.get(facultad) > 0;
					if (result && cuposDisponibles) {
						beneficiarios.add(estudiante);
						cuposCopy.put(facultad, cuposCopy.get(facultad) - 1);
					} else {
					}
				}
			}
		}
	}

	public int cuposSobrantes() {
		int total = 0;
		for (Facultad f : cuposPorFacultad.keySet()) {
			total += cuposPorFacultad.get(f);
		}
		return total - beneficiarios.size();
	}

	public ArrayList<Persona> getBeneficiarios() {
		return beneficiarios;
	}

	public float promedioBeneficiarios() {
		float total = 0;
		for (Persona p : beneficiarios) {
			total += p.calcularPromedio();
		}
		return total / beneficiarios.size();
	}

	public float getCriterio() {
		return criterio;
	}

	public int getCupos() {
		return cupos;
	}

	public void setCupos(int cupos) {
		this.cupos = cupos;
	}

	public static ArrayList<Beca> getListaBecas() {
		return listaBecas;
	}

	public static void setListaBecas(ArrayList<Beca> listaBecas) {
		Beca.listaBecas = listaBecas;
	}

	public HashMap<Facultad, Integer> getCuposPorFacultad() {
		return cuposPorFacultad;
	}

	public void setCuposPorFacultad(HashMap<Facultad, Integer> cuposPorFacultad) {
		this.cuposPorFacultad = cuposPorFacultad;
	}

	public boolean isProfesoral() {
		return isProfesoral;
	}

	public void setProfesoral(boolean isProfesoral) {
		this.isProfesoral = isProfesoral;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
