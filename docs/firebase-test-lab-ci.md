# Firebase Test Lab CI

The `Firebase Test Lab` workflow builds the debug application APK and its Espresso instrumentation-test APK on pull requests to `main`, pushes to `main`, and manual dispatches. It always uploads the generated APKs as workflow artifacts. It submits the cloud test only when the run is trusted and the required repository variables are configured.

## Test scope

The initial instrumented test is intentionally a small smoke test. It launches `WebViewActivity` using the approved destination and verifies that the WebView view is visible. It neither enters data, submits forms, accesses customer accounts, nor asserts remote page content.

## Required repository variables

Configure these as repository variables, not source-code constants:

| Variable | Required value |
|---|---|
| `FIREBASE_PROJECT_ID` | The dedicated Firebase or Google Cloud project ID used for testing. |
| `GCP_WORKLOAD_IDENTITY_PROVIDER` | Full Workload Identity Provider resource name trusted by the Google Cloud project. |
| `GCP_SERVICE_ACCOUNT` | The dedicated service-account email used only for Test Lab CI. |
| `FIREBASE_RESULTS_BUCKET` | A dedicated `gs://` results bucket with a retention policy appropriate for test logs, screenshots, and video. |

The workflow uses GitHub OIDC and Workload Identity Federation. It does not store a long-lived service-account key in the repository or workflow configuration.

## Required cloud setup

Create a Workload Identity Provider that accepts GitHub Actions OIDC tokens only from `oghenekumejohn25-lang/reimagined-telegram`. Restrict the trust policy to the repository and, where appropriate, the intended branch or workflow. Google recommends federation because it avoids exporting long-lived service-account keys.

Grant the dedicated service account only the permissions required to submit Test Lab matrices and to write/read the designated results bucket. Firebase’s reference documents the Test Lab Admin and Firebase Analytics Viewer roles for gcloud execution with a custom results bucket. Avoid broad project-editor access, and scope storage access to the dedicated testing bucket where possible.

## Trusted-run behavior

Cloud submission is skipped for pull requests from forks and whenever a required repository variable is absent. In either case, the workflow still builds the application and instrumentation APKs and publishes them as GitHub Actions artifacts. This prevents untrusted code from receiving cloud credentials while retaining useful build feedback.

## Device matrix and results

The first configuration uses the Test Lab default device environment, with a 10-minute execution limit, Android Test Orchestrator, automatic Google login disabled, video recording enabled, and a unique result path per GitHub run. Select a small explicit device matrix after reviewing the initial result history and available Test Lab devices. Test logs, screenshots, and video can contain application or page information; protect the results bucket accordingly and set an appropriate retention policy.

## References

[1]: https://firebase.google.com/docs/test-lab/android/get-started "Firebase Test Lab — Android getting started"
[2]: https://docs.cloud.google.com/sdk/gcloud/reference/firebase/test/android/run "gcloud firebase test android run"
[3]: https://firebase.google.com/docs/test-lab/android/iam-permissions-reference "Firebase Test Lab IAM permissions reference"
[4]: https://docs.cloud.google.com/iam/docs/workload-identity-federation-with-deployment-pipelines "Workload Identity Federation with deployment pipelines"
[5]: https://github.com/google-github-actions/auth "Authenticate to Google Cloud from GitHub Actions"
