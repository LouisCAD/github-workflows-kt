package it.krzeminski.githubactions.domain.triggers

import it.krzeminski.githubactions.dsl.CustomValue

/**
 * https://docs.github.com/en/actions/using-workflows/events-that-trigger-workflows#issue_comment
 */
data class IssueComment(
    override val _customArguments: Map<String, CustomValue> = mapOf(),
) : Trigger()
