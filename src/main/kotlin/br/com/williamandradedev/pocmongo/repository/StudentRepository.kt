package br.com.williamandradedev.pocmongo.repository

import br.com.williamandradedev.pocmongo.model.Student
import org.springframework.data.mongodb.repository.MongoRepository


interface StudentRepository: MongoRepository<Student,String> {

    fun findByEmail(email: String): Student?
}