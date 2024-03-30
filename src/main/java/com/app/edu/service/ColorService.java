package com.app.edu.service;

import com.app.edu.dtos.ColorDto;
import java.util.List;

public interface ColorService {

    List<ColorDto> getAllColors();

    ColorDto getColorById(Integer id);
}
