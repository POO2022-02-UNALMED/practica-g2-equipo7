from gestorAplicacion.personas.persona import Persona
from gestorAplicacion.gestion.beca import Beca
from functools import total_ordering


@total_ordering
class Profesor(Persona):

    listaProfesores = []

    def __init__(
        self, documento, nombre, edad, asignaturasDictadas, facultad, fechaIngreso
    ):

        super().__init__(documento, nombre, edad)
        self.asignaturasDictadas = asignaturasDictadas
        self.facultad = facultad
        self.fechaIngreso = fechaIngreso
        Profesor.listaProfesores.append(self)

    def calcularPromedio(self):
        sum = 0
        for key in list(self.asignaturasDictadas.keys()):
            sum += self.asignaturasDictadas[key]
        return sum / len(self.asignaturasDictadas)

    def getAsignaturasDictadas(self):
        return self.asignaturasDictadas

    def setAsignaturasDictadas(self, asignaturas):
        from gestorAplicacion.gestion.asignatura import Asignatura

        for key in list(self.asignaturasDictadas.keys()):
            key.agregarProfesor(self)

    def vistaGeneralProfesores(self):
        ## Ya que vamos a implementar un GUI, no veo necesidad de
        ## implementar alguna libreria o implementación muy detallada para la generación de tablas

        print("-------------")
        print("-------------")
        print(
            "NOMBRE", "DOCUMENTO", "BECA", "PROMEDIOASIGNATURASDICTADAS", "FECHAINGRESO"
        )
        for p in Profesor.listaProfesores:
            informacion_beca = "No aplica"
            fecha_inicio = (
                "No adjudicada" if p.fechaIngreso == None else p.getFechaIngreso()
            )

            for b in Beca.getListabecas():
                if p in b.getBeneficiarios():
                    informacion_beca = b.getNombre()
                    break

            print(
                p.getNombre(),
                p.getDocumento(),
                informacion_beca,
                p.calcularPromedio(),
                fecha_inicio,
            )
        print("-------------")
        print("-------------")

    def agregarAsignaturaDisctadas(self, key, value):
        self.asignaturasDictadas[key] = value
    def getFacultad(self):
        return self.facultad

    def setFacultad(self, facultad):
        self.facultad = facultad

    def getFechaIngreso(self):
        return self.fechaIngreso

    def setFechaIngeso(self, fecha):
        self.fechaIngreso = fecha

    def getPromedio(self):
        return self.calcularPromedio()

    def setPromedio(self, promedio):
        self.promedio = promedio

    @classmethod
    def getListaProfesores(cls):
        return cls.listaProfesores

    @classmethod
    def setListaProfesores(cls, listaProfesores):
        cls.listaProfesores = listaProfesores

    def __lt__(self, other):
        return self.calcularPromedio() > other.calcularPromedio()

    def __eq__(self, other):
        return self.calcularPromedio() == other.calcularPromedio()
