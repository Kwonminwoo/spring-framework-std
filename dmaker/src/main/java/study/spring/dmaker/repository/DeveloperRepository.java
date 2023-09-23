package study.spring.dmaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.spring.dmaker.entity.Developer;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}
