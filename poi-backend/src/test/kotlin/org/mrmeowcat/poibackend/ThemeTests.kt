package org.mrmeowcat.poibackend

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mrmeowcat.poibackend.domain.document.Theme
import org.mrmeowcat.poibackend.domain.repository.Repositories
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest
class ThemeTests {

    @Autowired
    lateinit var repositories: Repositories

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Before
    fun addThemes() {
        var json = "{\n\"Feature\" : \"Style\"\n}"
        var jsonNode = objectMapper.readValue(json, JsonNode::class.java)
        json = jsonNode.toString()


        val users = repositories.users.findAll()


        val theme = Theme()
        theme.name = "Standard"
        theme.style = json
        theme.userId = users[0].id


        val theme1 = Theme()
        theme1.name = "Standard"
        theme1.style = json
        theme1.userId = users[1].id


        repositories.themes.save(theme)

        users[0].themeId = theme.id

        repositories.users.save(users[0])


//        repositories.themes.save(theme)
        repositories.themes.save(theme1)
    }

    @After
    fun clean() {
        repositories.themes.deleteAll()
        val user = repositories.users.findAll()[0]
        user.themeId = null
        repositories.users.save(user)
    }


    @Test
    fun getThemesByName() {
        val themes = repositories.themes.findByName("Standard")

        themes.forEach({
            println(it)
        })

        println("end getThemeByName")
    }

    @Test
    fun getThemesByUserId() {
        val user = repositories.users.findAll()[1]!!
        val themes = repositories.themes.findByUserId(user.id!! + "asd")

        themes.forEach({
            println(it)
        })

        println("end getThemeByUserId")
    }

    @Test
    fun existsByUserId() {
        val user = repositories.users.findAll()[0]!!
        val user2 = repositories.users.findAll()[1]!!
        val themes = repositories.themes.findAll()

        println(repositories.themes.existsByIdAndUserId(themes[0].id!!, user.id!!))
        println(repositories.themes.existsByIdAndUserId(themes[0].id!!, user2.id!!))
        println(repositories.themes.existsByIdAndUserId(themes[0].id!!, "asdasdasd"))

        println(repositories.themes.findByIdAndUserId(themes[0].id!!, user.id!!))
        println(repositories.themes.findByIdAndUserId(themes[0].id!!, user2.id!!))
        println(repositories.themes.findByIdAndUserId(null, user2.id!!))

    }

}