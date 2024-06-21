# Bredex Test

## Leírás

Az alkalmazás egy internetes álláskereső oldal API szolgáltatását kívánja megvalósítani.

Felhasznált technológiák:
  - [Spring (Spring Boot version 2.5.7)](https://spring.io)
  - [Apache Maven](https://maven.apache.org)
  - [Java 11](https://www.java.com/en/)
  - [Swagger UI (version 2.9.2)](https://swagger.io/tools/swagger-ui/)

### Konfigurálás

- Fejlesztői környezet konfigurálása:
    - Amazon Corretto Java 11 SDK telepítése
    - Bármilyen IDE vagy code editor használható (IntelliJ ajánlott)

- Futtatás (több opció):
    1. IntelliJ beépített futtató környezete
    2. Java CLI
    3. Maven CLI

## Továbbfejlesztésre való javaslatok

- UUID token helyett JWT használata
- Authorizációs és authentikációs rétegek implementálása
- Érzékeny adatok titkosítása
- Perzisztens adatbázis használata
- Az adatbázisban lévő rekordoknak több attribútumot kell tartalmazniuk
- Loggolás, monitorozás
- Automatizált/Manuális tesztelés pl.: JUnit
- Konténerizálás Docker használatával