# Reimagined Telegram

This repository contains a minimal Android application skeleton for preparing an approved HubSpot or WebView implementation.

## Current scope

The project includes a single Kotlin Android application module, a launcher activity, a unit-test target, and baseline Gradle configuration. It deliberately does **not** include HubSpot credentials, portal identifiers, chat configuration, a WebView implementation, network-permission changes, or telemetry.

Those integrations should be added only after issue #1 has a reproducible symptom, a confirmed source requirement, and an approved implementation design.

## Requirements

- JDK 17
- Android SDK Platform 37
- Android build tools compatible with Android Gradle Plugin 9.3.0

## Build and test

Once the Gradle wrapper is available, run:

```bash
./gradlew testDebugUnitTest
./gradlew assembleDebug
```

## Project layout

| Path | Purpose |
|---|---|
| `app/` | Android application module. |
| `app/src/main/` | Manifest, launcher activity, and resources. |
| `app/src/test/` | Local unit tests. |
| `docs/hubspot-webview-readiness.md` | Integration prerequisites and implementation handoff. |

## Next implementation gate

Before adding HubSpot or WebView code, document the observed behavior, expected behavior, reproduction steps, Android device and System WebView versions, selected SDK version, privacy constraints, and release validation plan.
