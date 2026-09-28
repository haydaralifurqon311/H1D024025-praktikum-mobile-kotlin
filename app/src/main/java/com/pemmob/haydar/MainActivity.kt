package com.pemmob.haydar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pemmob.haydar.ui.screen.BasicInfoScreen
import com.pemmob.haydar.ui.screen.DaftarProdukScreen
import com.pemmob.haydar.ui.screen.DetailProductScreen
import com.pemmob.haydar.ui.screen.HubungiKamiScreen
import com.pemmob.haydar.ui.theme.JualanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JualanTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "daftar_produk") {
                        composable("daftar_produk") {
                            DaftarProdukScreen(navController = navController)
                        }
                        composable(
                            route = "detail/{productId}",
                            arguments = listOf(navArgument("productId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
                            DetailProductScreen(productId = productId, navController = navController)
                        }
                        composable("hubungi_kami") {
                            HubungiKamiScreen(navController = navController)
                        }
                        composable("form_screen") {
                            HubungiKamiScreen(navController = navController)
                        }
                        composable("basic_info") {
                            BasicInfoScreen(
                                onNavigateToContact = { navController.navigate(route = "hubungi_kami") }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JualanTheme {
        Greeting("Android")
    }
}
