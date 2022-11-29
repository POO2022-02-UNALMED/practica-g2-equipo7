import pickle

from gestorAplicacion.gestion.asignatura import Asignatura
from gestorAplicacion.gestion.beca import Beca
from gestorAplicacion.gestion.facultad import Facultad
from gestorAplicacion.gestion.lineasEnfasis import LineasEnfasis
from gestorAplicacion.gestion.subsidio import Subsidio
from gestorAplicacion.personas.estudiante import Estudiante
from gestorAplicacion.personas.profesor import Profesor


def serializar():
    print("serializando...")

    fileEstudiantes = open("temp/estudiantes.pkl", "wb")
    fileProfesores = open("temp/profesores.pkl", "wb")
    fileFacultades = open("temp/facultades.pkl", "wb")
    fileAsignaturas = open("temp/asignaturas.pkl", "wb")
    fileBecas = open("temp/becas.pkl", "wb")
    fileSubsidio = open("temp/subsidios.pkl", "wb")

    files = [
        fileEstudiantes,
        fileProfesores,
        fileFacultades,
        fileAsignaturas,
        fileBecas,
        fileSubsidio
    ]

    pickle.dump(Estudiante.getListaEstudiantes(), fileEstudiantes)
    pickle.dump(Profesor.getListaProfesores(), fileProfesores)
    pickle.dump(Facultad.getListaFacultades(), fileFacultades)
    pickle.dump(Asignatura.getListaAsignaturas(), fileAsignaturas)
    pickle.dump(Beca.getListabecas(), fileBecas)
    pickle.dump(Subsidio.getListaSubsidio(), fileSubsidio)

    for file in files:
        file.close()
        del file

def deserializar():
    print("deserializando...")

    fileEstudiantes = open("baseDatos/temp/estudiantes.pkl", "rb")
    fileProfesores = open("baseDatos/temp/profesores.pkl", "rb")
    fileFacultades = open("baseDatos/temp/facultades.pkl", "rb")
    fileAsignaturas = open("baseDatos/temp/asignaturas.pkl", "rb")
    fileBecas = open("baseDatos/temp/becas.pkl", "rb")
    fileSubsidio = open("baseDatos/temp/subsidios.pkl", "rb")

    Estudiante.setListaEstudiantes(pickle.load(fileEstudiantes))
    Profesor.setListaProfesores(pickle.load(fileProfesores))
    Facultad.setListaFacultades(pickle.load(fileFacultades))
    Asignatura.setListaAsignaturas(pickle.load(fileAsignaturas))
    Beca.setListaBecas(pickle.load(fileBecas))
    Subsidio.setListaSubsidio(pickle.load(fileSubsidio))

class serializador:
    pass

if __name__ == "__main__":
    deserializar()
