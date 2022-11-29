class Facultad:
    listaFacultades = []
    profesoresVinculados = []

    def __init__(self, nombre, profesoresVinculados, estudiantes):
        from gestorAplicacion.personas.estudiante import Estudiante
        from gestorAplicacion.personas.profesor import Profesor

        self.nombre = nombre
        self.setProfesoresVinculados(profesoresVinculados)
        self.setEstudiantes(estudiantes)
        Facultad.listaFacultades.append(self)

    def vincularProfesor(self, profesor):
        self.profesoresVinculados.append(profesor)

    def crearAsignatura(self, asignatura):
        pass

    def getNombre(self):
        return self.nombre

    def setNombre(self, nombre):
        self.nombre = nombre

    def getProfesoresVinculados(self):
        return self.profesoresVinculados

    def setProfesoresVinculados(self, profesores):
        from gestorAplicacion.personas.profesor import Profesor

        self.profesoresVinculados = profesores
        for profesor in self.profesoresVinculados:
            profesor.setFacultad(self)

    def getEstudiantes(self):
        return self.estudiantes

    def setEstudiantes(self, estudiantes):
        for estudiante in estudiantes:
            estudiante.setFacultad(self)
        self.estudiantes = estudiantes

    @classmethod
    def getListaFacultades(cls):
        return cls.listaFacultades

    @classmethod
    def setListaFacultades(cls, facultades):
        cls.listaFacultades = facultades
