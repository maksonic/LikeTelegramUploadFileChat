package com.maksonic.liketelegramuploadfilechat.domain

import org.koin.dsl.module

/**
 * @Author: maksonic on 25.12.2021
 */
val domainModule = module {
    single<ShowUserGalleryImagesUseCase> { ShowUserGalleryImagesUseCase.Base(get()) }
}