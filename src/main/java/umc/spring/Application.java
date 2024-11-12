package umc.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.entity.Review;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.service.MemberService;
import umc.spring.service.MissionService;
import umc.spring.service.ReviewService;
import umc.spring.service.StoreQueryService;
import umc.spring.web.dto.MemberMissionDTO;
import umc.spring.web.dto.MissionDTO;
import umc.spring.web.dto.ReviewAddDTO;

import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	public CommandLineRunner run(ApplicationContext context) {
//		return args -> {
//			StoreQueryService storeService = context.getBean(StoreQueryService.class);
//
//			//파라미터 값 설정
//			String name = "요아정";
//			Float score = 4.0f;
//			//쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
//			System.out.println("Executing findStoresByNameAndScore with parameters:");
//			System.out.println("Name: " + name);
//			System.out.println("Score: " + score);
//
//			storeService.findStoresByNameAndScore(name, score)
//					.forEach(System.out::println);
//		};
//	}

//	내가 진행중 ( 진행 완료한 미션 모아서 보는 쿼리(페이징 포함) )
//	@Bean
//	public CommandLineRunner run(ApplicationContext context) {
//
//		return args -> {
//
//			MissionService missionService = context.getBean(MissionService.class);
//
//			Long memberId = 1L;
//			Integer startPage = 0;
//			MissionStatus status = MissionStatus.COMPLETE;
//
//			System.out.println("검사 시작");
//			System.out.println("memberId: " + memberId);
//			System.out.println("status: " + status);
//
//			Page<MissionDTO.MissionStatusDTO> MemberMissionDTO = missionService.findMyMissionsByStatus(memberId, status, startPage);
//			for (MissionDTO.MissionStatusDTO missionStatusDTO : MemberMissionDTO) {
//				System.out.println(missionStatusDTO);
//			}
//
//		};
//
//	}

//	리뷰 조회
//	@Bean
//	public CommandLineRunner run(ApplicationContext context) {
//
//		return args -> {
//
//			ReviewService reviewService = context.getBean(ReviewService.class);
//
//			Long storeId = 1L;
//
//			System.out.println("조회 시작");
//			System.out.println("storeId : " + storeId);
//
//			reviewService.getReviews(storeId).forEach(System.out::println);
//
//		};
//
//	}

	//리뷰 작성
//	@Bean
//	public CommandLineRunner run(ApplicationContext context) {
//
//		return args -> {
//			ReviewService reviewService = context.getBean(ReviewService.class);
//
//			Long memberId = 1L;
//			Long storeId = 1L;
//			Float score = 4.0F;
//			String body = "너무 맛있어요..";
//
//			ReviewAddDTO reviewAddDTO = new ReviewAddDTO(memberId, storeId, score, body);
//			reviewService.writeReview(reviewAddDTO);
//
//			System.out.println("추가 : " + reviewAddDTO);
//		};
//
//	}

	//홈 화면 쿼리 (현재 선택 된 지역에서 도전이 가능한 미션 목록, 페이징 포함)
//	@Bean
//	public CommandLineRunner run(ApplicationContext context) {
//
//		return args -> {
//
//			MissionService missionService = context.getBean(MissionService.class);
//
//			Long memberId = 1L;
//			Long regionId = 2L;
//			Integer startPage = 0;
//
//			System.out.println("홈 화면 로딩");
//			System.out.println("memberId: " + memberId);
//			System.out.println("regionId: " + regionId);
//
//			Page<MissionDTO.MissionRegionDTO> missionsForHomePage = missionService.findMissionsForHomePage(memberId, regionId, MissionStatus.CHALLENGING, startPage);
//			for (MissionDTO.MissionRegionDTO missionRegionDTO : missionsForHomePage) {
//				System.out.println(missionRegionDTO);
//			}
//			Page<MissionDTO.MissionRegionDTO> cleared = missionService.findMissionsForHomePage(memberId, regionId, MissionStatus.COMPLETE, startPage);
//			System.out.println("count = " + cleared.getTotalElements());
//
//		};
//
//	}

	//마이페이지 화면 쿼리
	@Bean
	public CommandLineRunner run(ApplicationContext context) {

		return args -> {
			MemberService memberService = context.getBean(MemberService.class);
			Long memberId = 1L;
			System.out.println(memberService.findMemberForMyPage(memberId));
		};

}

}
