/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example

import de.vandermeer.asciitable.AsciiTable
import kotlin.text.toIntOrNull

fun imprimirHeader() {
    println("Bienvenido a almacenes")
    val asciiArt =
            """
         ____ ___ __  __    _    _   _ 
        / ___|_ _|  \/  |  / \  | \ | |
        \___ \| || |\/| | / _ \ |  \| |
         ___) | || |  | |/ ___ \| |\  |
        |____/___|_|  |_/_/   \_\_| \_|
    """.trimIndent()

    println(asciiArt)

    println()
}

fun imprimirInventario(inventario: List<ProductoInventario>) {
    println("Presentamos nuestro catálogo de productos:")

    println()

    val at = AsciiTable()

    // Añadir encabezados de columna
    at.addRule()
    at.addRow("ID - Producto", "Precio", "Cantidad disponible")
    at.addRule()

    // Añadir filas
    inventario.forEach { producto ->
        at.addRow(
                "${producto.producto.id} - ${producto.producto.nombre}",
                "$${producto.producto.precio}",
                "${producto.cantidadDisponible}"
        )
    }

    at.addRule()

    // Imprimir la tabla
    println(at.render())
}

fun main() {

    // Creando inventario

    val inventario = crearInventario()
    val carrito = Carrito()
    var opt = 0
    val menu = Menu()

    do {
        menu.cleanScreen()

        imprimirHeader()

        imprimirInventario(inventario)

        println("")

        for (option in menu.menuList) {
            println(option)
        }

        println("")
        print("Ingrese la opción que desea realizar: ")

        opt = readLine()?.toIntOrNull() ?: continue

        if(!menu.menuOpt.containsKey(opt)) {
            menu.cleanScreen()
            continue
        }

        menu.menuOpt[opt]?.let { it(inventario, carrito) };
        menu.cleanScreen()
    } while(opt != 5)
    print("Gracias por usar nuestro programa! 😁")

}
