from enum import Enum


class CalidadEstudiante(Enum):
    BAJA = 1
    MEDIA = 2
    ALTA = 3
    SOBRESALIENTE = 4

    def ObtenerCalidadEstudiante(self, promedio):
        if promedio <= 3.5:
            return CalidadEstudiante.BAJA
        elif promedio <= 4.0:
            return CalidadEstudiante.MEDIA
        elif promedio <= 4.5:
            return CalidadEstudiante.ALTA
        else:
            CalidadEstudiante.SOBRESALIENTE
