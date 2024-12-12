package umc.spring.service.StoreService;

import org.springframework.data.domain.Page;
import umc.spring.domain.entity.Review;
import umc.spring.domain.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
    List<Store> findStoresByNameAndScore(String name, float score);
    public boolean existsById(Long id);
    public Store findById(Long id);
    Page<Review> getReviewListByStoreId(Long storeId, Integer page);
}
