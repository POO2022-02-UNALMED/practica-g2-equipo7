from gestorAplicacion.gestion.beca import Beca
from gestorAplicacion.gestion.asignatura import Asignatura
from gestorAplicacion.gestion.calidadEstudiante import CalidadEstudiante
from gestorAplicacion.gestion.facultad import Facultad
from gestorAplicacion.gestion.lineasEnfasis import LineasEnfasis
from gestorAplicacion.gestion.subsidio import Subsidio
from gestorAplicacion.personas.persona import Persona
from gestorAplicacion.personas.estudiante import Estudiante
from gestorAplicacion.personas.profesor import Profesor
import tkinter as tk
from tkinter import Frame

import sys

from baseDatos.serializador import deserializar, serializar

espaciado = """



"""


def terminarPrograma():
    subOpcion = 0
    while subOpcion > 1 or subOpcion < 0:
        print("-Ingrese 1 para Regresar al Inicio")
        print("-Ingrese 0 para Salir del programa.")
        subOpcion = int(input("Digite una opción: "))
    match (subOpcion):
        case 1:
            print(espaciado)
            return subOpcion
        case 0:
            ##Pendiente serializar
            sys.exit()


def funcionalidad1():
    seleccion = 0
    while seleccion < 1 and seleccion > 3:
        print(espaciado)
        print("\n--- Menu Recomendar Asignaturas ---\n")
        if seleccion != 0:
            print("--- Ingrese una opción valida ---\n")
        menuRecomendarAsignaturas = """
        ¿Con cual criterio desea filtrar la recomendacion de asignatura?
        1. Asignatura por linea de enfasis
        2. Asignaturas basicas
        3. Regresar al Inicio
        """
        print(menuRecomendarAsignaturas)
        seleccion = int(input("Digite una opcion: "))
    match seleccion:
        case 1:
            subOpcion = 0
            while subOpcion < 1 or subOpcion > 3:
                print(espaciado)
                print("\n--- Menu Recomendar Asignaturas ---\n")
                print("Criterio elegido: Linea de enfasis\n")
                if seleccion != 0:
                    print("--- Ingrese una opción valida ---\n")
                menuRecomendarAsignaturas = """
                ¿Que Recomendacion desea consultar?
                1. Recomendar asignatura a un estudiante
                2. Recomendacion de asignatura de todos los estudiantes
                3. Regresar al Inicio
                """
                print(menuRecomendarAsignaturas)
                subOpcion = int(input("Digite una opcion: "))

            match subOpcion:
                case 1:
                    ##Pendiente
                    pass


def funcionalidad2():
    pass


def funcionalidad3():
    seleccion = 0

    while seleccion > len(Beca.getListabecas()) + 1 or seleccion < 1:
        print(espaciado)
        print("\n--- Menu Becas ---\n")
        if seleccion != 0:
            print("--- Ingrese una opción valida ---\n")
        menuBecas = ["¿Sobre que Beca desea obtener información?"]
        contador = 1
        for beca in Beca.getListabecas():
            opcion = str(contador) + ". " + beca.getNombre()
            menuBecas.append(opcion)
            contador += 1
        menuBecas.append(str(contador) + ". Regresar al inicio")
        print("\n".join(menuBecas))
        seleccion = int(input("Digite una opcion: "))
        if seleccion == contador:
            return

    BecaElegida = Beca.getListabecas()[seleccion - 1]
    subOpcion = 0

    while subOpcion > 4 or subOpcion < 1:
        print(espaciado)
        print("\n--- Menu Becas ---\n")
        print("Beca Elegida: " + BecaElegida.getNombre() + "\n")
        if subOpcion != 0:
            print("\n--- Ingrese una opción valida ---\n")
        print(
            """
¿Qué información desea acerca de la beca?
1. Consultar elegibilidad de un vinculado
2. Tabla de vinculados elegibles
3. Información asociada
4. Regresar al inicio
        """
        )
        subOpcion = int(input("Digite una opción: "))
        if subOpcion == 4:
            print(espaciado)
            return

    match subOpcion:
        case 1:
            documento = 0
            vinculadoConsultado = None
            condicional = False
            contadorLocal = 0
            while not condicional:
                print(espaciado)
                print("\n--- Menu Becas ---\n")
                print("Beca Elegida: " + BecaElegida.getNombre() + "\n")
                print("Opcion Elegida: Elegibilidad de vinculado\n")

                if contadorLocal != 0:
                    print("--- Documento Invalido ---\n")

                print("- Ingrese el documento del vinculado.")
                print("- Ingrese 0 para salir")
                documento = int(input("Digite una opcion: "))

                if documento == 0:
                    print(espaciado)
                    return

                contadorLocal += 1
                vinculadosGeneral = (
                        Estudiante.getListaEstudiantes() + Profesor.getListaProfesores()
                )
                for vinculado in vinculadosGeneral:
                    if documento == vinculado.getDocumento():
                        condicional = True
                        vinculadoConsultado = vinculado

            condicional2 = vinculadoConsultado in BecaElegida.getBeneficiarios()
            print("\n--- Menu Becas ---\n")
            print("Beca Elegida: " + BecaElegida.getNombre() + "\n")
            print("Opcion Elegida: Elegibilidad de vinculado\n")
            if (
                    isinstance(vinculadoConsultado, Estudiante)
                    and BecaElegida.isProfesoral()
            ):
                print(
                    "Esta es una beca profesoral\nel documento ingresado es de un Estudiante"
                )
            elif (isinstance(vinculadoConsultado, Profesor)) and (
                    not BecaElegida.isProfesoral()
            ):
                print(
                    "Esta es una beca estudiantil\nel documento ingresado es de un Profesor"
                )
            if condicional2:
                print("El vinculado SI es elegible para la Beca\n")
            else:
                print("El vinculado NO es elegible para la Beca\n")
            terminarPrograma()
            return

        case 2:
            subOpcion = 0
            while subOpcion > 3 or subOpcion < 1:
                if subOpcion != 0:
                    print("Opcion Invalida")
                print(espaciado)
                print("--- Menu Becas ---")
                print("Beca Elegida: " + BecaElegida.getNombre())
                print(
                    """
                Opcion Elegida: Ver vinculado elegibles\n
                1. Ver vinculados elegibles por facultad
                2. Ver vinculados elegibles por linea de enfasis
                3. Ver todos los vinculados elegibles
                4. Regresar al inicio
                """
                )
                subOpcion = int(input("Digite una opcion: "))
                if subOpcion == 4:
                    return

            match subOpcion:
                case 1:
                    ##Imprimir Tabla Por Facultad
                    terminarPrograma()
                    return
                case 2:
                    ##Imprimir tabla por linea de Enfasis
                    terminarPrograma()
                    return
                case 3:
                    ##Imprimir tabla general
                    terminarPrograma()
                    return
        case 3:
            ##Label para mostrar info de la beca elegida
            terminarPrograma()
            return


def funcionalidad4():
    seleccion = 0

    while seleccion > len(Subsidio.getListaSubsidio()) + 1 or seleccion < 1:
        print(espaciado)
        print("\n--- Menu Subsidios ---\n")
        if seleccion != 0:
            print("--- Ingrese una opción valida ---\n")
        menuSubsidios = ["¿Sobre qué subsidio desea obtener información?"]
        contador = 1
        for subsidio in Subsidio.getListaSubsidio():
            opcion = str(contador) + ". " + subsidio.getNombre()
            menuSubsidios.append(opcion)
            contador += 1
        menuSubsidios.append(str(contador) + ". Regresar al inicio")
        print("\n".join(menuSubsidios))
        seleccion = int(input("Digite una opcion: "))
        if seleccion == contador:
            return

    subsidioElegido = Subsidio.getListaSubsidio()[seleccion - 1]
    subOpcion = 0

    while subOpcion > 4 or subOpcion < 1:
        print(espaciado)
        print("\n--- Menu Subsidios ---\n")
        print("Subsidio Elegido: " + subsidioElegido.getNombre() + "\n")
        if subOpcion != 0:
            print("\n--- Ingrese una opción valida ---\n")
        print(
            """
        ¿Qué información desea acerca del subsidio?
        1. Consultar elegibilidad de un vinculado"
        2. Tabla de vinculados elegibles"
        3. Información asociada"
        4. Regresar al Inicio"
        """
        )
        subOpcion = int(input("Digite una opción: "))
        if subOpcion == 4:
            print(espaciado)
            return

    match subOpcion:
        case 1:
            documento = 0
            vinculadoConsultado = None
            condicional = False
            contadorLocal = 0
            while not condicional:
                print(espaciado)
                print("\n--- Menu Subsidios ---\n")
                print("Subsidio elegido: " + subsidioElegido.getNombre() + "\n")
                print("Opcion Elegida: Elegibilidad de vinculado\n")

                if contador != 0:
                    print("--- Documento Invalido ---\n")

                print("- Ingrese el documento del vinculado.")
                print("- Ingrese 0 para salir")
                documento = int(input("Digite una opcion: "))

                if documento == 0:
                    print(espaciado)
                    return

                contadorLocal += 1
                vinculadosGeneral = (
                        Estudiante.getListaEstudiantes + Profesor.getListaProfesores
                )
                for vinculado in vinculadosGeneral:
                    if documento == vinculado.getDocumento():
                        condicional = True
                        vinculadoConsultado = vinculado

            condicional2 = vinculadoConsultado in subsidioElegido.getBeneficiarios()
            print("\n--- Menu Subsidios ---\n")
            print("Subsidio elegido: " + subsidioElegido.getNombre() + "\n")
            print("Opcion Elegida: Elegibilidad de vinculado\n")
            if condicional2:
                print("El vinculado SI es elegible para el Subsidio\n")
            else:
                print("El vinculado NO es elegible para el Subsidio\n")
            terminarPrograma()
            return

        case 2:
            subOpcion = 0
            while subOpcion > 3 or subOpcion < 1:
                if subOpcion != 0:
                    print("Opcion Invalida")
                print(espaciado)
                print("--- Menu Subsidios ---")
                print("Subsidio elegido: " + subsidioElegido.getNombre() + "\n")
                print(
                    """
                Opcion Elegida: Ver vinculado elegibles\n
                1. Ver vinculados elegibles por facultad
                2. Ver vinculados elegibles por linea de enfasis
                3. Ver todos los vinculados elegibles
                4. Regresar al inicio
                """
                )
                subOpcion = int(input("Digite una opcion: "))
                if subOpcion == 4:
                    return

            match subOpcion:
                case 1:
                    ##Imprimir Tabla Por Facultad
                    terminarPrograma()
                    return
                case 2:
                    ##Imprimir tabla por linea de Enfasis
                    terminarPrograma()
                    return
                case 3:
                    ##Imprimir tabla general
                    terminarPrograma()
                    return
        case 3:
            ##Label para mostrar info de la beca elegida
            terminarPrograma()
            return


def funcionalidad5():
    seleccion = 0

    while 1 > seleccion or seleccion > 2:
        print(espaciado)
        print("--- Menu Posición de Estudiante ---")

        if (seleccion != 0):
            print("--- Ingrese una opción valida ---\n")

        print("Seleccione una opción")
        print("1. Consultar por documento del Estudiante.")
        print("2. Regresar al Inicio.")

        seleccion = int(input("Digite una opcion: "))

        if (seleccion == 2):
            return

        if (seleccion == 1):
            documento = int(input("Ingrese el documento del estudiante: "))
            estudianteConsultado = None

            for facultad in Facultad.getListaFacultades():
                for estudiante in facultad.getEstudiantes():
                    if (documento == estudiante.documento):
                        estudianteConsultado = estudiante

            if (estudianteConsultado is None):
                print("Documento no encontrado")

            else:
                seleccion2 = 0

                while 1 > seleccion2 or seleccion2 > 4:
                    print(espaciado)
                    print("\n--- Menu Posición de Estudiante ---\n")
                    print("Documento válido\n")
                    print(estudianteConsultado)

                    if (seleccion2 != 0):
                        print("--- Ingrese una opción valida ---\n")

                    print("¿Que información desea obtener acerca del estudiante?")
                    print("1. Consultar la posición del estudiante en su semestre")
                    print("2. Consultar la posición del estudiante en su facultad")
                    print("3. Consultar la posición del estudiante en su línea de énfasis")
                    print("4. Regresar al Inicio")

                    seleccion2 = int(input("Digite una opcion: "))

                    if (seleccion2 == 4):
                        return

                match seleccion2:
                    case 1:
                        print('\n' + "POSICIÓN: " + str(Estudiante.posicionEstudiante(estudianteConsultado, "semestre"))
                              + '\n' + "SEMESTRE = " + str(estudianteConsultado.getSemestre()) + '\n')
                    case 2:
                        if (Estudiante.posicionEstudiante(estudianteConsultado, "facultad") == "NN"):
                            print("El estudiante no pertenece a ninguna facultad" + '\n')
                        else:
                            print('\n' + "POSICIÓN: " + str(Estudiante.posicionEstudiante(estudianteConsultado, "facultad")) + '\n'
                                  + "FACULTAD: " + estudianteConsultado.getFacultad().getNombre() + '\n')
                    case 3:
                        if (Estudiante.posicionEstudiante(estudianteConsultado, "LineaEnfasis") == "NN"):
                            print("El estudiante no pertenece a ninguna línea de énfasis" + '\n')
                        else:
                            print('\n' + "POSICIÓN: " + str(Estudiante.posicionEstudiante(estudianteConsultado, "lineaEnfasis")) + '\n'
                                  + "LÍNEA DE ÉNFASIS: " + estudianteConsultado.getLineaEnfasis().name + '\n')

class VentanaInicio(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("inicio")
        self.geometry("700x500")
        self.resizable(width=False, height=False)
        P1 = Frame(self, width=320, height=450)
        P1.place(x=20, y=20)
        P1.config(bg="black")
        P2 = Frame(self, width=320, height=450)
        P2.place(x=360, y=20)
        P2.config(bg="black")
        P3 = Frame(P1, width=300, height=100)
        P3.place(x=10, y=10)
        P3.config(bg="green")
        P4 = Frame(P1, width=300, height=320)
        P4.place(x=10, y=120)
        P4.config(bg="purple")
        P5 = Frame(P2, width=300, height=100)
        P5.place(x=10, y=10)
        P5.config(bg="green")
        P6 = Frame(P2, width=300, height=320)
        P6.place(x=10, y=120)
        P6.config(bg="purple")

        saludo = tk.Label(P3, text="Mensaje de bienvenida pendiente")
        saludo.place(x=50, y=40)
        P3.pack_propagate(False)

        self.mainloop()

    def principal(self):
        self.destroy()
        ventanaPrincipal()


class ventanaPrincipal(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Ventana principal")
        self.geometry("300x200")

        menuBar = tk.Menu(self)
        self.config(menu=menuBar)

        menu1 = tk.Menu(menuBar)
        menuBar.add_cascade(label="Archivo", menu=menu1)
        menu1.add_command(label="Salir", command=self.salir)
        self.mainloop()

    def salir(self):
        self.destroy()
        VentanaInicio()


def iniciar():
    VentanaInicio()
    deserializar()

    opcion = 0
    while opcion != 6:
        print("\n--- Menu Inicial ---\n")
        print("¿Que operacion desea realizar?")
        print(" 1. Recomendar Asignaturas")
        print(" 2. Calidad Estudiante")
        print(" 3. Información de Becas")
        print(" 4. Informacion de Subsidios Estudiantiles")
        print(" 5. Consultar posición de un estudiante")
        print(" 6. Salir del Sistema")
        opcion = int(input("Teclee opcion: "))

        match opcion:
            case 1:
                funcionalidad1()

            case 2:
                funcionalidad2()

            case 3:
                funcionalidad3()

            case 4:
                funcionalidad4()

            case 5:
                funcionalidad5()

            case 6:
                serializar()
                sys.exit()