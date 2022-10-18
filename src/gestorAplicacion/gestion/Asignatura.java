package gestorAplicacion.gestion;

import java.util.*;
import gestorAplicacion.gestion.*;
import gestorAplicacion.personas.*;

public class Asignatura {
	private int creditos;
	private ArrayList<Profesor> profesor;
	private ArrayList<Asignatura> prerrequisitos;
	private LineasEnfasis lineaEnfasis;
	private static ArrayList<Asignatura> listaAsignaturas;
	
	
	
	public Asignatura(int creditos, ArrayList<Profesor> profesor, ArrayList<Asignatura> prerrequisitos,
			LineasEnfasis lineaEnfasis) {
		super();
		this.creditos = creditos;
		this.profesor = profesor;
		this.prerrequisitos = prerrequisitos;
		this.lineaEnfasis = lineaEnfasis;
	}

	public Asignatura() {
		this(3, null, null, null);
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

	public LineasEnfasis getLineaEnfasis() {
		return lineaEnfasis;
	}

	public void setLineaEnfasis(LineasEnfasis lineaEnfasis) {
		this.lineaEnfasis = lineaEnfasis;
	}

	public ArrayList<Asignatura> getPrerrequisitos() {
		return prerrequisitos;
	}

	public void setPrerrequisitos(ArrayList<Asignatura> prerrequisitos) {
		this.prerrequisitos = prerrequisitos;
	}

	public static ArrayList<Asignatura> getListaAsignaturas() {
		return listaAsignaturas;
	}

	public static void setListaAsignaturas(ArrayList<Asignatura> listaAsignaturas) {
		Asignatura.listaAsignaturas = listaAsignaturas;
	}
	
}
