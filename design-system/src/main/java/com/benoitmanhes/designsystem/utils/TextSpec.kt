package com.benoitmanhes.designsystem.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

sealed interface TextSpec {

    @Composable
    fun value(): AnnotatedString?

    @Composable
    fun string(): String? = value()?.text

    fun string(context: Context): String?

    data class RawString(val value: String?) : TextSpec {
        @Composable
        override fun value(): AnnotatedString? {
            return value?.let(::AnnotatedString)
        }

        override fun string(context: Context): String? = value
    }

    data class RawAnnotatedString(val value: AnnotatedString) : TextSpec {
        @Composable
        override fun value(): AnnotatedString {
            return value
        }

        override fun string(context: Context): String = value.text
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

        override fun string(context: Context): String = context.getString(id, *args)
    }

    companion object {
        fun loreumIpsum(words: Int): TextSpec =
            RawString(LoremIpsum(words).values.joinToString(" "))
    }
}
