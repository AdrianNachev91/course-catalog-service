package com.kotlinspring.controller

import com.kotlinspring.dto.InstructorDTO
import com.kotlinspring.repository.InstructorRepository
import com.kotlinspring.util.PostgreSQLContainerInitializer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class InstructorControllerIntgTest : PostgreSQLContainerInitializer() {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var instructorRepository: InstructorRepository

    @BeforeEach
    fun setUp() {
        instructorRepository.deleteAll()
    }

    @Test
    fun createInstructor() {

        val instructorDTO = InstructorDTO(null, "Instructor")

        val savedInstructorDTO = webTestClient
            .post()
            .uri("/v1/instructors")
            .bodyValue(instructorDTO)
            .exchange()
            .expectStatus().isCreated
            .expectBody(InstructorDTO::class.java)
            .returnResult().responseBody

        Assertions.assertTrue {
            savedInstructorDTO!!.id != null
        }
    }
}