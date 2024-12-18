package umc.spring.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import umc.spring.domain.enums.Role;
import umc.spring.validation.annotation.ExistCategories;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    @Setter
    public static class JoinDto {

        @NotBlank
        String name;
        @NotBlank
        @Email
        String email;
        @NotBlank
        String password;
        @NotNull
        Integer gender;
        @NotNull
        Integer birthYear;
        @NotNull
        Integer birthMonth;
        @NotNull
        Integer birthDay;
        @NotNull
        String address;
        @NotNull
        String specAddress;
        @ExistCategories
        List<Long> preferCategory;
        @NotNull
        Role role;

        @Override
        public String toString() {
            return "JoinDto{" +
                    "name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    ", gender=" + gender +
                    ", birthYear=" + birthYear +
                    ", birthMonth=" + birthMonth +
                    ", birthDay=" + birthDay +
                    ", address='" + address + '\'' +
                    ", specAddress='" + specAddress + '\'' +
                    ", preferCategory=" + preferCategory +
                    ", role=" + role +
                    '}';
        }
    }


}
