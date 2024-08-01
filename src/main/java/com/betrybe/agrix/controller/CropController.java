package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping(value = "/crops")
public class CropController {

  private final CropService cropService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService the crop service
   */
  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  @GetMapping
  public List<CropDto> getAllCrops() {
    return cropService.findAll()
        .stream()
        .map(CropDto::fromEntity)
        .toList();
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{id}")
  public CropDto getCropById(@PathVariable Long id) throws CropNotFoundException {
    return CropDto.fromEntity(cropService.findById(id));
  }

  /**
   * Gets crop by harvest date.
   *
   * @param start the start
   * @param end   the end
   * @return the crop by harvest date
   */
  @GetMapping("/search")
  public List<CropDto> getCropByHarvestDate(
      @RequestParam LocalDate start, @RequestParam LocalDate end
  ) {
    return cropService.findByHarvestDate(start, end)
        .stream()
        .map(CropDto::fromEntity)
        .toList();
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
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  @ResponseStatus(HttpStatus.CREATED)
  public String createCropsAndFertilizers(
      @PathVariable Long cropId,
      @PathVariable Long fertilizerId
  ) throws CropNotFoundException, FertilizerNotFoundException {
    return cropService.createCropsAndFertilizers(cropId, fertilizerId);
  }

  /**
   * Gets fertilizers by crops.
   *
   * @param cropId the crop id
   * @return the fertilizers by crops
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{cropId}/fertilizers")
  public List<FertilizerDto> getFertilizersByCrops(@PathVariable Long cropId)
      throws CropNotFoundException {
    return cropService.getFertilizersByCrops(cropId)
        .stream().map(FertilizerDto::fromEntity).toList();
  }
}
