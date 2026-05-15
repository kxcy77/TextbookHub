# TextbookHub SS3 Implementation Documentation

Module: MADA372 Mobile Application Development  
Student Number: 24301339  
Project: TextbookHub / Textbook Store App  
SS3 Focus: Android Studio development implementation

## 1. SS3 Implementation Update

The SS3 submission converts the SS1 strategy and SS2 prototype into a working Android application built with Kotlin, XML layouts, Gradle, and Android Studio. The implemented application is a student-focused textbook marketplace where users can register, sign in, browse books, search listings, view book details, send inquiries, list textbooks for sale, view messages, reply in chat threads, and view profile information saved from registration.

The project is implemented as an Android app package:

- Application ID: `com.textbookhub.app`
- Main branch: `main`
- Development branch: `development`
- Build command: `.\gradlew.bat assembleDebug`
- Debug APK output: `app/build/outputs/apk/debug/app-debug.apk`

## 2. Alignment Between SS1, SS2, and SS3

| SS1 Strategy Requirement | SS2 Design / Prototype Response | SS3 Development Implementation |
| --- | --- | --- |
| User registration interface | Registration screen with full name, student number, institution, email, and password fields | `RegistrationActivity.kt` and `activity_registration.xml` implement the form, validation, STADIO institution dropdown, and saved profile details |
| Login / returning user flow | Login screen included in sitemap and prototype flow | `LoginActivity.kt` and `activity_login.xml` implement email/password sign-in with validation before entering Home |
| Book listing display | Home screen shows scrollable textbook cards | `HomeActivity.kt`, `BookAdapter.kt`, and `item_book_card.xml` show sample textbook data in a RecyclerView |
| Search textbooks | Search bar and category chips shown on Home prototype | `HomeActivity.kt` filters books by title, author, course code, condition, or category |
| Book detail view | Detail screen displays full book information and seller details | `BookDetailActivity.kt` and `activity_book_detail.xml` open from selected book cards and pass book data with intent extras |
| Seller inquiry messaging | Inquiry screen lets a buyer contact a seller | `InquiryActivity.kt` and `activity_inquiry.xml` allow message sending and redirect to Messages |
| Messages / conversation screen | Messages screen and buyer-seller thread included in information architecture | `MessagesActivity.kt`, `MessageAdapter.kt`, and `MessageThreadActivity.kt` implement inbox items, conversation view, message input, validation, and send action |
| Add book listing | Sell screen for title, author, price, condition, and photo | `SellActivity.kt` and `activity_sell.xml` validate listing fields and open the Android image picker for book photos |
| Navigation between screens | Bottom navigation / main section access planned in SS2 | Home provides visible navigation to Sell Book, Messages, and Profile; screen back buttons and close actions are wired |
| Error handling and input validation | SS1 and SS2 specify error states and invalid-input feedback | Login, registration, sell listing, and chat reply screens validate empty/invalid input and display errors or toasts |
| Mobile-first Android interface | SS2 prototype designed for 360 x 800 dp Android screen | XML ConstraintLayout/CardView/RecyclerView screens are implemented for Android emulator and physical devices |
| Version control workflow | SS1 DevOps plan requires main/development branches and meaningful commits | Git repository uses `main` and `development`, with meaningful commits pushed to GitHub |

## 3. Implemented SS3 Features

### Authentication and Profile

The welcome screen no longer skips directly to Home. Users choose either registration or sign-in. Registration validates required fields, email format, and password length. The selected institution dropdown includes **STADIO** and other South African universities. Registration details are stored locally with `SharedPreferences` through `UserProfileStore.kt`, then shown on the Profile page.

Implemented files:

- `app/src/main/java/com/textbookhub/app/SplashActivity.kt`
- `app/src/main/java/com/textbookhub/app/LoginActivity.kt`
- `app/src/main/java/com/textbookhub/app/RegistrationActivity.kt`
- `app/src/main/java/com/textbookhub/app/UserProfileStore.kt`
- `app/src/main/java/com/textbookhub/app/ProfileActivity.kt`

### Marketplace Browsing and Search

Home displays sample textbook listings using a RecyclerView. Users can search by textbook name, author, course code, condition, or category chip. Tapping a book opens the detail page.

Implemented files:

- `app/src/main/java/com/textbookhub/app/HomeActivity.kt`
- `app/src/main/java/com/textbookhub/app/Book.kt`
- `app/src/main/java/com/textbookhub/app/BookAdapter.kt`
- `app/src/main/java/com/textbookhub/app/CategoryAdapter.kt`
- `app/src/main/res/layout/activity_home.xml`
- `app/src/main/res/layout/item_book_card.xml`

### Book Inquiry and Messaging

Book detail includes an **Inquire Now** action. The inquiry page allows a buyer to send a message about a selected book. The Messages screen lists conversations, and tapping a message opens a chat thread with a bottom text box and send button.

Implemented files:

- `app/src/main/java/com/textbookhub/app/BookDetailActivity.kt`
- `app/src/main/java/com/textbookhub/app/InquiryActivity.kt`
- `app/src/main/java/com/textbookhub/app/MessagesActivity.kt`
- `app/src/main/java/com/textbookhub/app/MessageThreadActivity.kt`
- `app/src/main/res/layout/activity_book_detail.xml`
- `app/src/main/res/layout/activity_inquiry.xml`
- `app/src/main/res/layout/activity_messages.xml`
- `app/src/main/res/layout/activity_message_thread.xml`

### Selling a Textbook

The Sell screen supports adding a book listing with photo upload, title, author, ISBN, price, and condition selection. The form validates required inputs and prevents incomplete listing submission.

Implemented files:

- `app/src/main/java/com/textbookhub/app/SellActivity.kt`
- `app/src/main/res/layout/activity_sell.xml`

### Notifications

The Home notification bell requests notification permission where required and sends a local sample TextbookHub notification.

Implemented files:

- `app/src/main/java/com/textbookhub/app/HomeActivity.kt`
- `app/src/main/AndroidManifest.xml`

## 4. UI Screenshots and Implemented UI References

The SS2 prototype screenshots are included in this repository under `docs/ui-references/` and are mapped to the implemented SS3 Android screens below.

| Screen | SS2 Prototype Reference | SS3 Implemented UI |
| --- | --- | --- |
| Splash / Welcome | `docs/ui-references/ss2_splash.png` | `app/src/main/res/layout/activity_splash.xml` |
| Registration | `docs/ui-references/ss2_registration.png` | `app/src/main/res/layout/activity_registration.xml` |
| Home / Search | `docs/ui-references/ss2_home.png` | `app/src/main/res/layout/activity_home.xml` |
| Book Detail | `docs/ui-references/ss2_book_detail.png` | `app/src/main/res/layout/activity_book_detail.xml` |
| Sell Book | `docs/ui-references/ss2_sell.png` | `app/src/main/res/layout/activity_sell.xml` |
| Inquiry | `docs/ui-references/ss2_inquiry.png` | `app/src/main/res/layout/activity_inquiry.xml` |
| Messages | `docs/ui-references/ss2_messages.png` | `app/src/main/res/layout/activity_messages.xml` |
| Profile | `docs/ui-references/ss2_profile.png` | `app/src/main/res/layout/activity_profile.xml` |
| Chat Thread | SS3 extension from SS1/SS2 messaging requirement | `app/src/main/res/layout/activity_message_thread.xml` |
| Login | SS3 extension from SS2 login sitemap item | `app/src/main/res/layout/activity_login.xml` |

## 5. SS1 Strategy to SS3 Development Traceability

SS1 defined the problem as the lack of a structured, trusted, student-focused textbook exchange system. SS3 addresses this by implementing a working Android prototype with structured book cards, searchable listings, student registration, institution selection, and buyer-seller communication.

SS1 excluded backend APIs, cloud hosting, payment processing, and database storage. SS3 follows that MVP boundary by using local runtime/sample data and `SharedPreferences` only for profile persistence. This keeps the implementation aligned with the original MVP scope while still demonstrating working user flows.

SS1 also planned Git usage with `main` and `development` branches. The SS3 repository implements this version-control strategy and includes regular commits with meaningful messages.

## 6. SS2 Design to SS3 Development Traceability

SS2 proposed a mobile-first, card-based design using a warm academic theme, clear navigation, profile, messaging, selling, and inquiry screens. SS3 implements these screens in XML and Kotlin. The prototype interactions from SS2 are represented as real Android activity transitions:

1. Welcome screen to Registration or Login
2. Successful registration/sign-in to Home
3. Home search and category filtering
4. Book card to Book Detail
5. Book Detail to Inquiry
6. Inquiry to Messages
7. Messages to Chat Thread
8. Home to Sell Book
9. Home to Profile

## 7. Testing and Build Evidence

The SS3 Android project was built successfully using:

```powershell
.\gradlew.bat assembleDebug
```

The app was also installed on the Android emulator during development using ADB. Runtime issues found during testing were corrected, including malformed XML, broken font loading, hidden navigation, chat input usability, message sending, notification permission handling, and profile data persistence.

## 8. GitHub and Version Control Evidence

GitHub repository:

`https://github.com/kxcy77/TextbookHub`

Branches:

- `main`
- `development`

Meaningful commits include:

- `Set up Android project structure`
- `Add TextbookHub screens and visual resources`
- `Implement app navigation and marketplace flows`
- `Add institution dropdown options`
- `Persist registration details in profile`

This supports the SS1 DevOps plan and demonstrates regular development progress for SS3.
