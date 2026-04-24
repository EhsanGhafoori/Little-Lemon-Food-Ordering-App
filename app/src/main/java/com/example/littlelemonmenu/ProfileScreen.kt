package com.example.littlelemonmenu

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    prefs: UserPreferencesRepository,
    onBack: () -> Unit,
    onLoggedOut: () -> Unit,
) {
    val firstName by prefs.firstName.collectAsStateWithLifecycle(initialValue = "")
    val lastName by prefs.lastName.collectAsStateWithLifecycle(initialValue = "")
    val email by prefs.email.collectAsStateWithLifecycle(initialValue = "")
    val savedPhone by prefs.phone.collectAsStateWithLifecycle(initialValue = "")
    var phone by remember(savedPhone) { mutableStateOf(savedPhone) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back to Home",
                )
            }
            Text(
                text = "LITTLE LEMON",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp),
            )
            Box(modifier = Modifier.size(48.dp))
        }
        Text(
            text = "Personal information",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
        )
        OutlinedTextField(
            value = firstName,
            onValueChange = {},
            readOnly = true,
            label = { Text("First name") },
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            value = lastName,
            onValueChange = {},
            readOnly = true,
            label = { Text("Last name") },
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            value = email,
            onValueChange = {},
            readOnly = true,
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone number") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                scope.launch {
                    prefs.savePhone(phone)
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Save changes")
        }
        Button(
            onClick = {
                scope.launch {
                    prefs.clearAll()
                    onLoggedOut()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4CE14)),
        ) {
            Text("Log out", color = Color.Black)
        }
    }
}
