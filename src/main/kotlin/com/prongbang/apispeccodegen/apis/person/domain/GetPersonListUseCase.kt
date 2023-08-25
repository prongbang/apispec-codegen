package com.prongbang.apispeccodegen.apis.person.domain

import com.prongbang.apispeccodegen.apis.person.data.PersonRepository
import com.prongbang.apispeccodegen.apis.person.domain.model.Person
import com.prongbang.apispeccodegen.core.domain.NoneParamUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface GetPersonListUseCase : NoneParamUseCase<List<Person>>

@Service
class GetPersonListUseCaseImpl : GetPersonListUseCase {

    @Autowired
    lateinit var personRepository: PersonRepository

    override fun execute(): List<Person> {
        return personRepository.getPersonList()
    }
}