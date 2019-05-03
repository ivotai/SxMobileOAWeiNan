package com.unicorn.sxmobileoa.app.port

import android.content.Context
import android.content.res.AssetManager
import com.google.gson.reflect.TypeToken
import com.unicorn.sxmobileoa.app.di.ComponentHolder
import com.unicorn.sxmobileoa.select.deptUser.model.User
import java.io.BufferedReader
import java.io.InputStreamReader

object PortConfigHelper {

    lateinit var portConfigs: List<PortConfig>

    fun init(context: Context) {
        val json = readJsonFromAssets(context)
        val type = object : TypeToken<List<PortConfig>>() {}.type
        val portConfigs = ComponentHolder.appComponent.getGson().fromJson<List<PortConfig>>(json, type)
        this.portConfigs = portConfigs
    }

    private fun readJsonFromAssets(context: Context): String {
        val jsonFileName = "portConfig.json"
        val stringBuilder = StringBuilder()
        val bufferedReader = BufferedReader(InputStreamReader(context.assets.open(jsonFileName)))
        var temp: String?
        while (true) {
            temp = bufferedReader.readLine()
            if (temp == null) break
            stringBuilder.append(temp)
        }
        return stringBuilder.toString()
    }

}