package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonplaceholderBaseUrl {
    protected RequestSpecification spec;  // Base URL mizi RequestSpecification ın içine attık.
                                          // Protected oluşturduk ki projenin her yerinde child-parent ilişkisi içerisinde ulaşabilelim.

    @Before // Extend yaptığımız her class ta Test methodundan önce çalışması için ekledik.
    public void setup() {
        spec = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
    }
    /*
    Normalde constructor oluştururken class'la aynı isimde oluşturmamız gerekiyor.
    Fakat RequestSpecification bir interface, bunun için constructor oluşturamıyoruz.
    Bir alt class'ta bunu yapmamız gerekiyor. Bu yüzden RequestSpecBuilder'ı kullanıyoruz.



     */

}