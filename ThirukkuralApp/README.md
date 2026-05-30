# திருக்குறள் | Thirukkural Android App

A daily Thirukkural app with notifications, bilingual support (Tamil & English),
and sequential delivery of all 1,330 kurals.

---

## Features
- 📅 **One Kural per day** in sequential order (1 → 1330, then loops)
- 🔔 **Daily push notifications** with configurable time
- 🌐 **Bilingual** — Tamil (with explanations) + English (translation + explanation)
- 📚 **Browse all 1330 kurals** with full-text search
- 🔍 **Search** by kural line, chapter name, or meaning
- 📤 **Share** any kural
- ⚙️ **Settings** — language, notification toggle, time picker, test notification
- 💾 **100% offline** — all data in local Room database (loaded from bundled JSON)

---

## Import into Android Studio

### Step 1 — Open Project
1. Open Android Studio (Arctic Fox or later)
2. **File → Open** → select the `ThirukkuralApp` folder
3. Click **OK** — Gradle sync will start automatically

### Step 2 — Sync Gradle
- Wait for the Gradle sync to complete (downloads dependencies)
- If prompted about missing Gradle wrapper JAR: click **OK** to let Android Studio download it

### Step 3 — Set Up Tamil Font
**Option A (Downloadable Font — recommended):**
- The `res/font/noto_sans_tamil.xml` is already configured for Google Fonts
- Requires internet on first app launch to download font
- Works out of the box

**Option B (Bundled TTF — for offline-only use):**
1. Download Noto Sans Tamil from https://fonts.google.com/noto/specimen/Noto+Sans+Tamil
2. Save as `app/src/main/res/font/noto_sans_tamil_regular.ttf`
3. Update `noto_sans_tamil.xml` to reference the local file (see file for instructions)

### Step 4 — Add Full Kural Data (Optional but Recommended)
The bundled `kurals.json` has complete data for Kurals 1–30.
To add all 1,330:
```bash
pip install requests
python3 fetch_kural_data.py
```
Or download a dataset from https://github.com/tk120404/thirukkural

### Step 5 — Configure local.properties
Edit `local.properties` and set your Android SDK path:
```
sdk.dir=/Users/YOUR_NAME/Library/Android/sdk     # macOS
sdk.dir=C\:\\Users\\YOUR_NAME\\AppData\\Local\\Android\\Sdk  # Windows
sdk.dir=/home/YOUR_NAME/Android/Sdk               # Linux
```
*(Android Studio usually sets this automatically)*

### Step 6 — Build & Run
- Connect a device or start an emulator (API 24+)
- Press **Run ▶** or Shift+F10

---

## Project Structure
```
app/src/main/
├── AndroidManifest.xml
├── assets/
│   └── kurals.json              ← All 1330 kural data
├── java/com/thirukkural/app/
│   ├── ThirukkuralApp.kt        ← Application class
│   ├── model/Kural.kt           ← Room entity
│   ├── data/
│   │   ├── db/KuralDao.kt
│   │   ├── db/KuralDatabase.kt
│   │   └── repository/KuralRepository.kt
│   ├── notifications/
│   │   ├── NotificationHelper.kt
│   │   ├── NotificationReceiver.kt  ← AlarmManager broadcast
│   │   └── BootReceiver.kt
│   ├── utils/
│   │   ├── PreferencesManager.kt
│   │   └── NotificationScheduler.kt
│   └── ui/
│       ├── MainActivity.kt
│       ├── splash/SplashActivity.kt
│       ├── home/HomeFragment.kt     ← Today's Kural
│       ├── kural/BrowseFragment.kt  ← All 1330 list
│       ├── kural/KuralDetailFragment.kt
│       └── settings/SettingsFragment.kt
└── res/
    ├── layout/          ← All XML layouts
    ├── navigation/      ← nav_graph.xml
    ├── drawable/        ← Icons + shapes
    ├── font/            ← Noto Sans Tamil
    ├── values/          ← strings, colors, themes, dimens
    ├── values-ta/       ← Tamil string overrides
    └── anim/            ← Transition animations
```

---

## Notification Flow
1. User sets time in Settings (default: 8:00 AM)
2. `NotificationScheduler.scheduleDaily()` uses `AlarmManager.setExactAndAllowWhileIdle()`
3. At the scheduled time, `NotificationReceiver` fires
4. It reads today's kural from Room DB, shows notification
5. Notification taps open `MainActivity` with the kural number as an extra
6. After firing, `PreferencesManager.advanceKural()` moves to next kural
7. On device reboot, `BootReceiver` reschedules the alarm

---

## Minimum Requirements
- **Android 7.0** (API 24) and above
- **Target SDK**: 34 (Android 14)
- Kotlin 1.9, Room 2.6, WorkManager 2.9

---

## Data License
Thirukkural text is in the public domain (composed ~300 BCE – 500 CE).
English translations and explanations sourced from public domain works by
G.U. Pope, W.H. Drew, and others.
