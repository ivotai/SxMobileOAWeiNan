package com.unicorn.sxmobileoa.app

import com.unicorn.sxmobileoa.login.model.LoginInfo
import com.unicorn.sxmobileoa.simple.court.model.Court
import com.unicorn.sxmobileoa.spd.model.Spd

object Global {

    var ticket: String? = null

    var loginInfo: LoginInfo? = null

    var court: Court? = null

    //

    lateinit var spd: Spd

}
