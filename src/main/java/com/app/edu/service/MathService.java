package com.app.edu.service;

import com.app.edu.dtos.MathDto;
import java.util.List;

public interface MathService {

    List<MathDto> getAllMathProblems();

    MathDto getMathProblemById(Integer id);

    String compareAnswer(Integer id, String answer);
}
