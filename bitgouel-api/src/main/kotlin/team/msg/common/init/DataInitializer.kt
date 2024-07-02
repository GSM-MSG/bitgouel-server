package team.msg.common.init

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import team.msg.common.logger.LoggerDelegator
import team.msg.domain.club.model.Club
import team.msg.domain.club.repository.ClubRepository
import team.msg.domain.school.model.School
import team.msg.domain.school.repository.SchoolRepository
import java.util.*

@Component
class DataInitializer(
    private val schoolRepository: SchoolRepository,
    private val clubRepository: ClubRepository
) {
//
//    private val log by LoggerDelegator()
//
//    companion object {
//        val schoolList = listOf(
//            School(highSchool = HighSchool.GWANGJU_TECHNICAL_HIGH_SCHOOL),
//            School(highSchool = HighSchool.KUMPA_TECHNICAL_HIGH_SCHOOL),
//            School(highSchool = HighSchool.JEONNAM_TECHNICAL_HIGH_SCHOOL),
//            School(highSchool = HighSchool.GWANGJU_GIRLS_COMMERCIAL_HIGH_SCHOOL),
//            School(highSchool = HighSchool.JEONNAM_GIRLS_COMMERCIAL_HIGH_SCHOOL),
//            School(highSchool = HighSchool.GWANGJU_NATURAL_SCIENCE_HIGH_SCHOOL),
//            School(highSchool = HighSchool.GWANGJU_ELECTRONIC_TECHNICAL_HIGH_SCHOOL),
//            School(highSchool = HighSchool.DONGIL_HIGH_SCHOOL_OF_FUTURE_SCIENCE_HIGH_SCHOOL),
//            School(highSchool = HighSchool.SEOJIN_GIRLS_HIGH_SCHOOL),
//            School(highSchool = HighSchool.SUNGUI_SCIENCE_TECHNOLOGY_HIGH_SCHOOL),
//            School(highSchool = HighSchool.SONGWON_GIRLS_COMMERCIAL_HIGH_SCHOOL),
//            School(highSchool = HighSchool.GWANGJU_AUTOMATIC_EQUIPMENT_TECHNICAL_HIGH_SCHOOL),
//            School(highSchool = HighSchool.GWANGJU_SOFTWARE_MEISTER_HIGH_SCHOOL)
//        )
//    }
//
//    /**
//     * ApplicationReadyEvent가 발행되면 initData가 실행됩니다.
//     * school, club id = 1인 엔티티가 존재하면 Data Init을 실행하지 않는다.
//     */
//    @EventListener(ApplicationReadyEvent::class)
//    @Transactional(readOnly = true)
//    fun initData() {
//        if(!schoolRepository.existsOne(1)) {
//            log.info("=== RUN Init School Data ===")
//            initSchool()
//        }
//
//        if(!clubRepository.existsOne(1)) {
//            log.info("=== RUN Init Club Data ===")
//            initClub()
//        }
//    }
//
//    private fun initSchool() {
//        schoolRepository.saveAll(schoolList)
//    }
//
//    private fun initClub() {
//
//        val gwangjuTechnicalHighSchool = schoolList[0]
//        val kumpaTechnicalHighSchool = schoolList[1]
//        val jeonnamTechnicalHighSchool = schoolList[2]
//        val gwangjuGirlsCommercialHighSchool = schoolList[3]
//        val jeonnamGirlsCommercialHighSchool = schoolList[4]
//        val gwangjuNaturalScienceHighSchool = schoolList[5]
//        val gwangjuElectronicTechnicalHighSchool = schoolList[6]
//        val dongilHighSchoolOfFutureScienceHighSchool = schoolList[7]
//        val seojinGirlsHighSchool = schoolList[8]
//        val sunguiScienceTechnologyHighSchool = schoolList[9]
//        val songwonGirlsCommercialHighSchool = schoolList[10]
//        val gwangjuAutomaticEquipmentTechnicalHighSchool = schoolList[11]
//        val gwangjuSoftwareMeisterHighSchool = schoolList[12]
//
//        val clubList = listOf(
//            /**
//            * Gwangju Technical HighSchool's Club
//            * Gwangju Technical HighSchool ID = 1
//            */
//            Club(1, gwangjuTechnicalHighSchool, "SMART JOB PROJECT"),
//            Club(2, gwangjuTechnicalHighSchool, "나의 미래는 내가 주인공이다!"),
//            Club(3, gwangjuTechnicalHighSchool, "설비의 달인"),
//            Club(4, gwangjuTechnicalHighSchool, "특수용접 화이팅"),
//            Club(5, gwangjuTechnicalHighSchool, "전기가 미래다"),
//            Club(6, gwangjuTechnicalHighSchool, "전자 어벤져스"),
//            Club(7, gwangjuTechnicalHighSchool, "전자 히어로즈"),
//            Club(8, gwangjuTechnicalHighSchool, "Civil 마스터"),
//            Club(9, gwangjuTechnicalHighSchool, "건축연구소"),
//
//            /**
//            * Kumpa Technical HighSchool's Club
//            * Kumpa Technical HighSchool ID = 2
//            */
//            Club(10, kumpaTechnicalHighSchool, "레프리"),
//            Club(11, kumpaTechnicalHighSchool, "블라썸"),
//            Club(12, kumpaTechnicalHighSchool, "유선통신"),
//            Club(13, kumpaTechnicalHighSchool, "천기꿈나무"),
//            Club(14, kumpaTechnicalHighSchool, "어썸"),
//            Club(15, kumpaTechnicalHighSchool, "다이나믹"),
//            Club(16, kumpaTechnicalHighSchool, "금호로80 베이커리"),
//
//            /**
//             * Jeonnam Technical HighSchool's Club
//             * Jeonnam Technical HighSchool ID = 3
//             */
//            Club(17, jeonnamTechnicalHighSchool, "진짜기계"),
//            Club(18, jeonnamTechnicalHighSchool, "핫앤쿨"),
//            Club(19, jeonnamTechnicalHighSchool, "에너지지키미"),
//            Club(20, jeonnamTechnicalHighSchool, "라온하제"),
//            Club(21, jeonnamTechnicalHighSchool, "스카이드론"),
//            Club(22, jeonnamTechnicalHighSchool, "그린라이트"),
//
//            /**
//             * Gwangju Girls Commercial HighSchool's Club
//             * Gwangju Girls Commercial HighSchool ID = 4
//             */
//            Club(23, gwangjuGirlsCommercialHighSchool, "금융실무"),
//            Club(24, gwangjuGirlsCommercialHighSchool, "소개팅"),
//            Club(25, gwangjuGirlsCommercialHighSchool, "취사모"),
//
//            /**
//             * Jeonnam Girls Commercial HighSchool's Club
//             * Jeonnam Girls Commercial HighSchool ID = 5
//             */
//
//            /**
//             * Gwangju Natural Science HighSchool's Club
//             * Gwangju Natural Science HighSchool ID = 6
//             */
//            Club(26, gwangjuNaturalScienceHighSchool, "DCT"),
//            Club(27, gwangjuNaturalScienceHighSchool, "뉴쿡"),
//            Club(28, gwangjuNaturalScienceHighSchool, "베이커리 카페 CEO"),
//            Club(29, gwangjuNaturalScienceHighSchool, "우아행"),
//
//            /**
//             * Gwangju Electronic Technical HighSchool's Club
//             * Gwangju Electronic Technical HighSchool ID = 7
//             */
//            Club(30, gwangjuElectronicTechnicalHighSchool, "감성기계"),
//            Club(31, gwangjuElectronicTechnicalHighSchool, "열정 그 자체"),
//            Club(32, gwangjuElectronicTechnicalHighSchool, "ACT"),
//            Club(33, gwangjuElectronicTechnicalHighSchool, "ECT"),
//            Club(34, gwangjuElectronicTechnicalHighSchool, "Tesla"),
//            Club(35, gwangjuElectronicTechnicalHighSchool, "발자국"),
//            Club(36, gwangjuElectronicTechnicalHighSchool, "M lab"),
//
//            /**
//             * Dongil HighSchool Of Future Science HighSchool's Club
//             * Dongil HighSchool Of Future Science HighSchool ID = 8
//             */
//            Club(37, dongilHighSchoolOfFutureScienceHighSchool, "놀고잡고"),
//            Club(38, dongilHighSchoolOfFutureScienceHighSchool, "믿고잡고"),
//            Club(39, dongilHighSchoolOfFutureScienceHighSchool, "따고잡고"),
//            Club(40, dongilHighSchoolOfFutureScienceHighSchool, "쓰고잡고"),
//            Club(41, dongilHighSchoolOfFutureScienceHighSchool, "하고잡고"),
//            /**
//             * Seojin Girls HighSchool's Club
//             * Seojin Girls HighSchool ID = 9
//             */
//
//            /**
//             * Sungui Science Technology HighSchool's Club
//             * Sungui Science Technology HighSchool ID = 10
//             */
//            Club(42, sunguiScienceTechnologyHighSchool, "서전트스나이퍼"),
//            Club(43, sunguiScienceTechnologyHighSchool, "카-페인팅"),
//            Club(44, sunguiScienceTechnologyHighSchool, "드림온"),
//            Club(45, sunguiScienceTechnologyHighSchool, "볼트와 암페어"),
//            Club(46, sunguiScienceTechnologyHighSchool, "크로스핏마스터"),
//            Club(47, sunguiScienceTechnologyHighSchool, "비상"),
//            Club(48, sunguiScienceTechnologyHighSchool, "캐치어드론"),
//            Club(49, sunguiScienceTechnologyHighSchool, "내빵네빵"),
//            Club(50, sunguiScienceTechnologyHighSchool, "카페바리"),
//            Club(51, sunguiScienceTechnologyHighSchool, "쿠킹마스터즈"),
//
//            /**
//             * Songwon Girls Commercial HighSchool's Club
//             * Songwon Girls Commercial HighSchool ID = 11
//             */
//            Club(52, songwonGirlsCommercialHighSchool, "건강지킴이"),
//            Club(53, songwonGirlsCommercialHighSchool, "미용서비스"),
//            Club(54, songwonGirlsCommercialHighSchool, "뷰티아트"),
//            Club(55, songwonGirlsCommercialHighSchool, "클로즈업"),
//
//            /**
//             * Gwangju Automatic Equipment Technical HighSchool's Club
//             * Gwangju Automatic Equipment Technical HighSchool ID = 12
//             */
//            Club(56, gwangjuAutomaticEquipmentTechnicalHighSchool, "HMI동아리"),
//            Club(57, gwangjuAutomaticEquipmentTechnicalHighSchool, "마취제"),
//            Club(58, gwangjuAutomaticEquipmentTechnicalHighSchool, "빛go job go"),
//            Club(59, gwangjuAutomaticEquipmentTechnicalHighSchool, "취업진로 동아리"),
//
//            /**
//             * Gwangju Software Meister HighSchool's Club
//             * Gwangju Software Meister HighSchool ID = 13
//             */
//            Club(60, gwangjuSoftwareMeisterHighSchool, "dev GSM")
//        )
//        clubRepository.saveAll(clubList)
//    }
}