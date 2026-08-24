# HubSpot and WebView Integration Readiness

The Android skeleton now contains a restricted baseline `WebViewActivity`, but it has no configured destination and no HubSpot SDK. A product-specific URL, allowed-host list, JavaScript requirement, or HubSpot integration must be approved only after the product requirement and issue reproduction are confirmed.

## Required inputs

1. A clear observed-versus-expected behavior statement.
2. Reproduction steps, including account, navigation, and network conditions.
3. Affected device model, Android version, and Android System WebView version.
4. Confirmed HubSpot Android SDK version and supported integration pattern.
5. Sanitized logs or crash traces, with no API keys, access tokens, portal IDs, or customer messages.
6. A decision on fallback behavior when the chat or web content cannot load.

## Implementation boundaries

- Keep HubSpot configuration and secrets outside source control.
- Supply only HTTPS URLs and an explicit, exact allowed-host set to `WebViewActivity.newIntent()`.
- Do not enable cleartext traffic or weaken certificate validation to work around a loading failure.
- Keep WebView navigation outside the approved-host set, JavaScript, storage, popups, file access, and JavaScript bridge capabilities disabled unless a specific requirement and security review approve them.
- Add narrowly scoped release shrinker rules only when supported by release-build test evidence.

## Validation plan

Before release, test the original reproduction environment, a supported device/OS/WebView matrix, network failures, app background/foreground transitions, and a release-equivalent build. Record the acceptance evidence in the issue or pull request.
