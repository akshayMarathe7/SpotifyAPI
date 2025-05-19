package utils;

import com.github.javafaker.Faker;

public class FakerUtils {
    public static String getName(){

        Faker faker=new Faker();
       String name= "playlist "+faker.regexify("[A-Za-z0-9 ,!@#$_-] {10}");
        return name;
    }
}
