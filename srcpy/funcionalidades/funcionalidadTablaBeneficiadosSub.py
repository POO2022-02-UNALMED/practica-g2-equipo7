import tkinter as tk
from tkinter import ttk
from gestorAplicacion.gestion.subsidio import Subsidio
from gestorAplicacion.gestion.facultad import Facultad
from gestorAplicacion.personas.estudiante import Estudiante
from uiMain.fieldFrame import FieldFrame
from gestorAplicacion.gestion.lineasEnfasis import LineasEnfasis
from excepciones.invalidLinea_exception import invalidLinea
from excepciones.subsidioNotSelected_exception import SubsidioNotSelected
from excepciones.exception_pop_up import ExceptionPopUp
from excepciones.error_aplicacion import ErrorAplicacion


class FuncionalidadTablaBeneficiadosSubsidio(tk.Frame):
    def __init__(self, master):
        super().__init__(master=master, width=1000, height=1000)

        self.nombreFrame = tk.Label(self, text="Tabla de Elegibles", bd=10)
        titulo = "Filtros"
        valores = "Valores"
        self.fieldFrame = FieldFrame(
            self,
            titulo,
            ["Subsidio", "Facultad", "Linea Enfasis"],
            valores,
            None,
            [True, True, True],
            [
                [subsidio.getNombre() for subsidio in Subsidio.getListaSubsidio()],
                [facultad.getNombre() for facultad in Facultad.getListaFacultades()],
                [member.name for member in LineasEnfasis],
            ],
        )

        self.frameTabla = tk.Frame(self, bg="white", width=100, height=100)

        def metodoAceptar():

            subsidioElegido = self.fieldFrame.obtenerEntrada(0).get()
            for subsidio in Subsidio.getListaSubsidio():
                if subsidioElegido == subsidio.getNombre():
                    subsidioElegido = subsidio

            facultadElegida = self.fieldFrame.obtenerEntrada(1).get()
            for facultad in Facultad.getListaFacultades():
                if facultadElegida == facultad.getNombre():
                    facultadElegida = facultad
            lineaElegida = self.fieldFrame.obtenerEntrada(2).get()
            for linea in LineasEnfasis:
                if lineaElegida == linea.name:
                    lineaElegida = linea

            if not subsidioElegido:
                raise SubsidioNotSelected("No fue seleccionado un subsidio")

            self.frameTabla.grid(row=3, column=0, columnspan=2, padx=20, pady=20)

            t = TableEstudiantes(
                self.frameTabla, subsidioElegido, facultadElegida, lineaElegida
            )

        def borrarTabla():
            for widget in self.frameTabla.winfo_children():
                widget.destroy()
            self.frameTabla.grid_forget()

        def metodoBorrar():
            borrarTabla()
            self.fieldFrame.borrarEntradas()

        def metodoAceptarBtn():
            try:
                borrarTabla()
                metodoAceptar()
            except ErrorAplicacion as e:
                ExceptionPopUp(str(e))

        self.fieldFrame.crearBotones(metodoAceptarBtn, metodoBorrar)

    def mostrar(self):
        self.fieldFrame.actualizacion()
        self.nombreFrame.grid(row=0, column=0, columnspan=2)
        self.fieldFrame.grid(row=1, column=0, columnspan=2)


class TableEstudiantes:
    def __init__(self, root, subsidioElegido, facultad=None, lineaEnfasis=None):

        columnas = [("Nombre", "IES", "Facultad", "Linea Enfasis")]
        if not facultad and not lineaEnfasis:
            tabla = [
                (
                    est.getNombre(),
                    "%.2f" % round(Subsidio.calcularIES(est), 2),
                    est.getFacultad().getNombre(),
                    est.getLineaEnfasis().name,
                )
                for est in subsidioElegido.getBeneficiarios()
            ]
        elif facultad and not lineaEnfasis:
            tabla = [
                (
                    est.getNombre(),
                    "%.2f" % round(Subsidio.calcularIES(est), 2),
                    est.getFacultad().getNombre(),
                    est.getLineaEnfasis().name,
                )
                for est in subsidioElegido.getBeneficiarios()
                if est.getFacultad().getNombre() == facultad.getNombre()
            ]
        elif not facultad and lineaEnfasis:

            tabla = [
                (
                    est.getNombre(),
                    "%.2f" % round(Subsidio.calcularIES(est), 2),
                    est.getFacultad().getNombre(),
                    est.getLineaEnfasis().name,
                )
                for est in subsidioElegido.getBeneficiarios()
                if est.getLineaEnfasis().name == lineaEnfasis.name
            ]

        elif facultad and lineaEnfasis:

            tabla = [
                (
                    est.getNombre(),
                    "%.2f" % round(Subsidio.calcularIES(est), 2),
                    est.getFacultad().getNombre(),
                    est.getLineaEnfasis().name,
                )
                for est in subsidioElegido.getBeneficiarios()
                if est.getFacultad().getNombre() == facultad.getNombre()
                and est.getLineaEnfasis().name == lineaEnfasis.name
            ]

        self.lst = columnas + tabla

        totalEstudiantes = len(self.lst)
        totalParametros = len(self.lst[0])

        for i in range(totalEstudiantes):
            for j in range(totalParametros):

                self.e = tk.Entry(
                    root, width=20, fg="black", font=("Helvetica", 9, "bold")
                )

                self.e.grid(row=i, column=j)
                self.e.insert(tk.END, self.lst[i][j])
                self.e.config(state=tk.DISABLED)
