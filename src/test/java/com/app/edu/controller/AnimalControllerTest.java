package com.app.edu.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import com.app.edu.dtos.AnimalDto;
import com.app.edu.service.AnimalServiceImpl;
import com.app.edu.utils.AnimalTypeEnum;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class AnimalControllerTest {

    @Mock
    private AnimalServiceImpl animalService;

    @InjectMocks
    private AnimalController animalController;

    private AnimalDto animalDto;
    private Resource mockResource;

    @BeforeEach
    void setup() {
        animalDto = new AnimalDto(AnimalTypeEnum.DOMESTIC, "Dog", "Germany");
        animalDto.setId(1);
        animalDto.setName("Rex");
        animalDto.setSoundPath("path/to/sound");
        animalDto.setImagePath("path/to/image");
        mockResource = mock(Resource.class);
    }

    @Test
    void testGetAnimalsByAnimalTypeWithResults() {
        when(animalService.getAnimalsByAnimalType(AnimalTypeEnum.DOMESTIC)).thenReturn(Arrays.asList(animalDto));
        ResponseEntity<List<AnimalDto>> response = animalController.getAnimalsByAnimalType(AnimalTypeEnum.DOMESTIC.ordinal());

        assertEquals(OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Rex", response.getBody().get(0).getName());
    }

    @Test
    void testGetAnimalsByAnimalTypeNoResults() {
        when(animalService.getAnimalsByAnimalType(AnimalTypeEnum.WILD)).thenReturn(Arrays.asList());

        ResponseEntity<List<AnimalDto>> response = animalController.getAnimalsByAnimalType(AnimalTypeEnum.WILD.ordinal());

        assertEquals(NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testGetAnimalByIdFound() {
        when(animalService.getAnimalById(1)).thenReturn(animalDto);
        ResponseEntity<AnimalDto> response = animalController.getAnimalById(1);
        assertEquals(OK, response.getStatusCode());
        assertEquals("Rex", response.getBody().getName());
    }

    @Test
    void testGetAnimalByIdNotFound() {
        when(animalService.getAnimalById(2)).thenReturn(null);
        ResponseEntity<AnimalDto> response = animalController.getAnimalById(2);
        assertEquals(NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testReturnAnimalSoundPathSuccess() {
        when(animalService.returnSoundPath(1)).thenReturn(mockResource);
        ResponseEntity<Resource> response = animalController.returnAnimalSoundPath(1);
        assertEquals(OK, response.getStatusCode());
    }

    @Test
    void testReturnAnimalSoundPathNotFound() {
        given(animalService.returnSoundPath(2)).willThrow(new RuntimeException("Animal not found"));
        ResponseEntity<?> response = animalController.returnAnimalSoundPath(2);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testReturnAnimalImagePathSuccess() {
        when(animalService.returnImagePath(1)).thenReturn(mockResource);
        ResponseEntity<Resource> response = animalController.returnAnimalImagePath(1);
        assertEquals(OK, response.getStatusCode());
    }

    @Test
    void testReturnAnimalImagePathNotFound() {
        given(animalService.returnImagePath(2)).willThrow(new RuntimeException("Animal not found"));
        ResponseEntity<?> response = animalController.returnAnimalImagePath(2);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
