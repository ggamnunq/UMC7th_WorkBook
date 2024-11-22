package umc.spring.web.converter;

import umc.spring.domain.entity.Store;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

public class StoreConverter {

    public static StoreResponseDTO.AddResultDto toAddResultDto(Store store) {

        return StoreResponseDTO.AddResultDto.builder()
                .storeId(store.getId())
                .createdAt(store.getCreatedAt())
                .build();
    }

    public static Store toStore(StoreRequestDTO.AddDto request) {

        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .build();


    }

}
