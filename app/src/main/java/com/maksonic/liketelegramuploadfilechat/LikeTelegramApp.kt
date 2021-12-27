package com.maksonic.liketelegramuploadfilechat

import android.app.Application
import com.maksonic.liketelegramuploadfilechat.domain.domainModule
import com.maksonic.liketelegramuploadfilechat.navigation.navigationModule
import com.maksonic.liketelegramuploadfilechat.presentation.screen.files_sheet.filesBottomSheetModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * @Author: maksonic on 25.12.2021
 */

class LikeTelegramApp : Application() {

    private val moduleList = listOf(navigationModule, domainModule, filesBottomSheetModule)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LikeTelegramApp)
            modules(moduleList)
        }
    }
}