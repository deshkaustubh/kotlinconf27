package tech.kaustubhdeshpande.kotlinconf27

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import tech.kaustubhdeshpande.kotlinconf27.root.App
import tech.kaustubhdeshpande.kotlinconf27.root.AppInitializer

fun main() {
    System.setProperty("java.util.logging.SimpleFormatter.format", $$"%5$s%6$s%n")

    application {
        AppInitializer.initialize {}
        Window(
            onCloseRequest = ::exitApplication,
            title = "KotlinConf27",
        ) {
            App()
        }
    }
}
