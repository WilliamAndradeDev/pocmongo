package br.com.williamandradedev.pocmongo.controller

import br.com.williamandradedev.pocmongo.controller.dto.StudentAddressDTO
import br.com.williamandradedev.pocmongo.controller.dto.StudentDTO
import br.com.williamandradedev.pocmongo.controller.dto.StudentUpdateDTO
import br.com.williamandradedev.pocmongo.controller.dto.toModel
import br.com.williamandradedev.pocmongo.model.Address
import br.com.williamandradedev.pocmongo.model.Student
import br.com.williamandradedev.pocmongo.repository.StudentRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigInteger
import java.util.UUID

@RestController
@RequestMapping("v1/student")
class StudentController(
    private val studentRepository: StudentRepository
) {

    @GetMapping
    fun getStudents(): List<Student> = studentRepository.findAll()

    @PostMapping
    fun createStudent(studentDTO: StudentDTO): ResponseEntity<Student> =
        ResponseEntity(
            studentRepository.save(studentDTO.toModel()),
            HttpStatus.CREATED
        )

    @GetMapping("/{id}/address")
    fun getStudentAddressById(
            @PathVariable("id") id: String
    ): ResponseEntity<Address> =
            ResponseEntity(
                    studentRepository.getAddressById(UUID.fromString(id)).address,
                    HttpStatus.OK
            )

    @GetMapping("/{id}")
    fun getStudentId(
        @PathVariable("id") id: String
    ): ResponseEntity<Student> =
        ResponseEntity(
            studentRepository.findByIdOrNull(UUID.fromString(id)) ?: throw Exception("estudante não encontrado"),
            HttpStatus.OK
        )

    @GetMapping("/classId/{classId}")
    fun getStudentByEmail(
        @PathVariable("classId") classId: BigInteger,
        pageable: Pageable
    ): Page<Student> = studentRepository.findByClassId(classId, pageable)

    @PutMapping("/{id}")
    fun updateStudent(
        @PathVariable("id") id: String,
        @RequestBody studentUpdateDTO: StudentUpdateDTO
    ): ResponseEntity<Any> {
        val updatedStudent = studentRepository.findAndSetStudentById(
            id = UUID.fromString(id),
            firstName = studentUpdateDTO.firstName,
            lastName = studentUpdateDTO.lastName,
            email = studentUpdateDTO.email
        )
        return ResponseEntity.ok().build()
    }

    @PutMapping("/{id}/address")
    fun updateAdress(
        @PathVariable("id") id: String,
        @RequestBody studentAddressDTO: StudentAddressDTO
    ): ResponseEntity<Address> {
        val studentWithOnlyAddressLoaded = studentRepository.getAddressById(UUID.fromString(id))
        val addressUpdated = studentWithOnlyAddressLoaded.address?.copy(
            street = studentAddressDTO.street,
            neighborhood = studentAddressDTO.neighborhood,
            city = studentAddressDTO.city
        )
        studentRepository.findAndSetAddressById(
            studentWithOnlyAddressLoaded.id!!,
            addressUpdated!!
            )
        return ResponseEntity(
            addressUpdated,
            HttpStatus.OK
        )
    }

    @DeleteMapping("/{id}")
    fun deleteStudent(
        @PathVariable("id") id: String
    ): ResponseEntity<Any> {
        studentRepository.deleteById(UUID.fromString(id))
        return ResponseEntity.ok().build()
    }
}