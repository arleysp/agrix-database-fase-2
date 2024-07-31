package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropCreationDto;
import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FarmCreationDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {

  private final FarmService farmService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmService the farm service
   */
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Create farm dto.
   *
   * @param farmCreationDto the farm creation dto
   * @return the farm dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FarmDto createFarm(@RequestBody FarmCreationDto farmCreationDto) {
    return FarmDto.fromEntity(
        farmService.createFarm(farmCreationDto.toEntity())
    );
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  @GetMapping
  public List<FarmDto> getAllFarms() {
    return farmService.findAll()
        .stream()
        .map(FarmDto::fromEntity)
        .toList();
  }

  /**
   * Gets by id.
   *
   * @param id the id
   * @return the by id
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{id}")
  public FarmDto getById(@PathVariable Long id) throws FarmNotFoundException {
    return FarmDto.fromEntity(farmService.findById(id));
  }

  /**
   * Create farm crop crop dto.
   *
   * @param id              the id
   * @param cropCreationDto the crop creation dto
   * @return the crop dto
   * @throws FarmNotFoundException the farm not found exception
   */
  @PostMapping("/{id}/crops")
  @ResponseStatus(HttpStatus.CREATED)
  public CropDto createFarmCrop(@PathVariable Long id,
      @RequestBody CropCreationDto cropCreationDto) throws FarmNotFoundException {
    return CropDto.fromEntity(
        farmService.createFarmCrop(id, cropCreationDto.toEntity())
    );
  }

  /**
   * Gets farm crops.
   *
   * @param id the id
   * @return the farm crops
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{id}/crops")
  public List<CropDto> getFarmCrops(@PathVariable Long id) throws FarmNotFoundException {
    return farmService.listFarmCrops(id)
        .stream()
        .map(CropDto::fromEntity)
        .toList();
  }
}
