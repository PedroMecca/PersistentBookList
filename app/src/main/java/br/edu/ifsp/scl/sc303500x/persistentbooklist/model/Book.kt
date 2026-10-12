package br.edu.ifsp.scl.sc303500x.persistentbooklist.model

import java.io.Serializable

data class Book (
    val id: Int = INVALID_BOOK_ID,
    val title: String = "",
    val author: String = "",
    val genre: String = "",
    val year: Int = 0,
    val read: Boolean = false
): Serializable {
    companion object {
        const val INVALID_BOOK_ID = -1
    }
}