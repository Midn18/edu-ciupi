package com.app.edu.service;

import com.app.edu.dtos.ShapeDto;
import java.util.List;

public interface ShapeService {

    List<ShapeDto> getAllShapes();

    ShapeDto getShapeById(Integer id);
}
