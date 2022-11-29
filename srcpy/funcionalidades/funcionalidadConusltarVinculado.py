import tkinter as tk
from tkinter import ttk
from gestorAplicacion.gestion.beca import Beca
from gestorAplicacion.personas.estudiante import Estudiante


class FuncionalidadConsultarVinculado(tk.Frame):
    def __init__(self, master):
        super().__init__(master=master)
        self.nombre = tk.Label(
            master=self, text="Consulta Elegibilidad", font="Helvetica 11 bold"
        )
        self.subnombre = tk.Label(master=self, text="", font="Helvetica 9")
        self.infoBeca = tk.Label(master=self, text="Beca: ", font="Helvetica 9")
        self.infoDocumento = tk.Label(
            master=self, text="Documento: ", font="Helvetica 9"
        )

        self.consultaVinculado = tk.Button(
            master=self, text="Validar documento", command=self.consultarVinculado
        )
        listaBecas = [beca.getNombre() for beca in Beca.getListabecas()]
        self.becaSeleccionada = ttk.Combobox(
            master=self, state="readonly", values=listaBecas
        )
        self.documetoIngresado = tk.Entry(master=self, width=20)
        self.borrar = tk.Button(master=self, text="Borrar", command=self.borrarInfo)

    def mostrar(self):
        self.nombre.grid(row=0, column=0, columnspan=2, pady=20)
        self.subnombre.grid(row=1, column=0, columnspan=2, pady=20)
        self.infoBeca.grid(row=2, column=0, pady=20)
        self.becaSeleccionada.grid(row=2, column=1, pady=20)
        self.infoDocumento.grid(row=3, column=0, pady=20)
        self.documetoIngresado.grid(row=3, column=1, pady=20)
        self.consultaVinculado.grid(row=4, column=0, pady=20)
        self.borrar.grid(row=4, column=1, padx=20, pady=20)

        pass

    def borrarInfo(self):
        self.infoTexto.grid_forget()

    def consultarVinculado(self):
        documento = int(self.documetoIngresado.get())
        becaElegida = self.becaSeleccionada.get()
        for beca in Beca.getListabecas():
            if becaElegida == beca.getNombre():
                becaElegida = beca
        self.becaSeleccionada.set("")
        self.documetoIngresado.delete(0, "end")
        for estudiante in becaElegida.getBeneficiarios():
            print([i.getDocumento() for i in becaElegida.getBeneficiarios()])
            if estudiante.getDocumento() == documento:
                mensaje = (
                    "El vinculado con documento " + str(documento) + " SI es elegible"
                )
                self.infoTexto = tk.Label(
                    self,
                    text=mensaje,
                    font="Helvetica 11",
                )
                self.infoTexto.grid(row=5, column=0, columnspan=2, padx=20, pady=20)

                return
        mensaje = "El vinculado con documento " + str(documento) + " NO es elegible"
        self.infoTexto = tk.Label(
            self,
            text=mensaje,
            font="Helvetica 11",
        )
        self.infoTexto.grid(row=5, column=0, columnspan=2, padx=20, pady=20)

        return
