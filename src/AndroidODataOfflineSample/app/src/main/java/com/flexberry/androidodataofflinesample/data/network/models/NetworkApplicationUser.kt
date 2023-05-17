package com.flexberry.androidodataofflinesample.data.network.models

import java.util.Date
import java.util.UUID

/**
 * Network representation of [ApplicationUser]
 */
@Suppress("kotlin:S117")
data class NetworkApplicationUser(
    val __PrimaryKey : UUID,
    val CreateTime: Date? = null,
    val Creator: String? = null,
    val EditTime: Date? = null,
    val Editor: String? = null,
    val Name: String? = null,
    val EMail: String,
    val Phone1: String? = null,
    val Phone2: String? = null,
    val Phone3: String? = null,
    val Activated: Boolean? = null,
    val VK: String? = null,
    val Facebook: String? = null,
    val Twitter: String? = null,
    val Birthday: Date? = null,
    val Gender: String? = null,
    val Vip: Boolean? = null,
    val Karma: Double? = null,
    val Votes: List<NetworkVote>? = null
)