# Lab 6: Data Storage & Persistence

## 📋 Requirements
This laboratory work introduces various data storage mechanisms in Android: **DataStore**, **SharedPreferences**, and **File Storage**.

### ✅ Implemented Features
1.  **SettingsFragment**: A new screen for managing app settings and backups.
2.  **DataStore (Preferences)**: Used for reactive settings (Theme, Notifications).
    *   `SettingsDataStore` class wraps DataStore operations.
    *   Exposes `Flow<Boolean>` for real-time updates.
3.  **SharedPreferences**: Used for simple key-value pairs (Email, Nickname, Backup Filename).
    *   `SettingsPreferences` wrapper class.
4.  **File Storage (Backup)**:
    *   **External Storage**: Saves restaurant list to `/Documents` (public directory).
    *   **Internal Storage**: Creates a hidden copy when "deleting" the external backup.
    *   **Restore**: Restores the hidden internal copy back to external storage.
5.  **Permissions**: Handles `READ_EXTERNAL_STORAGE` / `WRITE_EXTERNAL_STORAGE` permissions.

### 🛠️ Tech Stack
*   **Reactive Storage**: `androidx.datastore:datastore-preferences`
*   **Simple Storage**: `SharedPreferences`
*   **File API**: `java.io.File`, `Environment.getExternalStoragePublicDirectory`
*   **Coroutines**: `Dispatchers.IO` for file operations

## 🚀 How to Run
1.  Open the project in Android Studio (folder `lab-6`).
2.  Run `./gradlew assembleDebug`.
3.  Navigate to **Settings** (click on "Welcome..." text in Home screen).
4.  Toggle "Dark Theme" -> Observe reactive change (if implemented in Activity).
5.  Enter Email/Nickname -> Go back -> Return -> Data persists.
6.  **Backup**:
    *   Click "Create Backup" -> Check your device's Documents folder.
    *   Click "Delete Backup" -> File removed from Documents (but saved internally).
    *   Click "Restore Backup" -> File reappears in Documents.

## 📱 Screenshots
*(Add screenshots here)*
