package team.msg.domain.school.presentation.web

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import team.msg.common.enums.Field
import team.msg.common.enums.Line
import team.msg.common.validation.NotBlankList

data class UpdateSchoolWebRequest(

    @field:NotBlank
    val schoolName: String,

    @field:NotNull
    val schoolField: Field,

    @field:NotNull
    val line: Line,

    @field:NotBlankList
    val departments: List<String>

)