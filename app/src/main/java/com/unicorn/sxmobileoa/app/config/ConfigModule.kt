package com.unicorn.sxmobileoa.app.config

import android.os.Environment
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.app.port.PortConfigHelper
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
        const val updatePort = "8089"
        const val defaultFydm = "R50"

        private val defaultPort: String
            get() = PortConfigHelper.portConfigs.find { defaultFydm.toLowerCase() in it.dms.map { dm -> dm.toLowerCase() } }!!.port

        val fydm: String get() = Global.court?.dm ?: defaultFydm
        private val port: String
            get() {
                val port = PortConfigHelper.portConfigs.find { fydm.toLowerCase() in it.dms.map { dm -> dm.toLowerCase() } }?.port
                        ?: defaultPort
                return port
            }

        val appListener: String get() = "appListener_${fydm.toLowerCase()}"

        val baseUrl: String get() = "http://$ip:$port/$appListener/"

        val baseAttachmentUrl = "${baseUrl}attachRedirect.do"

    }

}


