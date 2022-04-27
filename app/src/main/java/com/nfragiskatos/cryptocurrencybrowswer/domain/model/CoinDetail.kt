package com.nfragiskatos.cryptocurrencybrowswer.domain.model

import com.nfragiskatos.cryptocurrencybrowswer.data.remote.dto.TeamMemberDto

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMember>
)
