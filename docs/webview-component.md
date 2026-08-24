# WebView Component

`WebViewActivity` is a restricted internal activity for displaying an approved HTTPS page inside the application. It is not exported and opens the verified Oghenekume Digital Hub destination only when the user selects **Open website** from the launcher screen.

## Usage contract

The approved default route is `https://www.oghenekumejohn.online/`. It may be opened with:

```kotlin
startActivity(WebViewActivity.newIntent(this))
```

The default configuration allows only the exact `www.oghenekumejohn.online` host. The overload that accepts an initial URL and host set is reserved for later approved destinations. The policy rejects malformed URLs, cleartext URLs, hosts outside the allowlist, and unapproved subdomains. Callers must not derive the allowlist from untrusted input.

## Default safeguards

| Control | Baseline behavior |
|---|---|
| Transport | Only HTTPS initial and navigated URLs are allowed; cleartext traffic is disabled for the app. |
| Navigation | Only exact allowed hosts remain in the WebView; other navigations are blocked. |
| JavaScript and storage | Disabled. |
| Native bridge | No JavaScript interface or message bridge is registered. |
| Local data access | File and content access are disabled. |
| Popups | Multiple windows and JavaScript-opened windows are disabled. |
| TLS errors | Cancelled rather than bypassed. |
| Activity exposure | The WebView activity is not exported. |

## Validation performed

The local Gradle test suite passed, including unit coverage for the URL allowlist. A debug APK build also completed successfully with `./gradlew assembleDebug`.

## Next approval gate

Before enabling JavaScript, adding a HubSpot SDK, allowing a new host, accepting a callback URI, or adding a native-web bridge, the issue must have a reproducible requirement and the change must receive security and product approval.

## References

[1]: https://developer.android.com/develop/ui/views/layout/webapps/webview "Build web apps in WebView — Android Developers"
[2]: https://developer.android.com/privacy-and-security/security-best-practices "Improve your app's security — Android Developers"
