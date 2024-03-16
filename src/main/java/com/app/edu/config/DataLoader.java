package com.app.edu.config;

import static com.app.edu.utils.AgeCategoryEnum.AGE_3_5;
import static com.app.edu.utils.AgeCategoryEnum.AGE_6_7;
import static com.app.edu.utils.AnimalTypeEnum.DOMESTIC;
import static com.app.edu.utils.AnimalTypeEnum.WILD;

import com.app.edu.entities.AnimalEntity;
import com.app.edu.entities.CategoryEntity;
import com.app.edu.entities.ResourceEntity;
import com.app.edu.repository.AnimalRepository;
import com.app.edu.repository.CategoryRepository;
import com.app.edu.repository.EducationalResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DataLoader {

    @Autowired AnimalRepository animalRepository;
    @Autowired CategoryRepository categoryRepository;
    @Autowired EducationalResourceRepository educationalResourceRepository;

    @Bean
    @Profile("dev")
    CommandLineRunner populateData() {
        return (args) -> {
            animalRepository.save(new AnimalEntity(1, "Câine", "src/main/resources/static/sounds/domesticAnimalsSounds/dogSound.wav",
                                                   "src/main/resources/static/images/dogImage.png", DOMESTIC,
                                                   "Câinele este brav", "Peste tot"));
            animalRepository.save(new AnimalEntity(2, "Pisică", "src/main/resources/static/sounds/domesticAnimalsSounds/catSound.wav",
                                                   "src/main/resources/static/images/catImage.png", DOMESTIC,
                                                   "Pisica este leneșă", "Peste tot"));
            animalRepository.save(new AnimalEntity(3, "Găină", "src/main/resources/static/sounds/domesticAnimalsSounds/chickenSound.wav",
                                                   "src/main/resources/static/images/chickenImage.png", DOMESTIC,
                                                   "Găina face ouă", "Peste tot"));
            animalRepository.save(new AnimalEntity(4, "Vacă", "src/main/resources/static/sounds/domesticAnimalsSounds/cowSound.wav",
                                                   "src/main/resources/static/images/cowImage.png", DOMESTIC,
                                                   "Vaca dă lapte", "Peste tot"));

            //wild animals
            animalRepository.save(new AnimalEntity(5, "Leu", "src/main/resources/static/sounds/wildAnimalsSounds/lionSound.wav",
                                                   "src/main/resources/static/images/lionImage.png", WILD,
                                                   "Leul este regele junglei", "Africa"));

            // categories
            categoryRepository.save(new CategoryEntity(1, "Animale", "src/main/resources/static/images/categories/animalCategory.png", AGE_3_5));
            categoryRepository.save(new CategoryEntity(2, "Cifre", "src/main/resources/static/images/categories/animalCategory.png", AGE_3_5));
            categoryRepository.save(new CategoryEntity(3, "Litere", "src/main/resources/static/images/categories/animalCategory.png", AGE_3_5));
            categoryRepository.save(new CategoryEntity(4, "Culori", "src/main/resources/static/images/categories/animalCategory.png", AGE_3_5));

            categoryRepository.save(new CategoryEntity(5, "Matematică", "src/main/resources/static/images/categories/animalCategory.png", AGE_6_7));
            categoryRepository.save(new CategoryEntity(6, "Litere", "src/main/resources/static/images/categories/animalCategory.png", AGE_6_7));
            categoryRepository.save(new CategoryEntity(7, "Figuri geometrice", "src/main/resources/static/images/categories/animalCategory.png", AGE_6_7));
            categoryRepository.save(new CategoryEntity(8, "Științe", "src/main/resources/static/images/categories/animalCategory.png", AGE_6_7));

            // educational resources
            educationalResourceRepository.save(new ResourceEntity(1, "Test resource", "src/main/resources/static/educationalResources/TestResource.pdf"));
            educationalResourceRepository.save(new ResourceEntity(2, "Omida Mâncăcioasă", "src/main/resources/static/educationalResources/OmidaMancacioasa.pdf"));
        };
    }
}
