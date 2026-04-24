package com.example.littlelemonmenu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun LittleLemonApp(userPreferencesRepository: UserPreferencesRepository) {
    val navController = rememberNavController()
    var startDestination by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        startDestination =
            if (userPreferencesRepository.isOnboardingCompleted()) AppDestinations.HOME else AppDestinations.ONBOARDING
    }

    if (startDestination == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
        return
    }

    NavHost(
        navController = navController,
        startDestination = startDestination!!,
    ) {
        composable(AppDestinations.ONBOARDING) {
            OnboardingScreen(
                prefs = userPreferencesRepository,
                onRegistered = {
                    navController.navigate(AppDestinations.HOME) {
                        popUpTo(AppDestinations.ONBOARDING) { inclusive = true }
                    }
                },
            )
        }
        composable(AppDestinations.HOME) {
            HomeScreen(
                onProfile = { navController.navigate(AppDestinations.PROFILE) },
            )
        }
        composable(AppDestinations.PROFILE) {
            ProfileScreen(
                prefs = userPreferencesRepository,
                onBack = { navController.popBackStack() },
                onLoggedOut = {
                    navController.navigate(AppDestinations.ONBOARDING) {
                        popUpTo(AppDestinations.HOME) { inclusive = true }
                    }
                },
            )
        }
    }
}
