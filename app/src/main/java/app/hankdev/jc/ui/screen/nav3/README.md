# Nav3

## Configuration

To use Nav3, we have to set up the Kotlin Serialization plugin.

Step 1: Add serialization plugin to version catalog

```toml
kotlin-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlin" }  
```

Step 2: Declare plugin in project-level build file

```kotlin
alias(libs.plugins.kotlin.serialization) apply false
```

Step 3: Apply plugin in app module

```kotlin
alias(libs.plugins.kotlin.serialization)
```

And then sync Gradle project.