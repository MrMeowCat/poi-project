package org.mrmeowcat.poibackend

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test
import org.junit.runner.RunWith
import org.mrmeowcat.poibackend.application.dto.UserDto
import org.mrmeowcat.poibackend.application.mapper.BeanMapper
import org.mrmeowcat.poibackend.domain.document.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class MapperTests {

    @Autowired
    lateinit var mapper: BeanMapper

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun test_convertUser2UserDto() {
        val user = User()
        user.username = "!"
        user.email = "!"
        user.password = "!"


        val userDto: UserDto = mapper.map(user, UserDto::class)
        println(userDto)
    }

    @Test
    fun minifyJson() {
        var json = "{\n" +
                "\"Feature\"  \"Style\"\n" +
                "}"
        println(objectMapper.readValue(json, JsonNode::class.java).toString())
    }
}