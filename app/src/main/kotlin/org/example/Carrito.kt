package org.example

import de.vandermeer.asciitable.AsciiTable

class Carrito {
    private val productosEnCarrito = mutableListOf<ProductoCarrito>()

    fun getProductoEnCarrito(id: Int): ProductoCarrito? {
        return productosEnCarrito.find { it.producto.id == id }
    }

    fun getProductosEnCarrito(): List<ProductoCarrito> {
        return productosEnCarrito
    }

    // Añadir un producto al carrito
    fun agregarProducto(producto: Producto, cantidad: Int = 1) {
        val productoEnCarrito = productosEnCarrito.find { it.producto.id == producto.id }

        if (productoEnCarrito != null) {
            // Si el producto ya está en el carrito, aumenta la cantidad
            productoEnCarrito.cantidad += cantidad
        } else {
            // Si el producto no está, lo agrega al carrito
            productosEnCarrito.add(ProductoCarrito(producto, cantidad))
        }
    }

    // Eliminar un producto del carrito
    fun eliminarProducto(producto: Producto, cantidad: Int = 1) {
        val productoEnCarrito = productosEnCarrito.find { it.producto.id == producto.id }

        if (productoEnCarrito != null) {
            if (productoEnCarrito.cantidad > cantidad) {
                // Si la cantidad en el carrito es mayor que la cantidad a eliminar, solo reduce la cantidad
                productoEnCarrito.cantidad -= cantidad
            } else {
                // Si la cantidad es igual o menor, elimina el producto completamente del carrito
                productosEnCarrito.remove(productoEnCarrito)
            }
        }
    }

    // Mostrar el contenido del carrito
    fun mostrarCarrito() {
        if (productosEnCarrito.isEmpty()) {
            println("El carrito está vacío.")
        } else {
            println("Productos en el carrito:")

            println("")

            val at = AsciiTable()

            // Añadir encabezados de columna
            at.addRule()
            at.addRow("ID - Producto", "Precio unitario", "Cantidad", "Subtotal")
            at.addRule()

            // Añadir filas
            productosEnCarrito.forEach { producto ->
                val subtotal = producto.producto.precio * producto.cantidad
                at.addRow("${producto.producto.id} - ${producto.producto.nombre}", "$${producto.producto.precio}", "${producto.cantidad}", "$${subtotal}")
            }

            at.addRule()

            // Imprimir la tabla
            println(at.render())

            println("")

            val total = obtenerTotal()

            println("Total $${total}")
        }
    }

    // Obtener el total del carrito
    fun obtenerTotal(): Double {
        return productosEnCarrito.sumOf { it.producto.precio * it.cantidad }
    }
}
