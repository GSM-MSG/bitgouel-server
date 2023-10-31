package team.msg.domain.lecture.presentation.web

import javax.persistence.EnumType
import javax.persistence.Enumerated
import team.msg.common.enum.ApproveStatus
import team.msg.domain.lecture.enum.LectureType

data class QueryAllLecturesWebRequest(
    @Enumerated(EnumType.STRING)
    val type: LectureType?,

    @Enumerated(EnumType.STRING)
    val status: ApproveStatus?
)
