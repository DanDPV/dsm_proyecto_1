package  org.example

import de.vandermeer.asciitable.AsciiTable
import kotlin.text.toIntOrNull

class Menu {
    val menuOpt = mutableMapOf<Int, (List<ProductoInventario>, Carrito) -> Unit>()
    val menuList = listOf(
        "1. Agregar producto al carrito",
        "2. Eliminar producto del carrito",
        "3. Ver carrito",
        "4. Mostrar factura",
        "5. salir"
    )

    init {
        menuOpt[1] = ::addToCarrito
        menuOpt[2] = ::removeFromCarrito
        menuOpt[3] = ::showCarrito
        menuOpt[4] = ::printFactura
    }

    fun addToCarrito(inventario: List<ProductoInventario>, carrito: Carrito) {
        print("Escriba el id del producto que quiere añadir al carrito: ")

        var input = readLine() // Leer el input del usuario como String
        val idProducto = input?.toIntOrNull() // Convertir el input a Int de forma segura

        if (idProducto != null) {
            val productoSeleccionado = inventario.find { it.producto.id == idProducto }

            println("")

            if (productoSeleccionado != null) {
                println("Producto seleccionado: ${productoSeleccionado.producto.nombre}")

                print("Ingrese la cantidad: ")

                input = readLine() // Leer el input del usuario como String

                val cantidad = input?.toIntOrNull() // Convertir el input a Int de forma segura

                if (cantidad != null) {
                    carrito.agregarProducto(productoSeleccionado.producto, cantidad)

                    println("")

                    carrito.mostrarCarrito()
                    readlnOrNull()
                } else {
                    println("El valor ingresado en cantidad no es un entero válido.")
                }
            }
        } else {
            println("El valor ingresado no es un número entero válido.")
        }
    }

    fun removeFromCarrito(inventario: List<ProductoInventario>, carrito: Carrito) {
        cleanScreen()

        carrito.mostrarCarrito()

        println("")
        print("Escriba el id del producto que desea remover del carrito: ")

        var input = readLine()
        val idProducto = input?.toIntOrNull()

        if (idProducto != null) {
            val productoSeleccionado = carrito.getProductoEnCarrito(idProducto)
            if (productoSeleccionado == null) {
                println("Producto no existente en el carrito")
                readlnOrNull()
                return
            }
            println("")
            println("Producto seleccionado ${productoSeleccionado.producto.nombre}")
            print("Ingrese la cantidad a elimiar (max: ${productoSeleccionado.cantidad}): ")
            input = readLine()
            val cantidad = input?.toIntOrNull()
            if (cantidad == null || cantidad > productoSeleccionado.cantidad) {
                print("Cantidad erronea")
                readlnOrNull()
                return
            }

            carrito.eliminarProducto(productoSeleccionado.producto, cantidad)
            println("Producto eliminado con exito!")

            println("")

            carrito.mostrarCarrito()
            readlnOrNull()
        } else {
            println("El valor ingresado no es un número entero válido.")
            readlnOrNull()
        }
    }

    fun printFactura(inventario: List<ProductoInventario>, carrito: Carrito) {
        cleanScreen()

        if (carrito.obtenerTotal() == 0.0) {
            println("No hay productos en el carrito para generar una factura.")
            readlnOrNull()
            return
        }

        println("Generando factura...")

        println("")

        val asciiTable = AsciiTable()

        // Añadir encabezados de columna
        asciiTable.addRule()
        asciiTable.addRow("ID - Producto", "Precio unitario", "Cantidad", "Subtotal")
        asciiTable.addRule()

        // Añadir filas para cada producto del carrito
        carrito.getProductosEnCarrito().forEach { producto ->
            val subtotal = producto.producto.precio * producto.cantidad
            asciiTable.addRow(
                "${producto.producto.id} - ${producto.producto.nombre}",
                "$${producto.producto.precio}",
                "${producto.cantidad}",
                "$${subtotal}"
            )
        }

        asciiTable.addRule()

        // Calcular total e impuestos
        val total = carrito.obtenerTotal()
        val ivaAmount = total * 0.13
        val totalWithIVA = total + ivaAmount

        // Imprimir la tabla de la factura
        println(asciiTable.render())

        println("")

        // Mostrar detalles del total, impuestos y total final
        println("Subtotal: $${String.format("%.2f", total)}")
        println("IVA (13%): $${String.format("%.2f", ivaAmount)}")
        println("Total + IVA: $${String.format("%.2f", totalWithIVA)}")

        println("")

        // Confirmar la compra
        println("Gracias por su compra! 😁")
        carrito.clear()
        readlnOrNull()
    }

    fun showCarrito(inventario: List<ProductoInventario>, carrito: Carrito) {
        cleanScreen()
        carrito.mostrarCarrito()
        readlnOrNull()
    }

    fun cleanScreen() {
        print("\u001b[H\u001b[2J") //clean screen
    }
}