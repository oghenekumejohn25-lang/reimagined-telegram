# Android Skeleton Validation

## Build baseline

| Component | Selected baseline |
|---|---|
| Android Gradle Plugin | 9.3.0 |
| Gradle wrapper | 9.5.0 |
| Java toolchain | 17 |
| Compile and target SDK | 37 |
| Minimum SDK | 26 |
| Language | Kotlin through Android Gradle Plugin 9 built-in Kotlin support |

The project uses the standard Gradle wrapper and a version catalog. It requires JDK 17 and an Android SDK platform matching API 37 to build.

## Local validation completed

The following commands completed successfully in the prepared validation environment:

```bash
./gradlew testDebugUnitTest
./gradlew assembleDebug
```

The unit-test task compiled and ran `ProjectSkeletonTest`. The debug-build task produced an APK successfully.

## Current project contents

- A single `app` Android application module.
- A launcher `MainActivity` with no network, HubSpot, or WebView implementation.
- A minimal manifest, theme, local unit test, release shrinker-rule placeholder, Gradle wrapper, and Android-specific ignore rules.
- Documentation describing the evidence and security boundaries for a later HubSpot or WebView integration.

## Explicitly out of scope

This skeleton does not include HubSpot credentials, portal configuration, a HubSpot SDK dependency, a WebView, JavaScript enablement, WebView bridge code, network-permission changes, analytics, telemetry, or a production chat flow. Adding any of these requires an approved reproduction, implementation design, privacy review, and release validation plan.

## Recommended next step

Merge this skeleton only after review. Then update the project’s CI workflow to run `./gradlew testDebugUnitTest` and `./gradlew assembleDebug` on pull requests and `main` pushes. Implement the HubSpot or WebView flow only after issue #1 has sufficient reproduction evidence.
