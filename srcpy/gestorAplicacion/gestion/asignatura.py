class Asignatura:
    listaAsiganturas = []

    def __init__(
        self, nombre, creditos, profesor, prerrequisitos, lineaenfasis
    ) -> None:
        self.nombre = nombre
        self.creditos = creditos
        self.profesor = profesor
        self.prerrequisitos = prerrequisitos
        self.lineaEnfasis = lineaenfasis
        Asignatura.listaAsiganturas.append(self)

    def getCreditos(self):
        return self.creditos

    def setCreditos(self, creditos):
        self.creditos = creditos

    def getProfesor(self):
        return self.profesor

    def setProfesor(self, profesor):
        self.profesor = profesor

    def getLineaEnfasis(self):
        return self.lineaEnfasis

    def setLineaEnfasis(self, linea):
        self.lineaEnfasis = linea

    def getPrerequisitos(self):
        return self.prerrequisitos

    def setPrerequisitos(self, prerrequisitos):
        self.prerrequisitos = prerrequisitos
    @classmethod
    def getListaAsignaturas(cls):
        return cls.listaAsiganturas
    @classmethod
    def setListaAsignaturas(cls, lista):
        cls.listaAsiganturas = lista

    def getNombre(self):
        return self.nombre

    def setNombre(self, nombre):
        self.nombre = nombre

    def agregarProfesor(self, profesor):
        self.profesor.append(profesor)
