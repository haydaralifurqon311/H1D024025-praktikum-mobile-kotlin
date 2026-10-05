package com.pemmob.haydar.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemmob.haydar.data.model.Category
import com.pemmob.haydar.data.model.Product
import com.pemmob.haydar.network.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface ProductUiState {
    object Loading : ProductUiState
    data class Success(val categories: List<Category>, val products: List<Product>) : ProductUiState
    data class Error(val message: String) : ProductUiState
}

class ProductViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            _uiState.value = ProductUiState.Loading
            try {
                val categories = ApiClient.instance.getCategories()
                val products = ApiClient.instance.getProducts()

                val categoryMap = categories.associateBy { it.id }
                val mappedProducts = products.map { product ->
                    product.copy(category = categoryMap[product.category_id])
                }

                _uiState.value = ProductUiState.Success(categories, mappedProducts)
            } catch (e: Exception) {
                _uiState.value = ProductUiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}
