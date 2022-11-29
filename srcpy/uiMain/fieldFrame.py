from tkinter import *
from tkinter import ttk
from excepciones.empty_exception import EmptyException
from excepciones.numeric_exception import NumericException
from excepciones.length_exception import LengthException

# Creación de la clase field frame
# tipos:
# s = string
# n = number
# list = combobox
class FieldFrame(Frame):
    def __init__(
        self,
        master,
        tituloCriterios,
        criterios,
        tituloValores,
        valores,
        habilitado,
        tipos,
        tabla=False,
    ):
        super().__init__(master)
        self._tituloCriterios = tituloCriterios
        self._criterios = criterios
        self._tituloValores = tituloValores
        self._valores = valores
        self._habilitado = habilitado
        self._entradas = list()
        self._tipos = tipos
        self._tabla = tabla

    # Funcion que permite crear el grid del FieldFrame y actualizarlo con nuevos datos.
    def actualizacion(self):
        Label(self, text=self._tituloCriterios).grid(padx=80, column=0, row=0)
        Label(self, text=self._tituloValores).grid(padx=80, column=1, row=0)
        for i in range(len(self._criterios)):
            Label(self, text=self._criterios[i]).grid(
                padx=80, pady=2, column=0, row=(i + 1)
            )

            valorTexto = ""
            if self._valores:
                valorTexto = self._valores[i]

            if self._habilitado[i] or self._habilitado[i] == None:
                texto = StringVar(value=valorTexto)

                if type(self._tipos[i]) is list:
                    comboboxValor = ttk.Combobox(
                        master=self,
                        values=self._tipos[i],
                        font="Helvetica 10",
                        state="readonly",
                        width=32,
                    )
                    entrada = comboboxValor
                else:
                    entrada = Entry(
                        self, width=40, textvariable=texto, justify="center"
                    )
            else:
                texto = StringVar(value=valorTexto)
                entrada = Entry(
                    self, width=40, textvariable=texto, state=DISABLED, justify="center"
                )

            entrada.grid(padx=80, pady=2, column=1, row=i + 1)
            self._entradas.append(entrada)

    # Funcion auxiliar del boton aceptar
    def validacion(self):
        criteriosFaltantes = []
        datosErroneos = []
        faltan = False
        erroneos = False
        if not self._valores:
            self._valores = ["" for i in self._entradas]
        for i in range(len(self._entradas)):  # _entradas es la lista con las entradas.
            self._valores[i] = self._entradas[i].get()
            if self._valores[i] == "":
                faltan = True
                criteriosFaltantes.append(self._criterios[i])

        if faltan:
            faltantes = ", ".join(criteriosFaltantes)
            raise EmptyException(
                "Los siguientes campos faltan por rellenar: " + faltantes
            )

        for i in range(len(self._entradas)):
            if self._tipos[i] == "s":
                if self._valores[i].isdigit():
                    erroneos = True
                    datosErroneos.append(self._criterios[i])
                elif len(self._valores[i]) < 3:
                    raise LengthException(self._criterios[i] + " muy corto.")
            elif self._tipos[i] == "n":
                if not self._valores[i].isdigit():
                    erroneos = True
                    datosErroneos.append(self._criterios[i])
        if erroneos:
            errores = ", ".join(datosErroneos)
            raise NumericException("Tipo de dato erroneo en: " + errores)

    # Funcion auxiliar del boton borrar para limpiar entradas
    def borrarEntradas(self):
        for entrada in self._entradas:
            try:
                entrada.set("")
            except:
                entrada.delete(0, END)

    def obtenerEntrada(self, entrada):
        return self._entradas[entrada]

    def getValue(self, criterio):
        criterios_dict = dict(zip(self._criterios, self._valores))
        return criterios_dict[criterio]

    def getCriterios(self):
        return self._criterios

    def getValores(self):
        return self._valores

    def setValores(self, valores):
        self._valores = valores

    def setEntries(self, entries):
        self._entradas = entries

    def crearBotones(self, comando1, comando2=None):
        # Boton aceptar
        Button(self, text="Aceptar", command=comando1).grid(
            pady=50, column=0, row=len(self._criterios) + 1
        )
        if comando2:
            # Boton borrar
            Button(self, text="Borrar", command=comando2).grid(
                pady=50, column=1, row=len(self._criterios) + 1
            )
        else:
            Button(self, text="Borrar", command=self.borrarEntradas).grid(
                pady=50, column=1, row=len(self._criterios) + 1
            )
