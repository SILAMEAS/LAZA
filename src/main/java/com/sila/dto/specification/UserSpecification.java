package com.sila.dto.specification;

import com.sila.model.User;
import com.sila.model.User_;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static Specification<User> search(String search){
        if(search==null){
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(
                criteriaBuilder.like(root.get(User_.FULL_NAME),search+"%"),
                criteriaBuilder.like(root.get(User_.email),search+"%")
        );

    }
}