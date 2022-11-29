class Beca:
    listaBecas = []

    def __init__(self, nombre, criterio, cuposPorFacultad, isProfesoral):
        self.nombre = nombre
        self.criterio = criterio
        self.cuposPorFacultad = cuposPorFacultad
        self.isprofesoral = isProfesoral
        self.setBeneficiarios()
        Beca.listaBecas.append(self)

    def Elegibilidad(self, beneficiario):
        from gestorAplicacion.personas.estudiante import Estudiante
        from gestorAplicacion.personas.profesor import Profesor

        promedio = beneficiario.calcularPromedio()
        result = promedio >= self.criterio
        return result

    def setBeneficiarios(self):
        from gestorAplicacion.personas.estudiante import Estudiante
        from gestorAplicacion.personas.profesor import Profesor
        from gestorAplicacion.gestion.facultad import Facultad

        Estudiante.getListaEstudiantes().sort()
        Profesor.getListaProfesores().sort()

        cuposCopy = self.cuposPorFacultad.copy()
        self.beneficiarios = []
        for facultad in cuposCopy.keys():
            if self.isProfesoral():
                for profesor in facultad.getProfesoresVinculados():
                    result = self.Elegibilidad(profesor)
                    cuposDisponibles = cuposCopy[facultad] > 0
                    if result and cuposDisponibles:
                        self.beneficiarios.append(profesor)
                        cuposCopy[facultad] = cuposCopy[facultad] - 1

            else:
                for estudiante in facultad.getEstudiantes():
                    result = self.Elegibilidad(estudiante)
                    cuposDisponibles = cuposCopy[facultad] > 0
                    if result and cuposDisponibles:
                        self.beneficiarios.append(estudiante)
                        cuposCopy[facultad] = cuposCopy[facultad] - 1

    def cuposSobrantes(self):
        total = 0
        for facultad in self.cuposPorFacultad.keys():
            total += self.cuposPorFacultad[facultad]
        return total - len(self.beneficiarios)

    def getBeneficiarios(self):
        return self.beneficiarios

    def promedioBeneficiarios(self):
        from gestorAplicacion.personas.estudiante import Estudiante
        from gestorAplicacion.personas.profesor import Profesor

        total = 0
        for persona in self.beneficiarios:
            total += persona.calcularPromedio()
        return total / len(self.beneficiarios)

    def getCriterio(self):
        return self.criterio

    def setCriterio(self, criterio):
        self.criterio = criterio

    def getCupos(self):
        return self.cupos

    def setCupos(self, cupos):
        self.cupos = cupos

    @classmethod
    def getListabecas(cls):
        return cls.listaBecas

    @classmethod
    def setListaBecas(cls, lista):
        cls.listaBecas = lista

    def getCuposPorFacultad(self):
        return self.cuposPorFacultad

    def setCuposPorFacultad(self, cupos):
        self.cuposPorFacultad = cupos

    def setProfesoral(self, isProfesoral):
        self.isprofesoral = isProfesoral

    def isProfesoral(self):
        return self.isprofesoral

    def getNombre(self):
        return self.nombre

    def setNombre(self, nombre):
        self.nombre = nombre
