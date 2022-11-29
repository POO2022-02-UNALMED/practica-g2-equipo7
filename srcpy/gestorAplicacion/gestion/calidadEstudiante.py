from enum import Enum


class CalidadEstudiante(Enum):
    BAJA = 1
    MEDIA = 2
    ALTA = 3
    SOBRESALIENTE = 4

    @staticmethod
    def ObtenerCalidadEstudiante(promedio):
        if promedio <= 3.5:
            return CalidadEstudiante.BAJA.name
        elif promedio <= 4.0:
            return CalidadEstudiante.MEDIA.name
        elif promedio <= 4.5:
            return CalidadEstudiante.ALTA.name

        return CalidadEstudiante.SOBRESALIENTE.name
