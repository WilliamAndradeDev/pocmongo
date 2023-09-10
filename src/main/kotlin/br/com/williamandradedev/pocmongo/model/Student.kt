package br.com.williamandradedev.pocmongo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Student(
    @Id
    val id: String?=null,
    val firstName: String,
    val lastName: String,
    val address: Address,
    @Indexed(unique = true)
    val email: String
)