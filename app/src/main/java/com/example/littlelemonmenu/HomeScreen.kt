package com.example.littlelemonmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

private val HeroGreen = Color(0xFF495E57)
private val LemonYellow = Color(0xFFF4CE14)

@Composable
fun HomeScreen(
    onProfile: () -> Unit,
    viewModel: MenuViewModel = viewModel(),
) {
    val databaseMenuItems by viewModel.databaseMenuItems.collectAsStateWithLifecycle(emptyList())

    var orderMenuItems by remember { mutableStateOf(false) }
    var searchPhrase by remember { mutableStateOf("") }
    var selectedCategories by remember {
        mutableStateOf(setOf("Starters", "Mains", "Desserts", "Drinks"))
    }

    val sortedMenuItems =
        if (orderMenuItems) databaseMenuItems.sortedBy { it.title } else databaseMenuItems

    val menuItems =
        if (searchPhrase.isNotEmpty()) {
            sortedMenuItems.filter {
                it.title.contains(searchPhrase, ignoreCase = true) ||
                    it.description.contains(searchPhrase, ignoreCase = true)
            }
        } else {
            sortedMenuItems
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEDEFEE)),
    ) {
        Surface(color = Color.White) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "LITTLE LEMON",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = HeroGreen,
                    modifier = Modifier.padding(start = 8.dp),
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = onProfile) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Profile",
                        tint = Color(0xFF333333),
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Surface(
                color = HeroGreen,
                shape = RoundedCornerShape(12.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(
                            text = "Little Lemon",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = LemonYellow,
                        )
                        Text(
                            text = "Chicago",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                        )
                        Text(
                            text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                        )
                        OutlinedTextField(
                            value = searchPhrase,
                            onValueChange = { searchPhrase = it },
                            placeholder = { Text("Enter search phrase", color = Color(0xFF888888)) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp),
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(100.dp)
                            .background(Color.Black.copy(alpha = 0.2f), RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Restaurant,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(56.dp),
                        )
                    }
                }
            }

            Button(onClick = { orderMenuItems = true }) {
                Text("Tap to Order By Name")
            }

            Text(
                text = "ORDER FOR DELIVERY!",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Filter by category (tap to include / exclude)",
                style = MaterialTheme.typography.labelLarge,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                listOf("Starters", "Mains", "Desserts", "Drinks").forEach { cat ->
                    FilterChip(
                        selected = cat in selectedCategories,
                        onClick = {
                            selectedCategories =
                                if (cat in selectedCategories) selectedCategories - cat else selectedCategories + cat
                        },
                        label = { Text(cat) },
                    )
                }
            }

            MenuItemsList(
                menuItems = menuItems,
                selectedCategories = selectedCategories,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
            )
        }
    }
}
