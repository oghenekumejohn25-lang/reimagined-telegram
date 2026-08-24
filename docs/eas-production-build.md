# EAS production-build readiness

## Current project assessment

This repository is a native Android Gradle project. It does not contain an Expo JavaScript or React Native application, but current Expo documentation states that EAS Build can build native projects as well as Expo and React Native projects. The configuration in this branch therefore adds only the minimal EAS project metadata and build profiles; it does not migrate the Android application to React Native or add Expo SDK libraries.

The Android application identifier is `com.oghenekume.reimaginedtelegram`. The supplied EAS project identifier is recorded in `app.json` as `extra.eas.projectId`.

## Build profiles

| Profile | Intended use | Android artifact |
|---|---|---|
| `preview` | Internal tester distribution after the app is ready for device testing. | APK |
| `production` | Store-ready Android release build. | Android App Bundle (AAB) |

The production profile deliberately produces an AAB rather than an APK. An AAB is the appropriate artifact for Google Play distribution; the `preview` profile remains available when an installable APK is required for internal testing.

## Before any external EAS action

1. Verify that the supplied EAS project ID belongs to the intended Expo account and to this Android application.
2. Log in to the intended Expo account with `npx eas-cli@latest login`.
3. Review the project metadata and build profiles in this branch.
4. Confirm whether Expo should manage a new Android signing credential or whether an existing release keystore must be used.
5. Confirm that `versionCode` and `versionName` in `app/build.gradle.kts` are the intended release values.

## Approved command sequence after verification

Run these commands from the repository root only after the above checks are approved:

```sh
npx eas-cli@latest init --id 9ab3bc2c-5b86-4005-a8e6-5b7ccbe99206
npx eas-cli@latest build --platform android --profile production
```

The `init` command links local project metadata to an external EAS project. The build command submits the source to EAS Build and can create or use signing credentials. These actions should be performed only after confirming the Expo account, EAS project ownership, and signing policy.

## References

[1]: https://docs.expo.dev/build/introduction/ "Expo — EAS Build"
[2]: https://docs.expo.dev/tutorial/eas/configure-development-build/ "Expo — Configure a development build in cloud"
[3]: https://docs.expo.dev/build/eas-json/ "Expo — Configure EAS Build with eas.json"
