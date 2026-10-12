package br.edu.ifsp.scl.sc303500x.persistentbooklist

import androidx.lifecycle.ViewModel
import br.edu.ifsp.scl.sc303500x.persistentbooklist.model.Book
import br.edu.ifsp.scl.sc303500x.persistentbooklist.model.Book.Companion.INVALID_BOOK_ID
import br.edu.ifsp.scl.sc303500x.persistentbooklist.model.Operation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.collections.emptyList

class BookViewModel : ViewModel () {

    private val _currentBook = MutableStateFlow(Book())
    val currentBook: StateFlow<Book> = _currentBook.asStateFlow()

    private val _bookList: MutableStateFlow<List<Book>> = MutableStateFlow(emptyList())
    val bookList: StateFlow<List<Book>> = _bookList.asStateFlow()

    private var nextId = 0

    fun saveContact (book: Book) {
        if(book.id == INVALID_BOOK_ID){
            _bookList.update { it + book.copy(id = nextId++) }
        } else {
            _bookList.update { list -> list.map { if (it.id == book.id) book else it } }
        }

    }

    private val _operation: MutableStateFlow<Operation> = MutableStateFlow(Operation.NEW_OR_EDIT)
    val operation: StateFlow<Operation> = _operation.asStateFlow()

    fun updateCurrentBookAndOperation (book: Book, operation: Operation){
        _currentBook.value = book
        _operation.value = operation
    }

    fun removeContact(book: Book) {
        _bookList.update { list -> list.filterNot { it.id == book.id } }
    }

}