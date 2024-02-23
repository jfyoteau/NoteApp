package jfyoteau.noteapp.note.presentation.notelist.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import jfyoteau.noteapp.note.presentation.notelist.state.NoteListState
import jfyoteau.noteapp.note.presentation.notelist.state.NoteListUiEvent
import kotlinx.coroutines.flow.collectLatest
import noteapp.core.resources.generated.resources.Res
import noteapp.core.resources.generated.resources.add_note
import noteapp.core.resources.generated.resources.icon_add
import noteapp.core.resources.generated.resources.icon_sort
import noteapp.core.resources.generated.resources.note_deleted
import noteapp.core.resources.generated.resources.undo
import noteapp.core.resources.generated.resources.your_note
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun NoteListScreen(state: NoteListState) {
    val uiState by state.uiState.subscribeAsState()
    @Suppress("SpellCheckingInspection") val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(key1 = Unit) {
        state.uiEvent.collectLatest { event ->
            when (event) {
                is NoteListUiEvent.NoteDeleted -> {
                    val result = snackbarHostState.showSnackbar(
                        message = getString(Res.string.note_deleted),
                        actionLabel = getString(Res.string.undo),
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        state.restoreNote()
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    state.addNote()
                }
            ) {
                Icon(
                    painter = painterResource(Res.drawable.icon_add),
                    contentDescription = stringResource(Res.string.add_note),
                )
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(Res.string.your_note),
                    style = MaterialTheme.typography.headlineMedium
                )
                IconButton(
                    onClick = {
                        state.toggleOrderSection()
                    },
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.icon_sort),
                        contentDescription = null,
                    )
                }
            }

            AnimatedVisibility(
                visible = uiState.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    noteOrder = uiState.noteOrder,
                    onOrderChange = {
                        state.getNodes(it)
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 48.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(uiState.notes) { note ->
                    NoteItem(
                        note = note,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                state.editNote(note = note)
                            },
                        onDeleteClick = {
                            state.deleteNote(note)
                        }
                    )
                }
            }
        }
    }
}
