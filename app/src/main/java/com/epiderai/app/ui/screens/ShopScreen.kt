package com.epiderai.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.epiderai.app.ui.theme.MediCarePrimary
import com.epiderai.app.ui.theme.MediCareSurface

@Composable
fun ShopScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Pharmacy", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(10) { index ->
                ProductCard(name = "Product ${index + 1}", price = "$29.99")
            }
        }
    }
}

@Composable
fun ProductCard(name: String, price: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MediCareSurface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                Text("Image", color = MaterialTheme.colorScheme.onBackground)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(name, style = MaterialTheme.typography.titleMedium)
            Text(price, style = MaterialTheme.typography.bodyLarge)
            Button(
                onClick = { /* TODO Buy */ },
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MediCarePrimary)
            ) {
                Icon(Icons.Outlined.AddShoppingCart, contentDescription = "Add to cart", modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Add")
            }
        }
    }
}
