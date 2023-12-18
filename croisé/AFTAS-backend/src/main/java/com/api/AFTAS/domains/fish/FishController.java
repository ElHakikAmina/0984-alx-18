package com.api.AFTAS.domains.fish;

import com.api.AFTAS.config.CrudController;
import com.api.AFTAS.config.CrudControllerInterface;
import com.api.AFTAS.config.ResponseMessage;
import com.api.AFTAS.domains.fish.DTOs.FishReqDTO;
import com.api.AFTAS.domains.fish.DTOs.FishRespDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("fish")
public class FishController extends CrudController<FishReqDTO, FishRespDTO,String,FishServiceInterface> {
}