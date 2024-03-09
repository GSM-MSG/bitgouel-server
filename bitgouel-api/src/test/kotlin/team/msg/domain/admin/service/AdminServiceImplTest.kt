package team.msg.domain.admin.service

import com.appmattus.kotlinfixture.kotlinFixture
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.repository.findByIdOrNull
import team.msg.common.enums.ApproveStatus
import team.msg.domain.admin.presentation.data.request.QueryUsersRequest
import team.msg.domain.user.enums.Authority
import team.msg.domain.user.event.WithdrawUserEvent
import team.msg.domain.user.exception.UserAlreadyApprovedException
import team.msg.domain.user.model.User
import team.msg.domain.user.presentation.data.response.AdminUserResponse
import team.msg.domain.user.presentation.data.response.UserDetailsResponse
import team.msg.domain.user.presentation.data.response.UsersResponse
import team.msg.domain.user.repository.UserRepository
import java.util.*

class AdminServiceImplTest : BehaviorSpec({
    isolationMode = IsolationMode.InstancePerLeaf
    val fixture = kotlinFixture()

    val userRepository = mockk<UserRepository>()
    val applicationEventPublisher = mockk<ApplicationEventPublisher>()
    val adminServiceImpl = AdminServiceImpl(
        userRepository = userRepository,
        applicationEventPublisher = applicationEventPublisher
    )

    // queryUsers 테스트 코드
    Given("QueryUsersRequest가 주어졌을 때") {
        val userId = UUID.randomUUID()
        val keyword = "keyword"
        val authority = Authority.ROLE_STUDENT
        val approveStatus = ApproveStatus.APPROVED

        val user = fixture<User> {
            property(User::id) { userId }
            property(User::name) { keyword }
            property(User::authority) { authority }
            property(User::approveStatus) { approveStatus }

        }

        val request = fixture<QueryUsersRequest> {
            property(QueryUsersRequest::keyword) { keyword }
            property(QueryUsersRequest::authority) { authority }
            property(QueryUsersRequest::approveStatus) { approveStatus }
        }

        val adminUserResponse = fixture<AdminUserResponse> {
            property(AdminUserResponse::id) { userId }
            property(AdminUserResponse::name) { keyword }
            property(AdminUserResponse::authority) { authority }
            property(AdminUserResponse::approveStatus) { approveStatus }
        }

        val response = fixture<UsersResponse> {
            property(UsersResponse::users) { listOf(adminUserResponse) }
        }

        every { userRepository.query(keyword, authority, approveStatus) } returns listOf(user)

        When("User 리스트 요청 시") {
            val result = adminServiceImpl.queryUsers(request)

            Then("result가 response와 같아야 한다") {
                result shouldBe response
            }
        }
    }

    // approveUsers 테스트 코드
    Given("userIds가 주어졌을때") {
        val userId = UUID.randomUUID()

        val user = fixture<User> {
            property(User::id) { userId }
            property(User::approveStatus) { ApproveStatus.PENDING }
        }

        val approvedUser = fixture<User> {
            property(User::approveStatus) { ApproveStatus.APPROVED }
        }

        every { userRepository.findByIdIn(listOf(userId)) } returns listOf(user)
        every { userRepository.saveAll(listOf(user)) } returns listOf(user)

        When("User 승인 시") {
            adminServiceImpl.approveUsers(listOf(userId))

            Then("승인된 User 가 저장이 되어야 한다") {
                verify(exactly = 1) { userRepository.saveAll(listOf(user)) }
            }
        }

        When("이미 승인된 User 라면") {
            every { userRepository.findByIdIn(listOf(userId)) } returns listOf(approvedUser)

            Then("UserAlreadyApprovedException 이 발생해야 한다") {
                shouldThrow<UserAlreadyApprovedException> {
                    adminServiceImpl.approveUsers(listOf(userId))
                }
            }
        }
    }

    // rejectUsers 테스트 코드
    Given("userIds 가 주어졌을 때") {
        val userId = UUID.randomUUID()
        val userIds = listOf(userId)

        val user = fixture<User> {
            property(User::id) { userId }
            property(User::approveStatus) { ApproveStatus.PENDING }
        }

        val approvedUser = fixture<User> {
            property(User::approveStatus) { ApproveStatus.APPROVED }
        }

        every { userRepository.findByIdIn(userIds) } returns listOf(user)
        every { applicationEventPublisher.publishEvent(WithdrawUserEvent(user)) } just Runs
        every { userRepository.deleteByIdIn(any()) } returns Unit

        When("User 회원가입 거절 시") {
            adminServiceImpl.rejectUsers(userIds)

            Then("withdrawUserEvent 를 발행해야 한다") {
                verify(exactly = 1) { applicationEventPublisher.publishEvent(WithdrawUserEvent(user)) }
            }

            Then("User가 삭제가 되어야 한다") {
                verify(exactly = 1) { userRepository.deleteByIdIn(userIds) }
            }
        }

        When("이미 승인된 User 라면") {
            every { userRepository.findByIdIn(userIds) } returns listOf(approvedUser)

            Then("UserAlreadyApprovedException 이 발생해야 한다") {
                shouldThrow<UserAlreadyApprovedException> {
                    adminServiceImpl.rejectUsers(userIds)
                }
            }
        }
    }

    // queryUserDetails 테스트 코드
    Given("userId가 주어졌을 때") {
        val userId = UUID.randomUUID()
        val name = "name"
        val authority = Authority.ROLE_STUDENT
        val approveStatus = ApproveStatus.APPROVED

        val user = fixture<User> {
            property(User::id) { userId }
            property(User::name) { name }
            property(User::authority) { authority }
            property(User::approveStatus) { approveStatus }
        }

        val response = fixture<UserDetailsResponse> {
            property(UserDetailsResponse::id) { userId }
            property(UserDetailsResponse::name) { name }
            property(UserDetailsResponse::authority) { authority }
            property(UserDetailsResponse::approveStatus) { approveStatus }
        }

        every { userRepository.findByIdOrNull(userId) } returns user

        When("User 상세 정보 요청 시") {
            val result = adminServiceImpl.queryUserDetails(userId)

            Then("result가 response와 같아야 한다") {
                result shouldBe response
            }
        }
    }

    // forceWithdraw 테스트 코드
    Given("userId가 주어지면") {
        val userId = UUID.randomUUID()

        val user = fixture<User> {
            property(User::id) { userId }
        }

        every { userRepository.findByIdOrNull(userId) } returns user
        every { applicationEventPublisher.publishEvent(WithdrawUserEvent(user)) } just Runs
        every { userRepository.delete(user) } returns Unit

        When("User 강제 탈퇴 시") {
            adminServiceImpl.forceWithdraw(userId)

            Then("withdrawUserEvent 를 발행해야 한다") {
                verify(exactly = 1) { applicationEventPublisher.publishEvent(WithdrawUserEvent(user)) }
            }

            Then("User가 삭제가 되어야 한다") {
                verify(exactly = 1) { userRepository.delete(user) }
            }
        }
    }
})