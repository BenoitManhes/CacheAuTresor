package com.benoitmanhes.designsystem.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

sealed interface TextSpec {

    @Composable
    fun value(): AnnotatedString?

    data class RawString(val value: String?) : TextSpec {
        @Composable
        override fun value(): AnnotatedString? {
            return value?.let(::AnnotatedString)
        }
    }

    data class RawAnnotatedString(val value: AnnotatedString) : TextSpec {
        @Composable
        override fun value(): AnnotatedString {
            return value
        }
    }

    class Resources(
        @StringRes val id: Int,
        vararg params: Any,
    ) : TextSpec {
        private val args: Array<out Any> = params

        @Suppress("SpreadOperator")
        @Composable
        override fun value(): AnnotatedString {
            return AnnotatedString(stringResource(id, *args))
        }
    }

    companion object {
        fun loreumIpsum(words: Int): TextSpec =
            TextSpec.RawString(LoremIpsum(words).values.joinToString(" "))
    }
}
