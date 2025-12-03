# Setup Lab Environment Strategy for Gemini

## Objective
Initialize a specific lab environment by branching from the previous state and configuring the necessary project files.

## Steps to Execute:
1.  **Branch Creation**:
    *   Check if `lab-<number>` branch exists.
    *   If not, create it from `lab-<number-1>` (or `main` for Lab 1).
    *   Command: `git checkout -b lab-<number> <base_branch>`

2.  **Code Migration**:
    *   If base code is missing, copy from `lab-<number-1>`.
    *   Ensure `AndroidManifest.xml` reflects the correct activities/permissions for the specific lab.

3.  **Dependency Management**:
    *   Read `build.gradle.kts` (or `build.gradle`).
    *   Add lab-specific dependencies (e.g., Retrofit for Lab 5, Room for Lab 7).
    *   Remove dependencies strictly forbidden in this lab (e.g., remove Navigation Component in Lab 1).

4.  **Documentation**:
    *   Create/Update `README.md` in the root of the lab branch.
    *   List specific requirements implemented in this lab.

5.  **Commit**:
    *   Stage changes: `git add .`
    *   Commit: `git commit -m "chore: setup lab-<number> environment"`
