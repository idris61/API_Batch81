package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
public class ObjectMapperUtils {

    // new ObjectMapper().readValue(jsonInString, HashMap .class);
    // ==> ObjectMapper'ı her kullanacapımız zaman obje oluşturmamız gerekiyordu ve her seferinde try catch fırlatıyordu.

    private static ObjectMapper mapper;  // ==> static yaparak class adıyla ulaşacağız. obje oluşturmamıza gerek kalmayacak

    static {                             // ==> static blok, herşeyden önce çalışır. hatta constructor dan bile önce çalışır.
        mapper = new ObjectMapper();     // yani, constructor objeyi oluşturur, static bloc obje oluşumundan bile önce çalıştığı için
    }                                    // her obje oluşumunda tekardan oluşmamış olur.
                                         // ObjectMapper() oluşmuş oldu

    public static <T> T convertJsonToJava(String json, Class<T> cls){ // <T> ==> Generic Method (herhangi bir data tipi demek)
                                                                      // Json'ı istediğimiz herhangi bir class'a çevirecek.

        T javaResult = null;

        try {                                                         // exception ı da handle etmiş olduk.
            javaResult = mapper.readValue(json,cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return javaResult;
    }
    // convertJsonToJava() methodunu kullanarak, gireceğimiz String json'ı istediğimiz data tipine çevireceğiz.(ObjectMapper)
}