[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/NYuLn2p4)

## App Description 

This app displays NCAA basketball scores for a selected date and gender (Men/Women). The app retrieves data from the NCAA API and displays a list of games with their current status, score, and start time. Users can change the date using a date picker, toggle between men’s and women’s games, and refresh the data.

The app is built using Jetpack Compose for the UI and follows a simple MVVM-style architecture with a repository layer. Data is fetched from the API using Retrofit and Moshi, then stored locally using Room so the app can still display cached scores when offline.

## Architecture 

The app is organized into several layers:

UI Layer
* ScoresScreen.kt displays the UI using Compose
* GameRow.kt renders each game card
* ScoresUiState.kt stores the UI state

ViewModel
* ScoresViewModel.kt manages UI state and handles user actions
* Uses StateFlow to update the UI reactively

Repository
* ScoresRepository.kt handles data logic
* Fetches data from the NCAA API
* Saves and reads cached data from Room

Data Layer
* NcaaApiService.kt defines the Retrofit API calls
* ScoreboardDto.kt stores the API response structure
* GameEntity.kt, GameDao.kt, and AppDatabase.kt handle local storage

Domain Layer
* Game.kt represents the app’s game model
* GameMapper.kt converts between API data, database entities, and domain models

## Offline Behavior

When the app refreshes data, the results are stored in the local Room database. If the device loses internet connection, the app will still display the cached games for that date.

## Acknowledgements 

Chat GPT 5.4 was used to help teach me concepts and debug different approaches of my own work in this assignment. 
