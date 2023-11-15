package christmas.exception

class ValidationException(message: String) :
    IllegalArgumentException("[ERROR] " + message + " 다시 입력해 주세요.")