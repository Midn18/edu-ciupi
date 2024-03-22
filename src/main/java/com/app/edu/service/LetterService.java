package com.app.edu.service;

import com.app.edu.dtos.LetterDto;
import java.util.List;

public interface LetterService {

    List<LetterDto> getAllLetters();

    LetterDto getLetterById(Integer id);
}
