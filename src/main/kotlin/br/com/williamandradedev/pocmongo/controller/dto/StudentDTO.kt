package br.com.williamandradedev.pocmongo.controller.dto

import br.com.williamandradedev.pocmongo.model.Address
import br.com.williamandradedev.pocmongo.model.Student

data class StudentDTO(
    val firstName: String,
    val lastName: String,
    val email: String,
    val street: String,
    val neighborhood: String,
    val city:String
)

fun StudentDTO.toModel() =
    Student(
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        address = Address(
            street = street,
            neighborhood = neighborhood,
            city = city
        )
    )