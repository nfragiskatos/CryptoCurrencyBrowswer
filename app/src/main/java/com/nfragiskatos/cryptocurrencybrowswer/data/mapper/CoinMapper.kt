package com.nfragiskatos.cryptocurrencybrowswer.data.mapper

import com.nfragiskatos.cryptocurrencybrowswer.data.remote.dto.CoinDetailDto
import com.nfragiskatos.cryptocurrencybrowswer.data.remote.dto.CoinDto
import com.nfragiskatos.cryptocurrencybrowswer.data.remote.dto.TeamMemberDto
import com.nfragiskatos.cryptocurrencybrowswer.domain.model.Coin
import com.nfragiskatos.cryptocurrencybrowswer.domain.model.CoinDetail
import com.nfragiskatos.cryptocurrencybrowswer.domain.model.TeamMember

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol
    )
}

fun TeamMemberDto.toTeamMember() : TeamMember {
    return TeamMember(
        id = id,
        name = name,
        position = position
    )
}

fun CoinDetailDto.toCoinDetail() : CoinDetail {
    return CoinDetail(
        coinId = id,
        name = name,
        description = description,
        symbol = symbol,
        rank = rank,
        isActive = isActive,
        tags = tags.map { it.name },
        team = team.map { it.toTeamMember() }
    )
}