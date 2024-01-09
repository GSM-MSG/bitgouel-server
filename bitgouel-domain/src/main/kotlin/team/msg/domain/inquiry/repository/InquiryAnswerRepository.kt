package team.msg.domain.inquiry.repository

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.repository.CrudRepository
import team.msg.domain.inquiry.model.InquiryAnswer
import java.util.UUID

interface InquiryAnswerRepository : CrudRepository<InquiryAnswer, UUID> {
    @EntityGraph(attributePaths = ["admin"], type = EntityGraph.EntityGraphType.FETCH)
    fun findByInquiryId(inquiryId: UUID): InquiryAnswer?
}