package com.epiderai.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.epiderai.app.ui.theme.MediCarePrimary

data class Subscription(val name: String, val price: Double, val features: List<String>)

@Composable
fun ProfileScreen() {
    val subscriptions = listOf(
        Subscription("Free", 0.0, listOf("Basic analysis", "Limited history")),
        Subscription("Basic", 4.99, listOf("Advanced analysis", "Full history", "Doctor chat")),
        Subscription("Premium", 9.99, listOf("All Basic features", "Personalized recommendations"))
    )
    var selectedSubscription by remember { mutableStateOf(subscriptions[0]) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Profile Settings", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = "Epider User",
            onValueChange = {},
            label = { Text("Display Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Subscriptions", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(subscriptions) { subscription ->
                SubscriptionCard(
                    subscription = subscription,
                    isSelected = subscription == selectedSubscription,
                    onSelect = { selectedSubscription = subscription }
                )
            }
        }
    }
}

@Composable
fun SubscriptionCard(subscription: Subscription, isSelected: Boolean, onSelect: () -> Unit) {
    Card(
        modifier = Modifier.width(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MediCarePrimary else MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                subscription.name,
                style = MaterialTheme.typography.titleLarge,
                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "$${subscription.price}/month",
                style = MaterialTheme.typography.titleMedium,
                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(16.dp))
            subscription.features.forEach {
                Text(
                    text = "• $it",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onSelect,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.surface else MediCarePrimary
                )
            ) {
                Text(
                    if (isSelected) "Selected" else "Select",
                    color = if (isSelected) MediCarePrimary else MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}
