from excepciones.selected_exception import SelectedException


class invalidLinea(SelectedException):
    def __init__(
        self,
        message="Esta beca es profesoral, y los profesores no tienen Linea de Enfasis",
    ):
        super().__init__(message)
