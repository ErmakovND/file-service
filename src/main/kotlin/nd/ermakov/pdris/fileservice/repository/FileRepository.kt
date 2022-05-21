package nd.ermakov.pdris.fileservice.repository

import nd.ermakov.pdris.fileservice.model.File
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface FileRepository : JpaRepository<File, Long> {
    fun findByName(name: String): File?
    fun existsByName(name: String): Boolean
    fun removeByName(name: String)
    @Query("select name from #{#entityName}")
    fun findAllNames(): List<String>
    @Modifying
    @Query("update #{#entityName} set name = :newName where name = :oldName")
    fun updateName(@Param("oldName") oldName: String, @Param("newName") newName: String)
}
