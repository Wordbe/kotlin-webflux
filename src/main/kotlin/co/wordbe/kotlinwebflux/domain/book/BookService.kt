package co.wordbe.kotlinwebflux.domain.book

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.atomic.AtomicLong

@Service
class BookService {

    private val nextId = AtomicLong(0)

    val books = mutableListOf(
        Book(id = nextId.incrementAndGet(), name = "코틀린 인 액션", price = 30000),
        Book(id = nextId.incrementAndGet(), name = "HTTP 완벽 가이드", price = 40000),
    )

    fun getAll(): Flux<Book> {
        return books.toFlux()
//        return Flux.fromIterable(books)
    }

    fun get(id: Long): Mono<Book> {
        return books.find { id == it.id }.toMono()
//        return Mono.justOrEmpty(books.find { id == it.id })
    }

    fun add(request: Map<String, Any>): Mono<Book> {
        return Mono.just(request)
            .map {
                val book = Book(
                    id = nextId.incrementAndGet(),
                    name = it["name"].toString(),
                    price = it["price"] as Int
                )
                books.add(book)
                book
            }
    }

    fun delete(id: Long): Mono<Void> {
        return Mono.justOrEmpty(books.find { it.id == id })
            .map { books.remove(it) }
            .then()
    }
}