import math


class Subsidio:
    listaSubsidio = []

    def __init__(self, nombre, condicion, cupos):

        from gestorAplicacion.personas.estudiante import Estudiante
        from gestorAplicacion.personas.profesor import Profesor

        self.nombre = nombre
        self.condicion = condicion
        self.cupos = cupos
        Subsidio.listaSubsidio.append(self)
        Estudiante.getListaEstudiantes().sort()
        Profesor.getListaProfesores().sort()
        self.setBeneficiarios()

    @classmethod
    def calcularIES(cls, beneficiario):
        from gestorAplicacion.personas.estudiante import Estudiante

        puntuacion = (beneficiario.getIngresosFamiliares() * 100) / (
            beneficiario.getNumeroFamiliares() * 760000
        )
        ubicacion = math.pow(beneficiario.getEstrato(), 2) - 4
        if beneficiario.isColegioPublico():
            puntuacion -= 10
        puntuacion += ubicacion - 100
        return puntuacion

    def setBeneficiarios(self):
        from gestorAplicacion.personas.estudiante import Estudiante

        estudiantesGeneral = Estudiante.getListaEstudiantes()
        for estudiante in estudiantesGeneral:

            result = Subsidio.calcularIES(estudiante)
            cuposDisponibles = self.cupos > 0
            if result < self.condicion and cuposDisponibles:
                self.beneficiarios.append(estudiante)
                self.cupos -= 1

    def promedioBeneficiarios(self):
        from gestorAplicacion.personas.estudiante import Estudiante

        total = 0
        for persona in self.beneficiarios:
            total += persona.calcularPromedio()
        return total / len(self.beneficiarios)

    def cuposSobrantes(self):
        return self.cupos - len(self.beneficiarios)

    def getBeneficiarios(self):
        return self.beneficiarios

    @classmethod
    def getListaSubsidio(cls):
        return cls.listaSubsidio

    @classmethod
    def setListaSubsidio(cls, lista):
        cls.listaSubsidio = lista

    ##Metodo de comparacion para estudiantes

    def getNombre(self):
        return self.nombre

    def setNombre(self, nombre):
        self.nombre = nombre

    def getCondicion(self):
        return self.condicion
