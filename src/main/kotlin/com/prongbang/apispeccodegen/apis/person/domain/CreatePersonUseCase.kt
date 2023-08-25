package com.prongbang.apispeccodegen.apis.person.domain

import com.prongbang.apispeccodegen.apis.person.data.PersonRepository
import com.prongbang.apispeccodegen.apis.person.domain.model.Person
import com.prongbang.apispeccodegen.core.domain.UseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface CreatePersonUseCase : UseCase<Person, Person>

@Service
class CreatePersonUseCaseImpl : CreatePersonUseCase {

    @Autowired
    lateinit var personRepository: PersonRepository

    override fun execute(params: Person): Person {
        return personRepository.createPerson(params)
    }
}