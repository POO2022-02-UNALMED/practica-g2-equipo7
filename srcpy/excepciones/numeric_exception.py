from excepciones.field_exception import FieldException


class NumericException(FieldException):
    def __init__(self, message="Esta usando valores no numericos"):
        super().__init__(message)
