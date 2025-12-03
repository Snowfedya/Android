# Lab 3: Fragments & FragmentManager

## 📋 Requirements
This laboratory work introduces Fragments as the primary UI building blocks, replacing separate Activities.

### ✅ Implemented Features
1.  **Single Activity Architecture**: The app now has only one `MainActivity` which hosts all screens.
2.  **FragmentContainerView**: Used in `activity_main.xml` to host fragments.
3.  **Fragment Conversion**:
    *   `OnboardActivity` → `OnboardFragment`
    *   `SignInActivity` → `SignInFragment`
    *   `SignUpActivity` → `SignUpFragment`
    *   `HomeActivity` → `HomeFragment`
4.  **Manual Navigation**: Used `parentFragmentManager.commit { ... }` for navigation transactions:
    *   `replace(R.id.fragment_container, ...)` to switch screens.
    *   `addToBackStack(null)` to enable "Back" button functionality.
    *   `popBackStack()` to return from Sign Up to Sign In.
5.  **Data Passing**:
    *   **Arguments (Bundle)**: Used to pass Email from `SignInFragment` to `HomeFragment`.
    *   **Fragment Result API**: Used to pass the created `User` object from `SignUpFragment` back to `SignInFragment` (`setFragmentResult` / `setFragmentResultListener`).

### 🛠️ Tech Stack
*   **Core**: `androidx.fragment:fragment-ktx`
*   **Container**: `FragmentContainerView`
*   **Navigation**: `FragmentManager`, `FragmentTransaction`
*   **Communication**: `Bundle`, `FragmentResultListener`

## 🚀 How to Run
1.  Open the project in Android Studio (folder `lab-3`).
2.  Run `./gradlew assembleDebug`.
3.  Verify that navigation works smoothly within a single Activity.

## 📱 Screenshots
*(Add screenshots here)*
