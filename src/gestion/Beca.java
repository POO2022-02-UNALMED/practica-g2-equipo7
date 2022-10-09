package gestion;

import java.util.*;

import personas.Persona;

public class Beca {

    private final float criterio;
    private int cupos;
    private ArrayList<Persona> beneficiarios;
    private ArrayList<Dictionary> cuposPorFacultad;
    private static ArrayList<Beca> becas ;

    public Beca(){
         //SE NECESITA DECLARAR LA REFERENCIA AL OTRO CONSTRUCTOR
    }
    
    public Beca(float criterio, int cupos, ArrayList<Persona> beneficiarios, ArrayList<Dictionary> cuposPorFacultad) {
		super();
		this.criterio = criterio;
		this.cupos = cupos;
		this.beneficiarios = beneficiarios;
		this.cuposPorFacultad = cuposPorFacultad;
        Beca.becas.add(this);

	}



	public boolean Eligibilidad(Persona beneficiario){
		
    }

    public void SetBeneficiarios(ArrayList<Persona> beneficiarios){
        this.beneficiarios = beneficiarios;
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
