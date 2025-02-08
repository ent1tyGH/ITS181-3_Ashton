package com.griel.ashton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.griel.ashton.ui.theme.AshtonTheme

// Data class to hold image and string resource IDs
data class Conversation(val imageId: Int, val strId: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // List of Conversations
        val conversations = listOf(
            Conversation(R.drawable.weeh, R.string.weeh),
            Conversation(R.drawable.ayoko, R.string.ayawko),
            Conversation(R.drawable.gustoko, R.string.gustko),
            Conversation(R.drawable.wagyan, R.string.wagyan)
        )

        setContent {
            AshtonTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ConversationList(conversations, Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ConversationList(conversations: List<Conversation>, modifier: Modifier = Modifier) {
    Surface {
        LazyColumn(modifier = modifier.padding(16.dp)) {
            items(conversations) { conversation ->
                ConversationRow(conversation)
            }
        }
    }
}

@Composable
fun ConversationRow(conversation: Conversation) {
    Row(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(id = conversation.imageId),
            contentDescription = stringResource(id = R.string.description),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = stringResource(id = conversation.strId),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = Modifier
                .alignByBaseline()
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConversationList() {
    val conversations = listOf(
        Conversation(R.drawable.weeh, R.string.weeh),
        Conversation(R.drawable.ayoko, R.string.ayawko),
        Conversation(R.drawable.gustoko, R.string.gustko),
        Conversation(R.drawable.wagyan, R.string.wagyan)
    )

    AshtonTheme {
        ConversationList(conversations)
    }
}
