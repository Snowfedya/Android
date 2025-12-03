# 📱 Android Lab Projects - Master Configuration

## 🎓 Project Context
**Course**: Разработка мобильных приложений 2025-2026 (ЦТУТП)  
**Goal**: Prepare 7 progressive Android lab projects (lab-1 through lab-7) as separate branches/directories  
**Repository**: https://github.com/Snowfedya/MobileDev  
**Current State**: Partial implementations in branches: lab1-2, lab3-4-1, main, Manus, test

## 🧠 Core Architecture Principles

### Project Structure Strategy
```
MobileDev/
├── lab-1/          # Pure Activities only
├── lab-2/          # Activities + Lifecycle + Intent data passing
├── lab-3/          # Fragments + FragmentManager
├── lab-4/          # Navigation Component + ViewBinding
├── lab-5/          # Networking (Retrofit/Ktor + Kotlinx Serialization)
├── lab-6/          # Data Storage (DataStore + SharedPreferences + External Storage)
└── lab-7/          # Room Database + Kotlin Flow
```

### Progressive Technology Whitelist

#### 🟢 LAB 1 - ALLOWED ONLY:
- `AppCompatActivity` (4 separate Activities)
- `Intent` + `startActivity()`
- XML layouts: `ConstraintLayout`, `LinearLayout`, `ScrollView`
- `RecyclerView` (optional for static list)
- `findViewById()` or minimal ViewBinding (if professor allows)
- Material Design 3 theme
- **MUST HAVE**: OnboardActivity, SignInActivity, SignUpActivity, HomeActivity

#### 🔵 LAB 2 - ADDS:
- Activity Lifecycle logging (`onCreate`, `onStart`, `onResume`, `onPause`, `onStop`, `onDestroy`)
- `startActivityForResult()` + `onActivityResult()`
- Data passing: `Intent.putExtra()` with primitives
- Custom `User` class as `Parcelable` or `Serializable`
- Lifecycle inheritance (base `BaseActivity` with logging)

#### 🟣 LAB 3 - ADDS:
- Convert Activities → Fragments (OnboardFragment, SignInFragment, SignUpFragment, HomeFragment)
- Single `MainActivity` with `FragmentContainerView`
- `FragmentManager` + `FragmentTransaction`
- Fragment-to-Fragment data passing (Bundle + setArguments)

#### 🟠 LAB 4 - ADDS:
- **Navigation Component**: nav_graph.xml + NavController
- **ViewBinding** (mandatory for all layouts)
- Safe Args for type-safe data passing
- Replace FragmentTransaction with Navigation actions

#### 🔴 LAB 5 - ADDS:
- Internet permission in AndroidManifest
- HTTP client: **Retrofit** or **Ktor Client**
- **Kotlinx Serialization** for JSON parsing
- Repository/DataSource pattern for network calls
- Error handling (no network, empty data)
- **API**: Game of Thrones (https://anapioficeandfire.com/api/characters)
- Display 50 characters based on student number

#### 🟤 LAB 6 - ADDS:
- **SettingsFragment** with customizable settings
- **DataStore** + **SharedPreferences** for settings storage
- File permissions in AndroidManifest
- External Storage: save data to `/Documents` or `/Downloads` as .txt
- Internal Storage: backup file functionality
- Settings: email, password change, notifications, theme, language, font size, backup management

#### ⚫ LAB 7 - ADDS:
- **Room Database**: Entity + DAO + Database
- CRUD operations through Repository
- "Cold start" logic: check Room → if empty, fetch from API
- "Refresh" button: re-fetch from API + update Room
- "Load more" button: fetch next batch (different student number)
- **Kotlin Flow** for reactive UI updates

## 🚫 CRITICAL CONSTRAINTS

### Technology Blacklist by Lab:
```
Lab 1: ❌ Fragments, Navigation, ViewBinding, MVVM, Dagger/Hilt, Retrofit, Room
Lab 2: ❌ Fragments, Navigation, ViewBinding, MVVM, Dagger/Hilt, Retrofit, Room
Lab 3: ❌ Navigation Component, ViewBinding, MVVM, Dagger/Hilt, Retrofit, Room
Lab 4: ❌ MVVM (ViewModel allowed in later labs), Dagger/Hilt, Retrofit, Room
Lab 5: ❌ MVVM (unless simple), Dagger/Hilt, Room (use in-memory lists)
Lab 6: ❌ MVVM (unless simple), Dagger/Hilt, Room
Lab 7: ✅ All technologies allowed (final state)
```

## 🛠️ Development Commands

### Build & Run
```bash
./gradlew clean assembleDebug
./gradlew installDebug
adb logcat | grep "LifecycleTag"  # For Lab 2 lifecycle logging
```

### Testing
```bash
./gradlew testDebugUnitTest
./gradlew connectedAndroidTest
```

### Git Workflow
```bash
# Create lab branches
git checkout -b lab-1 main
git checkout -b lab-2 lab-1
git checkout -b lab-3 lab-2
# ... continue for all 7 labs

# Never merge upward (lab-2 → lab-1), only forward (lab-1 → lab-2)
```

## 📋 Task Execution Protocol

### When I say "Prepare Lab X":
1. **Analyze**: Read the lab PDF requirements
2. **Audit**: Check current branch for over-engineered code
3. **Downgrade**: Remove technologies not yet introduced
4. **Preserve**: Keep ALL existing UI/UX design intact
5. **Validate**: Ensure AndroidManifest matches lab requirements
6. **Document**: Update README.md with lab-specific setup
7. **Test**: Verify app builds and runs without crashes

### Code Style Standards
- **Language**: Kotlin (prefer) or Java
- **Architecture**: KISS principle (Keep It Stupidly Simple) until Lab 5+
- **Naming**: 
  - Activities: `*Activity.kt`
  - Fragments: `*Fragment.kt`
  - Layouts: `activity_*.xml` / `fragment_*.xml`
- **Comments**: Russian or English (match my preference)

## 🔍 Key Validation Rules

### Lab 1 Checklist:
- [ ] Exactly 4 Activities in AndroidManifest
- [ ] OnboardActivity has MAIN/LAUNCHER intent-filter
- [ ] No Fragment imports anywhere
- [ ] No NavController or nav_graph.xml
- [ ] HomeActivity shows static RecyclerView list
- [ ] Material3 theme applied

### Lab 2 Checklist:
- [ ] Lifecycle methods logged in all Activities
- [ ] startActivityForResult used for SignUp → SignIn
- [ ] User object is Parcelable/Serializable
- [ ] Email and password displayed in SignInActivity after registration

### Lab 3 Checklist:
- [ ] Single MainActivity only
- [ ] 4 Fragments exist
- [ ] FragmentManager used for navigation
- [ ] Bundle used for data passing

### Lab 4 Checklist:
- [ ] nav_graph.xml exists with 4 destinations
- [ ] ViewBinding enabled in build.gradle
- [ ] All findViewById replaced with binding
- [ ] Safe Args plugin configured

### Lab 5 Checklist:
- [ ] Retrofit/Ktor dependency added
- [ ] Kotlinx Serialization configured
- [ ] Character data model created
- [ ] API response displayed in HomeFragment
- [ ] Error handling for network failures

### Lab 6 Checklist:
- [ ] SettingsFragment created
- [ ] DataStore + SharedPreferences both used
- [ ] External file saved to /Documents
- [ ] Backup/restore functionality works
- [ ] File permissions granted

### Lab 7 Checklist:
- [ ] Room database configured
- [ ] DAO with CRUD operations
- [ ] Cold start checks Room first
- [ ] Refresh button updates from API
- [ ] Kotlin Flow observes database changes

## 🎨 UI/UX Preservation Rules

### DO NOT CHANGE:
- Color schemes
- Layout dimensions
- Image resources
- Typography settings
- Existing ConstraintLayout structures

### ONLY CHANGE:
- Code architecture (Activity ↔ Fragment)
- Navigation mechanism (Intent ↔ FragmentTransaction ↔ NavController)
- Data binding (findViewById ↔ ViewBinding)

## 📚 Reference Documentation

### Official Android:
- Fragments: https://developer.android.com/guide/fragments
- Navigation: https://developer.android.com/guide/navigation
- ViewBinding: https://developer.android.com/topic/libraries/view-binding
- Room: https://developer.android.com/training/data-storage/room
- DataStore: https://developer.android.com/topic/libraries/architecture/datastore

### Project Examples:
- Networking: https://github.com/jacksever/rut-networks-android
- Room: https://github.com/jacksever/rut-android-room

### APIs:
- Game of Thrones: https://anapioficeandfire.com/
- Rick and Morty: https://rickandmortyapi.com/
- Disney: https://disneyapi.dev/

## 🤖 Claude Code Behavior

### Preferred Workflow:
1. Use `/init` to establish baseline
2. Create custom commands:
   - `/project:setup-lab <number>` - Initialize lab branch
   - `/project:validate-lab <number>` - Check compliance
   - `/project:downgrade-lab <number>` - Remove future tech
   - `/project:migrate-lab <from> <to>` - Upgrade lab version

### Memory Management:
- Store current lab number in CLAUDE.local.md
- Reference existing code from previous lab
- Never assume technologies from future labs exist

### Communication Style:
- Ask clarifying questions about student number (for API character range)
- Confirm destructive operations (deleting Fragments in Lab 1)
- Provide step-by-step migration plans

## 🎯 Success Criteria

### Each lab must:
1. Build without errors
2. Run without crashes
3. Pass professor's visual inspection
4. Demonstrate ONLY techniques taught up to that point
5. Maintain consistent UI/UX across all labs

### Final Deliverable:
- 7 clean branches (lab-1 through lab-7)
- Each branch tagged with lab number
- README.md in each branch explaining setup
- Screenshots for each lab
- APK builds for testing

***

## 💬 Special Instructions for Russian Context

Когда я пишу на русском:
- Комментарии в коде на английском (стандарт индустрии)
- Имена переменных/функций на английском
- Логи Logcat могут быть на русском для отладки
- README.md на русском для понимания преподавателем

## 🔐 Permissions Template

### For Lab 5+ (AndroidManifest.xml):
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

### For Lab 6+ (AndroidManifest.xml):
```xml
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" 
                 android:maxSdkVersion="28" />
<uses-permission android:name="android.permission.MANAGE_EXTERNAL_STORAGE"
                 tools:ignore="ScopedStorage" />
```

## 🧪 Testing Strategy

### Manual Testing Checklist:
- [ ] Fresh install on emulator (API 33+)
- [ ] Rotate device (config changes)
- [ ] Background/foreground transitions (Lab 2)
- [ ] Airplane mode (Lab 5)
- [ ] Clear app data (Lab 6/7)

### Automated Testing (Optional):
- Espresso UI tests for navigation flows
- JUnit tests for data models
- Mockito for repository testing