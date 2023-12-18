package com.example.sb.resource;

import com.example.sb.model.dto.ResponsableDto;
import com.example.sb.model.dto.ResponsableRequest;
import com.example.sb.service.Impl.ResponsableManagerApplicationImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/responsables")
public class ResponsableResource extends Resource<ResponsableDto,ResponsableRequest,Long>{
    @Autowired
    public void setService(
            ResponsableManagerApplicationImpl service) {
        this.service = service;
    }

}