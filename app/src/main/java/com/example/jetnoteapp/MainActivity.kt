package com.example.jetnoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetnoteapp.data.NotesDataSource
import com.example.jetnoteapp.model.Note
import com.example.jetnoteapp.screen.NoteScreen
import com.example.jetnoteapp.screen.NoteViewModel
import com.example.jetnoteapp.ui.theme.JetNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetNoteAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val noteViewModel: NoteViewModel by viewModels()
                    NotesApp(noteViewModel)
                }
            }
        }
    }
}

@Composable
fun NotesApp(noteViewModel: NoteViewModel = viewModel()) {
    val notesList = noteViewModel.getAllNotes()

    NoteScreen(
        notes = notesList,
        onRemoveNote = { noteViewModel.removeNote(it) },
        onAddNote = { noteViewModel.addNote(it) }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetNoteAppTheme {

    }
}