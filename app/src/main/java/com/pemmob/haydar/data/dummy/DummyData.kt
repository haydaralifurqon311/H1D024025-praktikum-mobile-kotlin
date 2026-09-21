package com.pemmob.haydar.data.dummy

import com.pemmob.haydar.data.model.Category
import com.pemmob.haydar.data.model.Product

object DummyData {
    val categories = listOf(
        Category(id = 1, name = "Makanan", description = "Aneka Makanan Lokal", products_count = 5),
        Category(id = 2, name = "Minuman", description = "Minuman Segar", products_count = 5),
        Category(id = 3, name = "Kerajinan", description = "Kerajinan Tangan", products_count = 5)
    )

    val products = listOf(
        // Makanan
        Product(
            id = 1,
            category_id = 1,
            category = categories[0],
            name = "Kripik Singkong",
            description = "Kripik gurih",
            price = 15000.0,
            stock = 50,
            img = "img_kripik_singkong"
        ),
        Product(id = 2, category_id = 1, category = categories[0], name = "Mendoan", description = "Mendoan asli Purwokerto", price = 20000.0, stock = 30, img = "img_mendoan"),
        Product(id = 3, category_id = 1, category = categories[0], name = "Sale Pisang", description = "Sale pisang manis", price = 25000.0, stock = 20, img = "img_sale_pisang"),
        Product(id = 4, category_id = 1, category = categories[0], name = "Getuk Goreng", description = "Getuk khas", price = 30000.0, stock = 40, img = "img_getuk_goreng"),
        Product(id = 5, category_id = 1, category = categories[0], name = "Nopia", description = "Nopia rasa coklat", price = 22000.0, stock = 60, img = "img_nopia"),

        // Minuman
        Product(id = 6, category_id = 2, category = categories[1], name = "Es Dawet", description = "Dawet segar", price = 10000.0, stock = 100, img = "img_es_dawet"),
        Product(id = 7, category_id = 2, category = categories[1], name = "Kopi Robusta", description = "Kopi bubuk", price = 45000.0, stock = 20, img = "img_kopi_robusta"),
        Product(id = 8, category_id = 2, category = categories[1], name = "Wedang Jahe", description = "Jahe instan", price = 12000.0, stock = 50, img = "img_wedang_jahe"),
        Product(id = 9, category_id = 2, category = categories[1], name = "Teh Poci", description = "Teh melati", price = 15000.0, stock = 40, img = "img_teh_poci"),
        Product(id = 10, category_id = 2, category = categories[1], name = "Sirup Strawberi", description = "Sirup rasa", price = 35000.0, stock = 15, img = "img_sirup_strawberi"),

        // Kerajinan
        Product(id = 11, category_id = 3, category = categories[2], name = "Batik Purbalingga", description = "Kain batik", price = 150000.0, stock = 10, img = "img_batik_purbalingga"),
        Product(id = 12, category_id = 3, category = categories[2], name = "Sandal Bandol", description = "Sandal awet", price = 40000.0, stock = 25, img = "img_sandal_bandol"),
        Product(id = 13, category_id = 3, category = categories[2], name = "Sapu Glagah", description = "Sapu lantai", price = 25000.0, stock = 100, img = "img_sapu_glagah"),
        Product(id = 14, category_id = 3, category = categories[2], name = "Gantungan Kunci", description = "Gantungan kayu", price = 5000.0, stock = 150, img = "img_gantungan_kunci"),
        Product(id = 15, category_id = 3, category = categories[2], name = "Tas Rajut", description = "Tas rajut", price = 85000.0, stock = 5, img = "img_tas_rajut")
    )
}