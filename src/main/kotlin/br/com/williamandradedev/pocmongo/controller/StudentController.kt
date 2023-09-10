package br.com.williamandradedev.pocmongo.controller

import br.com.williamandradedev.pocmongo.controller.dto.StudentDTO
import br.com.williamandradedev.pocmongo.controller.dto.toModel
import br.com.williamandradedev.pocmongo.model.Student
import br.com.williamandradedev.pocmongo.repository.StudentRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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


}