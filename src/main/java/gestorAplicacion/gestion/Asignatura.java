package src.main.java.gestorAplicacion.gestion;

import java.util.*;

public class Asignatura {
	private int creditos;
	private ArrayList<Profesor> profesor;
	private ArrayList<Prerrequisitos> prerrequisitos;
	private String lineaEnfasis;
	private static Asignatura asignaturaPorEnfasis;
	
	public void displayProfesores() {
		
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public ArrayList<Profesor> getProfesor() {
		return profesor;
	}

	public void setProfesor(ArrayList<Profesor> profesor) {
		this.profesor = profesor;
	}

	public ArrayList<Prerrequisitos> getPrerrequisitos() {
		return prerrequisitos;
	}

	public void setPrerrequisitos(ArrayList<Prerrequisitos> prerrequisitos) {
		this.prerrequisitos = prerrequisitos;
	}

	public String getLineaEnfasis() {
		return lineaEnfasis;
	}

	public void setLineaEnfasis(String lineaEnfasis) {
		this.lineaEnfasis = lineaEnfasis;
	}

	public static Asignatura getAsignaturaPorEnfasis() {
		return asignaturaPorEnfasis;
	}

	public static void setAsignaturaPorEnfasis(Asignatura asignaturaPorEnfasis) {
		Asignatura.asignaturaPorEnfasis = asignaturaPorEnfasis;
	}
	
}
