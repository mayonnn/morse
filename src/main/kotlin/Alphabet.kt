import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.io.File

class Alphabet {
    @Composable
    fun Show(onBack: () -> Unit) {
        val fullAlphabet = LoadAlphabet()
        val letterAlphabet = fullAlphabet.filter { it.key[0].isLetter() }
        val numberAlphabet = fullAlphabet.filter { it.key[0].isDigit() }
        val specialAlphabet = fullAlphabet.filter { !it.key[0].isLetterOrDigit() }

        var alphabetScene by remember { mutableStateOf("letters") }
        val displayedAlphabet = when (alphabetScene) {
            "letters" -> letterAlphabet
            "numbers" -> numberAlphabet
            "special" -> specialAlphabet
            else -> letterAlphabet
        }

        Button(onClick = { onBack() }) {
            Text("Back")
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column {
                displayedAlphabet.forEach { (letter, morse) ->
                    Row {
                        Text(letter + ":  " + morse)
                    }
                }
            }
            var nextScene = "letters"
            if (alphabetScene == "letters") nextScene = "numbers"
            else if (alphabetScene == "numbers") nextScene = "special"
            else alphabetScene = "special"

            Button(onClick = {alphabetScene = nextScene}, modifier = Modifier.align(Alignment.BottomEnd)) {
                Text("Next")
            }
        }

    }

    fun LoadAlphabet(): Map<String, String> {
        val inputStream = this::class.java.getResourceAsStream("/alphabet.json")
        val jsonText = inputStream!!.bufferedReader().readText()
        val result = Json.decodeFromString<Map<String, String>>(jsonText)

        return result
    }
}