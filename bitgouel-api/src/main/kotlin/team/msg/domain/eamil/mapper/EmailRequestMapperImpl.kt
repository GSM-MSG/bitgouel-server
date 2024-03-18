package team.msg.domain.eamil.mapper

import org.springframework.stereotype.Component
import team.msg.domain.eamil.presentation.data.request.SendAuthenticationEmailRequestData
import team.msg.domain.eamil.presentation.web.SendAuthenticationEmailWebRequest

@Component
class EmailRequestMapperImpl : EmailRequestMapper {
    /**
     * 인증 email 전송 Web Request 를 애플리케이션 영역에서 사용될 Dto 로 매핑합니다.
     */
    override fun sendEmailWebRequestToDto(webRequest: SendAuthenticationEmailWebRequest): SendAuthenticationEmailRequestData =
        SendAuthenticationEmailRequestData(
            email = webRequest.email
        )
}