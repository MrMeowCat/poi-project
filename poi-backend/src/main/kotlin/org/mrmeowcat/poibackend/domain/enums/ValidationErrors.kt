package org.mrmeowcat.poibackend.domain.enums

enum class ValidationErrors(val value: String) {
    FORM_EMPTY("form_empty"),
    USERNAME_EMPTY("username_empty"),
    EMAIL_EMPTY("email_empty"),
    PASSWORD_EMPTY("password_empty"),
    CONFIRM_EMPTY("confirm_empty"),
    USER_EXISTS("user_exists"),
    EMAIL_EXISTS("email_exists"),
    EMAIL_INCORRECT("email_incorrect"),
    PASSWORD_INCORRECT("password_incorrect"),
    PASSWORD_MISMATCH("password_mismatch"),
}