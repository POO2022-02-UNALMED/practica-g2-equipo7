from excepciones.field_exception import FieldException


class LengthException(FieldException):
    def __init__(self, message="La longitud de la cadena no es suficiente"):
        super().__init__(message)
