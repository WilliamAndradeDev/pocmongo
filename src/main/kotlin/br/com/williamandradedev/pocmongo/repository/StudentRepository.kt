package br.com.williamandradedev.pocmongo.repository

import br.com.williamandradedev.pocmongo.model.Address
import br.com.williamandradedev.pocmongo.model.Student
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.Update
import java.math.BigInteger
import java.util.*


interface StudentRepository: MongoRepository<Student,UUID> {

    fun findByClassId(classId: BigInteger, pageable: Pageable): Page<Student>

    @Query(value = "{'_id': ?0}", fields = "{'address': 0}")
    fun getStudentWithoutAddressById(id: UUID): Student
    @Query(value = "{'_id': ?0}", fields = "{'address': 1}")
    fun getAddressById(id: UUID): Student

    @Update("{ '\$set' : { 'address' : ?1 } }")
    fun findAndSetAddressById(id: UUID, address: Address): Long

    @Update("{ '\$set' : { 'firstName' : ?1, 'lastName': ?2, 'email': ?3 } }")
    fun findAndSetStudentById(id: UUID, firstName: String, lastName: String, email: String): Long
}