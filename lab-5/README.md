# Lab 5: Networking & Coroutines

## 📋 Requirements
This laboratory work introduces **Networking** to fetch data from a remote API using **Retrofit** and **Kotlin Coroutines**.

### ✅ Implemented Features
1.  **Retrofit Client**: Configured a `Retrofit` instance with `GsonConverterFactory`.
2.  **API Interface**: Defined `RestaurantApiService` with suspend functions for network calls.
3.  **Coroutines**: Used `lifecycleScope` and `suspend` functions to perform asynchronous network operations without blocking the main thread.
4.  **Data Fetching**: Replaced hardcoded data in `HomeFragment` with real (or mocked) data fetched from an API.
5.  **Error Handling**: Implemented basic error handling (try-catch) for network failures.

### 🛠️ Tech Stack
*   **Networking**: `com.squareup.retrofit2:retrofit`
*   **Serialization**: `com.squareup.retrofit2:converter-gson`
*   **Concurrency**: `org.jetbrains.kotlinx:kotlinx-coroutines-android`
*   **Image Loading**: `com.github.bumptech.glide:glide` (Optional)

## 🚀 How to Run
1.  Open the project in Android Studio (folder `lab-5`).
2.  Run `./gradlew assembleDebug`.
3.  Verify that the list of restaurants is loaded from the network (simulated or real).

## 📱 Screenshots
*(Add screenshots here)*
