from tkinter import *
from tkinter import ttk
from tkinter import messagebox
from PIL import Image, ImageTk
from helpers.auxiliar import *
from funcionalidades.funcionalidadBecas import FuncionalidadBecas
from gestorAplicacion.gestion.subsidio import Subsidio
from creacion.crearProfesores import CrearProfesores
from creacion.crearEstudiante import CrearEstudiante
from creacion.crearAsignatura import CrearAsignatura
from creacion.crearBecas import CrearBecas
from creacion.asignarAsignaturasEstudiantes import AsignarAsignaturasEstudiantes
from creacion.asignarAsignaturasProfesores import AsignarAsignaturasProfesores

from funcionalidades.funcionalidadConusltarVinculado import (
    FuncionalidadConsultarVinculado,
)
from funcionalidades.funcionalidadInformacionBecas import FuncionalidadInformacionBecas
from funcionalidades.funcionalidadTablaBeneficiadosBeca import (
    FuncionalidadTablaBeneficiadosBeca,
)
from funcionalidades.funcionalidadRecomendarAsignaturas import (
    FuncionalidadRecomendarAsignatura,
)
from funcionalidades.funcionalidadPosicionEstudiante import (
    FuncionalidadPosicionEstudiante,
)
from funcionalidades.funcionalidadConsultarBeneficiario import (
    FuncionalidadConsultarBeneficiario,
)
from funcionalidades.funcionalidadTablaBeneficiadosSub import (
    FuncionalidadTablaBeneficiadosSubsidio,
)
from funcionalidades.funcionalidadVistaGeneralEstudiantes import (
    FuncionalidadVistaGeneralEstudiantes,
)
from funcionalidades.funcionalidadInformacionSubsidios import (
    FuncionalidadInformacionSubsidio,
)
from baseDatos.serializador import serializar


ventana = None
ventanaInicio = None
ventanaUsuario = None
menuVentanaInicio = None
menuVentanaUsuario = None
labelFoto1 = None
labelFoto2 = None
labelFoto3 = None
labelFoto4 = None
labelFotoSistema = None
cuerpoBio = None
funcionalidadBecas = None


def ocultarTodo():
    ventanaUsuario.pack_forget()
    ventanaInicio.pack_forget()
    ventanaBecas.pack_forget()
    ventanaCrearProfesor.pack_forget()
    ventanaTablaBeneficiadosBeca.pack_forget()
    ventanaInformacionBecas.pack_forget()
    ventanaConsultarVinculado.pack_forget()
    ventanaRecomendarAsignatura.pack_forget()
    ventanaCrearEstudiante.pack_forget()
    ventanaPosicionEstudiante.pack_forget()
    ventanaCrearAsignatura.pack_forget()
    ventanaConsultarBeneficiario.pack_forget()
    ventanaTablaBeneficiadosSubsidio.pack_forget()
    ventanaAsignarAsignaturasEstudiantes.pack_forget()
    ventanaAsignarAsignaturasProfesores.pack_forget()
    ventanaCrearBecas.pack_forget()
    ventanaVistaGeneralEstudiantes.pack_forget()
    ventanaInformacionSubsidios.pack_forget()


def iniciarAplicacion():
    global ventana
    ventana = Tk()
    ventana.title("inicio")
    ventana.geometry(
        "900x600"
    )  # Ajustar al tamaño que deseemos para todas las ventanas !!!
    ventana.resizable(width=False, height=False)
    ventana.option_add("*tearOff", False)
    ventana.iconbitmap("Imagenes\icon.ico")  # icono de ventana
    ventana.grid_columnconfigure(0, weight=1)
    ventana.grid_columnconfigure(1, weight=1)
    asignarMenuInicio(ventana)
    crearComponentesVentanaInicio(ventana)
    crearVentanaUsuario()
    # bypass ventanaInicio
    inicializarVentanas()
    ventana.mainloop()


# Asignacion a ventanaInicio
def asignarMenuInicio(ventana):
    global menuVentanaInicio
    menuVentanaInicio = Menu(ventana, font="Helvetica 11 bold", fg="red")
    menuInicio = Menu(
        menuVentanaInicio,
        font="Helvetica 11",
    )
    menuVentanaInicio.add_cascade(menu=menuInicio, label="Inicio")
    menuInicio.add_command(label="Descripción", command=descripcion)
    menuInicio.add_command(label="Salir", command=salirInicio)
    ventana["menu"] = menuVentanaInicio


# COMPONENTES DE LA VENTANA DEL USUARIO:
def crearVentanaUsuario():
    # Se crea la ventana de usuario, así como la sección de qué se puede hacer con la aplicación.
    global ventanaUsuario
    ventanaUsuario = Frame()
    tituloInfo = Label(
        master=ventanaUsuario,
        text="¿Cómo usar esta aplicación y qué puede hacer con ella?",
        font="Helvetica 11 bold",
    )

    # Se define el mensaje que aparecerá cuando se accede a la ventana de Usuario desde la ventana de Inicio.
    tutorial = """
Para el acceder a las funcionalidades de la aplicación hay que dirigirse a la parte superior izquierda de la ventana\n
donde veremos los diferentes menús despegables de la aplicación, el que no interesa es "Procesos y consultas"\n
En este menú habrá 3 submenús los cuales son:\n
FUNC. ESTUDIANTES:
En este submenú encontraremos diferentes funcionalidades interesantes para estudiantes,
como la función recomendar asignaturas, el cual se le ingresara el documento de un estudiante,
el cual es un número entero, también está el consultar posición 
que al igual que antes ingresaremos un documento
dependiendo del filtro que elijamos nos dará nuestra posición respecto a otros estudiantes\n
BENEFICIOS:
En este submenú se encuentra la parte de becas y subsidios, las cuales comparten
las mismas funcionalidades como consultar un vinculado u obtener información acerca
de las becas y subsidios, en la cual podremos ver en la tabla de beneficiarios una mejor
vista acerca de los beneficiados por estas becas\n
CREACIÓN:
En este submenú encontraremos todo lo relacionado frente a la creación de objetos para
la aplicación, podemos crear profesores y estudiantes, después de creados podemos asignarles
las asignaturas que dicta o que cursa respectivamente, al igual que la función de crear estas asignaturas
    """

    info = Label(master=ventanaUsuario, text=tutorial, font="Helvetica 10")
    tituloInfo.pack(fill=BOTH, padx=5, pady=5)
    info.pack(fill=BOTH, padx=5, pady=5)
    asignacionMenuUsuario(ventanaUsuario)


# COMPONENTES DEL MENÚ DE LA VENTANA DEL USUARIO:
def asignacionMenuUsuario(ventana):
    global menuVentanaUsuario
    # menu general
    menuVentanaUsuario = Menu(ventana, font="Helvetica 11 bold")

    # archivo
    menuArchivo = Menu(menuVentanaUsuario, font="Helvetica 11")
    menuVentanaUsuario.add_cascade(menu=menuArchivo, label="Archivo")
    menuArchivo.add_command(label="Aplicación", command=aplicacion)
    menuArchivo.add_command(label="Salir", command=salirUsuario)

    # procesos
    menuProcesos = Menu(menuVentanaUsuario, font="Helvetica 11")
    menuVentanaUsuario.add_cascade(menu=menuProcesos, label="Procesos y consultas")

    # Estudiantes
    menuEstudiantes = Menu(menuVentanaUsuario, font="Helvetica 11")
    menuProcesos.add_cascade(menu=menuEstudiantes, label="Estudiantes")
    menuEstudiantes.add_command(
        label="Recomendacion Asignaturas", command=funcionalidadRecomendarAsignaturas
    )
    menuEstudiantes.add_command(
        label="Consultar posicion", command=funcionalidadPosicionEstudiante
    )
    menuEstudiantes.add_command(label="Calidad de estudiante")
    menuEstudiantes.add_command(
        label="Estudiantes vinculados", command=funcionalidadVistaGeneralEstudiantes
    )

    # Menu Beneficios
    menuBeneficios = Menu(menuVentanaUsuario, font="Helvetica 11")
    menuProcesos.add_cascade(menu=menuBeneficios, label="Beneficios")

    ## Menu Beneficios-Becas
    menuBecas = Menu(menuVentanaUsuario, font="Helvetica 11")
    menuBeneficios.add_cascade(menu=menuBecas, label="Func. Becas")

    menuBecas.add_command(
        label="Consultar Vinculado", command=funcionalidadConsultarVinculado
    )
    menuBecas.add_command(
        label="Informacion Becas", command=funcionalidadInformacionBecas
    )
    menuBecas.add_command(
        label="Tabla de beneficiarios", command=funcionalidadTablaBeneficiadosBeca
    )

    ## Menu Beneficios-Subsidios
    menuSubsidio = Menu(menuVentanaUsuario, font="Helvetica 11")
    menuBeneficios.add_cascade(menu=menuSubsidio, label="Func. Subsidios")

    menuSubsidio.add_command(
        label="Consultar Beneficiario", command=funcionalidadConsultarBeneficiario
    )
    menuSubsidio.add_command(
        label="Informacion Subsidio", command=funcionalidadInformacionSubsidios
    )
    menuSubsidio.add_command(
        label="Tabla de beneficiarios", command=funcionalidadTablaBeneficiadosSubsidio
    )
    # creación
    menuCreacion = Menu(menuVentanaUsuario, font="Helvetica 11")
    menuProcesos.add_cascade(menu=menuCreacion, label="Creacion")
    menuCreacion.add_command(label="Crear becas", command=funcionalidadCrearBecas)
    menuCreacion.add_command(label="Crear profesor", command=funcionalidadCrearProfesor)
    menuCreacion.add_command(
        label="Crear estudiante", command=funcionalidadCrearEstudiante
    )
    menuCreacion.add_command(
        label="Crear asignatura", command=funcionalidadCrearAsignatura
    )
    # asignar
    menuAsignar = Menu(menuVentanaUsuario, font="Helvetica 11")
    menuCreacion.add_cascade(menu=menuAsignar, label="Asignar asignaturas")
    menuAsignar.add_command(
        label="Asignar a estudiante", command=funcionalidadAsignarAsignaturasEstudiantes
    )
    menuAsignar.add_command(
        label="Asignar a profesor", command=funcionalidadAsignarAsignaturasProfesores
    )

    # ayuda
    menuAyuda = Menu(menuVentanaUsuario, font="Helvetica 11")
    menuVentanaUsuario.add_cascade(menu=menuAyuda, label="Ayuda")
    menuAyuda.add_command(label="Acerca de", command=ayuda)


# Por medio de la siguiente función se cambia la imagen relativa al sistema (Imágenes de animales).
def cambiarImagen(e):
    global posImg
    posImg += 1
    if posImg == 6:
        posImg = 1
    foto = (Image.open("Imagenes/Aplicativo/" + str(posImg) + ".jpg")).resize(
        (400, 400)
    )
    foto = ImageTk.PhotoImage(foto)
    labelFotoSistema.configure(image=foto)
    labelFotoSistema.image = foto


# Por medio de la siguiente función se cambia la hoja de vida y las imágenes asociadas a cada autor de la aplicación.
def cambiarBio(e):

    global labelFoto1, labelFoto2, labelFoto3, labelFoto4, cuerpoBio

    # Sistema para loopear por las vidas y fotos
    global posBio
    posBio += 1
    if posBio == 5:
        posBio = 0

    listaPersonas = [
        "Juanes",
        "Andres",
        "Felipe",
        "Zedin",
        "Santiago",
    ]  # lista personas

    # Asignacion fotos para cuadricula biografia
    foto1 = (Image.open("Imagenes/" + listaPersonas[posBio] + "/1.jpg")).resize(
        (200, 200)
    )
    foto1 = ImageTk.PhotoImage(foto1)
    labelFoto1.configure(image=foto1)
    labelFoto1.image = foto1

    foto2 = (Image.open("Imagenes/" + listaPersonas[posBio] + "/2.jpg")).resize(
        (200, 200)
    )
    foto2 = ImageTk.PhotoImage(foto2)
    labelFoto2.configure(image=foto2)
    labelFoto2.image = foto2

    foto3 = (Image.open("Imagenes/" + listaPersonas[posBio] + "/3.jpg")).resize(
        (200, 200)
    )
    foto3 = ImageTk.PhotoImage(foto3)
    labelFoto3.configure(image=foto3)
    labelFoto3.image = foto3
    foto4 = (Image.open("Imagenes/" + listaPersonas[posBio] + "/4.jpg")).resize(
        (200, 200)
    )
    foto4 = ImageTk.PhotoImage(foto4)
    labelFoto4.configure(image=foto4)
    labelFoto4.image = foto4

    # Cambio texto de biografia
    cuerpoBio.config(text=listaBio[posBio])


def descripcion():
    descripcion = """Sistema consulta de Becas:
Esta aplicación tiene como función principal ser una herramienta para el manejo de becas, Se puede gestionar la creación de becas con diferentes parámetros, de igual modo consultar quien es elegible a una beca.

La aplicación cuenta con funcionalidades extras para los estudiantes"""

    messagebox.showinfo(title="Descripción de la aplicación", message=descripcion)


def salirInicio():
    salir = messagebox.askyesno(
        title="Salir",
        message="¿Confirma que desea salir de la aplicación?",
        detail="Clic en Sí para salir",
    )
    if salir:

        serializar()
        ventana.destroy()


# La siguiente función es llamada cuando se presiona el botón Ingresar de la ventana de Inicio. Por medio de esta
# el usuario puede acceder a la ventana de Usuario, desde donde puede hacer uso de las distintas funcionalidades.
def ingresar():
    # ocultarTodo()
    # borrarTodo()
    ventanaUsuario.pack()
    ventanaInicio.pack_forget()
    ventana["menu"] = menuVentanaUsuario


# La siguiente función es llamada cuando se accede al menú de "Aplicación" de la ventana de Usuario. Por medio de esta
# se muestra al usuario NUEVAMENTE la descripción de la aplicación.
def aplicacion():
    descripcion()


# La siguiente función es llamada cuando se accede al menú de "Salir" de la ventana de Usuario. Por medio de esta
# se regresa a la ventana de Inicio.
def salirUsuario():
    salir = messagebox.askyesno(
        title="Salir",
        message="¿Confirma que desea regresar a la ventana de inicio?",
        detail="Clic en Sí para regresar",
    )
    if salir:
        ocultarTodo()
        ventana.geometry("900x600")
        ventanaUsuario.pack_forget()
        ventanaInicio.pack()
        ventana["menu"] = menuVentanaInicio


# La siguiente función es llamada cuando se accede al menú de "Acerca De" de la ventana de Usuario. Por medio de esta
# se muestra al usuario los autores de la aplicación.
def ayuda():
    autores = """Autores:

- Felipe Cabeza Parada
- Zedin Daniel Garzon Otero
- Juan Esteban Mejia Espejo
- Santiago Sosa Garcia
- Andre Felipe Villa Arias
    """
    messagebox.showinfo(title="Acerca de la aplicación", message=autores)


def funcionalidadRecomendarAsignaturas():
    ocultarTodo()
    ventanaRecomendarAsignatura.pack()
    ventanaRecomendarAsignatura.mostrar()


def funcionalidadCrearProfesor():
    ocultarTodo()
    ventanaCrearProfesor.pack()
    ventanaCrearProfesor.mostrar()


def funcionalidadCrearEstudiante():
    ocultarTodo()
    ventanaCrearEstudiante.pack()
    ventanaCrearEstudiante.mostrar()


def funcionalidadCrearAsignatura():
    ocultarTodo()
    ventanaCrearAsignatura.pack()
    ventanaCrearAsignatura.mostrar()


def funcionalidadCrearBecas():
    ocultarTodo()
    ventanaCrearBecas.pack()
    ventanaCrearBecas.mostrar()


def funcionalidadAsignarAsignaturasEstudiantes():
    ocultarTodo()
    ventanaAsignarAsignaturasEstudiantes.pack()
    ventanaAsignarAsignaturasEstudiantes.mostrar()


def funcionalidadAsignarAsignaturasProfesores():
    ocultarTodo()
    ventanaAsignarAsignaturasProfesores.pack()
    ventanaAsignarAsignaturasProfesores.mostrar()


def funcionalidadBecas():
    ocultarTodo()
    ventanaBecas.grid()
    ventanaBecas.config(width=700, height=700)
    ventanaBecas.mostrar()


def funcionalidadConsultarVinculado():
    ocultarTodo()
    ventanaConsultarVinculado.pack()
    ventanaConsultarVinculado.mostrar()


def funcionalidadTablaBeneficiadosBeca():
    ocultarTodo()
    ventanaTablaBeneficiadosBeca.pack()
    ventanaTablaBeneficiadosBeca.mostrar()


def funcionalidadInformacionBecas():
    ocultarTodo()
    ventanaInformacionBecas.pack()
    ventanaInformacionBecas.mostrar()


def funcionalidadPosicionEstudiante():
    ocultarTodo()
    ventanaPosicionEstudiante.pack()
    ventanaPosicionEstudiante.mostrar()


def funcionalidadVistaGeneralEstudiantes():
    ocultarTodo()
    ventanaVistaGeneralEstudiantes.pack()
    ventanaVistaGeneralEstudiantes.mostrar()


def funcionalidadConsultarBeneficiario():
    ocultarTodo()
    for i in Subsidio.getListaSubsidio():
        i.setBeneficiarios()

    ventanaConsultarBeneficiario.pack()
    ventanaConsultarBeneficiario.mostrar()


def funcionalidadTablaBeneficiadosSubsidio():
    ocultarTodo()
    for i in Subsidio.getListaSubsidio():
        i.setBeneficiarios()

    ventanaTablaBeneficiadosSubsidio.pack()
    ventanaTablaBeneficiadosSubsidio.mostrar()


def funcionalidadInformacionSubsidios():
    ocultarTodo()
    for i in Subsidio.getListaSubsidio():
        i.setBeneficiarios()

    ventanaInformacionSubsidios.pack()
    ventanaInformacionSubsidios.mostrar()


def inicializarVentanas():
    global ventanaBecas, ventanaCrearProfesor, ventanaConsultarVinculado, ventanaTablaBeneficiadosBeca, ventanaInformacionBecas, ventanaRecomendarAsignatura
    global ventanaCrearEstudiante, ventanaPosicionEstudiante, ventanaVistaGeneralEstudiantes, ventanaCrearAsignatura, ventanaConsultarBeneficiario, ventanaTablaBeneficiadosSubsidio
    global ventanaAsignarAsignaturasEstudiantes, ventanaAsignarAsignaturasProfesores, ventanaCrearBecas, ventanaInformacionSubsidios

    ventanaRecomendarAsignatura = FuncionalidadRecomendarAsignatura(ventana)
    ventanaCrearProfesor = CrearProfesores(ventana)
    ventanaBecas = FuncionalidadBecas(ventana)
    ventanaConsultarVinculado = FuncionalidadConsultarVinculado(ventana)
    ventanaTablaBeneficiadosBeca = FuncionalidadTablaBeneficiadosBeca(ventana)
    ventanaInformacionBecas = FuncionalidadInformacionBecas(ventana)
    ventanaCrearEstudiante = CrearEstudiante(ventana)
    ventanaPosicionEstudiante = FuncionalidadPosicionEstudiante(ventana)
    ventanaVistaGeneralEstudiantes = FuncionalidadVistaGeneralEstudiantes(ventana)
    ventanaCrearAsignatura = CrearAsignatura(ventana)
    ventanaConsultarBeneficiario = FuncionalidadConsultarBeneficiario(ventana)
    ventanaTablaBeneficiadosSubsidio = FuncionalidadTablaBeneficiadosSubsidio(ventana)
    ventanaAsignarAsignaturasEstudiantes = AsignarAsignaturasEstudiantes(ventana)
    ventanaAsignarAsignaturasProfesores = AsignarAsignaturasProfesores(ventana)
    ventanaCrearBecas = CrearBecas(ventana)
    ventanaInformacionSubsidios = FuncionalidadInformacionSubsidio(ventana)
    ventanaCrearBecas = CrearBecas(ventana)
    ventanaInformacionSubsidios = FuncionalidadInformacionSubsidio(ventana)


# Borrar
def sinAsignar():
    pass


# COMPONENTES DE LA VENTANA DE INICIO:
def crearComponentesVentanaInicio(ventana):
    global ventanaInicio, labelFoto1, labelFoto2, labelFoto3, labelFoto4, labelFotoSistema, cuerpoBio

    # Se crean cada uno de los frames especificados para la ventana de Inicio.
    ventanaInicio = Frame(master=ventana)
    P1 = Frame(master=ventanaInicio, highlightbackground="black", highlightthickness=1)
    P2 = Frame(master=ventanaInicio, highlightbackground="black", highlightthickness=1)
    P3 = Frame(master=P1, highlightbackground="black", highlightthickness=1)
    P4 = Frame(master=P1, highlightbackground="black", highlightthickness=1)
    P5 = Frame(master=P2, highlightbackground="black", highlightthickness=1)
    P6 = Frame(master=P2, highlightbackground="black", highlightthickness=1)

    # Se crea el Label de bienvenida a la aplicación.
    saludo = Label(
        master=P3, text="""Sistema consulta de Becas""", font="Helvetica 11 bold"
    )

    # Se crea el botón "Ingresar", que al ser presionado permitirá al usuario acceder a las funcionalidades.
    botonIngreso = Button(
        master=P4,
        text="Ingresar",
        font="Helvetica 11 bold",
        bg="grey",
        fg="white",
        borderwidth=5,
        relief="groove",
        command=ingresar,
    )

    # Se crea el título para las hojas de vida de los autores.
    tituloBio = Label(
        master=P5, text="Breve biografía de los autores", font="Helvetica 11 bold"
    )

    # Se crea el Label para el texto de las hojas de vida de los autores.
    cuerpoBio = Label(master=P5, text=BIOJUANES, font="Helvetica 10", anchor=W)

    # Se localizan las imágenes iniciales para las relacionadas con la aplicación y para las de hojas de vida de los autores.
    fotoSistema = (Image.open("Imagenes/Aplicativo/1.jpg")).resize(
        (400, 400),
    )
    fotoSistema = ImageTk.PhotoImage(fotoSistema)
    foto1 = (Image.open("Imagenes/Juanes/1.jpg")).resize(
        (200, 200),
    )
    foto1 = ImageTk.PhotoImage(foto1)
    foto2 = (Image.open("Imagenes/Juanes/2.jpg")).resize(
        (200, 200),
    )
    foto2 = ImageTk.PhotoImage(foto2)
    foto3 = (Image.open("Imagenes/Juanes/3.jpg")).resize(
        (200, 200),
    )
    foto3 = ImageTk.PhotoImage(foto3)
    foto4 = (Image.open("Imagenes/Juanes/4.jpg")).resize(
        (200, 200),
    )
    foto4 = ImageTk.PhotoImage(foto4)

    # Se crean los Label para las imágenes relacionadas con la aplicación y para las de hojas de vida de los autores.
    labelFotoSistema = Label(
        master=P4, image=fotoSistema, borderwidth=5, relief="ridge"
    )
    labelFoto1 = Label(master=P6, image=foto1, borderwidth=5, relief="ridge")
    labelFoto1.grid(column=0, row=0, padx=3, pady=3)
    labelFoto2 = Label(master=P6, image=foto2, borderwidth=5, relief="ridge")
    labelFoto2.grid(column=1, row=0, padx=3, pady=3)
    labelFoto3 = Label(master=P6, image=foto3, borderwidth=5, relief="ridge")
    labelFoto3.grid(column=0, row=1, padx=3, pady=3)
    labelFoto4 = Label(master=P6, image=foto4, borderwidth=5, relief="ridge")
    labelFoto4.grid(column=1, row=1, padx=3, pady=3)

    # previene el borrado de la imagen
    labelFotoSistema.image = fotoSistema
    labelFoto1.image = foto1
    labelFoto2.image = foto2
    labelFoto3.image = foto3
    labelFoto4.image = foto4

    # Se visualizan todos los elementos anteriormente creados.

    P1.pack(side=LEFT, fill=BOTH, padx=5, pady=5)
    P2.pack(side=RIGHT, fill=BOTH, padx=5, pady=5)
    P3.pack(side=TOP, fill=BOTH, padx=5, pady=5)
    P4.pack(side=BOTTOM, fill=BOTH, padx=5, pady=5)
    P5.pack(side=TOP, fill=BOTH, padx=5, pady=5)
    P6.pack(side=BOTTOM, fill=BOTH, padx=5, pady=5)
    saludo.pack(padx=5, pady=5)
    botonIngreso.pack(side=BOTTOM, padx=5, pady=5)
    labelFotoSistema.pack(side=TOP, padx=10, pady=10)
    tituloBio.pack(padx=5, pady=5)
    cuerpoBio.pack(padx=5, pady=5)

    ventanaInicio.pack()
    # Se asignan los comandos para cambiar de hoja de vida y de imagen relacionada a la aplicación.

    cuerpoBio.bind("<Button-1>", cambiarBio)
    labelFotoSistema.bind("<Enter>", cambiarImagen)

    # FUNCIONALIDAD DE MANTENIMIENTO:

    # ventanaMantenimiento = Mantenimiento()
    # ventanaMantenimiento.pack_forget()
    ##UNO DE ESTOS PARA CADA FUNCIONALIDAD
    # ingresar()
