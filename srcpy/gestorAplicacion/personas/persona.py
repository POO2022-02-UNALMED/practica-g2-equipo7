from abc import ABC, abstractmethod
from excepciones.documentoInvalido import DocumentoInvalido


class Persona(ABC):
    def __init__(self, documento, nombre, edad):
        self.documento = documento
        self.nombre = nombre
        self.edad = edad

    @classmethod
    def checkDocumento(cls, documento):
        from gestorAplicacion.personas.profesor import Profesor
        from gestorAplicacion.personas.estudiante import Estudiante

        vinculadosGeneral = (
            Profesor.getListaProfesores() + Estudiante.getListaEstudiantes()
        )
        documentosGeneral = [x.getDocumento() for x in vinculadosGeneral]
        if documento in documentosGeneral:
            raise DocumentoInvalido("El documento ingresado ya está registrado")

    @abstractmethod
    def calcularPromedio(self):
        pass

    def getFacultad(self):
        pass

    def getLineaEnfasis(self):
        pass

    def getDocumento(self):
        return self.documento

    def setDocumento(self, documento):
        self.documento = documento

    def getNombre(self):
        return self.nombre

    def setNombre(self, nombre):
        self.nombre = nombre

    def getEdad(self):
        return self.edad

    def setEdad(self, edad):
        self.edad = edad
