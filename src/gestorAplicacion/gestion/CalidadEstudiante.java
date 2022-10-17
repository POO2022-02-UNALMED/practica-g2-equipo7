package gestorAplicacion.gestion;

public enum CalidadEstudiante {
    BAJA, MEDIA, ALTA, SOBRESALIENTE;

    public static CalidadEstudiante ObtenerCalidadEstudiante(float promedio){
        if (promedio <= 3.5) {return CalidadEstudiante.BAJA;}
        if (promedio <= 4.0) {return CalidadEstudiante.MEDIA;}
        if (promedio <= 4.5) {return CalidadEstudiante.ALTA;}
        return CalidadEstudiante.SOBRESALIENTE;
    }
}