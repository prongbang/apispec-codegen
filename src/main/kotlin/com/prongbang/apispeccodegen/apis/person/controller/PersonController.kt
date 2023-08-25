package com.prongbang.apispeccodegen.apis.person.controller

import com.prongbang.apispeccodegen.apis.person.domain.CreatePersonUseCase
import com.prongbang.apispeccodegen.apis.person.domain.GetPersonListUseCase
import com.prongbang.apispeccodegen.apis.person.domain.model.Person
import com.prongbang.apispeccodegen.core.domain.model.ErrorResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class PersonController {
    private val ran = Random()

    @Autowired
    lateinit var getPersonListUseCase: GetPersonListUseCase

    @Autowired
    lateinit var createPersonUseCase: CreatePersonUseCase

    @PostMapping("/persons")
    fun person(@RequestBody person: @Valid Person): Person? {
        return createPersonUseCase.execute(person)
    }

    @Operation(summary = "Get a book by its id")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Found the person",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = Person::class),
                    ),
                ],
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid the parameter",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = ErrorResponse::class),
                    ),
                ],
            ),
            ApiResponse(
                responseCode = "404",
                description = "Person not found",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = ErrorResponse::class),
                    ),
                ],
            ),
        ]
    )
    @GetMapping(path = ["/persons"])
    fun findByLastName(): List<Person> {
        return getPersonListUseCase.execute()
    }
}