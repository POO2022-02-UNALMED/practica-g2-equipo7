import tkinter as tk


class FuncionalidadBecas(tk.Frame):
    def __init__(self, master):
        super().__init__(master=master)
        self.nombre = tk.Label(
            master=self, text="Apartado de Becas", font="Helvetica 11 bold"
        )
        self.subnombre = tk.Label(
            master=self, text="¿Qué acción desea realizar?", font="Helvetica 9"
        )

        self.consultaVinculado = tk.Button(text="Validar documento")

    def mostrar(self):
        self.nombre.pack(fill=tk.BOTH, padx=5, pady=5)
        self.subnombre.pack(fill=tk.BOTH, padx=5, pady=10)
        self.consultaVinculado.pack()
