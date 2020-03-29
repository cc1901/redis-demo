package com.example.redis

import com.example.redis.`interface`.PositionDto
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate


@SpringBootTest
@AutoConfigureMockMvc
class PositionControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @Test
    @Throws(Exception::class)
    fun getHello() {
        val objectMapper = jacksonObjectMapper()
        objectMapper.registerModule(KotlinModule())
        objectMapper.registerModule(JavaTimeModule())
        val positionToSave = PositionDto("700 HK", 10000, LocalDate.now())
        mvc.perform(post("/position")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(positionToSave))
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk)

        val result = mvc.perform(MockMvcRequestBuilders.get("/position").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andReturn()

        val position = objectMapper.readValue(result.response.contentAsString, object : TypeReference<List<PositionDto>>(){})
        assertThat(position[0]).isEqualTo(positionToSave)
    }
}