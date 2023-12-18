package com.WI.WIGOLDFISH.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<DTO_RSP, DTO_REQ, F>  {
    DTO_REQ save(DTO_REQ dtoMini);

    DTO_REQ update(DTO_REQ dtoMini, F f);

    Boolean delete(F f);

    DTO_RSP findOne(F f);

    List<DTO_RSP> findAll();
}
