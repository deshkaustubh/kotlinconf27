package tech.kaustubhdeshpande.kotlinconf27.util

import tech.kaustubhdeshpande.kotlinconf27.data.source.featureflag.FeatureFlagManager
import tech.kaustubhdeshpande.kotlinconf27.data.source.featureflag.NoImplFeatureFlagManager
import tech.kaustubhdeshpande.kotlinconf27.data.source.local.DatabaseProvider
import tech.kaustubhdeshpande.kotlinconf27.data.source.local.DatabaseProviderImpl
import tech.kaustubhdeshpande.kotlinconf27.data.source.local.databaseModule
import tech.kaustubhdeshpande.kotlinconf27.data.source.preferences.PreferencesDataStoreProvider
import tech.kaustubhdeshpande.kotlinconf27.data.source.preferences.PreferencesDataStoreProviderImpl
import tech.kaustubhdeshpande.kotlinconf27.presentation.components.ads.AdsManager
import tech.kaustubhdeshpande.kotlinconf27.presentation.components.ads.NoImplAdsManager
import tech.kaustubhdeshpande.kotlinconf27.util.analytics.Analytics
import tech.kaustubhdeshpande.kotlinconf27.util.analytics.NoImplAnalytics
import tech.kaustubhdeshpande.kotlinconf27.util.file.FileManager
import tech.kaustubhdeshpande.kotlinconf27.util.file.FileManagerImpl
import com.mmk.kmpnotifier.KMPNotifier
import com.mmk.kmpnotifier.notification.configuration.NotificationPlatformConfiguration
import com.mmk.kmpnotifier.push.firebase.FirebasePush
import io.github.vinceglb.filekit.FileKit
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    includes(databaseModule)
    singleOf(::DatabaseProviderImpl) bind DatabaseProvider::class
    singleOf(::PreferencesDataStoreProviderImpl) bind PreferencesDataStoreProvider::class
    factory<FileManager> { FileManagerImpl() }
    factoryOf(::AppUtilImpl) bind AppUtil::class
    single { NoImplFeatureFlagManager } bind FeatureFlagManager::class
    single { NoImplAnalytics } bind Analytics::class
    single { NoImplAdsManager } bind AdsManager::class
}

internal actual fun onApplicationStartPlatformSpecific() {
    KMPNotifier.initialize(NotificationPlatformConfiguration.Desktop(), FirebasePush)
    FileKit.init("tech.kaustubhdeshpande.kotlinconf27")
}

actual fun getPlatform(): Platform = Platform.Desktop

internal actual val isAndroid: Boolean
    get() = false
internal actual val isDebug: Boolean
    get() = System.getProperty("app.debug") == "true" ||
        System.getenv("APP_DEBUG") == "true"

actual val defaultAsyncDispatcher: CoroutineDispatcher = Dispatchers.IO
