from excepciones.field_exception import FieldException


class EmptyException(FieldException):
    def __init__(self, message="Campo vacio"):
        super().__init__(message)
