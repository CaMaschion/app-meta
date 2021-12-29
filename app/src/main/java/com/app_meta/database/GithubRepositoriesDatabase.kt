package com.app_meta.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app_meta.dao.GithubRepositoriesDao
import com.app_meta.network.model.RepositoryEntity

@Database(entities = [RepositoryEntity::class], version = 1, exportSchema = false)
abstract class GithubRepositoriesRoomDatabase : RoomDatabase() {

    abstract fun githubRepositoriesDao(): GithubRepositoriesDao

    companion object {

        private var INSTANCE: GithubRepositoriesRoomDatabase? = null

        fun getDatabase(context: Context): GithubRepositoriesRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GithubRepositoriesRoomDatabase::class.java,
                    "github_repositories_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}




//Defini um singleton GithubRepositoriesRoomDatabase para evitar que várias instâncias
//do banco de dados sejam abertas ao mesmo tempo. O singleton é uma classe que vai ter uma única
//instância e manter na memória sempre, nunca mais é apagado
//getDatabase retorna o Singleton. Ele criará o banco de dados na primeira vez que for
//    acessado, usando o builder do banco de dados da Room para criar um objeto RoomDatabase
//no contexto do aplicativo da classe GithubRepositoriesRoomDatabase e o nomeará
// como "github_repositories_database".
