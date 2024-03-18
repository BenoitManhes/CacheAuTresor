package com.benoitmanhes.common.compose.text

import android.content.Context
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.benoitmanhes.common.kotlin.TextTranslatable

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

    data class Translation(
        val textTranslatable: TextTranslatable,
    ) : TextSpec {
        @Composable
        override fun value(): AnnotatedString? {
            val locale = Locale.current.language
            val value = textTranslatable.map[locale]
                ?: textTranslatable.map["en"]
            return value?.let(::AnnotatedString)
        }

        override fun string(context: Context): String? {
            val language = context.resources.configuration.locales.get(0).language
            return textTranslatable.map[language] ?: textTranslatable.map["en"]
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
            return AnnotatedString(stringResource(id, *args.resolveArgsContext()))
        }

        @Suppress("SpreadOperator")
        override fun string(context: Context): String = context.getString(id, *args.resolveArgsContext(context))
    }

    class PluralResources(
        @PluralsRes val id: Int,
        private val count: Int,
        vararg params: Any,
    ) : TextSpec {
        private val args: Array<out Any> = params

        @Suppress("SpreadOperator")
        @Composable
        override fun value(): AnnotatedString {
            return AnnotatedString(pluralStringResource(id, count, *args.resolveArgsContext()))
        }

        @Suppress("SpreadOperator")
        override fun string(context: Context): String =
            context.resources.getQuantityString(id, count, *args.resolveArgsContext(context))
    }

    companion object {
        fun loreumIpsum(words: Int): TextSpec =
            RawString(LoremIpsum(words).values.joinToString(" "))

        private fun Array<out Any>.resolveArgsContext(context: Context): Array<out Any?> {
            return Array(this.size) { idx ->
                val entry = this[idx]
                if (entry is TextSpec) {
                    entry.string(context) // marked as compile error due to Java(?)
                } else {
                    this[idx]
                }
            }
        }

        @Composable
        private fun Array<out Any>.resolveArgsContext(): Array<out Any> {
            return Array(this.size) { idx ->
                val entry = this[idx]
                if (entry is TextSpec) {
                    entry.value() ?: "" // marked as compile error due to Java(?)
                } else {
                    this[idx]
                }
            }
        }
    }
}
