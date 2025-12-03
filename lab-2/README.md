# Lab 2: Activity Lifecycle & Intents

## 📋 Requirements
This laboratory work builds upon Lab 1 by introducing lifecycle management and advanced data passing.

### ✅ Implemented Features
1.  **BaseActivity**: A common base class for all activities that logs lifecycle events (`onCreate`, `onStart`, etc.) to Logcat with tag `LifecycleTag`.
2.  **Parcelable Data**: The `User` and `RestaurantItem` models now implement `Parcelable` for efficient inter-activity data transfer.
3.  **Legacy Result Pattern**: `SignInActivity` starts `SignUpActivity` using `startActivityForResult` (deprecated but required).
4.  **Data Return**: `SignUpActivity` returns the created `User` object to `SignInActivity` via `setResult`.

### 🛠️ Tech Stack
*   **Lifecycle**: `Log.d()` in BaseActivity.
*   **Data Passing**: `Intent`, `Bundle`, `Parcelable`.
*   **Navigation**: `startActivityForResult`, `onActivityResult`, `setResult`.

## 🚀 How to Run
1.  Open the project in Android Studio.
2.  Run `./gradlew assembleDebug`.
3.  Observe Logcat with filter `tag:LifecycleTag`.
4.  Go to Sign Up -> Register -> Observe email auto-fill in Sign In.

## 📱 Screenshots
*(Add screenshots here)*