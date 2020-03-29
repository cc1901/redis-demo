package com.example.redis.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.time.LocalDate


@RedisHash("Position")
data class Position(
        @Id val ticker: String,
        val quantity: Long,
        val date : LocalDate
)