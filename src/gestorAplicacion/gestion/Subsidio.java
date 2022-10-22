package gestorAplicacion.gestion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.lang.Math;
import baseDatos.Serializador;
import gestorAplicacion.gestion.*;
import gestorAplicacion.personas.*;

public class Subsidio {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final float condicion;
	private ArrayList<Estudiante> beneficiarios;
	private int cupos;
	public static ArrayList<Subsidio> listaSubsidio = new ArrayList<Subsidio>();
	private String nombre;

	public Subsidio( String nombre,float condicion, int cupos) {
		super();
		this.condicion = condicion;
		this.cupos = cupos;
		this.nombre = nombre;
		this.beneficiarios = new ArrayList<Estudiante>();
		Subsidio.listaSubsidio.add(this);
		Collections.sort(Estudiante.getListaEstudiantes(), new SortbyIES());
		this.setBeneficiarios();

	}

	public static float calcularIES(Estudiante beneficiario) {
		float puntuacion = (beneficiario.getIngresosFamiliares() * 100) / (beneficiario.getNumeroFamiliares() * 760000);
		// Tomando 760.000 como el ingreso promedio por persona
		int ubicacion = (int) Math.pow(beneficiario.getEstrato(), 2f) - 4;
		if (beneficiario.isColegioPublico()) {
			puntuacion -= 10;
		}
		puntuacion += ubicacion - 100;
		return puntuacion;
	}


	public void setBeneficiarios() {
		ArrayList<Estudiante> estudiantesGeneral = Estudiante.getListaEstudiantes();
		for (Estudiante estudiante : estudiantesGeneral) {
			float result = calcularIES(estudiante);
			boolean cuposDisponibles = cupos > 0;
			if ((result < condicion) && cuposDisponibles) {
				beneficiarios.add(estudiante);
				cupos--;
			} else {
			}
		}

	}
	public float promedioBeneficiarios() {
		float total = 0;
		for (Persona p: beneficiarios) {
			total+=p.calcularPromedio();
			}
		return total/beneficiarios.size();
	}
	
	
	public int cuposSobrantes() {
		return this.cupos - beneficiarios.size();
	}
	public ArrayList<Estudiante> getBeneficiarios() {
		return beneficiarios;
	}

	public static ArrayList<Subsidio> getListaSubsidio() {
		return listaSubsidio;
	}

	public static void setListaSubsidio(ArrayList<Subsidio> listaSubsidio) {
		Subsidio.listaSubsidio = listaSubsidio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getCondicion() {
		return condicion;
	}
	
	public static void sortByIES() {
		Collections.sort(Estudiante.getListaEstudiantes(), new SortbyIES());
	}

}

class SortbyIES implements Comparator<Estudiante> {

	// Method
	// Sorting in ascending order of name
	public int compare(Estudiante a, Estudiante b) {

		return (int) (Subsidio.calcularIES(a) - Subsidio.calcularIES(b)) * 1000;
	}
}
