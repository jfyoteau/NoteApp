package jfyoteau.noteapp.note.presentation.notedetail.screen

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
import jfyoteau.noteapp.note.presentation.notedetail.state.NoteDetailState
import jfyoteau.noteapp.note.presentation.notedetail.state.NoteDetailUiEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import noteapp.feature.note.generated.resources.Res
import noteapp.feature.note.generated.resources.back
import noteapp.feature.note.generated.resources.enter_content
import noteapp.feature.note.generated.resources.enter_title
import noteapp.feature.note.generated.resources.icon_back
import noteapp.feature.note.generated.resources.icon_save
import noteapp.feature.note.generated.resources.note
import noteapp.feature.note.generated.resources.save_note
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun NoteDetailScreen(state: NoteDetailState) {
    val uiState by state.uiState.subscribeAsState()

    val titleState = uiState.noteTitle
    val contentState = uiState.noteContent

    val noteBackgroundAnimatable = remember {
        Animatable(
            Color(uiState.noteColor)
        )
    }
    val scope = rememberCoroutineScope()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(key1 = Unit) {
        state.uiEvent.collectLatest { event ->
            when (event) {
                is NoteDetailUiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is NoteDetailUiEvent.SaveNote -> {
                    state.back()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(Res.string.note))
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            state.back()
                        }
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.icon_back),
                            contentDescription = stringResource(Res.string.back),
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            state.saveNote()
                        },
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.icon_save),
                            contentDescription = stringResource(Res.string.save_note),
                        )
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
                                    color = if (uiState.noteColor == colorValue) {
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
                                    state.changeColor(colorValue)
                                }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(
                text = titleState.text,
                hint = stringResource(Res.string.enter_title),
                onValueChange = {
                    state.enterTitle(it)
                },
                onFocusChange = {
                    state.changeTitleFocus(it)
                },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(
                text = contentState.text,
                hint = stringResource(Res.string.enter_content),
                onValueChange = {
                    state.enterContent(it)
                },
                onFocusChange = {
                    state.changeContentFocus(it)
                },
                isHintVisible = contentState.isHintVisible,
                textStyle = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}
