package org.example

import kotlin.random.Random

fun crearProductos(): List<Producto> {
    return listOf(
        Producto(1, "Maleta de mano", 74.25),
        Producto(2, "Caminadora Proform", 524.25),
        Producto(3, "Samsung Galaxy S24 Ultra", 1599.00),
        Producto(4, "Cama Indufoam", 309.00)
    )
}

fun crearInventario(): List<ProductoInventario> {
    val productos = crearProductos()

    return productos.map {
        ProductoInventario(it, Random.nextInt(0, 11))
    }
}