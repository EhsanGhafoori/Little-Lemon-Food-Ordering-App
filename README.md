# Little Lemon food ordering app (Android / Kotlin)

Peer-graded capstone: **onboarding + profile + persisted user details**, **Navigation Compose** (back stack), **home hero + search + scrollable menu list**, plus **Retrofit → Room** menu data.

## Peer rubric (self-check)

- **Wireframe image:** Export your Home screen wireframe from Figma as **JPG**, commit it to this repo (for example `wireframe_home.jpg` next to `README.md`) so reviewers can open it from GitHub.
- **First launch:** Shows onboarding (`OnboardingScreen.kt`) to collect first name, last name, email; **Register** saves via **DataStore** (`UserPreferencesRepository.kt`).
- **Home layout:** Hero (Little Lemon / Chicago + restaurant copy + **search**) and **menu list** (`HomeScreen.kt`, `MenuItemsList.kt`).
- **Profile:** Populated from saved details (`ProfileScreen.kt`).
- **Persistence:** User fields survive app restart (DataStore on disk).
- **Log out:** Clears saved profile data and returns to onboarding.
- **Navigation:** Stack navigation between onboarding, home, and profile; system **Back** returns from Profile → Home (`LittleLemonApp.kt`).
- **Menu list:** Summarized rows (title, description, price, image placeholder).
- **Reviewer prompt:** Be ready to suggest one improvement (UX, accessibility, tests, etc.).

## Technical map

| Area | Files |
|------|--------|
| Navigation | `LittleLemonApp.kt`, `AppDestinations.kt` |
| User prefs | `UserPreferencesRepository.kt` |
| Screens | `OnboardingScreen.kt`, `HomeScreen.kt`, `ProfileScreen.kt` |
| Menu data | `MenuApi.kt`, `MenuRepository.kt`, `AppDatabase.kt`, `MenuDao.kt`, `MenuViewModel.kt` |
| Layout | `HomeScreen.kt` (hero + chips + search), `MenuItemsList.kt` |

## Remote JSON

- Bundled: `app/src/main/assets/menu.json`
- Repo root: `menu.json` (GitHub raw URL used by Retrofit)
- Base URL is set in `MenuRepository.kt` — push so `menu.json` matches.

## Project location

`D:\Documents\Projects\courses\17.04\test1\LittleLemonMenu`

## Coursera submission

1. Add your wireframe export to the repo and push (for example `wireframe_home.png`).
2. Coursera → **My submission** → Project title + **GitHub repo URL** (HTTPS) → Preview → Submit.
3. Review two peers: clone their repo, open in Android Studio, run on emulator/device.

### Important

- Submit the URL of this Little Lemon mobile app repository, not an unrelated repository.
- Reviewer checks include persisted onboarding/profile data, category filtering, and search behavior.

## Zip upload (if zip is accepted instead of GitHub)

Delete `build/` folders, then zip the whole `LittleLemonMenu` folder.

## Open locally

Android Studio → Open `LittleLemonMenu` → Sync Gradle → Run.
