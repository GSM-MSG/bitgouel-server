package team.msg.domain.auth.exception

import team.msg.domain.auth.exception.constant.AuthErrorCode
import team.msg.global.error.exception.BitgouelException

class AlreadyExistPhoneNumberException(
    message: String
) : BitgouelException(message, AuthErrorCode.ALREADY_EXIST_PHONE_NUMBER.status)