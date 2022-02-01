# Contribution guide for developers

# The application has several modules and features:

* `main` (or app)

* `buildSrc`

* `core`


#Libraries
For injecting libraries dependency used configuration file stored in "../gradle/libs.versions.toml"

#Code review
Applied detekt for code review. Each time, before commiting the changes it is required to run detect by
`./gradlew/detect` and fix all features