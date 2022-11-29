from gestorAplicacion.personas.persona import Persona
from functools import total_ordering


@total_ordering
class Estudiante(Persona):
    asignaturasInscritas = {}
    listaEstudiantes = []

    def __init__(
        self,
        documento,
        nombre,
        edad,
        asignaturasInscritas,
        promedio,
        semestre,
        lineaEnfasis,
        asignaturasAprobadas,
        estrato,
        ingresosFamiliares,
        colegioPublico,
        numeroFamiliares,
    ):
        from gestorAplicacion.gestion.asignatura import Asignatura
        from gestorAplicacion.gestion.lineasEnfasis import LineasEnfasis
        from gestorAplicacion.gestion.facultad import Facultad

        super().__init__(documento, nombre, edad)
        self.asignaturasInscritas = asignaturasInscritas
        self.promedio = promedio
        self.semestre = semestre
        self.lineaEnfasis = lineaEnfasis
        self.asignaturasAprobadas = asignaturasAprobadas
        self.facultad = None
        self.estrato = estrato
        self.ingresosFamiliares = ingresosFamiliares
        self.colegioPublico = colegioPublico
        self.numeroFamiliares = numeroFamiliares
        Estudiante.listaEstudiantes.append(self)

    def calcularPromedio(self):
        promedio = 0
        creditosInscritos = 0

        if len(self.asignaturasInscritas) == 0:
            return 3.0

        for key in list(self.asignaturasInscritas.keys()):
            creditos = key.getCreditos()
            creditosInscritos += creditos
            promedio += creditos * self.asignaturasInscritas[key]

        promedio = promedio / creditosInscritos
        return promedio

    def calidadEstudiante(self):
        from gestorAplicacion.gestion.calidadEstudiante import CalidadEstudiante

        return CalidadEstudiante.ObtenerCalidadEstudiante(self.calcularPromedio())

    @staticmethod
    def posicionEstudiante(estudiante_seleccionado, dominio: str):
        from gestorAplicacion.personas.estudiante import Estudiante
        from gestorAplicacion.gestion.facultad import Facultad

        posicion_estudiante = 1

        match dominio:
            case "semestre":
                for estudiante in Estudiante.getListaEstudiantes():
                    if (
                        estudiante.getSemestre()
                        == estudiante_seleccionado.getSemestre()
                    ):
                        if (
                            estudiante.getPromedio()
                            > estudiante_seleccionado.getPromedio()
                        ):
                            posicion_estudiante += 1
                return posicion_estudiante

            case "facultad":
                for facultad in Facultad.getListaFacultades():
                    if estudiante_seleccionado in facultad.getEstudiantes():
                        for estudiante in facultad.getEstudiantes():
                            if (
                                estudiante.getPromedio()
                                > estudiante_seleccionado.getPromedio()
                            ):
                                posicion_estudiante += 1

                            if (
                                estudiante.getPromedio()
                                == estudiante_seleccionado.getPromedio()
                            ):
                                if (
                                    estudiante.getSemestre()
                                    > estudiante_seleccionado.getSemestre()
                                ):
                                    posicion_estudiante += 1
                        return posicion_estudiante
                return "NN"
            case "lineaEnfasis":
                for facultad in Facultad.getListaFacultades():
                    if estudiante_seleccionado in facultad.getEstudiantes():
                        for estudiante in facultad.getEstudiantes():
                            if (
                                estudiante.getLineaEnfasis()
                                == estudiante_seleccionado.getLineaEnfasis()
                            ):
                                if (
                                    estudiante.getPromedio()
                                    > estudiante_seleccionado.getPromedio()
                                ):
                                    posicion_estudiante += 1

                                if (
                                    estudiante.getPromedio()
                                    == estudiante_seleccionado.getPromedio()
                                ):
                                    if (
                                        estudiante.getSemestre()
                                        > estudiante_seleccionado.getSemestre()
                                    ):
                                        posicion_estudiante += 1
                        return posicion_estudiante
                return "NN"
            case _:
                print("Ingrese un rango de busqueda válido")

    def recomendarAsignaturas(self):
        from gestorAplicacion.gestion.asignatura import Asignatura

        listado = Asignatura.getListaAsignaturas()
        lista_enfasis = []
        lista_recomendar = []

        for asignatura in listado:
            if self.lineaEnfasis != None and asignatura.getLineaEnfasis() != None:
                if (
                    (asignatura.getLineaEnfasis() == self.lineaEnfasis)
                    and (asignatura not in self.asignaturasAprobadas)
                    and (asignatura not in self.asignaturasInscritas)
                ):
                    lista_enfasis.append(asignatura)

        for asignatura in lista_enfasis:
            if asignatura.getPrerequisitos() != None:
                if asignatura.getPrerequisitos() != []:
                    if all(
                        item in asignatura.getPrerequisitos()
                        for item in self.asignaturasAprobadas
                    ):
                        lista_recomendar.append(asignatura)

            else:
                lista_recomendar.append(asignatura)

        return lista_recomendar

    def recomendarAsignaturasBasicas(self):
        from gestorAplicacion.gestion.asignatura import Asignatura
        from gestorAplicacion.gestion.lineasEnfasis import LineasEnfasis

        listado = Asignatura.getListaAsignaturas()
        lista_recomendar = []
        listado_basicas = []

        for asignatura in listado:
            if (
                asignatura.getLineaEnfasis() == LineasEnfasis.BASICAS
                and asignatura not in self.asignaturasAprobadas
                and asignatura not in self.asignaturasInscritas
            ):
                listado_basicas.append(asignatura)

        for asignatura in listado_basicas:
            if asignatura.getPrerequisitos() != None:
                if asignatura.getPrerequisitos() != []:
                    if all(
                        item in asignatura.getPrerequisitos()
                        for item in self.asignaturasAprobadas
                    ):
                        lista_recomendar.append(asignatura)
            else:
                lista_recomendar.append(asignatura)

        return lista_recomendar

    @staticmethod
    def vistaGeneralEstudiantes():
        from gestorAplicacion.gestion.beca import Beca
        from gestorAplicacion.gestion.subsidio import Subsidio

        print("-------------")
        print("-------------")
        print(
            "NOMBRE",
            "DOCUMENTO",
            "BECA",
            "SUBSIDIO",
            "CALIDAD",
            "PSEMESTRE",
            "PFACULTAD",
            "PLINEA",
        )
        print("-------------")
        print("-------------")

        for estudiante in Estudiante.getListaEstudiantes():
            informacion_beca = "No aplica"
            informacion_subsidio = "No aplica"

            for beca in Beca.getListabecas():
                if estudiante in beca.getBeneficiarios():
                    informacion_beca = beca.getNombre()
                    break

            for subsidio in Subsidio.getListaSubsidio():
                if estudiante in subsidio.getBeneficiarios():
                    informacion_subsidio = subsidio.getNombre()
                    break

            print(
                estudiante.getNombre(),
                estudiante.getDocumento(),
                informacion_beca,
                informacion_subsidio,
                estudiante.calidadEstudiante(),
                Estudiante.posicionEstudiante(estudiante, "semetre"),
                Estudiante.posicionEstudiante(estudiante, "facultad"),
                Estudiante.posicionEstudiante(estudiante, "lineaEnfasis"),
            )

            print("\n")

        print("-------------")
        print("-------------")

    def agregarAsignaturaInscrita(self, key, value):
        self.asignaturasInscritas[key] = value

    def agregarAsignaturaAprobada(self, asignatura):
        self.asignaturasAprobadas.append(asignatura)

    def getAsignaturasInscritas(self):
        return self.asignaturasInscritas

    def setAsignaturasInscritas(self, asignaturas):
        self.asignaturasInscritas = asignaturas

    def getPromedio(self):
        return self.promedio

    def setPromedio(self, promedio):
        self.promedio = promedio

    def getSemestre(self):
        return self.semestre

    def setSemestre(self, semestre):
        self.semestre = semestre

    def getLineaEnfasis(self):
        return self.lineaEnfasis

    def setLineaEnfasis(self, linea):
        self.lineaEnfasis = linea

    def getAsignaturasAprobadas(self):
        return self.asignaturasAprobadas

    def setAsignaturasAprobadas(self, asignaturas):
        self.asignaturasAprobadas = list(asignaturas)

    @classmethod
    def getListaEstudiantes(cls):
        return cls.listaEstudiantes

    @classmethod
    def setListaEstudiantes(cls, lista):
        cls.listaEstudiantes = lista

    def getEstrato(self):
        return self.estrato

    def setEstrato(self, estrato):
        self.estrato = estrato

    def getIngresosFamiliares(self):
        return self.ingresosFamiliares

    def setIngresosFamiliares(self, ingresos):
        self.ingresosFamiliares = ingresos

    def isColegioPublico(self):
        return self.colegioPublico

    def setColegioPublico(self, colegioPublico):
        self.colegioPublico = colegioPublico

    def getNumeroFamiliares(self):
        return self.numeroFamiliares

    def setNumeroFamiliares(self, numeroFamiliares):
        self.numeroFamiliares = numeroFamiliares

    def getFacultad(self):
        return self.facultad

    def setFacultad(self, facultad):
        self.facultad = facultad

    def getDocumento(self):
        return super().getDocumento()

    def setDocumento(self, documento):
        return super().setDocumento(documento)

    def __lt__(self, other):
        return self.calcularPromedio() > other.calcularPromedio()

    def __eq__(self, other):
        return self.calcularPromedio() == other.calcularPromedio()

    def __str__(self):
        return (
            "Nombre del estudiante: "
            + self.getNombre()
            + "\nPromedio: "
            + str(self.getPromedio())
            + "\nSemestre: "
            + str(self.getSemestre())
            + "\nLínea de Enfásis: "
            + self.getLineaEnfasis().name
            + "\nFacultad: "
            + self.getFacultad().getNombre()
            + "\n"
        )
