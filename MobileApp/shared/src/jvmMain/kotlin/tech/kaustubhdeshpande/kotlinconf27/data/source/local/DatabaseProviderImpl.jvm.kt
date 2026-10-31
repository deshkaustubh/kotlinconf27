package tech.kaustubhdeshpande.kotlinconf27.data.source.local

import androidx.room3.Room
import androidx.room3.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import tech.kaustubhdeshpande.kotlinconf27.util.Constants
import java.io.File

class DatabaseProviderImpl : DatabaseProvider {
    override fun provideAppDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
        // Scope under an app-id-named dir so apps built from the kit don't collide on the same file in
        // the shared temp dir; refactor_package.sh renames the app id here too.
        val dbDir = File(System.getProperty("java.io.tmpdir"), "tech.kaustubhdeshpande.kotlinconf27").apply { mkdirs() }
        val dbFilePath = File(dbDir, Constants.LOCAL_DB_STORAGE_NAME)
        return Room.databaseBuilder<AppDatabase>(
            name = dbFilePath.absolutePath,
        ).setDriver(BundledSQLiteDriver())
    }
}
