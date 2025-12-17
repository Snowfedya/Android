# Lab 7: Local Database (Room)

## 📋 Requirements
This laboratory work integrates a local database using **Room** to implement the **Single Source of Truth (SSOT)** pattern.

### ✅ Implemented Features
1.  **Room Database**:
    *   `RestaurantEntity`: Database table definition.
    *   `RestaurantDao`: Data Access Object with `Flow` return types.
    *   `AppDatabase`: Singleton database instance.
2.  **Repository Pattern**:
    *   `RestaurantRepository`: Mediates between Domain, Database, and Network.
    *   **SSOT**: UI observes Database; Network updates Database.
3.  **Reactive UI**:
    *   `HomeFragment` observes `Flow<List<RestaurantItem>>` from Repository.
    *   UI updates automatically when DB changes.
4.  **Offline Support**:
    *   App shows cached data immediately (Cold Start).
    *   Network errors don't clear the screen (cached data remains).
5.  **Swipe to Refresh**:
    *   `SwipeRefreshLayout` triggers network update.

### 🛠️ Tech Stack
*   **Database**: `androidx.room`
*   **Concurrency**: `Kotlin Coroutines` + `Flow`
*   **Architecture**: MVVM (Repository pattern)

## 🚀 How to Run
1.  Open the project in Android Studio (folder `lab-7`).
2.  Run `./gradlew assembleDebug`.
3.  **Cold Start**:
    *   First launch: Spinner -> Data loads from Network -> Saves to DB -> Shows on screen.
    *   Restart app (Airplane mode): Data loads instantly from DB.
4.  **Refresh**:
    *   Pull down to refresh -> Fetches new data -> Updates DB -> UI updates.

## 📱 Screenshots
*(Add screenshots here)*
