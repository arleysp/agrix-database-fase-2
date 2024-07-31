package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.repository.FarmRepository;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Farm service.
 */
@Service
public class FarmService {

  private final FarmRepository farmRepository;
  private final CropRepository cropRepository;

  /**
   * Instantiates a new Farm service.
   *
   * @param farmRepository the farm repository
   * @param cropRepository the crop repository
   */
  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  /**
   * Create farm.
   *
   * @param farm the farm
   * @return the farm
   */
  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  public List<Farm> findAll() {
    return farmRepository.findAll();
  }

  /**
   * Find by id farm.
   *
   * @param id the id
   * @return the farm
   * @throws FarmNotFoundException the farm not found exception
   */
  public Farm findById(Long id) throws FarmNotFoundException {
    return farmRepository.findById(id)
        .orElseThrow(FarmNotFoundException::new);
  }

  /**
   * Create farm crop crop.
   *
   * @param id   the id
   * @param crop the crop
   * @return the crop
   * @throws FarmNotFoundException the farm not found exception
   */
  public Crop createFarmCrop(Long id, Crop crop) throws FarmNotFoundException {
    Farm farm = farmRepository.findById(id)
        .orElseThrow(FarmNotFoundException::new);

    crop.setFarm(farm);
    return cropRepository.save(crop);
  }

  /**
   * List farm crops list.
   *
   * @param id the id
   * @return the list
   * @throws FarmNotFoundException the farm not found exception
   */
  public List<Crop> listFarmCrops(Long id) throws FarmNotFoundException {
    Farm farm = farmRepository.findById(id)
        .orElseThrow(FarmNotFoundException::new);

    return cropRepository.findAll()
        .stream()
        .filter(crop -> Objects.equals(crop.getFarm().getId(), id))
        .toList();
  }
}
