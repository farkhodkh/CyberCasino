# Contribution guide for developers

# The application has several modules and features:

* `main` (or app)

* `buildSrc`

* `core`

* `feature-auth`
The authorization feature
  
#Code review
Applied detekt for code review. Each time, before commiting the changes it is required to run detect by
`./gradlew/detect` and fix all features

#Libraries
For injecting libraries dependency used configuration file stored in "../gradle/libs.versions.toml"

* `UI`
  The [Jetpack Compose](https://developer.android.com/jetpack/compose) is used for application UI. Also some helper libraries from [Accompanist](https://google.github.io/accompanist/) are used to supplement Jetpack Compose.

* `Dependency Injection`
  The [Hilt](https://dagger.dev/hilt/modules) is used to provide dependencies between components.

* `Networking`
  The combination of [Retrofit](https://square.github.io/retrofit/) and [Moshi](https://github.com/square/moshi) is used to perform API requests.

* `Storage`
  The [Jetpack DataStore](https://developer.android.com/topic/libraries/architecture/datastore) is used to store key-value pairs or small typed objects.

* `Image Loading`
  The [Coil](https://coil-kt.github.io/coil/) image loading library is used to load images.