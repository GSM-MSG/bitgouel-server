package team.msg.domain.auth.presentation.data.web

import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class StudentSignUpWebRequest(

    @field:Email
    @field:NotNull
    val email: String,

    @field:NotBlank
    val name: String,

    @field:NotBlank
    @field:Pattern(regexp = "^010[0-9]{8}\$")
    val phoneNumber: String,

    @field:Pattern(regexp = "^(?=.*[A-Za-z0-9])[A-Za-z0-9!@#\\\\\$%^&*]{8,24}\$")
    val password: String,

    @field:NotNull
    val highSchool: String,

    @field:NotBlank
    val clubName: String,

    @field:Min(1)
    @field:Max(3)
    val grade: Int,

    @field:NotNull
    val classRoom: Int,

    @field:NotNull
    val number: Int,

    @field:NotNull
    val admissionNumber: Int,

    @field:NotNull
    val subscriptionGrade: Int
)