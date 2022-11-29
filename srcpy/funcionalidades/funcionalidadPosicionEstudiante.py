import tkinter as tk
from tkinter import ttk
from tkinter import *
from gestorAplicacion.gestion.beca import Beca
from gestorAplicacion.gestion.facultad import Facultad
from gestorAplicacion.personas.estudiante import Estudiante
from gestorAplicacion.personas.profesor import Profesor
from excepciones.documentoInvalido import DocumentoInvalido
from excepciones.exception_pop_up import ExceptionPopUp


class FuncionalidadPosicionEstudiante(tk.Frame):
    def __init__(self, master):
        super().__init__(master=master)
        self.ventana = master
        self.titulo = tk.Label(
            self, text="CONSULTAR POSICIÓN DEL ESTUDIANTE", font="Helvetica 11 bold"
        )
        self.subTitulo = tk.Label(
            self,
            text="Ingrese el documento del estudiante",
            font="Helvetica 9",
            pady=5,
        )
        self.document = IntVar()
        self.documentEntry = Entry(self, textvariable=self.document, bd=3)
        self.buscarButton = tk.Button(self, text="Buscar", command=self.busqueda)
        self.borrarButton = tk.Button(self, text="Borrar", command=self.borrar)

        self.estdInfo = tk.Label(self, text="", font="Helvetica 11 bold")

        self.estdName = tk.Label(self, text="NOMBRE", font="Helvetica 11")
        self.estdProm = tk.Label(self, text="PROMEDIO", font="Helvetica 11")
        self.estdSem = tk.Label(self, text="SEMESTRE", font="Helvetica 11")
        self.estdLE = tk.Label(self, text="LINEA DE ENFASIS", font="Helvetica 11")
        self.estdFac = tk.Label(self, text="FACULTAD", font="Helvetica 11")

        self.nameEntry = Entry(self, bd=3, state="disabled", width=30)
        self.promEntry = Entry(self, bd=3, state="disabled", width=30)
        self.semEntry = Entry(self, bd=3, state="disabled", width=30)
        self.leEntry = Entry(self, bd=3, state="disabled", width=30)
        self.facEntry = Entry(self, bd=3, state="disabled", width=30)

        self.resultlbl = tk.Label(self, text="", font="Helvetica 11 bold")
        self.consultarButton = tk.Button(
            self, text="Consultar", command=self.consulta, bd=3
        )
        self.rangolbl = tk.Label(
            self, text="CONSULTAR\nPOSICIÓN POR:", font="Helvetica 11", width=15
        )
        self.rangoBusqueda = ttk.Combobox(
            self, values=["SEMESTRE", "FACULTAD", "LINEA DE ENFASIS"], width=25
        )

    def mostrar(self):
        # Adecuaciones de la ventana
        self.ventana.geometry("800x600")
        self.ventana.config(cursor="hand2")
        self.ventana.resizable(True, True)
        self.ventana.title("Consultar posición")

        self.titulo.grid(row=0, column=1, pady=20)
        self.subTitulo.grid(row=1, column=1, pady=7)
        self.documentEntry.grid(row=2, column=1, pady=7)
        self.buscarButton.grid(row=3, column=1, pady=10)
        self.borrarButton.grid(row=2, column=1, pady=10, sticky="e")
        self.estdInfo.grid(row=4, column=1, pady=10)

        self.estdName.grid(row=5, column=0, pady=2, sticky="e")
        self.estdProm.grid(row=6, column=0, pady=2, sticky="e")
        self.estdSem.grid(row=7, column=0, pady=2, sticky="e")
        self.estdLE.grid(row=8, column=0, pady=2, sticky="e")
        self.estdFac.grid(row=9, column=0, pady=2, sticky="e")

        self.nameEntry.grid(row=5, column=1, columnspan=2, pady=2)
        self.promEntry.grid(row=6, column=1, columnspan=2, pady=2)
        self.semEntry.grid(row=7, column=1, columnspan=2, pady=2)
        self.leEntry.grid(row=8, column=1, columnspan=2, pady=2)
        self.facEntry.grid(row=9, column=1, columnspan=2, pady=2)

    def checkDoc(self, documento):
        vinculadosGeneral = (
            Profesor.getListaProfesores() + Estudiante.getListaEstudiantes()
        )
        documentosGeneral = [x.getDocumento() for x in vinculadosGeneral]
        if documento not in documentosGeneral:
            raise DocumentoInvalido("El documento ingresado no existe")

    def busqueda(self):
        documento = self.document.get()
        estudianteConsultado = None

        name = StringVar()
        prom = StringVar()
        sem = StringVar()
        le = StringVar()
        fac = StringVar()

        self.nameEntry.config(textvariable=name)
        self.promEntry.config(textvariable=prom)
        self.semEntry.config(textvariable=sem)
        self.leEntry.config(textvariable=le)
        self.facEntry.config(textvariable=fac)

        for facultad in Facultad.getListaFacultades():
            for estudiante in facultad.getEstudiantes():
                if documento == estudiante.documento:
                    estudianteConsultado = estudiante
        try:
            self.checkDoc(documento)
        except Exception as e:
            ExceptionPopUp(str(e))
        if estudianteConsultado is None:
            self.estdInfo.config(text="--- Documento no encontrado ---")

            name.set("")
            prom.set(str(""))
            sem.set(str(""))
            le.set("")
            fac.set("")

            self.consultarButton.grid_forget()
            self.rangolbl.grid_forget()
            self.rangoBusqueda.grid_forget()
            self.resultlbl.grid_forget()
        else:
            self.estdInfo.config(text="--- Documento encontrado ---")

            name.set(estudianteConsultado.getNombre())
            prom.set(str(estudianteConsultado.calcularPromedio()))
            sem.set(str(estudianteConsultado.getSemestre()))
            le.set(estudianteConsultado.getLineaEnfasis().name)
            fac.set(estudianteConsultado.getFacultad().getNombre())

            self.consultarButton.grid(
                row=12,
                column=1,
                pady=10,
            )
            self.rangolbl.grid(row=10, column=0, pady=10, rowspan=2, sticky="e")
            self.rangoBusqueda.grid(row=11, column=1, pady=10)
            self.resultlbl.grid(row=13, column=1, pady=10, rowspan=2)

    def consulta(self):
        estudianteConsultado = None
        documento = self.document.get()
        for facultad in Facultad.getListaFacultades():
            for estudiante in facultad.getEstudiantes():
                if documento == estudiante.documento:
                    estudianteConsultado = estudiante

        message = "POR FAVOR INGRESE UN RANGO DE BUSQUEDA PARA CONSULTAR"
        if self.rangoBusqueda.get() == "SEMESTRE":
            message = (
                "\n"
                + "POSICIÓN: "
                + str(Estudiante.posicionEstudiante(estudianteConsultado, "semestre"))
                + "\n"
                + "SEMESTRE: "
                + str(estudianteConsultado.getSemestre())
                + "\n"
            )
        elif self.rangoBusqueda.get() == "FACULTAD":
            message = (
                "\n"
                + "POSICIÓN: "
                + str(Estudiante.posicionEstudiante(estudianteConsultado, "facultad"))
                + "\n"
                + "FACULTAD: "
                + estudianteConsultado.getFacultad().getNombre()
                + "\n"
            )
        else:
            message = (
                "\n"
                + "POSICIÓN: "
                + str(
                    Estudiante.posicionEstudiante(estudianteConsultado, "lineaEnfasis")
                )
                + "\n"
                + "LÍNEA DE ÉNFASIS: "
                + estudianteConsultado.getLineaEnfasis().name
                + "\n"
            )

        self.resultlbl.config(text=message)

    def borrar(self):
        self.consultarButton.grid_forget()
        self.rangolbl.grid_forget()
        self.rangoBusqueda.grid_forget()
        self.resultlbl.grid_forget()

        self.document.set(0)
