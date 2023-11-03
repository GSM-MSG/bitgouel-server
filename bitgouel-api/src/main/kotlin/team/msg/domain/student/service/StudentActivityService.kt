package team.msg.domain.student.service

import org.springframework.data.domain.Pageable
import team.msg.domain.student.presentation.data.request.CreateStudentActivityRequest
import team.msg.domain.student.presentation.data.request.UpdateStudentActivityRequest
import team.msg.domain.student.presentation.data.response.AllStudentActivitiesResponse
import java.util.*

interface StudentActivityService {
    fun createStudentActivity(request: CreateStudentActivityRequest)
    fun updateStudentActivity(id: UUID, request: UpdateStudentActivityRequest)
    fun deleteStudentActivity(id: UUID)
    fun rejectStudentActivity(id: UUID)
    fun approveStudentActivity(id: UUID)
    fun queryAllStudentActivity(pageable: Pageable): AllStudentActivitiesResponse
    fun queryStudentActivityByStudent(studentId: UUID, pageable: Pageable): AllStudentActivitiesResponse
}