# Lab 1: Introduction to Android Activities

## 📋 Requirements
This laboratory work demonstrates the fundamental building blocks of an Android application using **Pure Activities**.

### ✅ Implemented Features
1.  **OnboardActivity**: The entry point of the application (Launcher). Contains a "Get Started" button.
2.  **SignInActivity**: Allows users to sign in. Validates input. Links to Sign Up.
3.  **SignUpActivity**: Registration form with validation. Returns to Sign In upon success.
4.  **HomeActivity**: Displays a list of restaurant items using `RecyclerView`.

### 🚫 Restrictions (Strictly Observed)
*   No `Fragments`.
*   No `Navigation Component` (nav_graph).
*   No `ViewModel` or `LiveData`.
*   No `Room` database.
*   No `Retrofit` networking.
*   No `Parcelable` data passing (Basic Intent extras only).
*   No `BaseActivity` inheritance.

### 🛠️ Tech Stack
*   **Language**: Kotlin
*   **UI**: XML Layouts + ViewBinding (Minimal)
*   **Navigation**: `Intent` + `startActivity()`
*   **Lists**: `RecyclerView` + `ListAdapter`

## 🚀 How to Run
1.  Clone the repository.
2.  Switch to `lab-1` branch.
3.  Run `./gradlew assembleDebug` (requires JDK 17).
4.  Install on device: `./gradlew installDebug`.

## 📱 Screenshots
*(Add screenshots here after running on emulator)*
