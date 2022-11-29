from tkinter import Frame, Text, Label
from uiMain.fieldFrame import FieldFrame
from excepciones.error_aplicacion import ErrorAplicacion
from excepciones.exception_pop_up import ExceptionPopUp
from gestorAplicacion.gestion.beca import Beca
from gestorAplicacion.gestion.facultad import Facultad


class CrearBecas(Frame):
    def __init__(self, master):
        super().__init__(master=master, width=1000, height=1000)
        self.profesorTexto = Label(self)
        self.nombreFrame = Label(self, text="Crear Beca", bd=10)
        titulo = "Datos"
        valores = "Valores"
        self.fieldFrame = FieldFrame(
            self,
            titulo,
            ["nombre", "criterio", "cupos por facultad", "Tipo"],
            valores,
            None,
            [True, True, True, True],
            ["s", "n", "n", ["estudiantil", "profesoral"]],
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
        criterio = int(valores[1])
        cuposFacultad = int(valores[2])
        facultades = Facultad.listaFacultades
        cuposPorFacultad = dict.fromkeys(facultades, cuposFacultad)
        profesoral = False
        if valores[3] == "profesoral":
            profesoral = True

        # Manejo de exepciones // No crear con el mismo documento

        becaCreada = Beca(nombre, criterio, cuposPorFacultad, profesoral)

        self.profesorTexto.pack_forget()
        self.profesorTexto = Label(
            self,
            text="--- la beca " + nombre + ", fue creado exitosamente ---",
            font="Helvetica 11",
        )
        self.profesorTexto.pack()

    def mostrar(self):
        self.fieldFrame.actualizacion()
        self.nombreFrame.pack()
        self.fieldFrame.pack()
