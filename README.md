# SpotifyAPITestDemo
Project to demonstrate automated testing of selected endpoints of the Spotify API using TestNG and Rest-Assured in Java

A PostMan collection is also included in the project repo of the test designs

## Prerequisites
1. Jave installed and configured in `PATH`.
2. Maven installed and configured in `PATH`.
3. Spotify developer API credentials. Register and follow the instructions at
https://developer.spotify.com/documentation/web-api/tutorials/getting-started
4. Using the 'Client ID' and 'Client secret' created above replace the placeholders in config.properties
(ordinarily these would be system variables but in the interest of portability I've done this)

## Execute Test Suite
Run test suite and generate allure report via `mvn clean test allure:serve` (This will open the report automatically after execution)

## Scenarios Tested
1. Get album by valid ID
2. Get album by invalid ID
3. Get tracks of an album
4. Get artist by valid ID
5. Get artist by invalid ID
6. Fetch authentication token with valid credentials
7. Fetch authentication token with invalid credentials
8. Search for an artist by name
9. Search for a track by name
10. Search for an invalid type
11. Search with a search result limit
12. Search with pagination offset
13. Get a track with a valid ID
14. Get a track with an invalid ID
