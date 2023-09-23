package com.example.contratistacompose.utils


object TextValidation {

    /**
     * Funcion para determinar si es un email valido
     */
    fun String.isEmailValid(): Boolean = this.matches("^[A-Za-z0-9+_.-]+@(.+)$".toRegex())
    fun String.toOnlyNumbers() = this.replace(Regex("^\\d*"), "")

    /**
     * Funcion usada para determinar si tiene formato de moneda
     */
    fun String.isMoney() = this.matches(Regex("^\\d+(\\.\\d+)?\$"))

    /**
     * Funcion usada para determinar si solo hay numeros en la cadena proporcionada
     */
    fun String.isOnlyNumber() = this.replace(Regex("[/d]*"), "").isEmpty()

    /**
     * Funcion usada para determinar si hay caracteres especiales en una cadena que solo
     * puede recibir letras, digitos, espacios en blanco, puntos y comas.
     */
    fun String.isOnlyLettersAndNumber(): Boolean =
        this.replace(Regex("[a-zA-ZÀ-ÖØ-öø-ÿ\\d\\s.,]*"), "").isEmpty()

    /**
     * Funcion usada para determinar si hay números en una cadena que solo
     * puede recibir letras, espacios en blando, puntos y comas.
     */
    fun String.isOnlyText(): Boolean =
        this.replace(Regex("[a-zA-ZÀ-ÖØ-öø-ÿ\\s,.]*"), "").isEmpty()

    fun String.isNumber(): Boolean = this.replace(Regex("\\d*"), "").isEmpty()

    /**
     * Funcion usada para evaluar la validez de la contraseña.
     *
     * Parametros a evaluar:
     *
     * - Longitud mínima y máxima: **^.{8,20}$**
     * - Al menos una letra mayúscula: **^(?=.*[A-Z]).*$**
     * - Al menos una letra minúscula: **^(?=.*[a-z]).*$**
     * - Al menos un dígito: **^(?=.*\d).*$**
     * - Al menos un carácter especial (por ejemplo, @, #, $, %): **^(?=.*[@#$%]).*$**
     * - Sin espacios en blanco: **^\S*$**
     * - No más de 3 caracteres repetidos en secuencia: **^(?!.*(.)\1\1\1).***
     * - Sin caracteres consecutivos (por ejemplo, abc, 123): **^(?!.*(abc|def|123)).***
     * - Sin caracteres repetidos en secuencia (por ejemplo, 111, aaa): **^(?!.*(.)\1\1).***
     *
     *
     * - **NOTA:** Al menos dos categorías de caracteres (mayúsculas, minúsculas,
     * dígitos, caracteres especiales) deben cumplirse para que la contraseña sea segura.
     */
    fun String.isPasswordValid(error: (String) -> Unit): Boolean {
        val password = this.trim()

        if (!Regex("^.{8,20}$").matches(password)) {
            error.invoke(MIN_MAX_LENGTH)
            return false
        }

        if (!Regex("^(?=.*[A-Z]).*$").matches(password))
            error.invoke(UPPERCASE)
        if (!Regex("^(?=.*[a-z]).*$").matches(password))
            error.invoke(LOWERCASE)
        if (!Regex("^(?=.*\\d).*$").matches(password))
            error.invoke(ONE_DIGIT)
        if (!Regex("^(?=.*[@#$%]).*$").matches(password))
            error.invoke(ONE_SPECIAL_CHARACTER)

        if (!Regex("^\\S*$").matches(password)) {
            error.invoke(NO_WHITESPACE)
            return false
        }
        if (Regex(".*(.)\\1\\1\\1.*").matches(password)) {
            error.invoke(THREE_CONSECUTIVE)
            return false
        }
        if (Regex(".*(abc|def|123).*").matches(password)) {
            error.invoke(NO_CONSECUTIVE)
            return false
        }
        if (Regex(".*(.)\\1\\1.*").matches(password)) {
            error.invoke(NO_REPEATED_CHARACTER)
            return false
        }

        return countCharacterCategories(password) >= 2
    }

    /**
     * Function used by count Character Categories
     * @param password
     */
    private fun countCharacterCategories(password: String): Int {
        val categories = listOf(
            Regex("[A-Z]"), // Uppercase letters
            Regex("[a-z]"), // Lowercase letters
            Regex("\\d"),   // Digits
            Regex("[@#$%]") // Special characters
        )

        return categories.count { it.containsMatchIn(password) }
    }

}