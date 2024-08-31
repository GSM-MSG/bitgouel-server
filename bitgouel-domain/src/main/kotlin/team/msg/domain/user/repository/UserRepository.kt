package team.msg.domain.user.repository

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import team.msg.domain.user.model.User
import team.msg.domain.user.repository.custom.CustomUserRepository
import java.util.UUID

interface UserRepository : CrudRepository<User, UUID>, CustomUserRepository {
    fun existsByEmail(email: String): Boolean
    fun existsByPhoneNumber(phoneNumber: String): Boolean
    fun findByEmail(email: String): User?
    fun findByIdIn(ids: List<UUID>): List<User>
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("delete from User u where u.id in :ids")
    fun deleteByIdIn(ids: List<UUID>)
    fun findByNameAndEmail(name: String, email: String): User?
}