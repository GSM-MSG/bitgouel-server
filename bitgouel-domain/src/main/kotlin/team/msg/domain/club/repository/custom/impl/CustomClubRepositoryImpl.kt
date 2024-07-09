package team.msg.domain.club.repository.custom.impl

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import team.msg.domain.club.model.QClub.club
import team.msg.domain.club.repository.custom.CustomClubRepository
import team.msg.domain.school.model.QSchool.school

class CustomClubRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : CustomClubRepository {

    override fun existsOne(id: Long): Boolean {
        val fetchOne = queryFactory.selectOne()
            .from(club)
            .where(club.id.eq(id))
            .fetchFirst() // limit 1

        return fetchOne != null
    }

    override fun deleteAllBySchoolId(schoolId: Long) {
        queryFactory.delete(school)
            .where(club.school.id.eq(schoolId))
            .execute()
    }

    override fun findAllBySchoolName(schoolName: String?): List<String> =
        queryFactory
            .select(club.name)
            .from(club)
            .where(schoolNameEq(schoolName))
            .orderBy(club.school.name.asc(), club.name.asc())
            .fetch()

    private fun schoolNameEq(schoolName: String?): BooleanExpression? =
        if (schoolName.isNullOrBlank()) null else club.school.name.eq(schoolName)

}