package com.example.cityservice.service.specification;

import com.example.cityservice.model.City;
import com.example.weatherservice.model.City_;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CitySpecification {
    private static final Specification<City> EMPTY_SPECIFICATION = Specification.where(null);

    public static Specification<City> byEnabled(Boolean isEnabled) {
        return withDefault(isEnabled, () -> (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(City_.IS_ENABLED), isEnabled);
        });
    }

    private static <T> Specification<City> withDefault(T filter,
                                                        Supplier<Specification<City>> specification) {
        if (filter != null) {
            return specification.get();
        }
        return EMPTY_SPECIFICATION;
    }
}
