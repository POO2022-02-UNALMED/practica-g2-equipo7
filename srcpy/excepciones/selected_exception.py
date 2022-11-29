from excepciones.error_aplicacion import ErrorAplicacion


class SelectedException(ErrorAplicacion):
    def __init__(self, message):
        super().__init__(extra_message=message)
