package com.benoitmanhes.domain.model

import com.benoitmanhes.domain.interfaces.Model

data class Account(
    val explorerId: String,
    val email: String,
) : Model
