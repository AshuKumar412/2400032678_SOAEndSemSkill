
package soa.repository;

import soa.entity.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TouristRepository
        extends JpaRepository<Tourist, Long> {
}
