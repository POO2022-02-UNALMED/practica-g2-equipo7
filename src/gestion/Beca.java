package gestion;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.*;

import personas.Persona;

public class Beca {

    private final float criterio;
    private int cupos;
    private ArrayList<Persona> beneficiarios;
	private ArrayList<Pair<Asignatura, float>> cuposPorFacultad;
    private static ArrayList<Beca> becas ;

    public Beca(){
    	this(4, 20, null, null );
    }
    
    public Beca(float criterio, int cupos, ArrayList<Persona> beneficiarios, ArrayList<Pair<Asignatura, float>> cuposPorFacultad) {
		super();
		this.criterio = criterio;
		this.cupos = cupos;
		this.beneficiarios = beneficiarios;
		this.cuposPorFacultad = cuposPorFacultad;
        Beca.becas.add(this);

	}

	public boolean Eligibilidad(Persona beneficiario){
		boolean result;
		if (beneficiario.calcularPromedio() > this.criterio && this.getCupos() > 0) {
			
		}
    }

    public void SetBeneficiarios(ArrayList<Persona> beneficiarios){
        
    	
    	
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

	public ArrayList<Dictionary> getCuposPorFacultad() {
		return cuposPorFacultad;
	}

	public void setCuposPorFacultad(ArrayList<Dictionary> cuposPorFacultad) {
		this.cuposPorFacultad = cuposPorFacultad;
	}
    
    
}
