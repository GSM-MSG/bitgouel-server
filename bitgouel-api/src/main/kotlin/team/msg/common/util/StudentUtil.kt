package team.msg.common.util

import org.springframework.stereotype.Component
import team.msg.domain.club.model.Club
import team.msg.domain.student.enums.StudentRole
import team.msg.domain.student.model.Student
import team.msg.domain.student.repository.StudentRepository
import team.msg.domain.user.model.User
import java.util.*

@Component
class StudentUtil(
    private val studentRepository: StudentRepository
) {
    fun createStudent(user: User, club: Club, grade: Int, classRoom: Int, number: Int, admissionNumber: Int) {
        val student = Student(
            id = UUID.randomUUID(),
            user = user,
            club = club,
            grade = grade,
            classRoom = classRoom,
            number = number,
            cohort = admissionNumber - 2020,
            credit = 0,
            studentRole = StudentRole.STUDENT
        )
        studentRepository.save(student)
    }
}