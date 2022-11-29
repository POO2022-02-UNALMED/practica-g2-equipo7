import tkinter as tk
from tkinter import ttk
from gestorAplicacion.gestion.beca import Beca
from gestorAplicacion.gestion.facultad import Facultad
from gestorAplicacion.personas.estudiante import Estudiante


class FuncionalidadInformacionBecas(tk.Frame):
    def __init__(self, master):
        super().__init__(master=master)
        self.infoTexto = tk.Label(self)

        self.nombre = tk.Label(
            master=self, text="Informacion general Becas", font="Helvetica 11 bold"
        )
        self.subnombre = tk.Label(
            master=self,
            text="Elige una beca para visualizar Informacion",
            font="Helvetica 9",
            pady=5,
        )
        listaBecas = [beca.getNombre() for beca in Beca.getListabecas()]
        self.becaSeleccionada = ttk.Combobox(
            master=self, state="readonly", values=listaBecas
        )
        self.generar = tk.Button(
            master=self, text="Obtener", command=self.desplegarInformacion
        )
        self.borrar = tk.Button(master=self, text="Borrar", command=self.borrarInfo)

    def mostrar(self):
        self.nombre.grid(row=0, column=0, columnspan=2, pady=20)
        self.subnombre.grid(row=1, column=0, columnspan=2, pady=20)
        self.becaSeleccionada.grid(row=2, column=0, columnspan=2, pady=20)
        self.generar.grid(row=3, column=0, padx=20, pady=20)
        self.borrar.grid(row=3, column=1, padx=20, pady=20)

    def borrarInfo(self):
        self.infoTexto.grid_forget()

    def desplegarInformacion(self):
        becaElegida = self.becaSeleccionada.get()
        for beca in Beca.getListabecas():
            if becaElegida == beca.getNombre():
                becaElegida = beca
        self.becaSeleccionada.set("")

        if becaElegida.isProfesoral():
            dirigidoA = "Profesores"
        else:
            dirigidoA = "Estudiantes"

        self.infoTexto.grid_forget()
        mensaje = "Nombre de Beca : {nombre}\nCriterio de aptitud: {criterio}\nEsta beca está destinada a : {dirigidoA}\nCantidad de beneficiarios: {beneficiarios}\nCupos sobrantes: {sobrantes}\nPromedio académico beneficiarios: {promedio}".format(
            nombre=becaElegida.getNombre(),
            criterio=str(becaElegida.getCriterio()),
            dirigidoA=dirigidoA,
            beneficiarios=str(len(becaElegida.getBeneficiarios())),
            sobrantes=str(becaElegida.cuposSobrantes()),
            promedio=str(becaElegida.promedioBeneficiarios()),
        )
        self.infoTexto = tk.Label(
            self,
            text=mensaje,
            font="Helvetica 11",
        )
        self.infoTexto.grid(row=4, column=0, columnspan=2, padx=20, pady=20)

        return
