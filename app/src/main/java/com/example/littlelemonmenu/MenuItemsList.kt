package com.example.littlelemonmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

private val categoryOrder = listOf("Starters", "Mains", "Desserts", "Drinks")

@Composable
fun MenuItemsList(
    menuItems: List<MenuItemEntity>,
    selectedCategories: Set<String>,
    modifier: Modifier = Modifier,
) {
    val visible = menuItems.filter { it.category in selectedCategories }
    val grouped = categoryOrder.mapNotNull { cat ->
        val rows = visible.filter { it.category == cat }
        if (rows.isEmpty()) null else cat to rows
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        grouped.forEach { (category, rows) ->
            item(key = "header-$category") {
                Text(
                    text = category,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                )
            }
            items(rows, key = { it.id }) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
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
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = item.description,
                                style = MaterialTheme.typography.bodySmall,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Text(
                                text = "$${String.format("%.2f", item.price)}",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                        Box(
                            modifier = Modifier
                                .padding(start = 12.dp)
                                .size(72.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFE0E0E0)),
                            contentAlignment = Alignment.Center,
                        ) {
                            Image(
                                painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                                contentDescription = "${item.title} image",
                                modifier = Modifier.size(56.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}
