package br.com.williamandradedev.pocmongo.controller

import br.com.williamandradedev.pocmongo.model.Address
import br.com.williamandradedev.pocmongo.model.Student
import br.com.williamandradedev.pocmongo.repository.StudentRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.MountableFile


@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var studentRepository: StudentRepository

    @Autowired
    lateinit var mapper: ObjectMapper

    companion object {

        @Container
        val mongoContainer =
            MongoDBContainer("mongo:7.0")
                .withCopyFileToContainer(MountableFile.forClasspathResource("/mongo/init.js"), "/docker-entrypoint-initdb.d/init.js")
                .apply{ this.start() }

        @JvmStatic
        @DynamicPropertySource
        fun mongoProperties(registry: DynamicPropertyRegistry) {
            registry.add(
                "spring.data.mongodb.uri",
                mongoContainer::getReplicaSetUrl
            )
        }
    }

    @BeforeEach
    fun setup() {
        studentRepository.deleteAll()

        studentRepository.saveAll(
            listOf(
                Student(

                    firstName = "Ana",
                    lastName = "MCclean",
                    address = Address(
                        street = "stuff",
                        neighborhood = "stuff",
                        city = "gotham"
                    ),
                    email = "123das@gmail.com"
                    ),
                Student(
                    firstName = "Bruno",
                    lastName = "MCclean",
                    address = Address(
                        street = "stuff",
                        neighborhood = "stuff",
                        city = "gotham"
                    ),
                    email = "123das@gmail.com"
                )
            )
        )
    }

    @Test
    fun `should return students from mongodb`() {
        val result = mockMvc.perform(
            get("/v1/student")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andReturn()

        val resultAsString = result.response.contentAsString
        val students: List<Student> =
            mapper.readValue(resultAsString)

        assertThat(students).hasSizeGreaterThan(0)
        assertThat(students[0].firstName).isEqualTo("Ana")
        assertThat(students[1].firstName).isEqualTo("Bruno")
    }
}