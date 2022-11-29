import tkinter as tk
from tkinter import ttk
from gestorAplicacion.gestion.subsidio import Subsidio
from gestorAplicacion.gestion.facultad import Facultad
from gestorAplicacion.personas.estudiante import Estudiante


class FuncionalidadInformacionSubsidio(tk.Frame):
    def __init__(self, master):
        super().__init__(master=master)
        self.infoTexto = tk.Label(self)

        self.nombre = tk.Label(
            master=self, text="Informacion general Subsidio", font="Helvetica 11 bold"
        )
        self.subnombre = tk.Label(
            master=self,
            text="Elige un Subsidio para visualizar Informacion",
            font="Helvetica 9",
            pady=5,
        )
        listaSubsidios = [
            subsidio.getNombre() for subsidio in Subsidio.getListaSubsidio()
        ]
        self.subsidioSeleccionado = ttk.Combobox(
            master=self, state="readonly", values=listaSubsidios
        )
        self.generar = tk.Button(
            master=self, text="Obtener", command=self.desplegarInformacion
        )
        self.borrar = tk.Button(master=self, text="Borrar", command=self.borrarInfo)

    def mostrar(self):
        listaSubsidios = [
            subsidio.getNombre() for subsidio in Subsidio.getListaSubsidio()
        ]
        self.subsidioSeleccionado = ttk.Combobox(
            master=self, state="readonly", values=listaSubsidios
        )
        self.nombre.grid(row=0, column=0, columnspan=2, pady=20)
        self.subnombre.grid(row=1, column=0, columnspan=2, pady=20)
        self.subsidioSeleccionado.grid(row=2, column=0, columnspan=2, pady=20)
        self.generar.grid(row=3, column=0, padx=20, pady=20)
        self.borrar.grid(row=3, column=1, padx=20, pady=20)

    def borrarInfo(self):
        self.infoTexto.grid_forget()

    def desplegarInformacion(self):
        subsidioElegido = self.subsidioSeleccionado.get()
        for subsidio in Subsidio.getListaSubsidio():
            if subsidioElegido == subsidio.getNombre():
                subsidioElegido = subsidio
        self.subsidioSeleccionado.set("")

        dirigidoA = "Estudiantes"

        self.infoTexto.grid_forget()

        mensaje = "Nombre de Subsidio : {nombre}\nCriterio de aptitud (IES): {criterio}\nEste Subsidio está destinado a : {dirigidoA}\nCantidad de beneficiarios: {beneficiarios}\nPromedio académico beneficiarios: {promedio}".format(
            nombre=subsidioElegido.getNombre(),
            criterio=str(subsidioElegido.getCondicion()),
            dirigidoA=dirigidoA,
            beneficiarios=str(len(subsidioElegido.getBeneficiarios())),
            promedio=str(subsidioElegido.promedioBeneficiarios()),
        )
        self.infoTexto = tk.Label(
            self,
            text=mensaje,
            font="Helvetica 11",
        )
        self.infoTexto.grid(row=4, column=0, columnspan=2, padx=20, pady=20)

        return
