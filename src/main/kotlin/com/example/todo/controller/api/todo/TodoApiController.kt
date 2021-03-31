package com.example.todo.controller.api.todo

import com.example.todo.model.http.TodoDto
import com.example.todo.service.TodoService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Api(description = "일정관리")
@RestController
@RequestMapping("/api/todo")
class TodoApiController (val todoService: TodoService){
    //TODO:Create = 201 + ResponseEntity
    //C
    @PostMapping(path=[""])
    fun create(@RequestBody todoDto: TodoDto): TodoDto? {
        return todoService.create(todoDto)
    }
    //R
    @ApiOperation(value = "일정확인",notes = "일정확인 GET API")
    @GetMapping(path=[""])
    fun read(@ApiParam(value = "index")
             @RequestParam(required = false) index:Int?): ResponseEntity<Any?> {
        return index?.let {
            todoService.read(it)
        }?.let {
            return ResponseEntity.ok(it);
        } ?:kotlin.run {
            return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .header(HttpHeaders.LOCATION,"/api/todo/all")
                .build()
        }
    }

    @GetMapping(path=["/all"])
    fun readAll(): MutableList<TodoDto> {
        return todoService.readAll()
    }
    //TODO:update = 200 + ResponseEntity
    //U
    @PutMapping(path=[""])
    fun update(@RequestBody todoDto: TodoDto): TodoDto? {
        return todoService.update(todoDto)
    }
    //D
    @DeleteMapping(path = ["/{index}"])
    fun delete(@PathVariable(name = "index") _index:Int):ResponseEntity<Any?>{
        if(!todoService.delete(_index)){
            return ResponseEntity.status(500).build()
        }
        return ResponseEntity.ok().build()
    }
}