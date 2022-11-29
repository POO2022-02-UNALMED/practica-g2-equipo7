import tkinter as tk

from gestorAplicacion.gestion.facultad import Facultad
from gestorAplicacion.personas.estudiante import Estudiante
from uiMain.fieldFrame import FieldFrame
from gestorAplicacion.gestion.lineasEnfasis import LineasEnfasis
from excepciones.exception_pop_up import ExceptionPopUp
from excepciones.error_aplicacion import ErrorAplicacion


class FuncionalidadVistaGeneralEstudiantes(tk.Frame):
    def __init__(self, master):
        super().__init__(master=master, width=1000, height=1000)

        self.nombreFrame = tk.Label(self, text="Tabla de estudiantes vinculados", bd=10)
        titulo = "Filtros"
        valores = "Valores"
        self.fieldFrame = FieldFrame(
            self,
            titulo,
            ["Facultad", "Linea de énfasis"],
            valores,
            None,
            [
                True,
                True,
            ],
            [
                [facu.getNombre() for facu in Facultad.getListaFacultades()],
                [linea.name for linea in LineasEnfasis],
            ],
        )
        # print(self.fieldFrame._entradas)

        self.frameTabla = tk.Frame(self, bg="white", width=150, height=100)

        def metodoAceptar():

            facultadElegida = self.fieldFrame.obtenerEntrada(0).get()
            for facultad in Facultad.getListaFacultades():
                if facultadElegida == facultad.getNombre():
                    facultadElegida = facultad.getNombre()

            lineaElegida = self.fieldFrame.obtenerEntrada(1).get()
            for linea in LineasEnfasis:
                if lineaElegida == linea.name:
                    lineaElegida = linea.name

            """if not facultadElegida:
                raise becaNotSelected("No fue seleccionada una facultad")

            if not lineaElegida:
                raise becaNotSelected("No fue seleccionada una linea de énfasis")"""

            self.frameTabla.grid(row=3, column=0, columnspan=2, padx=20, pady=20)

            t = TableEstudiantes(self.frameTabla, facultadElegida, lineaElegida)

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
    def __init__(self, root, facultad=None, lineaEnfasis=None):
        columnas = [
            (
                "Nombre",
                "Documento",
                "Semestre",
                "Promedio",
                "Facultad",
                "Linea de énfasis",
            )
        ]

        if not facultad and not lineaEnfasis:
            tabla = [
                (
                    est.getNombre(),
                    est.getDocumento(),
                    est.getSemestre(),
                    est.getPromedio(),
                    est.getFacultad().getNombre(),
                    est.getLineaEnfasis().name,
                )
                for est in Estudiante.getListaEstudiantes()
            ]

        elif facultad and not lineaEnfasis:
            tabla = [
                (
                    est.getNombre(),
                    est.getDocumento(),
                    est.getSemestre(),
                    est.getPromedio(),
                    est.getFacultad().getNombre(),
                    est.getLineaEnfasis().name,
                )
                for est in Estudiante.getListaEstudiantes()
                if est.getFacultad().getNombre() == facultad
            ]

        elif not facultad and lineaEnfasis:
            tabla = [
                (
                    est.getNombre(),
                    est.getDocumento(),
                    est.getSemestre(),
                    est.getPromedio(),
                    est.getFacultad().getNombre(),
                    est.getLineaEnfasis().name,
                )
                for est in Estudiante.getListaEstudiantes()
                if est.getLineaEnfasis().name == lineaEnfasis
            ]

        else:
            tabla = [
                (
                    est.getNombre(),
                    est.getDocumento(),
                    est.getSemestre(),
                    est.getPromedio(),
                    est.getFacultad().getNombre(),
                    est.getLineaEnfasis().name,
                )
                for est in Estudiante.getListaEstudiantes()
                if est.getFacultad().getNombre() == facultad
                and est.getLineaEnfasis().name == lineaEnfasis
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
