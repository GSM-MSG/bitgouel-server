package team.msg.domain.club.service

import team.msg.domain.club.presentation.data.reqeust.UpdateClubRequest
import team.msg.domain.club.presentation.data.response.ClubDetailsResponse
import team.msg.domain.club.presentation.data.response.ClubsResponse
import team.msg.domain.student.presentation.data.response.StudentDetailsResponse
import java.util.*

interface ClubService {
    fun queryAllClubs(highSchool: String): ClubsResponse
    fun queryClubDetailsById(id: Long): ClubDetailsResponse
    fun queryMyClubDetails(): ClubDetailsResponse
    fun queryStudentDetails(clubId: Long, studentId: UUID): StudentDetailsResponse
    fun updateClub(id: Long, updateClubRequest: UpdateClubRequest)
    fun deleteClub(id: Long)
}