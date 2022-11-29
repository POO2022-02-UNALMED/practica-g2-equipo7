from excepciones.selected_exception import SelectedException


class VinculadoInvalido(SelectedException):
    def __init__(
        self,
        message="Fue seleccionado un profesor para una beca estudiantil",
    ):
        super().__init__(message)
