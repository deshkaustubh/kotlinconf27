import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeUIViewController
import tech.kaustubhdeshpande.kotlinconf27.root.App
import tech.kaustubhdeshpande.kotlinconf27.util.LocalNativeViewFactory
import tech.kaustubhdeshpande.kotlinconf27.util.NativeViewFactory
import tech.kaustubhdeshpande.kotlinconf27.util.SwiftLibDependencyFactory
import tech.kaustubhdeshpande.kotlinconf27.util.swiftLibDependenciesModule
import org.koin.core.KoinApplication
import platform.UIKit.UIViewController

fun MainViewController(nativeViewFactory: NativeViewFactory): UIViewController = ComposeUIViewController {
    CompositionLocalProvider(LocalNativeViewFactory provides nativeViewFactory) {
        App()
    }
}

// This is called on application started on Swift side
fun KoinApplication.provideSwiftLibDependencyFactory(factory: SwiftLibDependencyFactory) = run { modules(swiftLibDependenciesModule(factory)) }
