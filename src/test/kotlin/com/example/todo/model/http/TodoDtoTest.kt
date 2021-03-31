package com.example.todo.model.http

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.validation.FieldError
import javax.validation.Validation

class TodoDtoTest{
    val validator = Validation.buildDefaultValidatorFactory().validator;

    @Test
    fun todoDtoTest(){
        val todoDto = TodoDto().apply {
            this.title = "테스트"
            this.description =""
            this.schedule ="2021-03-31 13:00:00"
        }

        val result = validator.validate(todoDto)
        assertEquals(true,result.isEmpty())
    }
}