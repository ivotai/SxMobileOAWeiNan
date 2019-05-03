package com.unicorn.sxmobileoa.app.config

import android.os.Environment
import dagger.Module
import java.io.File

@Module
class ConfigModule {

    companion object {

        fun baseDir(): String {
            val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val baseDir = File(downloadDir, "SxMobileOA")
            if (!baseDir.exists()) baseDir.mkdir()
            return baseDir.absolutePath
        }

        const val ip = "219.145.168.171"
        const val mailPort = "8090"
        const val defaultFydm = "R50"   // 渭南

        val baseUrl = "http://219.145.168.171:8787/appListener/"





        val baseAttachmentUrl = "${baseUrl}attachRedirect.do"

    }

}


