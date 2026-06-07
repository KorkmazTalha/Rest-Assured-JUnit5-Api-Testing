package com.api.test;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

// BaseTest sınıfını miras (extends) alarak URL yapısını otomatik devralıyoruz
public class UserApiRegressionTests extends BaseTest {

    @Test
    @DisplayName("Regresyon Testi 1: Tekil Post Getirme (GET İsteği)")
    public void testGetSinglePost() {
        given()
            .contentType(ContentType.JSON) // İstek tipi JSON olarak belirtildi
        .when()
            .get("/posts/1") // Base URL'in sonuna eklenecek endpoint
        .then()
            // İster 1: Status Code Kontrolü (Başarılı GET isteği için 200 OK bekleniyor)
            .statusCode(200)
            
            // İster 2: Response Body İçerisindeki Beklenen Değer Kontrolleri
            .body("id", equalTo(1))
            .body("userId", equalTo(1))
            .body("title", notNullValue()) // Title alanının boş (null) olmadığı doğrulanıyor
            .body("body", notNullValue())  // Body alanının boş olmadığı doğrulanıyor
            
            // İster 3: Belirli bir süre (X süre) altında cevap dönüldüğünün kontrolü
            // 3000 milisaniye (3 saniye) altında dönmesi bekleniyor
            .time(Matchers.lessThan(3000L));
    }

    @Test
    @DisplayName("Regresyon Testi 2: Yeni Post Oluşturma (POST İsteği ve Request Body Kullanımı)")
    public void testCreatePost() {
        // İdeal regresyon testi pratiği için gönderilecek JSON Request Body yapısı
        String requestBody = "{\n" +
                "    \"title\": \"Yazilim Test Muhendisligi Donem Odevi\",\n" +
                "    \"body\": \"Rest Assured Kutuphanesi ile Otomatik Regresyon Testi\",\n" +
                "    \"userId\": 1\n" +
                "}";

        given()
            .contentType(ContentType.JSON) // İçerik tipinin JSON olduğu belirtiliyor
            .body(requestBody)            // Hazırlanan request body isteğe ekleniyor
            .log().body()
        .when()
            .post("/posts")               // POST aksiyonu gerçekleştiriliyor
        .then()
        	.log().body()
            // İster 1: Status Code Kontrolü (Yeni kayıt başarıyla oluşturulduğu için 201 Created bekleniyor)
            .statusCode(201)
            
            // İster 2: Response Body İçerisindeki Beklenen Değer Kontrolleri
            .body("title", equalTo("Yazilim Test Muhendisligi Donem Odevi"))
            .body("body", equalTo("Rest Assured Kutuphanesi ile Otomatik Regresyon Testi"))
            .body("userId", equalTo(1))
            .body("id", notNullValue())   // API tarafından otomatik atanan id'nin varlığı kontrol ediliyor
            
            // İster 3: Süre Kontrolü (3000 ms altında cevap dönme şartı)
            .time(Matchers.lessThan(3000L));
    }
    
    @Test
    @DisplayName("Regresyon Testi 3: Var Olan Postu Silme (DELETE İsteği)")
    public void testDeletePost() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .delete("/posts/1") // 1 numaralı postu silme aksiyonu
        .then()
            // İster 1: Status Code Kontrolü (200 OK)
            .statusCode(200)
            
            // İster 2: Response Body Kontrolü (Boş obje kontrolü)
            .body(equalTo("{}")) 
            
            // İster 3: Süre Kontrolü
            .time(Matchers.lessThan(3000L));
    }
    
}
