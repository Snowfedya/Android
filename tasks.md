# 📝 Mobile Development Tasks Tracker

## 📊 Progress Overview

| Lab | Status | Branch | Notes |
|---|---|---|---|
| **Lab 1** | ✅ Ready | `lab-1` | Pure Activities, No Fragments, Build Passing. |
| **Lab 2** | ⏳ Pending | - | Needs Lifecycle, Parcelable, startActivityForResult. |
| **Lab 3** | ⏳ Pending | - | Fragments introduction. |
| **Lab 4** | ⏳ Pending | - | Navigation Component, ViewBinding. |
| **Lab 5** | ⏳ Pending | - | Retrofit, API integration. |
| **Lab 6** | ⏳ Pending | - | DataStore, Settings. |
| **Lab 7** | ⏳ Pending | - | Room Database. |

## 🛠️ Current Tasks (Lab 1 Refactoring)
- [x] Initialize `lab-1` branch.
- [x] Remove `Navigation Component` dependencies.
- [x] Remove `Fragments` (`Home`, `Onboard`, `SignIn`, `SignUp`).
- [x] Create `Activities` (`Home`, `Onboard`, `SignIn`, `SignUp`).
- [x] Update `AndroidManifest.xml` to register Activities.
- [x] Downgrade `build.gradle` (Remove `safeargs`, `kotlin-parcelize`).
- [x] Fix `Java 25` vs `Gradle 8.13` compatibility issues (Using JDK 17).
- [x] Verify Build (`assembleDebug`).
- [x] Create Documentation (`README.md`).

## 🔮 Next Steps
1.  Start Lab 2 setup: `/project:setup-lab 2`.
2.  Implement Lifecycle logging.
3.  Implement Data passing via Parcelable.