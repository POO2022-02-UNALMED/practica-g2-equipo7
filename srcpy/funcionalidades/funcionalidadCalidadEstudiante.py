import tkinter as tk
from tkinter import *
from tkinter import ttk
from gestorAplicacion.personas.estudiante import Estudiante
from gestorAplicacion.gestion.facultad import Facultad
from gestorAplicacion.gestion.lineasEnfasis import LineasEnfasis
from excepciones.empty_exception import EmptyException
from excepciones.exception_pop_up import ExceptionPopUp
from excepciones.error_aplicacion import ErrorAplicacion


class FuncionalidadCalidadEstudiante(tk.Frame):
    def __init__(self, master):
        super().__init__(master=master)
        self.ventana = master
        self.nombre = tk.Label(
            master=self, text="CALIDAD ESTUDIANTE", font="Helvetica 11 bold"
        )
        self.subTitulo = tk.Label(
            self,
            text="Seleccione el criterio por el cual quiere realizar la busqueda",
            font="Helvetica 9",
            pady=5
        )

        self.criterioConsulta = ttk.Combobox(
            self,
            values=[
                "Documento",
                "Facultad",
                "Linea de enfasis"
            ]
        )

        self.seleccionar = tk.Button(
            self,
            text="Seleccionar",
            command=self.seleccionar
        )

        self.seleccionTabla = tk.Frame(
            self,
        )

        self.informacionConsultaDocumento = tk.Label(
            self.seleccionTabla,
            text="Ingrese el documento",
            font="Helvetica 9",
            pady=5
        )

        self.informacionConsultaFacultad = tk.Label(
            self.seleccionTabla,
            text="Seleccione la facultad",
            font="Helvetica 9",
            pady=5
        )

        self.informacionConsultaLineaEnfasis = tk.Label(
            self.seleccionTabla,
            text="Seleccione la linea de enfasis",
            font="Helvetica 9",
            pady=5
        )

        self.documento = IntVar()
        self.documentoEntry = tk.Entry(self.seleccionTabla, textvariable=self.documento, width=20)
        self.consultarPorDocumento = tk.Button(
            self.seleccionTabla,
            text="Consultar", command=self.consultaPorDocumento,
            bd=3
        )

        listaFacultades = [facultad.getNombre() for facultad in Facultad.getListaFacultades()]
        self.facultadSeleccionada = ttk.Combobox(
            self.seleccionTabla,
            values=listaFacultades
        )

        lineasEnfasis = [enfasis.name for enfasis in LineasEnfasis]
        self.lineaEnfasisSeleccionada = ttk.Combobox(
            self.seleccionTabla,
            values=lineasEnfasis
        )

        self.consultarPorTabla = tk.Button(
            self.seleccionTabla,
            text="Consultar", command=self.consultaPorTabla,
            bd=3
        )

        self.frameTabla = tk.Frame(
            self,
            width=100,
            height=100
        )

    def mostrar(self):
        self.nombre.grid(row=0, column=1, pady=20)
        self.subTitulo.grid(row=1, column=1, pady=7)
        self.criterioConsulta.grid(row=2, column=1, pady=10)
        self.seleccionar.grid(row=3, column=1, pady=7)
        self.seleccionTabla.grid(row=10, column=1, pady=20, padx=20)
        self.frameTabla.grid(row=14, column=1, pady=20, padx=20)

    def consultaPorDocumento(self):
        try:
            documentoEstudiante = self.documento.get()

            if not documentoEstudiante:
                raise EmptyException()

            for estudiante in Estudiante.getListaEstudiantes():
                if estudiante.getDocumento() == documentoEstudiante:
                    calidadEstudiante = estudiante.calidadEstudiante()
                    return tk.messagebox.showinfo(
                        message=f"El estudiante con documento {documentoEstudiante} tiene una calidad de estudiante {calidadEstudiante}",
                        title="Calidad Estudiante"
                    )

            return tk.messagebox.showinfo(
                message=f"No se ha encontrado información para el estudiante con documento {documentoEstudiante}",
                title="Calidad Estudiante"
            )

        except ErrorAplicacion as e:
            ExceptionPopUp(str(e))

    def consultaPorTabla(self):
        try:
            ##Limpieza de la tabla
            for widget in self.frameTabla.winfo_children():
                widget.destroy()

            if self.criterioConsulta.get() == "Facultad":
                if not self.facultadSeleccionada.get():
                    raise EmptyException()
                facultadSeleccionada = self.facultadSeleccionada.get()
                for facultad in Facultad.getListaFacultades():
                    if facultadSeleccionada == facultad.getNombre():
                        facultadSeleccionada = facultad

                columnas = [("Nombre", "Calidad", "Facultad")]
                parametros = [
                    (est.getNombre(),
                     est.calidadEstudiante(),
                     est.getFacultad().getNombre())
                    for est in facultadSeleccionada.getEstudiantes()
                ]
                t = TableEstudiantes(self.frameTabla, columnas, parametros)

            elif self.criterioConsulta.get() == "Linea de enfasis":
                if not self.lineaEnfasisSeleccionada.get():
                    raise EmptyException()
                lineaSeleccionada = self.lineaEnfasisSeleccionada.get()
                listaEstudiantes = [estudiante for estudiante in Estudiante.getListaEstudiantes() if
                                    estudiante.getLineaEnfasis().name == lineaSeleccionada]
                columnas = [("Nombre", "Calidad", "Linea de enfasis")]
                parametros = [
                    (est.getNombre(),
                     est.calidadEstudiante(),
                     est.getLineaEnfasis().name)
                    for est in listaEstudiantes
                ]
                t = TableEstudiantes(self.frameTabla, columnas, parametros)

        except ErrorAplicacion as e:
            ExceptionPopUp(str(e))

    def seleccionar(self):

        for widget in self.seleccionTabla.winfo_children():
            widget.grid_forget()

        try:
            if not self.criterioConsulta.get():
                raise EmptyException()

            if self.criterioConsulta.get() == "Documento":
                self.informacionConsultaDocumento.grid(row=11, column=1)
                self.documentoEntry.grid(row=12, column=1, pady=7)
                self.consultarPorDocumento.grid(row=13, column=1)

            elif self.criterioConsulta.get() == "Facultad":
                self.informacionConsultaFacultad.grid(row=11, column=1)
                self.facultadSeleccionada.grid(row=12, column=1)
                self.consultarPorTabla.grid(row=13, column=1, pady=7)

            elif self.criterioConsulta.get() == "Linea de enfasis":
                self.informacionConsultaLineaEnfasis.grid(row=11, column=1)
                self.lineaEnfasisSeleccionada.grid(row=12, column=1)
                self.consultarPorTabla.grid(row=13, column=1, pady=7)

        except ErrorAplicacion as e:
            ExceptionPopUp(str(e))


class TableEstudiantes:
    def __init__(self, root, lstColumnas, lstParametros):

        self.lst = lstColumnas + lstParametros

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
