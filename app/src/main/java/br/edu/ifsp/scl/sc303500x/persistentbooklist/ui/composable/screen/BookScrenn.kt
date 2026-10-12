package br.edu.ifsp.scl.sc303500x.persistentbooklist.ui.composable.screen

import android.R.attr.enabled
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import br.edu.ifsp.scl.sc303500x.persistentbooklist.model.Book
import br.edu.ifsp.scl.sc303500x.persistentbooklist.model.Operation


@Composable
fun bookScreen (
    book: Book,
    operation: Operation,
    modifier: Modifier = Modifier,
    onSaveAndQuit: (Book) -> Unit
){

    var currentBook by rememberSaveable { mutableStateOf(book) }
    val isFieldsEnabled = operation == Operation.NEW_OR_EDIT

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = currentBook.title,
            label = {Text (text = "Title")},
            onValueChange =  {currentBook = currentBook.copy (title = it )},
            enabled = isFieldsEnabled,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = currentBook.author,
            label = {Text (text = "Author")},
            onValueChange =  {currentBook = currentBook.copy (title = it )},
            enabled = isFieldsEnabled,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = currentBook.genre,
            label = {Text (text = "Genre")},
            onValueChange =  {currentBook = currentBook.copy (title = it )},
            enabled = isFieldsEnabled,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = if (currentBook.year == 0) "" else currentBook.year.toString(),
            label = {Text(text = "Year") },
            onValueChange = { currentBook = currentBook.copy(year = it.toIntOrNull() ?: 0) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            enabled = isFieldsEnabled,
            modifier = Modifier.fillMaxWidth()
        )
        if (isFieldsEnabled) {
            Button(
                onClick = {onSaveAndQuit(currentBook)},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Save and Quit")
            }
        }
    }

}