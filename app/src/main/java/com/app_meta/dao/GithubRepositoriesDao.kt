package com.app_meta.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app_meta.network.model.RepositoryEntity

@Dao
interface GithubRepositoriesDao {

    @Query("SELECT * FROM github_repositories")
    fun getRepositoryEntity(): List<RepositoryEntity>

    @Insert
    fun insert(item: List<RepositoryEntity>)

}

//A Room usa o DAO para emitir consultas ao banco de dados.
//Os DAOs precisam ser interfaces ou classes abstratas.
//A anotação @Dao a identifica como uma classe DAO para a Room.
