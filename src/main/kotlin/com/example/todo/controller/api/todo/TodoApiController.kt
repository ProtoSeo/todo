package com.example.todo.controller.api.todo

import com.example.todo.model.http.TodoDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todo")
class TodoApiController {
    //C
    @PostMapping(path=[""])
    fun create(@RequestBody todoDto: TodoDto){

    }
    //R
    @GetMapping(path=[""])
    fun read(@RequestParam(required = false) index:Int?){

    }
    //U
    @PutMapping(path=[""])
    fun update(@RequestBody todoDto: TodoDto){

    }
    //D
    @DeleteMapping(path = ["/{index}"])
    fun delete(@PathVariable(name = "index") _index:Int){

    }
}