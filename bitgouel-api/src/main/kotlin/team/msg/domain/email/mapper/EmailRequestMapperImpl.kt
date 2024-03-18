package team.msg.domain.email.mapper

import org.springframework.stereotype.Component
import team.msg.domain.email.presentation.data.request.CheckEmailAuthenticationRequest
import team.msg.domain.email.presentation.data.request.SendAuthenticationEmailRequest
import team.msg.domain.email.presentation.web.CheckAuthenticationEmailWebRequest
import team.msg.domain.email.presentation.web.SendAuthenticationEmailWebRequest

@Component
class EmailRequestMapperImpl : EmailRequestMapper {
    /**
     * 인증 email 전송 Web Request 를 애플리케이션 영역에서 사용될 Dto 로 매핑합니다.
     */
    override fun sendEmailWebRequestToDto(webRequest: SendAuthenticationEmailWebRequest): SendAuthenticationEmailRequest =
        SendAuthenticationEmailRequest(
            email = webRequest.email
        )

    /**
     * 인증 이메일 여부 조회 Web Request 를 애플리케이션 영역에서 사용될 Dto 로 매핑합니다.
     */
    override fun checkEmailWebRequestToDto(webRequest: CheckAuthenticationEmailWebRequest): CheckEmailAuthenticationRequest =
        CheckEmailAuthenticationRequest(
            email = webRequest.email
        )
}