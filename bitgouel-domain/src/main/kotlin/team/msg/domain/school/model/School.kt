package team.msg.domain.school.model

import javax.persistence.*

@Entity
class School(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long = 0,

    @Column(name = "logoImageUrl", columnDefinition = "VARCHAR(100)", nullable = false)
    val logoImageUrl: String,

    @Column(name = "name", columnDefinition = "VARCHAR(100)", nullable = false, unique = true, updatable = false)
    val name: String

)