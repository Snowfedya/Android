# 📱 Mobile Development Labs (Android)

This repository contains a series of progressive laboratory works for the Mobile Development course. Each lab represents a specific stage in Android app development history and architecture.

## 📂 Project Structure & Navigation

The project uses a **cumulative directory structure**. Each branch contains the work for the current lab plus all previous labs as separate, archived projects.

| Lab # | Topic | Directory | Branch | Status |
| :--- | :--- | :--- | :--- | :--- |
| **Lab 1** | **Pure Activities**<br>No Fragments, Basic Intents. | [`lab-1/`](./lab-1) | `lab-1` | ✅ Completed |
| **Lab 2** | **Lifecycle & Data**<br>BaseActivity, Parcelable, Result API. | [`lab-2/`](./lab-2) | `lab-2` | ✅ Completed |
| **Lab 3** | **Fragments**<br>Single Activity, FragmentManager. | [`lab-3/`](./lab-3) | `lab-3` | ✅ Completed |
| **Lab 4** | **Jetpack Navigation**<br>NavController, ViewBinding. | [`lab-4/`](./lab-4) | `lab-4` | ✅ Completed |
| **Lab 5** | **Networking**<br>Retrofit, API, Coroutines. | [`lab-5/`](./lab-5) | `lab-5` | ⏳ Pending |
| **Lab 6** | **Data Storage**<br>DataStore, Files, Settings. | [`lab-6/`](./lab-6) | `lab-6` | ⏳ Pending |
| **Lab 7** | **Database**<br>Room, Flow, Offline-first. | [`lab-7/`](./lab-7) | `lab-7` | ⏳ Pending |

## 🤖 Instructions for AI Agents / Developers

### Prerequisites
*   **JDK**: 17+
*   **Android SDK**: API 34 (Target) / API 24 (Min)

### How to switch between labs
To work on a specific lab, check out its corresponding branch.
```bash
git checkout lab-3  # To see Lab 3 (and 1, 2)
```

### How to run a specific lab
Navigate to the lab's directory and run Gradle:
```bash
cd lab-3
./gradlew installDebug
```

### How to start a NEW lab (Agent Instruction)
1.  Ensure you are on the latest completed branch (e.g., `lab-3`).
2.  Create a new branch (e.g., `lab-4`).
3.  Copy the previous lab folder to the new lab folder (e.g., `cp -r lab-3 lab-4`).
4.  Perform the necessary code changes inside the new folder only.
5.  Update this README.

---
**Student Info:**
*   **Name**: [Your Name]
*   **Group**: [Your Group]
*   **Variant**: [Your Number] (Please update `.gemini/GEMINI.local.md`)
