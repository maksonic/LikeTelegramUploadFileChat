package com.maksonic.liketelegramuploadfilechat.presentation.screen.files_sheet

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @Author: maksonic on 25.12.2021
 */
val filesBottomSheetModule = module {

    viewModel { FilesBottomSheetViewModel(useCase = get(), application = get()) }
}