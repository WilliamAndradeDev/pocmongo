package br.com.williamandradedev.pocmongo.controller

import br.com.williamandradedev.pocmongo.controller.dto.StudentDTO
import br.com.williamandradedev.pocmongo.controller.dto.toModel
import br.com.williamandradedev.pocmongo.model.Address
import br.com.williamandradedev.pocmongo.model.Student
import br.com.williamandradedev.pocmongo.repository.StudentRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

    @GetMapping("/{name}/address")
    fun getStudentAddressByName(
            @PathVariable("name") name: String
    ): ResponseEntity<Address> =
            ResponseEntity(
                    studentRepository.getAddressByName(name).address,
                    HttpStatus.OK
            )
}