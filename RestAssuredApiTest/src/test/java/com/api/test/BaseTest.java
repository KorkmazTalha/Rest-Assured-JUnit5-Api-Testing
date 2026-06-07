package com.api.test;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    public static void setup() {
        // Profesyonel regresyon pratiklerine uygun olarak API servisinin taban adresi tanımlandı.
        // İleride test ortamı değişirse (Test/Prod) sadece burayı değiştirmek yeterli olacaktır.
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }
}