package com.api.AFTAS.domains.fish;

import com.api.AFTAS.config.CrudInterface;
import com.api.AFTAS.domains.fish.DTOs.FishReqDTO;
import com.api.AFTAS.domains.fish.DTOs.FishRespDTO;

public interface FishServiceInterface extends CrudInterface<FishReqDTO, FishRespDTO,String> {
}
