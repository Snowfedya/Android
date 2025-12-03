# Downgrade Lab Strategy for Gemini

## Objective
Refactor the codebase to remove "future" technologies that are not allowed in the current lab (e.g., removing Fragments when backporting to Lab 1).

## Execution Plan:

1.  **Identification**:
    *   Scan for "advanced" files: `*Fragment.kt`, `nav_graph.xml`, `*ViewModel.kt`.
    *   Identify advanced dependencies in `build.gradle`.

2.  **Safety**:
    *   Ensure we are on the correct branch (`lab-<number>`).
    *   Recommend a backup commit before proceeding.

3.  **Refactoring (The Hard Part)**:
    *   **Fragments to Activities**:
        *   Take logic from `HomeFragment` -> Move to `HomeActivity`.
        *   Take layout `fragment_home.xml` -> Rename/Move to `activity_home.xml`.
        *   Update `AndroidManifest.xml` to register the new Activity.
    *   **Navigation**:
        *   Replace `findNavController().navigate(...)` with `startActivity(Intent(...))`.
    *   **ViewBinding (if forbidden)**:
        *   Replace `binding.textView.text = ...` with `findViewById<TextView>(R.id.textView).text = ...`.

4.  **Cleanup**:
    *   Remove unused files (old Fragments, nav graphs).
    *   Remove unused dependencies from `build.gradle`.
    *   Clean project: `./gradlew clean`.

5.  **Verification**:
    *   Run validation check (see `validate-lab.md`).
