# Food Ordering App
An application for ordering food and I am still new to android studio. Thus, there still be some mistakes I make in code and also the interface is still simple and may not comfortable to user

---

<!-- TABLE OF CONTENTS --> 
<details> 
  <summary><b>Table of Contents</b></summary>
  <ol> <li> <a href="#about-the-project">About The Project</a> <ul> <li>
    <a href="#built-with">Built With</a></li>
    <li><a href="#features">Features</a></li> </ul> </li> 
    <li> <a href="#getting-started">Getting Started</a> <ul> 
      <li><a href="#prerequisites">Prerequisites</a></li>
      <li><a href="#installation">Installation</a></li> </ul> </li> 
    <li><a href="#usage">Usage</a></li> <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li> <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li> </ol> </details>

---

<!-- ABOUT THE PROJECT -->
## About the project
![download](https://github.com/user-attachments/assets/6d39b5bc-fc9d-4815-bee7-74466dacc88b)

<sup><i> Logo for the food project </i></sup>

The **Food Ordering App** is designed simple for user about the process of ordering food. It includes:
* User-friendly login and registration screen
* Categorize food menu items
* Customize the prize when you want to order that food
* Show the order sumary when ordering the food
---
## Built With
- ![Android Studio](https://img.shields.io/badge/Android%20Studio-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white) - IDE for Android Development
- ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white) - Programming Language
- ![Firebase](https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=white) - Backend for saving some data
---
<!-- FEATURES -->
## Features
* Authentication: Login and registration with email and password and can change password if you forget
* Menu categories: With diffrent food types like pizza, burger ...
* Add to cart: Add items to your cart and review it
* Point direction: Have able to show specific location through google map and can choose location to deliver to
* Search bar: Find food quickly through search view page
* Profile update: Upload image user's profile and can change information
* Firebase: Using realtime firebase for tracking order and authentication for managing account
---
<!-- GETTING STARTED -->
## Getting Started
To download and open the project, follow these steps:

### Prerequisites
- **Android Studio** need to install in your computer and the **SDK** for project is 34
- Java Development Kit (JDK) right now I am using version 14 or can be highger in the future

### Installation
1. Clone the repo
   ```sh
   git clone https://github.com/dinhkhoi1205/food_app.git
   ```
2. Open the project in Android Studio
   * Launch Android Studio
   * Click File > Open, navigate project folder and select it
3. Sync Gradle files
   * After open the project, Android Studio will automatically sync it self
4. Install dependencies: Make sure to install while Gradle sync
5. Run the app
   * Set up an emulator or connect to an Android phone
   * Click the green button and run it
---
## Usage
 1. Login and registration:
   * Open the app and if don't have an account, you can sign up the account by pressing the sign up at the bottom in log in and filling the information.
    
   **Screenshot**:
   
   <img src="https://github.com/user-attachments/assets/1eac2ab2-f1a3-4bf0-9195-b08bf673b9b4" alt="Registration Screenshot" width="300"/>

  * After filling information for signing up account, the code is gonna save information in firebase real time and for the account is in firebase authentication.
    
    Code for saving information in registration and upload in firebase realtime and account in firebase authentication:

    ```
    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            String userId = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                            User user = new User(username,email,phone);

                            reference.child(userId).setValue(user).addOnCompleteListener(dbTask ->{
                                if(dbTask.isSuccessful()){
                                    Toast.makeText(registration_screen.this, "You have signed successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(registration_screen.this,login_home_page.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        } else{
                            String errorMessage = Objects.requireNonNull(task.getException()).getMessage();
                            assert errorMessage != null;
                            if (errorMessage.contains("The email address is already in use by another account")) {
                                Toast.makeText(registration_screen.this, "This email is already registered. Please use a different email.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(registration_screen.this, "Registration failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
    ```

    **Screenshot**:

    <img src="https://github.com/user-attachments/assets/3befa872-244f-4ea0-9ed9-84a33bef2be7" width="300"/>

     <sup><i> Account information in real time database </i></sup>
    
    
