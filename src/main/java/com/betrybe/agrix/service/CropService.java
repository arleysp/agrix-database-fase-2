package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Crop service.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Crop service.
   *
   * @param cropRepository    the crop repository
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public CropService(CropRepository cropRepository, FertilizerService fertilizerService) {
    this.cropRepository = cropRepository;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  public List<Crop> findAll() {
    return cropRepository.findAll();
  }

  /**
   * Find by id crop.
   *
   * @param id the id
   * @return the crop
   * @throws CropNotFoundException the crop not found exception
   */
  public Crop findById(Long id) throws CropNotFoundException {
    return cropRepository.findById(id)
        .orElseThrow(CropNotFoundException::new);
  }

  /**
   * Find by harvest date list.
   *
   * @param start the start
   * @param end   the end
   * @return the list
   */
  public List<Crop> findByHarvestDate(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
  }

  /**
   * Create crops and fertilizers string.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the string
   * @throws CropNotFoundException       the crop not found exception
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  public String createCropsAndFertilizers(Long cropId, Long fertilizerId)
      throws CropNotFoundException, FertilizerNotFoundException {
    Crop crop = findById(cropId);
    Fertilizer fertilizer = fertilizerService.findById(fertilizerId);

    crop.getFertilizers().add(fertilizer);

    return "Fertilizante e plantação associados com sucesso!";

  }
}
