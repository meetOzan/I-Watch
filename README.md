<h1 align="center">I-Watch</h1></br>
<p align="center">  
A movie and TV show info app for teaching how to use Jetpack Compose with Clean Architecture.
</p>
</br>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-red.svg"/></a>
  <a href="https://android-arsenal.com/api?level=23"><img alt="API" src="https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/meetOzab"><img alt="Profile" src="https://img.shields.io/badge/github-meetOzan-blue"/></a> 
</p>

## Screenshoots

| Splash | Entrance | Login |
| ------ | ---- | ------ |
|<img src="https://github.com/meetOzan/I-Watch/assets/99891928/8387294a-df6e-4137-873d-b3954dae3daa" width="250" height="550"/>|<img src="https://github.com/meetOzan/I-Watch/assets/99891928/38024c0a-9dfc-4a38-8e00-5bf5bf11c521" width="250" height="550"/>|<img src="https://github.com/meetOzan/I-Watch/assets/99891928/ca226317-a1fb-47f0-ae18-04c04fd56158" width="250" height="550"/>|

</br>

| Home | Detail | Top Rated List |
| --- | ------- | ------- |
|<img src="https://github.com/meetOzan/I-Watch/assets/99891928/e018a670-9c80-4560-b971-35a263285058)" width="250" height="550"/>|<img src="https://github.com/meetOzan/I-Watch/assets/99891928/102e2f44-383c-43d6-bf49-458d13df2547" width="250" height="550"/>|<img src="https://github.com/meetOzan/I-Watch/assets/99891928/c5d5f39e-d144-40cc-8abd-5b6097c32ee8" width="250" height="550"/>|

</br>

| Favorites | Search | Profile |
| --------- | ------ | ------- |
|<img src="https://github.com/meetOzan/E-Commerce/assets/99891928/ae3e444d-40d5-4555-8aa6-ef66c7c6057f" width="250" height="550"/>|<img src="https://github.com/meetOzan/E-Commerce/assets/99891928/b5d40595-5432-4ba9-a325-b4f21f8d0407" width="250" height="550"/>|<img src="https://github.com/meetOzan/E-Commerce/assets/99891928/77a6721f-335d-4397-ac07-005d102453a5" width="250" height="550"/>|

</br>

| Home | Detail |
| --------- | ------- |
|<img src="https://github.com/meetOzan/E-Commerce/assets/99891928/cdd81779-b5aa-4e15-97ce-84f65764d717" width="250" height="550"/>|<img src="https://github.com/meetOzan/E-Commerce/assets/99891928/49dd9308-94b3-4788-b022-311d9195d8dd" width="250" height="550"/> |


## What did I use?
- Minimum SDK level 23
- Only [Kotlin](https://kotlinlang.org/) based
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)
  -  A [single-activity](https://developer.android.com/guide/navigation/migrate) architecture
  - [Compose Navigation](https://developer.android.com/jetpack/compose/navigation) to manage composable transactions.
  - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - unique lifecycle in Jetpack Compose
  - Saving datas that requested from network, in local database.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [UseCases](https://developer.android.com/topic/architecture/domain-layer) - Located domain layer that sits between the UI layer and the data layer. 
  - [Repository](https://developer.android.com/topic/architecture/data-layer) - Located in data layer that contains application data and business logic.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - is Android’s next generation and recommended modern toolkit for building native UI with declaritive style. It makes development UI simpler and more understandable on Android.
- [Android Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency injection.
- [Retrofit](https://square.github.io/retrofit/) - for network calls.
- [Room](https://developer.android.com/training/data-storage/room) to local database and to make an application that can run completely locally.
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for async. operations.
- [Firebase Firestore](https://firebase.google.com/docs/firestore/quickstart) to user save operations.
- [OkHttp](https://square.github.io/okhttp/) An HTTP client that efficiently make network requests.
- [Chucker](https://github.com/ChuckerTeam/chucker) is simplifies the inspection of HTTP(S) requests/responses fired by your Android App.
- [Coil Compose](https://coil-kt.github.io/coil/compose/) to load image what comes from API.
- [Material Design 3](https://m3.material.io/) is the latest version of Google’s open-source design system.

## Architecture
This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture structure.
![](https://i.stack.imgur.com/cr3Qk.png)


## :heart: Are you liked that repository? 
__[Follow Me!](https://github.com/meetOzan)__ for projects!


# License
```xml
Designed and developed by 2023 meetOzan (Mert Ozan Kahraman)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
