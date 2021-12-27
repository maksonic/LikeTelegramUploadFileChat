package com.maksonic.liketelegramuploadfilechat.navigation

import org.koin.dsl.module

/**
 * @Author: maksonic on 25.12.2021
 */
val navigationModule = module {
    factory { SafeNavigation() }
    single<Navigator> { Navigator.Base(get()) }
}