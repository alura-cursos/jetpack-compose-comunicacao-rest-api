package br.com.alura.anyflix.di.modules

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.alura.anyflix.database.AnyflixDatabase
import br.com.alura.anyflix.database.dao.MovieDao
import br.com.alura.anyflix.model.toMovieEntity
import br.com.alura.anyflix.sampleData.sampleMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Volatile
    private var INSTANCE: AnyflixDatabase? = null

    class AnyflixDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    sampleMovies.forEach { movie ->
                        database.movieDao().save(movie.toMovieEntity())
                    }
                }
            }
        }
    }

    @Provides
    fun provideAnyflixDatabaseCallback(): AnyflixDatabaseCallback {
        val scope = CoroutineScope(IO)
        return AnyflixDatabaseCallback(scope)
    }

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
        callback: AnyflixDatabaseCallback
    ): AnyflixDatabase {
        return INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(
                context,
                AnyflixDatabase::class.java,
                "anyflix.db"
            ).addCallback(callback)
                .fallbackToDestructiveMigration()
                .build()
                .also { INSTANCE = it }
        }
    }

    @Provides
    fun provideMovieDao(db: AnyflixDatabase): MovieDao {
        return db.movieDao()
    }

}