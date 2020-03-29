package com.example.redis.domain.repository

import com.example.redis.domain.model.Position
import org.springframework.data.repository.CrudRepository

interface PositionRepository: CrudRepository<Position, String>