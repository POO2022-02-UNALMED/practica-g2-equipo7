from excepciones.selected_exception import SelectedException


class becaNotSelected(SelectedException):
    def __init__(
        self,
        message="No fue seleccionada una beca",
    ):
        super().__init__(message)
