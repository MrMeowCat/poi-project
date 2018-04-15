package org.mrmeowcat.poibackend.domain.enums

enum class ValidationErrors(val value: String) {
    FORM_EMPTY("FORM_EMPTY"),
    USERNAME_EMPTY("USERNAME_EMPTY"),
    EMAIL_EMPTY("EMAIL_EMPTY"),
    PASSWORD_EMPTY("PASSWORD_EMPTY"),
    CONFIRM_EMPTY("CONFIRM_EMPTY"),
    USER_EXISTS("USER_EXISTS"),
    EMAIL_EXISTS("EMAIL_EXISTS"),
    EMAIL_INCORRECT("EMAIL_INCORRECT"),
    PASSWORD_INCORRECT("PASSWORD_INCORRECT"),
    PASSWORD_MISMATCH("PASSWORD_MISMATCH"),
}