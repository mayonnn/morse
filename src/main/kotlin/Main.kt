import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.sql.DriverManager.println

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Morse") {
        var currentScene by remember { mutableStateOf("menu") }

        when(currentScene) {
            "menu" -> MenuScreen { currentScene = it }
            "alphabet" -> AlphabetScreen { currentScene = "menu" }
            "testMode" -> TestModeScreen { currentScene = "menu" }
            "learnMode" -> LearnModeScreen { currentScene = "menu" }
            "translator" -> TranslatorScreen { currentScene = "menu" }
        }
    }
}

@Composable
fun MenuScreen(onSelect: (String) -> Unit){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "hi",
                style = TextStyle(
                    color = Color.Blue,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Button(onClick = { onSelect("alphabet") }) {
                Text("Alphabet")
            }
            Button(onClick = { onSelect("testMode") }) {
                Text("Test Mode")
            }
            Button(onClick = { onSelect("learnMode") }) {
                Text("Learn Mode")
            }
            Button(onClick = { onSelect("translator") }) {
                Text("Translator")
            }
        }
    }
}

@Composable
fun AlphabetScreen(onBack: () -> Unit) {
    Alphabet().Show(onBack)
}

@Composable
fun TestModeScreen(onBack: () -> Unit) {
    TestMode().Show(onBack)
}

@Composable
fun LearnModeScreen(onBack: () -> Unit) {
    LearnMode().Show(onBack)
}

@Composable
fun TranslatorScreen(onBack: () -> Unit) {
    Translator().Show(onBack)
}