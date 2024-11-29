package com.shalenmathew.nexblock.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.shalenmathew.nexblock.core.data.networking.HttpClientFactory
import com.shalenmathew.nexblock.crypto.data.repository.CoinRepositoryImplementation
import com.shalenmathew.nexblock.crypto.domain.repository.CoinRepository
import com.shalenmathew.nexblock.crypto.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val appModule = module{
    single{
        HttpClientFactory.create(CIO.create())
    }
    singleOf(::CoinRepositoryImplementation).bind<CoinRepository>()

    viewModelOf(::CoinListViewModel)

}