package team.msg.domain.auth.service

import team.msg.domain.auth.presentation.data.request.*
import team.msg.domain.auth.presentation.data.response.TokenResponse

interface AuthService {
    fun studentSignUp(studentSignUpRequest: StudentSignUpRequest)
    fun teacherSignUp(teacherSignUpRequest: TeacherSignUpRequest)
    fun professorSignUp(professorSignUpRequest: ProfessorSignUpRequest)
    fun governmentSignUp(governmentSignUpRequest: GovernmentSignUpRequest)
    fun companyInstructorSignUp(companyInstructorSignUpRequest: CompanyInstructorSignUpRequest)
    fun login(request: LoginRequest): TokenResponse
}