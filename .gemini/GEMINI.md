# 📱 Android Lab Projects - Master Configuration

## 🎓 Context & Goal
**Project**: Progressive Android Development Course (7 Labs).
**Repository Structure**: Cumulative History.
**Target Audience**: Human Developers & AI Agents.

## 🏗️ Project Architecture & Git Flow

### 1. Branching Strategy
*   **Branch Name**: `lab-<N>` (e.g., `lab-1`, `lab-2`, ... `lab-7`).
*   **Base Branch**: Each new lab branch is created from the **previous lab's branch**.
    *   `lab-2` is branched from `lab-1`.
    *   `lab-3` is branched from `lab-2`.

### 2. Directory Structure (Cumulative)
Each branch must contain the folders of all previous labs plus the current one. This ensures history is preserved and easily accessible.

**Example Structure in branch `lab-3`:**
```text
MobileDev/ (Root)
├── .gemini/                # Agent Configuration
├── lab-1/                  # Completed Lab 1 Project (Archive)
├── lab-2/                  # Completed Lab 2 Project (Archive)
├── lab-3/                  # CURRENT Active Project
│   ├── app/
│   ├── build.gradle
│   └── ...
├── Android_PDF_.../        # Reference PDFs
├── README.md               # Master Navigation
└── tasks.md                # Progress Tracker
```

### 3. Technology Stack Roadmap (Whitelist)

| Lab | Key Tech & Restrictions |
| :--- | :--- |
| **Lab 1** | **Pure Activities**. No Fragments. `Intent` navigation. XML Layouts. |
| **Lab 2** | **Lifecycle & Intents**. `BaseActivity` logging. `Parcelable`. `startActivityForResult`. |
| **Lab 3** | **Fragments**. Single Activity. `FragmentContainerView`. `FragmentManager`. Result API. |
| **Lab 4** | **Navigation Component**. `nav_graph.xml`. `SafeArgs`. **ViewBinding** everywhere. |
| **Lab 5** | **Networking**. `Retrofit` / `Ktor`. `Kotlinx Serialization`. Internet Permission. |
| **Lab 6** | **Storage & Settings**. `DataStore` (Prefs). File I/O (External/Internal). SettingsFragment. |
| **Lab 7** | **Database**. `Room`. `DAO`. `Flow`. Offline-first logic. |

---

## 🤖 AI Agent Protocols

### 🔹 Protocol: Start New Lab (Setup)
**Trigger**: User asks "Start Lab N".
**Algorithm**:
1.  **Checkout**: `git checkout lab-(N-1)` (Ensure clean state).
2.  **Branch**: `git checkout -b lab-N`.
3.  **Archive Previous**: (Already done by directory structure, but ensure `lab-(N-1)` folder exists).
4.  **Create New Folder**: `cp -r lab-(N-1) lab-N`.
5.  **Clean Gradle**: Update `lab-N/settings.gradle` to point to `:app` inside `lab-N` (if complex) or usually standard structure works if `lab-N` is treated as root for Gradle calls. *Note: Usually we treat `lab-N` as the project root for opening in IDE.*
6.  **Update Docs**: Update root `README.md` and `tasks.md`.
7.  **Commit**: `git commit -m "chore: setup structure for lab-N"`.

### 🔹 Protocol: Validate Lab
**Trigger**: "Check code" or before finishing.
1.  **Context**: Read `Android_PDF.../Lab N.pdf`.
2.  **Checklist**:
    *   Does it abide by the **Tech Whitelist** for Lab N?
    *   Does it violate the **Blacklist** (e.g., using Room in Lab 2)?
    *   Does it compile? (`cd lab-N && ./gradlew assembleDebug`)

### 🔹 Protocol: Refactoring/Downgrading
If code from a future lab is found in a current lab (e.g., Fragments in Lab 1):
1.  **Identify** the advanced code.
2.  **Refactor** to use simpler primitives allowed in that specific Lab level.

---

## 🛠️ Common Commands

### Build specific lab
```bash
cd lab-<N>
./gradlew assembleDebug
```

### Logcat with Lifecycle
```bash
adb logcat -s LifecycleTag
```

## 📝 Commit Convention
*   `feat: ...` for new requirements.
*   `fix: ...` for bug fixes.
*   `refactor: ...` for code improvements without logic changes.
*   `chore: ...` for setup/maintenance tasks.