package team.msg.domain.post.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.msg.common.util.UserUtil
import team.msg.domain.post.model.Post
import team.msg.domain.post.presentation.data.request.CreatePostRequest
import team.msg.domain.post.repository.PostRepository
import team.msg.domain.post.enums.FeedType
import team.msg.domain.post.exception.ForbiddenPostException
import team.msg.domain.post.model.Link
import team.msg.domain.post.repository.LinkRepository
import team.msg.domain.user.enums.Authority
import java.util.UUID

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
    private val linkRepository: LinkRepository,
    private val userUtil: UserUtil
) : PostService {
    /**
     * 게시글을 생성하는 비지니스 로직입니다.
     * @param 게시글 생성에 필요한 정보가 담긴 dto
     */
    @Transactional(rollbackFor = [Exception::class])
    override fun createPostService(request: CreatePostRequest) {
        val user = userUtil.queryCurrentUser()

        when(user.authority){
            Authority.ROLE_ADMIN -> {}
            Authority.ROLE_COMPANY_INSTRUCTOR,
            Authority.ROLE_GOVERNMENT,
            Authority.ROLE_PROFESSOR,
            Authority.ROLE_BBOZZAK -> if (request.feedType == FeedType.INFORM) "공지를 작성할 권한이 없습니다." info user.authority
            else -> "게시글을 작성할 권한이 없습니다." info user.authority
        }

        val post = Post(
            id = UUID.randomUUID(),
            title = request.title,
            content = request.content,
            feedType = request.feedType,
            userId = user.id
        )

        val postEntity = postRepository.save(post)

        val links = request.link.map {
            Link(
                id = UUID.randomUUID(),
                post = postEntity,
                url = it
            )
        }

        linkRepository.saveAll(links)
    }

    infix fun String.info(authority: Authority): Nothing =
        throw ForbiddenPostException("$this info: [ userAuthority = $authority ]")

}