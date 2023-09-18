package br.com.williamandradedev.pocmongo.repository

import br.com.williamandradedev.pocmongo.model.Student
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query


interface StudentRepository: MongoRepository<Student,String> {

    fun findByEmail(email: String): Student?
    @Query(value = "{'firstName': ?0}", fields = "{'address': 1}")
    fun getAddressByName(firstName: String): Student
}