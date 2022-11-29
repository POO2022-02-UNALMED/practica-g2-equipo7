from excepciones.selected_exception import SelectedException


class DocumentoInvalido(SelectedException):
    def __init__(
        self,
        message="Se ingresó un documento ya registrado",
    ):
        super().__init__(message)
