package com.betrybe.agrix.repository;

import com.betrybe.agrix.entity.Crop;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Crop repository.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

  /**
   * Find by harvest date between list.
   *
   * @param start the start
   * @param end   the end
   * @return the list
   */
  List<Crop> findByHarvestDateBetween(LocalDate start, LocalDate end);
}
