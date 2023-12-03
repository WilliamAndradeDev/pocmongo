package br.com.williamandradedev.pocmongo.repository

import br.com.williamandradedev.pocmongo.model.Address
import br.com.williamandradedev.pocmongo.model.Student
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.Update


interface StudentRepository: MongoRepository<Student,String> {

    @Query(value = "{'_id': ?0}", fields = "{'address': 0}")
    fun getStudentWithoutAddressById(id: String): Student
    @Query(value = "{'_id': ?0}", fields = "{'address': 1}")
    fun getAddressById(id: String): Student

    @Update("{ '\$set' : { 'address' : ?1 } }")
    fun findAndSetAddressById(id: String, address: Address): Long

    @Update("{ '\$set' : { 'firstName' : ?1, 'lastName': ?2, 'email': ?3 } }")
    fun findAndSetStudentById(id: String, firstName: String, lastName: String, email: String): Long
}