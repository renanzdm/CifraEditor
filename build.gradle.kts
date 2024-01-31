plugins {
    id("com.android.application").version("8.1.2").apply(false)
    id("org.jetbrains.kotlin.android").version("1.8.10").apply(false)
    id("com.google.devtools.ksp").version("1.9.0-1.0.12").apply(false)
}
buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
    }
}
