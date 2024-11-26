package umc.spring.service.StoreService;

import umc.spring.domain.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
    List<Store> findStoresByNameAndScore(String name, float score);
    public boolean existsById(Long id);
    public Store findById(Long id);
}
