# Lotec_Case_Study
Lotec_Case_1, Lotec_Case_2, Lotec_Case_3

## Lotec_Case_3

https://the-internet.herokuapp.com sitesi üzerinde *Selenium WebDriver* ve *JUnit 5* kullanılarak yazılmış UI test otomasyon projesidir.

## Gereksinimler

Projeyi çalıştırmadan önce aşağıdakilerin sistemde kurulu olması gerekir:

- *Java JDK 21* veya üzeri
- *Maven 3.8+*
- *Google Chrome* (güncel sürüm)

> Not: ChromeDriver WebDriverManager tarafından otomatik olarak indirilir, manuel kurulum gerekmez.

## Kurulum

1. Projeyi klonlayın:
   bash
   git clone <repo-url>
   cd Lotec_Case_3-main
   

2. Bağımlılıkları indirin:
   bash
   mvn clean install -DskipTests
   

## Testleri Çalıştırma

Tüm testleri çalıştırmak için:
bash
mvn test


Sadece belirli bir test sınıfını çalıştırmak için:
bash
mvn test -Dtest=LoginTest


## Proje Yapısı

```
Lotec_Case_3-main/
├── pom.xml
└── src/
    └── test/
        ├── java/
        │   ├── BaseTest.java             # Ortak WebDriver kurulum/teardown
        │   ├── LoginTest.java            # Login & logout senaryoları
        │   ├── DynamicLoadingTest.java   # Dinamik yüklenen element senaryoları
        │   ├── FileUploadTest.java       # Dosya yükleme senaryoları
        │   └── TableTest.java            # Tablo işlemleri senaryoları
        └── resources/
            └── Upload/
                └── example.txt           # Upload testi için örnek dosya

```
## Kullanılan Teknolojiler

- Java 21
- Maven
- Selenium Java 4.35.0
- JUnit Jupiter 5.13.4
- WebDriverManager 6.1.0
