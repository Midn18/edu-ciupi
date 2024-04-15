package com.app.edu.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import com.app.edu.dtos.ResourceDto;
import com.app.edu.service.EducationalResourceServiceImpl;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class EducationalResourceControllerTest {

    @Mock
    private EducationalResourceServiceImpl educationalResourceService;

    @InjectMocks
    private EducationalResourceController educationalResourceController;

    @Test
    void getAllResourcesWhenResourcesExistReturnsResources() {
        ResourceDto resourceDto = new ResourceDto(1, "Alfabetul Disney", "src/test/resources/educationalResources/AlfabetulDisney.pdf");
        when(educationalResourceService.getAllResources()).thenReturn(Arrays.asList(resourceDto));

        ResponseEntity<?> response = educationalResourceController.getAllResources();

        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof List);
        assertEquals(1, ((List<?>) response.getBody()).size());
    }

    @Test
    void getAllResourcesWhenNoResourcesReturnsNotFound() {
        when(educationalResourceService.getAllResources()).thenReturn(Arrays.asList());
        ResponseEntity<?> response = educationalResourceController.getAllResources();
        assertEquals(NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getAllResourcesWhenErrorOccursReturnsInternalServerError() {
        when(educationalResourceService.getAllResources()).thenThrow(new RuntimeException("Unexpected error"));
        ResponseEntity<?> response = educationalResourceController.getAllResources();
        assertEquals(INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void downloadResourceWhenFileNotFoundReturnsNotFound() throws Exception {
        given(educationalResourceService.downloadResource(anyInt())).willThrow(FileNotFoundException.class);
        ResponseEntity<InputStreamResource> response = educationalResourceController.downloadResource(999);
        assertEquals(NOT_FOUND, response.getStatusCode());
    }

    @Test
    void downloadResourceWhenErrorOccursReturnsInternalServerError() throws Exception {
        given(educationalResourceService.downloadResource(anyInt())).willThrow(RuntimeException.class);
        ResponseEntity<InputStreamResource> response = educationalResourceController.downloadResource(1);
        assertEquals(INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
