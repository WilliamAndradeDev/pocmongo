package br.com.williamandradedev.pocmongo.model

import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import java.math.BigInteger
import java.util.*

@Document
data class Student(
    @MongoId
    val id: UUID = UUID.randomUUID(),
    val firstName: String?=null,
    val lastName: String?=null,
    val address: Address?=null,
    val classId: BigInteger?=null,
    @Indexed(unique = true)
    val email: String?=null
)