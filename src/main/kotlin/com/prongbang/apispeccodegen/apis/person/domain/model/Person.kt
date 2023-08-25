package com.prongbang.apispeccodegen.apis.person.domain.model

import jakarta.validation.constraints.*

class Person {
    var id: Long = 0

    @Size(min = 2, max = 50)
    var firstName: @NotBlank String? = null

    @Size(min = 2, max = 50)
    var lastName: @NotBlank String? = null

    @Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
    var email1: String? = null
    var email2: @Email String? = null

    @Min(18)
    @Max(30)
    var age = 0

}