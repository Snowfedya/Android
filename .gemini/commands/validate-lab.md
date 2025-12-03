# Validate Lab Compliance Strategy for Gemini

## Objective
Verify that the current code strictly adheres to the constraints and requirements of the specific lab number.

## Validation Steps:
1.  **Context Loading**:
    *   Read `.gemini/GEMINI.md` to get the "Technology Whitelist" and "Blacklist" for the target lab.

2.  **Code Scan**:
    *   Use `search_file_content` or `grep` to look for forbidden imports.
    *   *Example (Lab 1)*: Search for `androidx.fragment.app.Fragment`, `androidx.navigation`, `androidx.room`.
    *   *Example (Lab 5)*: Ensure `Retrofit` or `Ktor` is present.

3.  **Manifest Check**:
    *   Read `app/src/main/AndroidManifest.xml`.
    *   Verify required Permissions exist.
    *   Verify required Activities are declared.

4.  **Build Check**:
    *   Run `./gradlew assembleDebug` (or ask user to run it) to ensure the project compiles.

5.  **Report Generation**:
    *   Output a summary:
        *   ✅ Compliant technologies
        *   ❌ Violations found (with file paths)
        *   ⚠️ Warnings
