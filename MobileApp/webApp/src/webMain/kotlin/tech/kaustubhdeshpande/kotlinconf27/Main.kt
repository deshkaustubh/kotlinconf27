package tech.kaustubhdeshpande.kotlinconf27

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import tech.kaustubhdeshpande.kotlinconf27.root.App
import tech.kaustubhdeshpande.kotlinconf27.root.AppInitializer

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    AppInitializer.initialize {}
    ComposeViewport {
        App()
    }
}
