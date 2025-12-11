package com.epiderai.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class Doctor(
    val name: String,
    val specialty: String,
    val experience: Int,
    val degrees: String,
    val rating: Double
)

@Composable
fun BookingScreen() {
    val doctors = listOf(
        Doctor("Dr. Anika", "Dermatologist", 10, "MD, PhD", 4.9),
        Doctor("Dr. Finky", "Dermatologist", 15, "MD", 4.8),
        Doctor("Dr. Jenny", "Dermatologist", 8, "MD", 4.7),
        Doctor("Dr. Victor", "Dermatologist", 12, "MD, PhD", 4.9),
        Doctor("Dr. Layla", "Dermatologist", 5, "MD", 4.6),
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Top Doctors", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(doctors) { doctor ->
                DoctorCard(doctor = doctor)
            }
        }
    }
}

@Composable
fun DoctorCard(doctor: Doctor) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(doctor.name, style = MaterialTheme.typography.titleMedium)
                Text(doctor.specialty, style = MaterialTheme.typography.bodyMedium)
                Text("${doctor.experience} years of experience", style = MaterialTheme.typography.bodySmall)
                Text(doctor.degrees, style = MaterialTheme.typography.bodySmall)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFC107), modifier = Modifier.size(16.dp))
                    Text(" ${doctor.rating}", style = MaterialTheme.typography.bodySmall)
                }
            }
            Button(onClick = { /* TODO Book */ }) {
                Text("Book")
            }
        }
    }
}
