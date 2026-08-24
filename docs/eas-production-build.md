# EAS production-build readiness

## Current project assessment

This repository is a native Android Gradle project. It does not contain an Expo JavaScript or React Native application, but current Expo documentation states that EAS Build can build native projects as well as Expo and React Native projects. The configuration in this branch therefore adds only the minimal EAS project metadata and build profiles; it does not migrate the Android application to React Native or add Expo SDK libraries.

The Android application identifier is `com.oghenekume.reimaginedtelegram`. The project is linked to `@oghenekumdigitalhub/reimagined-telegram` with EAS project ID `c2398a87-79f1-4ef4-8894-4bfa209cf898`, recorded in `app.json` as `extra.eas.projectId`.

## Build profiles

| Profile | Intended use | Android artifact |
|---|---|---|
| `preview` | Internal tester distribution after the app is ready for device testing. | APK |
| `production` | Store-ready Android release build. | Android App Bundle (AAB) |

The production profile deliberately produces an AAB rather than an APK. An AAB is the appropriate artifact for Google Play distribution; the `preview` profile remains available when an installable APK is required for internal testing.

## Before any external EAS action

1. Confirm the current association with `npx eas-cli@latest project:info`; it should report `@oghenekumdigitalhub/reimagined-telegram` and project ID `c2398a87-79f1-4ef4-8894-4bfa209cf898`.
2. Log in to the intended Expo account with `npx eas-cli@latest login` if the CLI is not already authenticated.
3. Review the project metadata and build profiles in this branch.
4. Confirm whether Expo should manage a new Android signing credential or whether an existing release keystore must be used.
5. Confirm that `versionCode` and `versionName` in `app/build.gradle.kts` are the intended release values.

## Approved command sequence after verification

Run this command from the repository root only after the above checks are approved:

```sh
npx eas-cli@latest build --platform android --profile production
```

The project is already linked; do not run `eas init` unless the EAS association is intentionally being changed. The build command submits the source to EAS Build and can create or use signing credentials. It should be run only after confirming the Expo account, EAS project ownership, and signing policy.

## References

[1]: https://docs.expo.dev/build/introduction/ "Expo — EAS Build"
[2]: https://docs.expo.dev/tutorial/eas/configure-development-build/ "Expo — Configure a development build in cloud"
[3]: https://docs.expo.dev/build/eas-json/ "Expo — Configure EAS Build with eas.json"
