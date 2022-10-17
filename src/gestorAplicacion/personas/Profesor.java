package gestorAplicacion.personas;
import java.util.*;
import gestorAplicacion.gestion.*;
import gestorAplicacion.personas.*;

public class Profesor extends Persona{

	private HashMap<String, Float> asignaturasDictadas;
	private Facultad facultad;
	private Date fechaIngreso;

	public Profesor(HashMap<String, Float> asignaturasDictadas, Facultad facultad, Date fechaIngreso) {
		this.asignaturasDictadas = asignaturasDictadas;
		this.facultad = facultad;
		this.fechaIngreso = fechaIngreso;
	}
	@Override
	public float calcularPromedio(){
		float sum = 0;
		for(String key: asignaturasDictadas.keySet()){
			sum += asignaturasDictadas.get(key);
		}
		return sum/asignaturasDictadas.size();
	}

	public HashMap<String, Float> getAsignaturasDictadas() {
		return asignaturasDictadas;
	}

	public void setAsignaturasDictadas(HashMap<String, Float> asignaturasDictadas) {
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