package team.msg.domain.teacher.repository

import org.springframework.data.repository.CrudRepository
import team.msg.domain.club.model.Club
import team.msg.domain.teacher.model.Teacher
import java.util.UUID

interface TeacherRepository : CrudRepository<Teacher, UUID> {
    fun findByClub(club: Club): Teacher?
}