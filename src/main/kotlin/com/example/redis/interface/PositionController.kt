package com.example.redis.`interface`

import com.example.redis.domain.model.Position
import com.example.redis.domain.repository.PositionRepository
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


@RestController
class PositionController(val positionRepository: PositionRepository){

    @PostMapping("/position", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun insertPosition(@RequestBody position: PositionDto) {
        positionRepository.save(position.toDomain())
    }

    @GetMapping("/position")
    @ResponseBody
    fun getPosition() : List<PositionDto> {
        return positionRepository.findAll().toList().map { it.toDto() }
    }
}
