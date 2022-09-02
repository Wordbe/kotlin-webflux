package co.wordbe.kotlinwebflux.domain.book

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class BookController(
    private val bookService: BookService
) {

    @GetMapping("/books")
    fun getAll(): Flux<Book> {
        return bookService.getAll()
    }

    @GetMapping("/books/{id}")
    fun get(@PathVariable id: Long): Mono<Book> {
        return bookService.get(id)
    }

    @PostMapping("/books")
    fun add(@RequestBody request: Map<String, Any>) : Mono<Book> {
        return bookService.add(request)
    }

    @DeleteMapping("/books/{id}")
    fun delete(@PathVariable id: Long) : Mono<Void> {
        return bookService.delete(id)
    }
}

@RestController
@RequestMapping("/v2/books")
class BookV2Controller(
    private val bookRepository: BookRepository
) {
    @GetMapping("/{name}")
    fun getByName(@PathVariable name: String) : Mono<Book> {
        return bookRepository.findByName(name)
    }

    @PostMapping
    fun create(@RequestBody map: Map<String, Any>) : Mono<Book> {
        val book = Book(
            name = map["name"].toString(),
            price = map["price"] as Int
        )
        return bookRepository.save(book)
    }
}