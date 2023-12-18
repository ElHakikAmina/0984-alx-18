package com.api.AFTAS.domains.level;

import com.api.AFTAS.config.CrudController;
import com.api.AFTAS.config.CrudControllerInterface;
import com.api.AFTAS.config.ResponseMessage;
import com.api.AFTAS.domains.level.DTOs.LevelReqDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("level")
public class LevelController extends CrudController<LevelReqDTO,LevelReqDTO,Integer,LevelServiceInterface> {

}
