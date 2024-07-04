package team.msg.domain.bbozzak.repository

import org.springframework.data.repository.CrudRepository
import team.msg.domain.bbozzak.model.Bbozzak
import team.msg.domain.club.model.Club
import team.msg.domain.user.model.User
import java.util.*

interface BbozzakRepository : CrudRepository<Bbozzak, UUID> {
    fun findByUser(user: User): Bbozzak?

    fun existsByClub(club: Club): Boolean
}