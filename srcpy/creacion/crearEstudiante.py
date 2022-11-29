from tkinter import Frame, Text, Label
from gestorAplicacion.personas.profesor import Profesor
from gestorAplicacion.personas.persona import Persona
from gestorAplicacion.personas.estudiante import Estudiante
from uiMain.fieldFrame import FieldFrame
from excepciones.error_aplicacion import ErrorAplicacion
from excepciones.exception_pop_up import ExceptionPopUp
from gestorAplicacion.gestion.lineasEnfasis import LineasEnfasis


class CrearEstudiante(Frame):
    def __init__(self, master):
        super().__init__(master=master, width=1000, height=1000)
        self.estudianteTexto = Label(self)
        self.nombreFrame = Label(self, text="Crear Estudiante", bd=10)
        titulo = "Datos"
        valores = "Valores"
        self.fieldFrame = FieldFrame(
            self,
            titulo,
            [
                "documento",
                "nombre",
                "edad",
                "semestre",
                "linea enfasis",
                "estrato",
                "ingresos familiares",
                "tipo de colegio",
                "numero familiares",
            ],
            valores,
            None,
            [
                True,
                True,
                True,
                True,
                True,
                True,
                True,
                True,
                True,
            ],
            [
                "n",
                "s",
                "n",
                "n",
                [
                    linea.name
                    for linea in LineasEnfasis
                    if linea != LineasEnfasis.BASICAS
                ],
                [estrato for estrato in range(1, 7)],
                "n",
                ["Publico", "Privado"],
                "n",
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
        semestre = int(valores[3])
        lineaEnfasis = valores[4]
        for l in LineasEnfasis:
            if l.name == lineaEnfasis:
                lineaEnfasis = l
        estrato = int(valores[5])
        ingresosFamiliares = int(valores[6])
        tipoColegio = True
        if valores[7] == "Privado":
            tipoColegio = False
        numeroFamiliares = int(valores[8])

        # Manejo de exepciones // No crear con el mismo documento

        estudianteCreado = Estudiante(
            documento,
            nombre,
            edad,
            {},
            0,
            semestre,
            lineaEnfasis,
            [],
            estrato,
            ingresosFamiliares,
            tipoColegio,
            numeroFamiliares,
        )

        self.estudianteTexto.pack_forget()
        self.estudianteTexto = Label(
            self,
            text="--- El estudiante con documento "
            + str(documento)
            + ", fue creado exitosamente ---",
            font="Helvetica 11",
        )
        self.estudianteTexto.pack()

    def mostrar(self):
        self.fieldFrame.actualizacion()
        self.nombreFrame.pack()
        self.fieldFrame.pack()
