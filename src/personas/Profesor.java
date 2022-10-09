package personas;

import java.util.*;

public class Profesor extends Persona{

	private ArrayList<Dictionary> asignaturasDictadas;
	private Facultad facultad;
	private Date fechaIngreso;

	@Override
	private float calcularPromedio(){
		return 0;
	}

	public ArrayList<Dictionary> getAsignaturasDictadas() {
		return asignaturasDictadas;
	}

	public void setAsignaturasDictadas(ArrayList<Dictionary> asignaturasDictadas) {
		this.asignaturasDictadas = asignaturasDictadas;
	}

	public Facultad getFacultad() {
		return facultad;
	}

	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
}