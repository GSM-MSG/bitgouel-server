package team.msg.domain.auth.mapper

import team.msg.domain.auth.presentation.data.request.GovernmentSignUpRequest
import team.msg.domain.auth.presentation.data.request.ProfessorSignUpRequest
import team.msg.domain.auth.presentation.data.request.StudentSignUpRequest
import team.msg.domain.auth.presentation.data.request.TeacherSignUpRequest
import team.msg.domain.auth.presentation.data.web.GovernmentSignUpWebRequest
import team.msg.domain.auth.presentation.data.web.ProfessorSignUpWebRequest
import team.msg.domain.auth.presentation.data.web.StudentSignUpWebRequest
import team.msg.domain.auth.presentation.data.web.TeacherSignUpWebRequest

interface AuthRequestMapper {
    fun studentSignUpWebRequestToDto(webRequest: StudentSignUpWebRequest): StudentSignUpRequest
    fun teacherSignUpWebRequestToDto(webRequest: TeacherSignUpWebRequest): TeacherSignUpRequest
    fun professorSignUpWebRequestToDto(webRequest: ProfessorSignUpWebRequest): ProfessorSignUpRequest
    fun governmentSignUpWebRequestToDto(webRequest: GovernmentSignUpWebRequest): GovernmentSignUpRequest
}