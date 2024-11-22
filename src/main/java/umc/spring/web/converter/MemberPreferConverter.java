package umc.spring.web.converter;

import umc.spring.domain.entity.FoodCategory;
import umc.spring.domain.mapping.MemberPrefer;

import java.util.List;

public class MemberPreferConverter {

    public static List<MemberPrefer> toMemberPreferList(List<FoodCategory> foodCategoryList) {

        return foodCategoryList.stream()
                .map(category ->
                        MemberPrefer.builder()
                                .foodCategory(category)
                                .build()
                ).toList();
    }
}
