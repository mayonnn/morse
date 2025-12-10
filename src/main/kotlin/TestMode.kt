import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class TestMode {
    @Composable
    fun Show(onBack: () -> Unit) {
        Button(onClick = { onBack() }) {
            Text("Back")
        }
    }
}