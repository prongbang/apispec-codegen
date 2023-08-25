package com.prongbang.apispeccodegen.apis.person.data

import com.prongbang.apispeccodegen.apis.person.domain.model.Person
import org.springframework.stereotype.Repository
import java.util.ArrayList

interface PersonRepository {
    fun getPersonList(): List<Person>
    fun createPerson(person: Person): Person
}

@Repository
class PersonRepositoryImpl : PersonRepository {

    override fun getPersonList(): List<Person> {
        val hardCoded: MutableList<Person> = ArrayList()
        val person = Person()
        person.id = 1
        person.age = 20
        person.email1 = "xyz@abc.com"
        person.email2 = "abc@abc.com"
        person.firstName = "John"
        person.lastName = "Wick"
        hardCoded.add(person)
        return hardCoded
    }

    override fun createPerson(person: Person): Person {
        return person
    }

}