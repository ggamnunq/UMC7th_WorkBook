package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.PageHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.domain.entity.Mission;
import umc.spring.domain.entity.Review;
import umc.spring.domain.entity.Store;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoresByNameAndScore(String name, float score) {
        List<Store> filteredScores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);
        filteredScores.forEach(store -> System.out.println("Store : " + store));
        return filteredScores;
    }

    @Override
    public boolean existsById(Long id) {
        return storeRepository.existsById(id);
    }

    @Override
    public Store findById(Long id) {
        return storeRepository.findById(id).orElseThrow( () -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
    }

    @Override
    public Page<Review> getReviewListByStoreId(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).get();
        return reviewRepository.findAllByStore(store, PageRequest.of(page-1, 10));
    }

    @Override
    public Page<Mission> getMissionListByStoreId(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        Page<Mission> missionList = missionRepository.findMissionsByStore(store, PageRequest.of(page-1, 10));
        if(page > missionList.getTotalPages()) {
            throw new PageHandler(ErrorStatus.PAGE_OUT_OF_BOUND);
        }
        return missionList;
    }


}
