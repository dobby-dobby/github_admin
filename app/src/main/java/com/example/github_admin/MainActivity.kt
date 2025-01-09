package com.example.github_admin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.github_admin.ui.theme.GithubadminTheme
import com.example.github_admin.view.DetailUserScreen
import com.example.github_admin.view.ListUserScreen
import com.example.github_admin.viewmodel.DetailUserViewModel
import com.example.github_admin.viewmodel.ListUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GithubadminTheme {
                Box(Modifier.safeDrawingPadding()) {
                    MyNavGraphCenter()
                }
            }
        }
    }
}

@Composable
fun MyNavGraphCenter() {
    val navController = rememberNavController()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NavGraph(navController = navController)
    }
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "list_user") {
        composable("list_user") {
            val listUserViewModel: ListUserViewModel = hiltViewModel()
            ListUserScreen(
                navController,
                listUserViewModel = listUserViewModel
            )
        }
        composable("detail_user/{userLogin}") { backStackEntry ->
            val detailUserViewModel: DetailUserViewModel = hiltViewModel()
            val userLogin = backStackEntry.arguments?.getString("userLogin")

            if (userLogin != null) {
                DetailUserScreen(userLogin, detailUserViewModel, onGoBack = { navController.navigateUp()})
            }
        }
    }
}