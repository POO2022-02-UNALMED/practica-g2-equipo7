from excepciones.selected_exception import SelectedException


class SubsidioNotSelected(SelectedException):
    def __init__(
        self,
        message="No fue seleccionado un subsidio",
    ):
        super().__init__(message)
