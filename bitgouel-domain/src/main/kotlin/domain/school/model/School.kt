package domain.school.model

import common.entity.BaseUUIDEntity
import domain.school.enums.SchoolConstant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import java.util.UUID

@Entity
class School(

    override val id: UUID,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(30)", nullable = false, unique = true)
    val constant: SchoolConstant // 학교 이름 상수

) : BaseUUIDEntity(id)