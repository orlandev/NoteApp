package com.ondev.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ondev.noteapp.data.local.model.NoteEntity
import com.ondev.noteapp.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    NoteApp()
                }
            }
        }
    }
}

@Composable
fun NoteApp(
    viewModel: SharedViewModel = hiltViewModel()
) {

    val allNotes = viewModel.allNotes.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        NoteList(
            allNotes = allNotes, modifier = Modifier
                .fillMaxSize()
                .weight(8f)
        )
        InputForm(
            modifier = Modifier
                .fillMaxSize()
                .weight(4f), viewModel
        )
    }
}

@Composable
fun InputForm(modifier: Modifier = Modifier, viewModel: SharedViewModel? = null) {

    val noteTitle = remember {
        mutableStateOf(TextFieldValue())
    }

    val noteMessage = remember {
        mutableStateOf(TextFieldValue())
    }

    Card(modifier = modifier) {

        Row(modifier = Modifier.fillMaxSize(),verticalAlignment = Alignment.Bottom) {
            Column(modifier = Modifier
                .fillMaxSize()
                .weight(8f)) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    value = noteTitle.value,
                    onValueChange = { noteTitle.value = it })
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    value = noteMessage.value,
                    onValueChange = { noteMessage.value = it })
            }

            Button(modifier = Modifier.weight(3f), onClick = {
                val noteNow = NoteEntity(
                    title = noteTitle.value.text,
                    message = noteMessage.value.text,
                    date = System.currentTimeMillis()
                )
                viewModel?.onInsert(noteEntity = noteNow)
            }) {
                Text(text = "Add Note")
            }

        }

    }

}

@Composable
fun NoteList(modifier: Modifier = Modifier, allNotes: State<List<NoteEntity>>) {
    LazyColumn(modifier = modifier) {
        items(allNotes.value) { note ->
            NoteCard(title = note.title, message = note.message)
        }
    }
}


@Composable
fun NoteCard(title: String, message: String) {
    Card(
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            Modifier
                .padding(8.dp),
        ) {
            Text(text = title, overflow = TextOverflow.Ellipsis, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = message,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowNoteCard() {

    NoteCard(
        title = "kfjekwef",
        message = "JEFJergerg reg erg ergerg er ge rg erg erg er g erg erg er g erg erg er ge rg erg er gerg er ge rg    erg erg re ger greg re greg erg erg erg ewfwefwefefwefwefwefwefE"
    )

}

@Preview(showBackground = true)
@Composable
fun ShowNoteInputForm() {

    InputForm(modifier = Modifier.fillMaxSize())

}