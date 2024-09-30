package com.example.jonathansimen_multipaneshopping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.* // For layout components
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.* // For state management
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleContentPanesApp()
        }
    }
}

@Composable
fun SimpleContentPanesApp() {
    val windowInfo = calculateCurrentWindowInfo()
    val items = listOf("Pickled Oxalis Seeds", "Rock Samphire", "Ground Sumac Berries", "Berebere")
    var selectedItem by remember { mutableStateOf<String?>(null) }

    if (windowInfo.isWideScreen) {
        //Two panes for wide screens, left is the item list, right is the details
        Row(modifier = Modifier.fillMaxSize()) {
            TaskList(items = items, onItemSelected = { selectedItem = it }, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(16.dp))
            ItemDetailPane(item = selectedItem, modifier = Modifier.weight(1f))
        }
    } else {
        //if not wide screen, only display one pane at a time
        if (selectedItem == null) {
            TaskList(items = items, onItemSelected = { selectedItem = it }, modifier = Modifier.fillMaxSize())
        } else {
            ItemDetailPane(item = selectedItem, modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun TaskList(items: List<String>, onItemSelected: (String) -> Unit, modifier: Modifier = Modifier) {
    //Function to display the list of items pane
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Shopping Items",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            //lazycolumn used for future functionality to add products
            items(items) { item ->
                //when an item in the list is clicked, set that to most recently clicked
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onItemSelected(item) }
                        .padding(8.dp),
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@Composable
fun ItemDetailPane(item: String?, modifier: Modifier = Modifier) {
    //Function to display the details pane
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (item) {
            //display info for most recently clicked item
            "Pickled Oxalis Seeds" -> {
                Text(
                    text = "Details for $item",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "$15,000, great for sprinkling on your beluga caviar to make the flavor pop.",
                    fontSize = 16.sp
                )

            }
            "Rock Samphire" -> {
                Text(
                    text = "Details for $item",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "$0, come to my house I will give you this for free, I hate how it tastes.",
                    fontSize = 16.sp
                )
            }
            "Ground Sumac Berries" -> {
                Text(
                    text = "Details for $item",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "$10, pretty normal spice, good for curry.",
                    fontSize = 16.sp
                )
            }
            "Berebere" -> {
                Text(
                    text = "Details for $item",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Priceless. There is only one ounce of this on earth. Only three people alive have even smelled it.",
                    fontSize = 16.sp
                )
            }
            else -> {
                // No item selected
                Text(
                    text = "THIS IS A GENERIC MESSAGE. PLEASE SELECT A PRODUCT!",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun calculateCurrentWindowInfo(): WindowInfo {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    // 600 dp breakpoint for large device
    val isWideScreen = screenWidth >= 600

    return WindowInfo(
        isWideScreen = isWideScreen
    )
}

data class WindowInfo(
    val isWideScreen: Boolean
)
