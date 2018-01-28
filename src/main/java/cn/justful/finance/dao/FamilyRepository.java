package cn.justful.finance.dao;

import cn.justful.finance.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family, Integer> {
    Family findByName(String name);
}
