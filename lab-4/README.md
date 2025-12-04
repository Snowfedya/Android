# Lab 4: Jetpack Navigation

## 📋 Requirements
This laboratory work introduces **Jetpack Navigation Component** to manage app navigation, replacing manual `FragmentManager` transactions.

### ✅ Implemented Features
1.  **Navigation Graph**: Created `nav_graph.xml` defining all destinations (`OnboardFragment`, `SignInFragment`, `SignUpFragment`, `HomeFragment`) and actions.
2.  **NavHostFragment**: Replaced `FragmentContainerView` in `activity_main.xml` with `NavHostFragment` to host the navigation graph.
3.  **NavController**: Migrated all navigation logic to use `NavController`:
    *   `findNavController().navigate(R.id.action_...)` for screen transitions.
    *   `findNavController().popBackStack()` for back navigation.
4.  **Data Passing**: Updated data passing between `SignInFragment` and `HomeFragment` to use Bundle arguments compatible with Navigation Component.
5.  **Clean Architecture**: Removed manual fragment transaction logic from `MainActivity` and Fragments.

### 🛠️ Tech Stack
*   **Core**: `androidx.navigation:navigation-fragment-ktx`
*   **UI**: `androidx.navigation:navigation-ui-ktx`
*   **Architecture**: Single Activity, Navigation Graph

## 🚀 How to Run
1.  Open the project in Android Studio (folder `lab-4`).
2.  Run `./gradlew assembleDebug`.
3.  Verify that navigation works smoothly:
    *   Onboarding -> Sign In
    *   Sign In -> Sign Up -> (Back) -> Sign In
    *   Sign In -> Home (with email argument)

## 📱 Screenshots
*(Add screenshots here)*
