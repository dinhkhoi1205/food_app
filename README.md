<a id="readme-top"></a>
# Food Ordering App
An application for ordering food and I am still new to android studio. Thus, there still be some mistakes I make in code and also the interface is still simple and may not comfortable to user

---

<!-- TABLE OF CONTENTS --> 
<details> 
  <summary><b>Table of Contents</b></summary>
  <ol> <li> <a href="#about-the-project">About The Project</a> <ul> <li>
    <a href="#built-with">Built With</a></li>
    <li><a href="#features">Features</a></li> </ul> </li> 
    <li> <a href="#getting-started">Getting Started</a>
      <ul> 
      <li><a href="#prerequisites">Prerequisites</a></li>
      <li><a href="#installation">Installation</a></li> 
      </ul>
    </li> 
    <li><a href="#usage">Usage</a></li> 
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

---

<!-- ABOUT THE PROJECT -->
## About the project
<img src="https://github.com/user-attachments/assets/6d39b5bc-fc9d-4815-bee7-74466dacc88b" width="300" />

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

  <p align="right">(<a href="#readme-top">back to top</a>)</p>
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

  <p align="right">(<a href="#readme-top">back to top</a>)</p>
  
---
<!-- GETTING STARTED -->
## Getting Started
To download and open the project, follow these steps:

### Prerequisites
- **Android Studio** need to install in your computer and the **SDK** for project is 34
- Java Development Kit (JDK) right now I am using version 14 or can be highger in the future

  <p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- INSTALLATION -->
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
  
<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

<!-- USAGE -->
## Usage
 1. Login and registration:
   * Open the app and if don't have an account, you can sign up the account by pressing the sign up at the bottom in log in and filling the information.
    
   **Screenshot**:
   
   <img src="https://github.com/user-attachments/assets/1eac2ab2-f1a3-4bf0-9195-b08bf673b9b4" alt="Registration Screenshot" width="300"/>

  * After filling information for signing up account, the code is gonna save information in firebase real time and for the account is in firebase authentication.
    
    Code for saving information in registration and upload in firebase realtime and account in firebase authentication:

     <img src="https://github.com/user-attachments/assets/4d2b5c2a-2705-4322-b3ed-ac413395f7b1" alt="Registration Screenshot" width="300"/>

    **Screenshot**:

    <img src="https://github.com/user-attachments/assets/3befa872-244f-4ea0-9ed9-84a33bef2be7" width="300"/>

     <sup><i> Account information in real time database </i></sup>

    <img src="https://github.com/user-attachments/assets/00394bb9-8077-49ac-8abc-335ee23fe335" width="300" />

       <sup><i> Email and password in firebase authentication </i></sup>
    
2. Browse Categories
   * Select a category from home page
   * You will see list of food in category

     **Screenshot**:

     <img src="https://github.com/user-attachments/assets/80422039-1db0-43ab-8eb6-819d63394adf" width="300"/>

       <sup><i> List of categories in home page </i></sup>

    <img src="https://github.com/user-attachments/assets/753373d9-3d83-4e89-a374-ca85bfbeb98e" width="300" />

      <sup><i> List of pizza in pizza's category </i></sup>

  3. Add items to cart
     * Tap on a food in list
     * Add quantity for how many food you want
     * Click add to cart to view in cart order

       **Screenshot**:

       <img src="https://github.com/user-attachments/assets/78501397-fda6-47b3-997b-6101e8690217" width="300"/>

         <sup><i> Pizza detail in list </i></sup>

       Code add quantity and save items in cart order:

         <img src="https://github.com/user-attachments/assets/95113f06-6ec6-426b-9e1f-5d42a295e526" width="300"/>
       
  4. Place order
     * Review in your order and enter the current location in order to place it

       **Screenshot**:

       <img src="https://github.com/user-attachments/assets/300040d7-6c8b-47fe-b0be-e3d5db24fbf3" width="300" />

          <sup><i> View order and placed in cart order </i></sup>

       * After order, it gonna save in real time to track order

         **Screenshot**:

         <img src="https://github.com/user-attachments/assets/dd1f092f-dd0a-4b4b-b2fa-b4a591f6fb73" width="300" />
         
           <sup><i> Order save in real time data base </i></sup>

         Code to save order in real time:
         
         ```
         btnDialogYes.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onClick(View v) {
                        String addressToSave = enterAddress.getText().toString();
                        if (addressToSave.isEmpty()) {
                            Toast.makeText(getActivity(), "Please enter address", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                        if (currentUser == null) {
                            Toast.makeText(getActivity(), "User not authenticated", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        String userId = currentUser.getUid();
                        DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("User").child(userId);
                        DatabaseReference requestReference = FirebaseDatabase.getInstance().getReference("Request");
                        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String phone = snapshot.child("phone").getValue(String.class);
                                String name = snapshot.child("userName").getValue(String.class);
                                Request request = new Request(phone, name, addressToSave, total, orderList);

                                requestReference.push().setValue(request).addOnCompleteListener(uploadTask -> {
                                    if (uploadTask.isSuccessful()) {
                                        Toast.makeText(getActivity(), "Order successfully", Toast.LENGTH_SHORT).show();
                                        clearCart();
                                        dialog.dismiss();

                                        makeNotification();
                                    } else
                                        Toast.makeText(getActivity(), "Order fail", Toast.LENGTH_SHORT).show();
                                });

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });
         ```
  5. Profile management
     * Update your profile and change your profile picture.

       **Screenshot**:

       <img src="https://github.com/user-attachments/assets/70780fdd-2a37-4ab8-8f66-6d4550b146d2" width="300" />

       <sup><i> Your profile </i></sup>

  6. Set location to deliver
     * You can set location in google map to deliver food to that location.

       **Screenshot**:

       <img src="https://github.com/user-attachments/assets/99537f9d-0f2f-44bc-85a4-35d31ebb928a" width="300" />

       <sup><i> Set location in google map </i></sup>

       <img src="https://github.com/user-attachments/assets/f343a52f-0c6b-46c0-a39a-4d266ff5269c" width="300"/>

       <sup><i> Location update in home page </i></sup>

  7. View order
     * After you order, you can check food order that you have placed
     * Press clear order button will clear in both app and firebase

        **Screenshot**:

       <img src="https://github.com/user-attachments/assets/45b7bbc0-7c6e-40b8-80e7-4d5eabd9951e" width="300" />

       <sup><i> View order </i></sup>

       <img src="https://github.com/user-attachments/assets/a810a50d-97a9-4317-808b-65030cfbb79a" width="300" />

       <sup><i> Order delete in firebase </i></sup>

       <p align="right">(<a href="#readme-top">back to top</a>)</p>
      ---

     <!-- CONTACT -->
     ## Contact
     * Gmail: dinhkhoi2021@gmail.com
     * Project link: https://github.com/dinhkhoi1205/food_app
    
       <p align="right">(<a href="#readme-top">back to top</a>)</p>
       
    
