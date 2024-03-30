package com.app.edu.config;

import static com.app.edu.utils.AgeCategoryEnum.AGE_3_5;
import static com.app.edu.utils.AgeCategoryEnum.AGE_6_7;
import static com.app.edu.utils.AnimalTypeEnum.DOMESTIC;
import static com.app.edu.utils.AnimalTypeEnum.WILD;

import com.app.edu.entities.AnimalEntity;
import com.app.edu.entities.CategoryEntity;
import com.app.edu.entities.ColorEntity;
import com.app.edu.entities.LetterEntity;
import com.app.edu.entities.PlanetEntity;
import com.app.edu.entities.ResourceEntity;
import com.app.edu.entities.ShapeEntity;
import com.app.edu.repository.AnimalRepository;
import com.app.edu.repository.CategoryRepository;
import com.app.edu.repository.ColorsRepository;
import com.app.edu.repository.EducationalResourceRepository;
import com.app.edu.repository.LetterRepository;
import com.app.edu.repository.PlanetsRepository;
import com.app.edu.repository.ShapeRepository;
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
    @Autowired PlanetsRepository planetRepository;
    @Autowired ShapeRepository shapeRepository;
    @Autowired LetterRepository letterRepository;
    @Autowired ColorsRepository colorsRepository;

    @Bean
    @Profile("dev")
    CommandLineRunner populateData() {
        return (args) -> {
            animalRepository.save(new AnimalEntity(1, "Câine", "src/main/resources/static/sounds/domesticAnimalsSounds/dogSound.wav",
                                                   "src/main/resources/static/images/domesticAnimalsImages/dogImage.png", DOMESTIC,
                                                   "Câinele este brav", "Peste tot"));
            animalRepository.save(new AnimalEntity(2, "Pisică", "src/main/resources/static/sounds/domesticAnimalsSounds/catSound.wav",
                                                   "src/main/resources/static/images/domesticAnimalsImages/catImage.png", DOMESTIC,
                                                   "Pisica este leneșă", "Peste tot"));
            animalRepository.save(new AnimalEntity(3, "Găină", "src/main/resources/static/sounds/domesticAnimalsSounds/chickenSound.wav",
                                                   "src/main/resources/static/images/domesticAnimalsImages/chickenImage.png", DOMESTIC,
                                                   "Găina face ouă", "Peste tot"));
            animalRepository.save(new AnimalEntity(4, "Vacă", "src/main/resources/static/sounds/domesticAnimalsSounds/cowSound.wav",
                                                   "src/main/resources/static/images/domesticAnimalsImages/cowImage.png", DOMESTIC,
                                                   "Vaca dă lapte", "Peste tot"));

            //wild animals
            animalRepository.save(new AnimalEntity(5, "Leu", "src/main/resources/static/sounds/wildAnimalsSounds/lionSound.wav",
                                                   "src/main/resources/static/images/wildAnimalsImages/lionImage.png", WILD,
                                                   "Leul este regele junglei", "Africa"));

            // categories
            categoryRepository.save(new CategoryEntity(1, "Animale", "src/main/resources/static/images/categories"
                + "/animalCategory.png","Aici vei avea posibilitatea să faci cu minunata lume a animalelor", AGE_3_5));
            categoryRepository.save(new CategoryEntity(2, "Cifre", "src/main/resources/static/images/categories"
                + "/mathCategory.png", "Aici vei învăța să numeri", AGE_3_5));
            categoryRepository.save(new CategoryEntity(3, "Litere", "src/main/resources/static/images/categories"
                + "/letterCategory.png","Aici vei învăța literele", AGE_3_5));
            categoryRepository.save(new CategoryEntity(4, "Culori", "src/main/resources/static/images/categories"
                + "/colorCategory.png","Aici vei învăța culorile", AGE_3_5));

            categoryRepository.save(new CategoryEntity(5, "Matematică", "src/main/resources/static/images/categories"
                + "/mathCategory.png", "Aici vei putea studia operațiile matematice", AGE_6_7));
            categoryRepository.save(new CategoryEntity(6, "Litere", "src/main/resources/static/images/categories"
                + "/letterCategory.png", "Aici studia literele și cuvintele", AGE_6_7));
            categoryRepository.save(new CategoryEntity(7, "Figuri geometrice", "src/main/resources/static/images/categories"
                + "/shapeCategory.png", "Aici vei putea studia figuri geometrice", AGE_6_7));
            categoryRepository.save(new CategoryEntity(8, "Științe", "src/main/resources/static/images/categories"
                + "/scienceCategory.png", "Aici vei putea studia științele", AGE_6_7));

            // educational resources
            educationalResourceRepository.save(new ResourceEntity(1, "Fișa distractivă", "src/main/resources/static/educationalResources/TestResource.pdf"));
            educationalResourceRepository.save(new ResourceEntity(2, "Omida Flămândă", "src/main/resources/static/educationalResources/OmidaMancacioasa.pdf"));
            educationalResourceRepository.save(new ResourceEntity(3, "Alfabetul Disney", "src/main/resources/static/educationalResources/AlfabetulDisney.pdf"));
            educationalResourceRepository.save(new ResourceEntity(4, "Căsuța cu cifre", "src/main/resources/static/educationalResources/CasutaCuCifre.pdf"));
            educationalResourceRepository.save(new ResourceEntity(5, "Învățăm cifrele", "src/main/resources/static/educationalResources/InvatamCifrele.pdf"));
            educationalResourceRepository.save(new ResourceEntity(6, "Cifre de colorat", "src/main/resources/static/educationalResources/CifreDeColorat.pdf"));
            educationalResourceRepository.save(new ResourceEntity(7, "Litere de tipar", "src/main/resources/static/educationalResources/LitereDeTipar.pdf"));
            educationalResourceRepository.save(new ResourceEntity(8, "Coloreaza Animalele", "src/main/resources/static/educationalResources/ColoreazaAnimalele.pdf"));
            educationalResourceRepository.save(new ResourceEntity(9, "Desenează Oița", "src/main/resources/static/educationalResources/DeseneazaOița.pdf"));
            educationalResourceRepository.save(new ResourceEntity(10, "Drumul Viorii", "src/main/resources/static/educationalResources/DrumulViorii.pdf"));
            educationalResourceRepository.save(new ResourceEntity(11, "Figuri geometrice", "src/main/resources/static/educationalResources/FiguriGeometrice.pdf"));
            educationalResourceRepository.save(new ResourceEntity(12, "Coloreaza Oul", "src/main/resources/static/educationalResources/OulDePasti.pdf"));

            // planets
            planetRepository.save(new PlanetEntity(1, "Mercur", "src/main/resources/static/sounds/planetsSounds/mercurySound.wav",
                                                   "src/main/resources/static/images/planetsImages/mercuryImage.png", "Mercur este cel mai aproape de Soare"));
            planetRepository.save(new PlanetEntity(2, "Venus", "src/main/resources/static/sounds/planetsSounds/venusSound.wav",
                                                    "src/main/resources/static/images/planetsImages/venusImage.png", "Venus este cel mai aproape de Pământ"));
            planetRepository.save(new PlanetEntity(3, "Pământ", "src/main/resources/static/sounds/planetsSounds/earthSound.wav",
                                                    "src/main/resources/static/images/planetsImages/earthImage.png", "Pământul este planeta noastră"));
            planetRepository.save(new PlanetEntity(4, "Marte", "src/main/resources/static/sounds/planetsSounds/marsSound.wav",
                                                    "src/main/resources/static/images/planetsImages/marsImage.png", "Marte este planeta roșie"));
            planetRepository.save(new PlanetEntity(5, "Jupiter", "src/main/resources/static/sounds/planetsSounds/jupiterSound.wav",
                                                    "src/main/resources/static/images/planetsImages/jupiterImage.png", "Jupiter este cel mai mare planetă"));
            planetRepository.save(new PlanetEntity(6, "Saturn", "src/main/resources/static/sounds/planetsSounds/saturnSound.wav",
                                                    "src/main/resources/static/images/planetsImages/saturnImage.png", "Saturn are inele"));
            planetRepository.save(new PlanetEntity(7, "Uranus", "src/main/resources/static/sounds/planetsSounds/uranusSound.wav",
                                                    "src/main/resources/static/images/planetsImages/uranusImage.png", "Uranus este o planetă ciudată"));
            planetRepository.save(new PlanetEntity(8, "Neptun", "src/main/resources/static/sounds/planetsSounds/neptuneSound.wav",
                                                    "src/main/resources/static/images/planetsImages/neptuneImage.png", "Neptun este cel mai îndepărtat de Soare"));

            // shapes
            shapeRepository.save(new ShapeEntity(1, "Cerc","src/main/resources/static/sounds/shapesSounds/circleSound.wav",
                                                 "src/main/resources/static/images/shapesImages/circleImage.png"));
            shapeRepository.save(new ShapeEntity(2, "Triunghi","src/main/resources/static/sounds/shapesSounds/triangleSound.wav",
                                                    "src/main/resources/static/images/shapesImages/triangleImage.png"));

            // letters
            letterRepository.save(new LetterEntity(1, "A", "src/main/resources/static/sounds/lettersSounds/A.wav",
                                                   "src/main/resources/static/images/lettersImages/aImage.png"));
            letterRepository.save(new LetterEntity(2, "B", "src/main/resources/static/sounds/lettersSounds/B.wav",
                                                    "src/main/resources/static/images/lettersImages/bImage.png"));
            letterRepository.save(new LetterEntity(3, "C", "src/main/resources/static/sounds/lettersSounds/C.wav",
                                                    "src/main/resources/static/images/lettersImages/cImage.png"));
            letterRepository.save(new LetterEntity(4, "D", "src/main/resources/static/sounds/lettersSounds/D.wav",
                                                    "src/main/resources/static/images/lettersImages/dImage.png"));
            letterRepository.save(new LetterEntity(5, "E", "src/main/resources/static/sounds/lettersSounds/E.wav",
                                                    "src/main/resources/static/images/lettersImages/eImage.png"));
            letterRepository.save(new LetterEntity(6, "F", "src/main/resources/static/sounds/lettersSounds/F.wav",
                                                    "src/main/resources/static/images/lettersImages/fImage.png"));
            letterRepository.save(new LetterEntity(7, "G", "src/main/resources/static/sounds/lettersSounds/G.wav",
                                                    "src/main/resources/static/images/lettersImages/gImage.png"));
            letterRepository.save(new LetterEntity(8, "H", "src/main/resources/static/sounds/lettersSounds/H.wav",
                                                    "src/main/resources/static/images/lettersImages/hImage.png"));
            letterRepository.save(new LetterEntity(9, "I", "src/main/resources/static/sounds/lettersSounds/I.wav",
                                                   "src/main/resources/static/images/lettersImages/iImage.png"));
            letterRepository.save(new LetterEntity(10, "J", "src/main/resources/static/sounds/lettersSounds/J.wav",
                                                    "src/main/resources/static/images/lettersImages/jImage.png"));
            letterRepository.save(new LetterEntity(11, "K", "src/main/resources/static/sounds/lettersSounds/K.wav",
                                                    "src/main/resources/static/images/lettersImages/kImage.png"));
            letterRepository.save(new LetterEntity(12, "L", "src/main/resources/static/sounds/lettersSounds/L.wav",
                                                    "src/main/resources/static/images/lettersImages/lImage.png"));
            letterRepository.save(new LetterEntity(13, "M", "src/main/resources/static/sounds/lettersSounds/M.wav",
                                                    "src/main/resources/static/images/lettersImages/mImage.png"));
            letterRepository.save(new LetterEntity(14, "N", "src/main/resources/static/sounds/lettersSounds/N.wav",
                                                    "src/main/resources/static/images/lettersImages/nImage.png"));
            letterRepository.save(new LetterEntity(15, "O", "src/main/resources/static/sounds/lettersSounds/O.wav",
                                                    "src/main/resources/static/images/lettersImages/oImage.png"));
            letterRepository.save(new LetterEntity(16, "P", "src/main/resources/static/sounds/lettersSounds/P.wav",
                                                    "src/main/resources/static/images/lettersImages/pImage.png"));
            letterRepository.save(new LetterEntity(17, "Q", "src/main/resources/static/sounds/lettersSounds/Q.wav",
                                                    "src/main/resources/static/images/lettersImages/qImage.png"));
            letterRepository.save(new LetterEntity(18, "R", "src/main/resources/static/sounds/lettersSounds/R.wav",
                                                    "src/main/resources/static/images/lettersImages/rImage.png"));
            letterRepository.save(new LetterEntity(19, "S", "src/main/resources/static/sounds/lettersSounds/S.wav",
                                                    "src/main/resources/static/images/lettersImages/sImage.png"));
            letterRepository.save(new LetterEntity(20, "Ș", "src/main/resources/static/sounds/lettersSounds/Ș.wav",
                                                    "src/main/resources/static/images/lettersImages/șImage.png"));
            letterRepository.save(new LetterEntity(21, "T", "src/main/resources/static/sounds/lettersSounds/T.wav",
                                                    "src/main/resources/static/images/lettersImages/tImage.png"));
            letterRepository.save(new LetterEntity(22, "Ț", "src/main/resources/static/sounds/lettersSounds/Ț.wav",
                                                    "src/main/resources/static/images/lettersImages/țImage.png"));
            letterRepository.save(new LetterEntity(23, "U", "src/main/resources/static/sounds/lettersSounds/U.wav",
                                                    "src/main/resources/static/images/lettersImages/uImage.png"));
            letterRepository.save(new LetterEntity(24, "V", "src/main/resources/static/sounds/lettersSounds/V.wav",
                                                    "src/main/resources/static/images/lettersImages/vImage.png"));
            letterRepository.save(new LetterEntity(25, "W", "src/main/resources/static/sounds/lettersSounds/W.wav",
                                                    "src/main/resources/static/images/lettersImages/wImage.png"));
            letterRepository.save(new LetterEntity(26, "X", "src/main/resources/static/sounds/lettersSounds/X.wav",
                                                    "src/main/resources/static/images/lettersImages/xImage.png"));
            letterRepository.save(new LetterEntity(27, "Y", "src/main/resources/static/sounds/lettersSounds/Y.wav",
                                                    "src/main/resources/static/images/lettersImages/yImage.png"));
            letterRepository.save(new LetterEntity(28, "Z", "src/main/resources/static/sounds/lettersSounds/Z.wav",
                                                    "src/main/resources/static/images/lettersImages/zImage.png"));

            // colors
            colorsRepository.save(new ColorEntity(1, "Roșu", "src/main/resources/static/sounds/colorsSounds/redSound.wav",
                                                  "src/main/resources/static/images/colorsImages/red.png"));
            colorsRepository.save(new ColorEntity(2, "Albastru", "src/main/resources/static/sounds/colorsSounds/blueSound.wav",
                                                  "src/main/resources/static/images/colorsImages/blue.png"));
        };
    }
}
