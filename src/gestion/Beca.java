package gestion;

import java.util.*;

import personas.*;

public class Beca {

	private final float criterio;
	private int cupos;
	private ArrayList<Persona> beneficiarios;
	private HashMap<Facultad, Integer> cuposPorFacultad;
	private static ArrayList<Beca> becas;
	private boolean isProfesoral;

	public Beca() {
		this(4, 20, null, null, false);
	}

	public Beca(float criterio, int cupos, ArrayList<Persona> beneficiarios,
			HashMap<Facultad, Integer> cuposPorFacultad, boolean isProfesoral) {
		super();
		this.criterio = criterio;
		this.cupos = cupos;
		this.beneficiarios = beneficiarios;
		this.cuposPorFacultad = cuposPorFacultad;
		this.isProfesoral = isProfesoral;
		Beca.becas.add(this);

	}

	private boolean Elegibilidad(Persona beneficiario) {
		float promedio = beneficiario.calcularPromedio();
		boolean result = (promedio>= criterio);
		return result;
	}

	public void SetBeneficiarios(ArrayList<Persona> beneficiarios) {

		HashMap<Facultad, Integer> cuposCopy = new HashMap<>(cuposPorFacultad);

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

	public ArrayList<Persona> getBeneficiarios() {
		return beneficiarios;
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

	public void setBeneficiarios(ArrayList<Persona> beneficiarios) {
		this.beneficiarios = beneficiarios;
	}

	public static ArrayList<Beca> getBecas() {
		return becas;
	}

	public static void setBecas(ArrayList<Beca> becas) {
		Beca.becas = becas;
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

}
