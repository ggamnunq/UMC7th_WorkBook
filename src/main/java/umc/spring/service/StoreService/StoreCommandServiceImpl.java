package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.RegionHandler;
import umc.spring.domain.entity.Region;
import umc.spring.domain.entity.Store;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.converter.StoreConverter;
import umc.spring.web.dto.StoreRequestDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional(readOnly = false)
    public Store add(StoreRequestDTO.AddDto request) {
        Store newStore = StoreConverter.toStore(request);
        Region region = regionRepository.findById(request.getRegionId()).orElseThrow(() -> new RegionHandler(ErrorStatus.STORE_NOT_FOUND));
        newStore.setRegion(region);
        return storeRepository.save(newStore);
    }
}
