package ru.plnvoran;

import ru.plnvoran.datamodel.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;


public class UseJacksonLibTest {
    private ClassLoader cl = UseJacksonLibTest.class.getClassLoader();

    @DisplayName("Проверка содержимого файла типа JSON")
    @Test
    void  parsingJsonFile() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        try (
                InputStream resource = cl.getResourceAsStream("person.json");
                InputStreamReader reader = new InputStreamReader(resource);
        ) {

            Person personData = objectMapper.readValue(reader, Person.class);

            Assertions.assertTrue(!personData.getIsGoodPerson());
            Assertions.assertEquals("John Silver", personData.getName());
            Assertions.assertEquals("The secret leader of the pirates", personData.getDescription());
            Assertions.assertEquals(777, personData.getId());
            Assertions.assertEquals(55, personData.getPersonalInfo().getSize());
            Assertions.assertEquals(170, personData.getPersonalInfo().getHeight());
            Assertions.assertEquals(80, personData.getPersonalInfo().getWeight());


            Assertions.assertEquals("London", personData.getCities().get(0));
            Assertions.assertEquals("Paris", personData.getCities().get(1));

        }
    }
}