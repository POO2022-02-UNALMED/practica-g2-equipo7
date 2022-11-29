from tkinter import Frame, Text, Label
from gestorAplicacion.gestion.asignatura import Asignatura
from uiMain.fieldFrame import FieldFrame
from excepciones.error_aplicacion import ErrorAplicacion
from excepciones.exception_pop_up import ExceptionPopUp
from gestorAplicacion.gestion.lineasEnfasis import LineasEnfasis
from gestorAplicacion.personas.profesor import Profesor


class CrearAsignatura(Frame):
    def __init__(self, master):
        super().__init__(master=master, width=1000, height=1000)
        self.asignaturaTexto = Label(self)
        self.nombreFrame = Label(self, text="Crear Asignatura", bd=10)
        titulo = "Datos"
        valores = "Valores"
        profesores = [
            profesor.getNombre() for profesor in Profesor.getListaProfesores()
        ]
        profesores.insert(0, "Sin profesor")
        asignaturas = [
            asignatura.getNombre() for asignatura in Asignatura.getListaAsignaturas()
        ]
        asignaturas.insert(0, "Sin prerrequisito")
        self.fieldFrame = FieldFrame(
            self,
            titulo,
            ["nombre", "creditos", "profesor", "prerrequisito", "linea enfasis"],
            valores,
            None,
            [True, True, True, True, True],
            [
                "s",
                "n",
                profesores,
                asignaturas,
                [linea.name for linea in LineasEnfasis],
            ],
        )

        def metodoAceptar():
            try:
                self.fieldFrame.validacion()
                self.metodoCrear()
            except ErrorAplicacion as e:
                ExceptionPopUp(str(e))

        self.fieldFrame.crearBotones(metodoAceptar)

    def metodoCrear(self):
        valores = self.fieldFrame.getValores()

        nombre = valores[0]
        creditos = valores[1]
        profesor = valores[2]
        for p in Profesor.getListaProfesores():
            if p.getNombre() == profesor:
                profesor = [p]
            elif profesor == "Sin profesor":
                profesor = []

        prerrequisito = valores[3]
        for a in Asignatura.getListaAsignaturas():
            if a.getNombre() == prerrequisito:
                prerrequisito = [a]
            elif prerrequisito == "Sin prerrequisito":
                prerrequisito = []

        lineaEnfasis = valores[4]
        for l in LineasEnfasis:
            if l.name == lineaEnfasis:
                lineaEnfasis = l

        # Manejo de exepciones // No crear con el mismo nombre

        asignaturaCreada = Asignatura(
            nombre, creditos, profesor, prerrequisito, lineaEnfasis
        )

        self.asignaturaTexto.pack_forget()
        self.asignaturaTexto = Label(
            self,
            text="--- La asignatura " + nombre + ", fue creado exitosamente ---",
            font="Helvetica 11",
        )
        self.asignaturaTexto.pack()

    def mostrar(self):
        self.fieldFrame.actualizacion()
        self.nombreFrame.pack()
        self.fieldFrame.pack()
