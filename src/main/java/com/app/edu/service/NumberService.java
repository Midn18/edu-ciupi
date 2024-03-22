package com.app.edu.service;

import com.app.edu.dtos.NumberDto;
import java.util.List;

public interface NumberService {

    List<NumberDto> getAllNumbers();

    NumberDto getNumberById(Integer id);
}
