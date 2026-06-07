# 🚀 API Otomasyon & Regresyon Test Projesi

Bu proje, modern yazılım test mühendisliği pratikleri (Shift-Left, Test Piramidi) göz önünde bulundurularak geliştirilmiş kurumsal düzeyde bir **API Otomasyon ve Regresyon Test** çalışmasıdır.

Test senaryoları, canlı ortamda hizmet veren **JSONPlaceholder** REST API servisleri üzerinde uçtan uca (End-to-End) doğrulamalar gerçekleştirmektedir.

---

## 🛠️ Kullanılan Teknolojiler ve Mimari Altyapısı

* **Dil:** Java 11 / 17
* **API Test Kütüphanesi:** Rest Assured (Akıcı BDD sözdizimi altyapısı)
* **Test Framework:** JUnit 5 (Jupyter)
* **Build Aracı:** Maven
* **Doğrulama (Assertion):** Hamcrest Matchers

---

## 📐 Proje Klasör Yapısı (Maven Standartları)

Proje, yazılım dünyasında kabul görmüş standart Maven iskeletine uygun olarak tasarlanmıştır. `target` gibi geçici build dosyaları ve yerel IDE konfigürasyonları projeden arındırılarak temiz bir kod deposu (repository) sunulmuştur.

```text
├── src
│   ├── main
│   │   └── java          
│   └── test
│       └── java
│           └── com
│               └── api
│                   └── test
│                       ├── BaseTest.java                  # URL ve Merkezi Konfigürasyon yönetimi
│                       └── UserApiRegressionTests.java     # Canlı API Regresyon Test Senaryoları
├── pom.xml               # Proje bağımlılıkları ve kütüphane yönetim dosyası
