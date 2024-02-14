package jfyoteau.noteapp.note.presentation.notedetail

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import jfyoteau.noteapp.note.domain.model.Note
import jfyoteau.noteapp.note.presentation.notedetail.composable.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen(component: NoteDetailComponent) {
    val state by component.state.subscribeAsState()

    val titleState = state.noteTitle
    val contentState = state.noteContent

    val noteBackgroundAnimatable = remember {
        Animatable(
            Color(state.noteColor)
        )
    }
    val scope = rememberCoroutineScope()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(key1 = true) {
        component.uiEvent.collectLatest { event ->
            when (event) {
                is NoteDetailComponent.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is NoteDetailComponent.UiEvent.SaveNote -> {
                    component.onEvent(NoteDetailEvent.Back)
                }
            }
        }
    }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Note")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            component.onEvent(NoteDetailEvent.Back)
                        }
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            component.onEvent(NoteDetailEvent.SaveNote)
                        },
                    ) {
                        Icon(imageVector = Icons.Default.Save, contentDescription = "Save note")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                ),
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = noteBackgroundAnimatable.value,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items(Note.noteColors) { colorValue ->
                    Box(
                        modifier = Modifier
                            .size(66.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .shadow(8.dp, CircleShape)
                                .clip(CircleShape)
                                .background(Color(colorValue))
                                .border(
                                    width = 3.dp,
                                    color = if (state.noteColor == colorValue) {
                                        Color.Black
                                    } else Color.Transparent,
                                    shape = CircleShape
                                )
                                .clickable {
                                    scope.launch {
                                        noteBackgroundAnimatable.animateTo(
                                            targetValue = Color(colorValue),
                                            animationSpec = tween(
                                                durationMillis = 500
                                            )
                                        )
                                    }
                                    component.onEvent(NoteDetailEvent.ChangeColor(colorValue))
                                }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(
                text = titleState.text,
                hint = "Enter title...",
                onValueChange = {
                    component.onEvent(NoteDetailEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    component.onEvent(NoteDetailEvent.ChangeTitleFocus(it))
                },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(
                text = contentState.text,
                hint = "Enter some content",
                onValueChange = {
                    component.onEvent(NoteDetailEvent.EnteredContent(it))
                },
                onFocusChange = {
                    component.onEvent(NoteDetailEvent.ChangeContentFocus(it))
                },
                isHintVisible = contentState.isHintVisible,
                textStyle = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}
