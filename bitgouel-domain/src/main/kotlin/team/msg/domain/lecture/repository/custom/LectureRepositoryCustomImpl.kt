package team.msg.domain.lecture.repository.custom

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import team.msg.common.enums.ApproveStatus
import team.msg.domain.lecture.enums.LectureType
import team.msg.domain.lecture.model.QLecture.lecture
import team.msg.domain.user.model.QUser.user

@Component
class LectureRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory
) : LectureRepositoryCustom {
    override fun findAllByApproveStatusAndLectureType(pageable: Pageable, approveStatus: ApproveStatus?, lectureType: LectureType?) = PageImpl(
        queryFactory
            .selectFrom(lecture)
            .leftJoin(lecture.user, user)
            .fetchJoin()
            .where(
                eqLectureType(lectureType),
                eqApproveStatus(approveStatus)
            )
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch())

    private fun eqLectureType(lectureType: LectureType?): BooleanExpression? {
        lectureType ?: return null
        return lecture.lectureType.eq(lectureType)
    }

    private fun eqApproveStatus(approveStatus: ApproveStatus?): BooleanExpression? {
        approveStatus ?: return null
        return lecture.approveStatus.eq(approveStatus)
    }
}