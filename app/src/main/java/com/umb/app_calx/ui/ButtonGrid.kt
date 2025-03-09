import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import com.umb.app_calx.theme.ButtonColor
import com.umb.app_calx.theme.OrangeButtonColor

@Composable
fun ButtonGrid(input: String, onButtonClick: (String) -> Unit) {
    val buttons = listOf(
        listOf("C", "(", ")", "/"),
        listOf("7", "8", "9", "*"),
        listOf("4", "5", "6", "-"),
        listOf("1", "2", "3", "+"),
        listOf("0", ".", "=")
    )

    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        for (row in buttons) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                for (btn in row) {
                    val buttonColor = when (btn) {
                        "/", "*", "-", "+", "=" -> OrangeButtonColor
                        else -> ButtonColor
                    }

                    val displayText = when (btn) {
                        "C" -> if (input.isNotEmpty()) Icons.Default.Delete else Icons.Default.Refresh
                        else -> null
                    }

                    Button(
                        onClick = { onButtonClick(btn) },
                        modifier = Modifier.size(80.dp).padding(4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = buttonColor, contentColor = Color.White)
                    ) {
                        if (displayText != null) {
                            Icon(imageVector = displayText, contentDescription = "Borrar")
                        } else {
                            Text(text = btn, fontSize = 24.sp)
                        }
                    }
                }
            }
        }
    }
}
