package com.example.redis.`interface`

import com.example.redis.domain.model.Position
import java.time.LocalDate


data class PositionDto(
        var ticker: String = "",
        var quantity: Long = 0,
        var date: LocalDate = LocalDate.now()
) {
    fun toDomain() = Position(
            ticker = ticker,
            quantity = quantity,
            date = date
    )
}

fun Position.toDto() = PositionDto(
        ticker = this.ticker,
        quantity = this.quantity,
        date = this.date

)

