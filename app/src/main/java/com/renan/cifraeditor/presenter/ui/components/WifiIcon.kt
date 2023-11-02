import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun IconWifiFrequency(level: Int) {
    val size = 50.dp
    val pathFirstLine by remember {
        mutableStateOf(Path())
    }
    val pathSecondLine by remember {
        mutableStateOf(Path())
    }
    val pathThirdLine by remember {
        mutableStateOf(Path())
    }
    Canvas(
        modifier = Modifier.padding(end = 20.dp)
    ) {
        ///firstLine
        pathFirstLine.moveTo(0f, (size.value * 0.5).toFloat())
        pathFirstLine.lineTo(0f, (size.value * 0.5).toFloat())
        pathFirstLine.quadraticBezierTo(
            (size.value * 0.5).toFloat(), 4f, size.value, (size.value * 0.5).toFloat()
        )
        ///secondLine
        pathSecondLine.moveTo((size.value * 0.2).toFloat(), (size.value * 0.8).toFloat())
        pathSecondLine.lineTo((size.value * 0.2).toFloat(), (size.value * 0.8).toFloat())
        pathSecondLine.quadraticBezierTo(
            ((size.value) * 0.5).toFloat(),
            30f,
            (size.value * 0.8).toFloat(),
            (size.value * 0.8).toFloat()
        )
        //thirdLine
        pathThirdLine.moveTo((size.value * 0.4).toFloat(), size.value)
        pathThirdLine.lineTo((size.value * 0.4).toFloat(), size.value)
        pathThirdLine.quadraticBezierTo(
            (size.value * 0.5).toFloat(),
            45f,
            (size.value * 0.6).toFloat(),
            size.value
        )
        drawPath(
            pathFirstLine,
            color = if (level >= -40) Color.White else Color.White.copy(alpha = 0.6f),
            style = Stroke(
                width = 5f, cap = StrokeCap.Round
            )
        )
        drawPath(
            pathSecondLine,
            color = if (level >= -70) Color.White else Color.White.copy(alpha = 0.6f),
            style = Stroke(
                width = 5f, cap = StrokeCap.Round
            )
        )
        drawPath(
            pathThirdLine, color = Color.White, style = Stroke(
                width = 5f, cap = StrokeCap.Round
            )
        )
    }
}
