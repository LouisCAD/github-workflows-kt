package io.github.typesafegithub.workflows.docsnippets

import io.github.typesafegithub.workflows.actions.actions.UploadArtifactV3
import io.github.typesafegithub.workflows.domain.RunnerType.UbuntuLatest
import io.github.typesafegithub.workflows.domain.triggers.Push
import io.github.typesafegithub.workflows.dsl.expressions.expr
import io.github.typesafegithub.workflows.dsl.workflow
import io.kotest.core.spec.style.FunSpec

class CompensatingLibrarysMissingFeaturesSnippets : FunSpec({
    test("customArguments") {
        // --8<-- [start:custom-arguments-1]
        workflow(
            // --8<-- [end:custom-arguments-1]
            name = "customArguments",
            on = listOf(Push()),
            // --8<-- [start:custom-arguments-2]
            // ...
            _customArguments =
                mapOf(
                    "dry-run" to true,
                    "some-string-value" to "foobar",
                    "written-by" to listOf("Alice", "Bob"),
                    "concurrency" to
                        mapOf(
                            "group" to expr("github.ref"),
                            "cancel-in-progress" to "true",
                        ),
                ),
        )
        // --8<-- [end:custom-arguments-2]
        {
            job(id = "test_job", runsOn = UbuntuLatest) {
                run(command = "echo 'Hello world!'")
            }
        }
    }

    test("customInputs") {
        // --8<-- [start:custom-inputs-1]
        UploadArtifactV3(
            // --8<-- [end:custom-inputs-1]
            path = emptyList(),
            // --8<-- [start:custom-inputs-2]
            // ...
            _customInputs =
                mapOf(
                    "path" to "override-path-value",
                    "answer" to "42",
                ),
        )
        // --8<-- [end:custom-inputs-2]
    }

    test("customVersion") {
        // --8<-- [start:custom-version-1]
        UploadArtifactV3(
            // --8<-- [end:custom-version-1]
            path = emptyList(),
            // --8<-- [start:custom-version-2]
            // ...
            _customVersion = "v4",
        )
        // --8<-- [end:custom-version-2]
    }
})
