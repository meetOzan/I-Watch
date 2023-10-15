import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.mertozan.moviescompose.presentation.main.components.CustomText

@Composable
fun CustomToastMessage(
    message: String,
    modifier: Modifier = Modifier,
    toastBackgroundColor: Color = Color.Gray,
    toastShape: Shape = RoundedCornerShape(8.dp)
) {
    Box(
        modifier = modifier.then(Modifier.fillMaxSize()),
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = toastShape,
            backgroundColor = toastBackgroundColor
        ) {
            CustomText(text = message)
        }
    }
}
