# TextbookHub SS4 Video Presentation Script

Target time: 10 to 12 minutes  
Maximum allowed time: 15 minutes

Tip: Speak slowly. Pause at each line break. It is okay to read this script.

---

## 0:00 - 0:45 | Introduction

Good day.

My name is Kagiso Mmatloa.

My student number is 24301339.

This is my SS4 final presentation for Mobile Application Development A, module code MADA372.

My mobile application is called TextbookHub.

TextbookHub is a student textbook marketplace app.

The purpose of the app is to help students buy and sell second-hand textbooks in a simple and affordable way.

The target users are students in higher education institutions, especially students who need textbooks at lower prices.

---

## 0:45 - 1:45 | Project Problem and Purpose

The problem I wanted to address is that textbooks can be expensive.

Many students also struggle to find used textbooks from other students in a trusted and convenient way.

TextbookHub helps with this problem by allowing students to browse textbook listings, search for books, view book details, contact sellers, and list their own books for sale.

The app is designed as a minimum viable product.

This means it focuses on the most important features first.

The app does not use a full online database yet.

Instead, it uses Kotlin data models and local app logic to demonstrate the main textbook marketplace functionality.

---

## 1:45 - 3:00 | SS1 to SS4 Development Journey

This project was developed across four phases.

In SS1, I focused on strategic planning.

This included the problem statement, project scope, user needs, functional requirements, and the first idea of the system architecture.

In SS2, I focused on UX and UI design.

This included the wireframes, prototype screens, sitemap, information architecture, colours, and branding.

In SS3, I built the Android application using Android Studio.

The app was implemented with Kotlin and XML layouts.

In SS4, I refined the app, improved the documentation, checked the rubric requirements, added stronger ViewBinding evidence, and prepared the final project report.

The final app is therefore aligned with SS1, SS2, SS3, and SS4.

---

## 3:00 - 4:15 | Technical Architecture

The app was built in Android Studio.

It uses Kotlin for the application logic.

It uses XML for the user interface layouts.

The app uses Activity screens for the main pages.

These include the welcome screen, registration screen, login screen, home screen, book detail screen, inquiry screen, messages screen, chat screen, sell book screen, and profile screen.

The app also uses RecyclerView to display textbook listings and message previews.

For binding, the app uses ViewBinding.

This connects Kotlin code to XML user interface elements in a safer and cleaner way.

The app also includes a Book data class.

This data class stores textbook information such as title, author, edition, price, description, and image reference.

The app also uses a BookCatalogViewModel to hold and filter the textbook catalogue.

---

## 4:15 - 5:15 | Demo: Welcome, Register, and Login

I will now demonstrate the app.

The app opens on the welcome screen.

From here, the user can register or sign in.

I will open the registration screen.

The registration form asks for the user's full name, student number, institution, email, and password.

The institution dropdown includes STADIO.

The app validates the registration form.

If required information is missing, the app shows an error message.

After registration, the app saves the user's profile details locally.

The login screen also validates the email and password before the user enters the app.

This supports the error handling and input validation requirement.

---

## 5:15 - 6:45 | Demo: Home Screen, Listings, and Search

After signing in, the user goes to the home screen.

The home screen shows the textbook catalogue.

The textbook listings are displayed using RecyclerView.

Each listing shows important information, including the book title, author, edition, course code, condition, price, and seller.

The user can search for textbooks using the search bar.

The search can filter by title, author, edition, course code, condition, and description.

If the user presses search without typing anything, the app shows a Toast message asking the user to enter a search term.

This demonstrates search logic and input validation.

The app also has category chips.

These make it easier to filter the listings.

---

## 6:45 - 8:00 | Demo: Book Details and Inquiry

Next, I will open a textbook listing.

This takes the user to the book detail screen.

The detail screen shows expanded information about the selected textbook.

This includes the book title, author, edition, condition, campus, price, description, and seller name.

The user can tap the save button to save the listing.

The user can also tap Inquire Now.

This opens the inquiry screen.

On the inquiry screen, the user can type a message to the seller.

When the message is sent, the app gives feedback and opens the messages area.

This demonstrates user interaction logic and navigation from Home to Book Details to the interaction screen.

---

## 8:00 - 9:15 | Demo: Messages and Chat

The messages screen shows message previews from sellers.

These messages are also displayed using RecyclerView.

When the user taps a message, the app opens the chat thread.

The chat screen has a text box at the bottom.

The user can type a reply and send it.

If the user tries to send an empty message, the app shows an error.

When a valid message is sent, the new message is added to the chat.

This demonstrates dynamic UI updates, input validation, and user-to-user interaction.

---

## 9:15 - 10:15 | Demo: Sell Book and Profile

The Sell Book screen allows the user to create a textbook listing.

The user can enter the book title, author, price, and condition.

The user can also upload or select a book photo.

The app validates the form before publishing.

If important fields are missing, the app shows errors or Toast messages.

The Profile screen displays the information used during registration.

This includes the user's name, institution, student number, and email address.

The profile page also includes options such as My Listings, Settings, and Help and Support.

---

## 10:15 - 11:15 | GitHub and Deployment Evidence

The project is hosted on GitHub.

The repository link is:

https://github.com/kxcy77/TextbookHub

The project uses meaningful commits to show development progress.

It also has a main branch and a development branch.

For deployment, the app builds successfully as an APK.

The debug APK is available from the Android Studio build output.

For Amazon Appstore submission, the final release version would need to be signed and uploaded through the Amazon Developer Console with screenshots and an app description.

---

## 11:15 - 12:00 | Conclusion

In conclusion, TextbookHub meets the main MVP requirements for a student textbook marketplace.

It allows students to register, sign in, browse textbooks, search listings, view book details, contact sellers, reply to messages, upload book photos, and view profile information.

The app uses Kotlin, XML layouts, ViewBinding, RecyclerView, a ViewModel, input validation, and structured navigation.

The final project is aligned with the planning from SS1, the design from SS2, the development from SS3, and the final SS4 requirements.

Thank you.

---

## Emergency Short Version

Use this if you are running out of time.

TextbookHub is my Android textbook marketplace app for students.

It helps students find affordable second-hand textbooks and contact sellers.

The app was planned in SS1, designed in SS2, developed in SS3, and finalised in SS4.

It uses Kotlin, XML layouts, ViewBinding, RecyclerView, and a BookCatalogViewModel.

The app includes registration, login, textbook browsing, search, book details, seller inquiry, messages, chat replies, sell book, profile, and notifications.

It validates user input and shows error messages or Toast feedback.

The project is hosted on GitHub at https://github.com/kxcy77/TextbookHub.

The app builds successfully as an APK.

This final version meets the MVP requirements for the SS4 Textbook App project.
