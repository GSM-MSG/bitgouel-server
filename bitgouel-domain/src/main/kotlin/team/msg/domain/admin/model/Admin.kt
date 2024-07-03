package team.msg.domain.admin.model

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import team.msg.common.entity.BaseUUIDEntity
import team.msg.domain.user.model.User
import java.util.*

@Entity
class Admin(

    @get:JvmName("getIdentifier")
    override var id: UUID,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)")
    val user: User?

) : BaseUUIDEntity(id)