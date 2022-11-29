from tkinter import Frame, Text, Label, Entry
import tkinter as tk
from tkinter import ttk
from gestorAplicacion.personas.profesor import Profesor
from gestorAplicacion.gestion.asignatura import Asignatura

class AsignarAsignaturasProfesores(Frame):

    def __init__(self, master):
        super().__init__(master=master)
        
        self.ventana = master
        self.frame = Frame(self)
        self.frame.pack(expand=True)


        #Definicion Widgets
        self.nombre = Label(master=self.frame, text="Asignar asignaturas a profesor", font="Helvetica 11 bold")
        self.texto = Label(master=self.frame)

        self.infoDocumento = Label(master=self.frame, text="Documento: ", font="Helvetica 9")
        self.documentoSeleccionado = Entry(master=self.frame, width=23)

        self.infoInscrita = Label(master=self.frame, text="Asignatura inscrita: ", font="Helvetica 9")
        self.inscritaSeleccionado = Entry(master=self.frame, width=23)

        self.infoNota = Label(master=self.frame, text="Nota de la asignatura: ", font="Helvetica 9")
        self.notaSeleccionado = Entry(master=self.frame, width=23)

        self.consultaRecomendar = tk.Button(master=self.frame, text="Asignar", command=self.asignar)


    def asignar(self):
        if self.validar() == True:
            documento = int(self.documentoSeleccionado.get())
            inscrita = self.inscritaSeleccionado.get()
            nota = float(self.notaSeleccionado.get())
            profesor = None
            asignaturaInscrita = None

            for estu in Profesor.listaProfesores:
                if  documento == estu.getDocumento():
                    profesor = estu
            
            for asig in Asignatura.getListaAsignaturas():
                if inscrita == asig.getNombre():
                    asignaturaInscrita = asig

            profesor.agregarAsignaturaDisctadas(asignaturaInscrita, nota)
            
            self.texto.grid_forget()
            self.texto = Label(master=self.frame, text='--- El profesor con documento ' + str(documento) + ', ha asignado asignaturas exitosamente ---', font="Helvetica 11")
            self.texto.grid(row=5, column=0, columnspan=2, pady=10)


    def validar(self):
        documento = self.documentoSeleccionado.get()
        inscrita = self.inscritaSeleccionado.get()
        nota = self.notaSeleccionado.get()
        estudiante = None
        flag1 = False
        flag3 = False

        try:
            float(nota)
            int(documento)
        except:
            self.notaSeleccionado.delete(0,"end")
            self.documentoSeleccionado.delete(0,"end")
            return tk.messagebox.showinfo(
            message="La nota y el documento debe ser un numero, la nota puede ser un decimal",
            title="Recomendacion",
            )

        if documento == '' or inscrita == '' or nota == '':
            return tk.messagebox.showinfo(
            message="Recuerda llenar todos los campos",
            title="Recomendacion",
            )

        for estu in Profesor.listaProfesores:
            if  int(documento) == estu.getDocumento():
                estudiante = estu
                flag3 = True

        if flag3 != True:
            return tk.messagebox.showinfo(
            message="No hay profesor con documento " + str(documento),
            title="Recomendacion",
            )

        for i in Asignatura.listaAsiganturas:
            if inscrita == i.getNombre():
                flag1 = True
        
        if flag1 != True:
            return tk.messagebox.showinfo(
            message="No hay asignatura con nombre " + inscrita,
            title="Recomendacion",
            )

        if inscrita in [asignaturas.getNombre() for asignaturas in estudiante.getAsignaturasDictadas()]:
            return tk.messagebox.showinfo(
            message="El profesor con documento " + str(documento) + ' ya dicta ' + inscrita,
            title="Recomendacion",
            )

        return True



    def mostrar(self):
        
        self.nombre.grid(row=0, column=0, columnspan=2, pady=20)

        self.infoDocumento.grid(row=1, column=0, padx=10, pady=10, sticky='e')
        self.documentoSeleccionado.grid(row=1, column=1, padx=10, pady=10)

        self.infoInscrita.grid(row=2, column=0, padx=10, pady=10, sticky='e')
        self.inscritaSeleccionado.grid(row=2, column=1, padx=10, pady=10)

        self.infoNota.grid(row=3, column=0, padx=10, pady=10, sticky='e')
        self.notaSeleccionado.grid(row=3, column=1, padx=10, pady=10)

        self.consultaRecomendar.grid(row=4, column=0, columnspan=2, pady=10)