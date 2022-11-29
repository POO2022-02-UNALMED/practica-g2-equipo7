from tkinter import Frame, Text, Label
from gestorAplicacion.personas.profesor import Profesor
from gestorAplicacion.personas.persona import Persona
from uiMain.fieldFrame import FieldFrame
from excepciones.error_aplicacion import ErrorAplicacion
from excepciones.exception_pop_up import ExceptionPopUp
from gestorAplicacion.gestion.facultad import Facultad


class CrearProfesores(Frame):
    def __init__(self, master):
        super().__init__(master=master, width=1000, height=1000)
        self.profesorTexto = Label(self)
        self.nombreFrame = Label(self, text="Crear Profesor", bd=10)
        titulo = "Datos"
        valores = "Valores"
        self.fieldFrame = FieldFrame(
            self,
            titulo,
            ["documento", "nombre", "edad", "facultad"],
            valores,
            None,
            [True, True, True, True],
            [
                "n",
                "s",
                "n",
                [facultad.getNombre() for facultad in Facultad.getListaFacultades()],
            ],
        )

        def metodoAceptar():
            try:
                Persona.checkDocumento(int(self.fieldFrame.obtenerEntrada(0).get()))

                self.fieldFrame.validacion()
                self.metodoCrear()
            except ErrorAplicacion as e:
                ExceptionPopUp(str(e))

        self.fieldFrame.crearBotones(metodoAceptar)

    def metodoCrear(self):
        valores = self.fieldFrame.getValores()

        documento = int(valores[0])
        nombre = valores[1]
        edad = int(valores[2])
        facultad = valores[3]

        # Manejo de exepciones // No crear con el mismo documento

        profesorCreado = Profesor(documento, nombre, edad, {}, facultad, None)

        for f in Facultad.getListaFacultades():
            if f.getNombre() == facultad:
                f.vincularProfesor(profesorCreado)

        self.profesorTexto.pack_forget()
        self.profesorTexto = Label(
            self,
            text="--- El profesor con documento "
            + str(documento)
            + ", fue creado exitosamente ---",
            font="Helvetica 11",
        )
        self.profesorTexto.pack()

    def mostrar(self):
        self.fieldFrame.actualizacion()
        self.nombreFrame.pack()
        self.fieldFrame.pack()
