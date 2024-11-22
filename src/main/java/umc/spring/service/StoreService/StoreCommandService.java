package umc.spring.service.StoreService;


import umc.spring.domain.entity.Store;
import umc.spring.web.dto.StoreRequestDTO;


public interface StoreCommandService {

    public Store add(StoreRequestDTO.AddDto request);

}
