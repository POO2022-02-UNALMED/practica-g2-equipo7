import java.util.*

public class Beca {

    private float criterio;
    private int cupos;
    ArrayList<Persona> beneficiarios = new ArrayList<>();
    ArrayList<Facultad> facultad = new ArrayList<>();
    private static ArrayList<Beca> becas = new ArrayList<>();

    public Beca(){
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

    public void setCriterio(float criterio) {
        this.criterio = criterio;
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

    public ArrayList<Facultad> getFacultad() {
        return facultad;
    }

    public void setFacultad(ArrayList<Facultad> facultad) {
        this.facultad = facultad;
    }

    public static ArrayList<Beca> getBecas() {
        return becas;
    }

    public static void setBecas(ArrayList<Beca> becas) {
        Beca.becas = becas;
    }
}