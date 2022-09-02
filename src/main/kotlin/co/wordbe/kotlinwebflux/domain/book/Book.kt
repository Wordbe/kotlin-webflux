package co.wordbe.kotlinwebflux.domain.book

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class Book(
    @Id
    val id: Long? = null,

    val name: String,

    val price: Int,
)