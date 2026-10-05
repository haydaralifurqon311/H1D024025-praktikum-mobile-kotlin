package com.pemmob.haydar.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.pemmob.haydar.R
import com.pemmob.haydar.data.model.Category
import com.pemmob.haydar.data.model.Product
import com.pemmob.haydar.ui.viewmodel.ProductUiState
import com.pemmob.haydar.ui.viewmodel.ProductViewModel
import com.pemmob.haydar.util.JualanConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailProductScreen(
    productId: Int,
    navController: NavController? = null,
    viewModel: ProductViewModel = viewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    var quantity by rememberSaveable { mutableStateOf(1) }

    when (val state = uiState) {
        is ProductUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is ProductUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: ${state.message}", color = MaterialTheme.colorScheme.error)
            }
        }
        is ProductUiState.Success -> {
            val product = state.products.find { it.id == productId }
            StatelessDetailProduct(
                product = product,
                isLoading = false,
                quantity = quantity,
                onQuantityChange = { quantity = it },
                onBackClick = { navController?.popBackStack() },
                onAddToCartClick = {
                    Toast.makeText(context, "Dimasukkan: $quantity", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatelessDetailProduct(
    product: Product?,
    isLoading: Boolean,
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    onBackClick: () -> Unit,
    onAddToCartClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Produk") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.back_icon),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (product != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(state = rememberScrollState())
            ) {
                val imageUrl = if (product.img == "dummy_product") {
                    null
                } else {
                    "${JualanConstants.BASE_URL}img/${product.img}"
                }

                AsyncImage(
                    model = imageUrl ?: R.drawable.dummy_product,
                    contentDescription = product.name,
                    placeholder = painterResource(id = R.drawable.dummy_product),
                    error = painterResource(id = R.drawable.dummy_product),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                )

                Column(modifier = Modifier.padding(all = 16.dp)) {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Rp ${product.price}",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Deskripsi", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(product.description ?: "-")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Stok: ${product.stock}")

                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Jumlah Beli")
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            FilledTonalIconButton(
                                onClick = { if (quantity > 1) onQuantityChange(quantity - 1) },
                                enabled = quantity > 1
                            ) { Text("-") }

                            Text(quantity.toString(), modifier = Modifier.padding(horizontal = 16.dp))

                            FilledTonalIconButton(
                                onClick = { if (product.stock > 0 && quantity < product.stock) onQuantityChange(quantity + 1) },
                                enabled = product.stock > 0 && quantity < product.stock
                            ) { Text("+") }
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = onAddToCartClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        enabled = product.stock > 0 && quantity > 0
                    ) {
                        Text("Tambah ke Keranjang")
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Produk tidak ditemukan.")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailProduct() {
    StatelessDetailProduct(
        product = Product(
            id = 1,
            category_id = 1,
            category = Category(1, "Makanan", "Desc", 5),
            name = "Kripik Singkong",
            description = "Enak",
            price = 15000.0,
            stock = 10,
            img = "dummy_product"
        ),
        isLoading = false,
        quantity = 1,
        onQuantityChange = {},
        onBackClick = {},
        onAddToCartClick = {}
    )
}
