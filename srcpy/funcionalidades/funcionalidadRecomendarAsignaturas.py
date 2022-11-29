import tkinter as tk
from tkinter import ttk
from gestorAplicacion.personas.estudiante import Estudiante
from gestorAplicacion.gestion.lineasEnfasis import LineasEnfasis

class FuncionalidadRecomendarAsignatura(tk.Frame):
    def __init__(self, master):
        super().__init__(master=master)
        self.ventana = master
        self.frame = tk.Frame(self)
        self.frame.pack(expand=True)
        
        #Definicion Widgets
        self.nombre = tk.Label(
            master=self.frame, text="Consultar Asignaturas Recomendadas", font="Helvetica 11 bold"
        )
        self.infoDocumento = tk.Label(
            master=self.frame, text="Documento: ", font="Helvetica 9"
        )
        self.infoRecomendar = tk.Label(
            master=self.frame, text="Recomendar por:  ", font="Helvetica 9"
        )
        self.consultaRecomendar = tk.Button(
            master=self.frame, text="Consultar Recomendacion", command=self.asignaturasRecomendadas
        )
        self.documetoIngresado = tk.Entry(master=self.frame, width=23)

        listaRecomendar = ['Linea de Enfasis', 'Asignatura Basica']
        self.recomendacionSeleccionada = ttk.Combobox(
            master=self.frame, state="readonly", values=listaRecomendar
        )
        self.frameTabla = tk.Frame(self.frame, bg="gray94", width=100, height=100)

    def mostrar(self):

        self.ventana.geometry()
        self.ventana.config(cursor="hand2")
        self.ventana.resizable(True, True)

        self.nombre.grid(row=0, column=0, columnspan=2, pady=20)
        self.infoRecomendar.grid(row=1, column=0, padx=10, pady=10, sticky='e')
        self.recomendacionSeleccionada.grid(row=1, column=1, padx=10, pady=10)
        self.infoDocumento.grid(row=2, column=0, padx=10, pady=10, sticky='e')
        self.documetoIngresado.grid(row=2, column=1, padx=10, pady=10)
        self.consultaRecomendar.grid(row=3, column=0, columnspan=2, pady=10)
        pass

    def consultarVinculado(self):
        documento = self.documetoIngresado.get()
        lista = Estudiante.getListaEstudiantes()

        for estu in (lista):
            if str(estu.getDocumento()) == documento:
                return estu

        self.documetoIngresado.delete(0, "end")
        return tk.messagebox.showinfo(
            message="No hay estudiante con documento: " + str(documento),
            title="Recomendacion",
            )

    def filtro(self, estudiante, recomendacionFiltro):
        if recomendacionFiltro == 'Linea de Enfasis':
            return estudiante.getLineaEnfasis()
        else:
            return LineasEnfasis.BASICAS

    def asignaturasRecomendadas(self):
        estudiante = self.consultarVinculado()
        recomendacionFiltro = self.recomendacionSeleccionada.get()
        #Limpiar frame de la tabla
        for widget in self.frameTabla.winfo_children():
            widget.destroy()
        
        if len(self.documetoIngresado.get()) != 0:
            self.recomendar = tk.Label(master=self.frame, text="Asignaturas Recomendadas para " + estudiante.getNombre(), font="Helvetica 11 bold")
            self.recomendar.grid(row=4, column=0, columnspan=2, sticky='nsew')
            self.frameTabla.grid(row=5, column=0, columnspan=2, padx=20, pady=20)
        else:
            self.recomendar = tk.Label(master=self.frame, text="--- Documento no encontrado ---", font="Helvetica 11 bold")
            self.recomendar.grid(row=4, column=0, columnspan=2, sticky='nsew')
        
        if len(self.documetoIngresado.get()) != 0:
            filtro = self.filtro(estudiante, recomendacionFiltro)

            if filtro != LineasEnfasis.BASICAS:
                lista = estudiante.recomendarAsignaturas()
                if lista == []:
                    self.recomendar = tk.Label(master=self.frame, text="No hay asignaturas recomendadas para " + estudiante.getNombre(), font="Helvetica 11 bold")
                    self.recomendar.grid(row=4, column=0, columnspan=2, sticky='nsew')

                nombreAsignaturas = [asignatura.getNombre() for asignatura in lista]
                profesor = [asignatura.getProfesor() for asignatura in lista]
                nombresProfesores = []
                for i in profesor:
                    nombres = []
                    if i == []:
                        nombres.append('No hay profesores') 
                    for e in i:
                        nombres.append(e.getNombre())
                    nombres = ', '.join(nombres)
                    nombresProfesores.append(nombres)
                tabla = TableAsignaturas(self.frameTabla, nombreAsignaturas, nombresProfesores)

            else:
                lista = estudiante.recomendarAsignaturasBasicas()
                if lista == []:
                    self.recomendar = tk.Label(master=self.frame, text="No hay asignaturas recomendadas para " + estudiante.getNombre(), font="Helvetica 11 bold")
                    self.recomendar.grid(row=4, column=0, columnspan=2, sticky='nsew')
                nombreAsignaturas = [asignatura.getNombre() for asignatura in lista]
                profesor = [asignatura.getProfesor() for asignatura in lista]
                nombresProfesores = []
                for i in profesor:
                    nombres = []
                    if i == []:
                        nombres.append('No hay profesores') 
                    for e in i:
                        nombres.append(e.getNombre())
                    nombres = ', '.join(nombres)
                    nombresProfesores.append(nombres)
                tabla = TableAsignaturas(self.frameTabla, nombreAsignaturas, nombresProfesores)


class TableAsignaturas:
    def __init__(self, root, asignaturas, nombresProfesores):
        columnas = [("NOMBRE", "Profesores")]
        tabla = [
            (asignatura, profesor)
            for asignatura, profesor in zip(asignaturas, nombresProfesores)
        ]
        self.lst = columnas + tabla

        totalEstudiantes = len(self.lst)
        totalParametros = len(self.lst[0])

        for i in range(totalEstudiantes):
            for j in range(totalParametros):

                self.e = tk.Entry(
                    root, width=25, fg="black", font=("Helvetica", 9, "bold")
                )

                self.e.grid(row=i, column=j)
                self.e.insert(tk.END, self.lst[i][j])
                self.e.config(state=tk.DISABLED)
