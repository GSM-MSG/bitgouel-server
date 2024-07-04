package team.msg.domain.university.repository

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.repository.CrudRepository
import team.msg.domain.club.model.Club
import team.msg.domain.university.model.Professor
import team.msg.domain.university.model.University
import team.msg.domain.user.model.User
import java.util.*

interface ProfessorRepository : CrudRepository<Professor, UUID> {
    @EntityGraph(attributePaths = ["user"], type = EntityGraph.EntityGraphType.FETCH)
    fun findByUser(user: User): Professor?
    fun existsByClub(club: Club): Boolean
    fun existsByUniversity(university: University): Boolean
}