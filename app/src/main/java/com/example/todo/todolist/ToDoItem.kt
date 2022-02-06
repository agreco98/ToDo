package com.example.todo.todolist

import android.graphics.drawable.Icon
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todo.data.model.ToDo
import com.example.todo.util.Screen
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@Composable
fun ToDoItem(
    toDo: ToDo,
    onDelete: (ToDo) -> Unit,
    onChecked: (Boolean) -> Unit,
    modifier: Modifier= Modifier
    ) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 0.dp,
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = modifier
                    .weight(1f)
            ) {
                Text(
                    text = toDo.title,
                    style = MaterialTheme.typography.h6
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = toDo.description,
                    style = MaterialTheme.typography.h6,
                    maxLines = 2
                )
            }
            Checkbox(
                checked = toDo.isFinished,
                onCheckedChange = {  onChecked(it)  }
            )
            Spacer(Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        onDelete(toDo)
                    }
                    .padding(12.dp)
                    .size(24.dp)
            )
        }
    }
}

private fun Modifier.swipeToDismiss(
    onDismissed: () -> Unit
): Modifier = composed {
    val offsetX = remember { Animatable(0f) }
    pointerInput(Unit) {

        val decay = splineBasedDecay<Float>(this)

        coroutineScope {
            while (true) {

                val pointerId = awaitPointerEventScope { awaitFirstDown().id }
                offsetX.stop()

                val velocityTracker = VelocityTracker()

                awaitPointerEventScope {
                    horizontalDrag(pointerId) { change ->

                        val horizontalDragOffset = offsetX.value + change.positionChange().x
                        launch{
                            offsetX.snapTo(horizontalDragOffset)
                        }

                        velocityTracker.addPosition(change.uptimeMillis, change.position)
                        change.consumePositionChange()
                    }
                }

                val velocity = velocityTracker.calculateVelocity().x
                val targetOffsetX = decay.calculateTargetValue(offsetX.value, velocity)

                offsetX.updateBounds(
                    lowerBound = -size.width.toFloat(),
                    upperBound = size.width.toFloat()
                )
                launch {
                    if(targetOffsetX.absoluteValue <= size.width) {
                        offsetX.animateTo(targetValue = 0f, initialVelocity = velocity)
                    } else {
                        offsetX.animateDecay(velocity, decay)
                        onDismissed()
                    }
                }
            }
        }
    }
        .offset {
            IntOffset(offsetX.value.roundToInt(), 0)
        }
}