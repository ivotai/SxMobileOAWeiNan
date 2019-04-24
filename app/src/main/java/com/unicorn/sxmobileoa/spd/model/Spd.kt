package com.unicorn.sxmobileoa.spd.model

import android.text.BoringLayout
import com.chad.library.adapter.base.entity.IExpandable
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.unicorn.sxmobileoa.app.Global
import com.unicorn.sxmobileoa.spd.ui.FlowNodeAdapter
import org.joda.time.DateTime
import java.io.Serializable
import java.util.*

data class Spd(
        val cyy: List<Cyy>,
        val flowNodeList: List<FlowNode>,
        val spdData: MutableList<SpdData>,
        val spdXx: SpdXx,
        val nodeModel_1: NodeModel_1?,
        val nodeModel_2: NodeModel_2?,   // 自定义节点
        val spdFj: List<Fj>?,
        val operType: String,
        val spdMb: SpdMb
) : Serializable

data class FlowNode(
        val flowCode: String,
        val flowNodeId: String?,
        val formIndex: Int,
        val gdlx: String,
        val jdsfmrxz: String,
        val nodeid: String,
        val sfngr: Int,
        val sfqy: Int,
        val sftxspyj: Int,
        val sfzdgd: Int,
        val smsTepId: String,
        val spdCode: String,
        val spyjId: String,
        val spyjList: MutableList<Spyj>,
        val spyjNodeId: String,
        val spyjNodeName: String?,
        val nodemc: String?,
        val spyjSort: Int,
        val spyjStatus: Int,
        val spyjsfbt: String,
        val whsfbt: Int,
        val yjsfzwlj: String
) : IExpandable<Spyj>, MultiItemEntity, Serializable {
    override fun getSubItems(): MutableList<Spyj> {
        return spyjList
    }

    var b = false

    override fun isExpanded(): Boolean {
        return b
    }

    override fun getLevel(): Int {
        return 0
    }

    override fun setExpanded(expanded: Boolean) {
        b = expanded
    }

    override fun getItemType() = FlowNodeAdapter.TYPE_FLOW_NODE

    val safeSpyjNodeName get() = if (flowNodeId == null) nodemc else spyjNodeName
    val safeSpyjNodeId get() = if (flowNodeId == null) nodeid else spyjNodeId

}

data class SpdXx(
        val assignName: String,
        val blsj: String,
        val bmbm: String,
        val bmmc: String,
        var bt: String,
        var column1: String,
        val column10: String,
        var column2: String,
        var column3: String,
        var column4: String,
        var column5: String,
        val column6: String,
        val column7: String,
        var column8: String,
        val column9: String,
        val createUserId: String,
        val createUserName: String,
        val dbNodeId: String,
        val dbsprId: String,
        val dbsprmc: String,
        val dcllqtfyspdBfyCt: Int,
        val dcllqtfyspdCt: Int,
        val dcllqtfyspwcCt: Int,
        val dqh: Int,
        val dycount: Int,
        val fbrid: String,
        val fbrmc: String,
        val fbsj: String,
        var flowCode: String,
        val flowName: String,
        val flowParams: Any,
        var funCode: String,
        val fydm: String,
        val fyjc: String,
        val fymc: String,
        val gdbz: Int,
        val gdlx: String,
        val gdrId: String,
        val gdrmc: String,
        val gdsj: String,
        val gdzt: Int,
        val hisNodeId: String,
        val hisNodeName: String,
        val hjjexx: Int,
        val htmlPath: String,
        val id: String,
        val imgPath: String,
        val isUpdateIndex: String,
        val isfb: Int,
        val jsrId: String,
        val jssj: String,
        val lcmc: String,
        var moduleCode: String,
        val ndh: String,
        val nodeId: String,
        val nodeName: String,
        val processInstancesId: String,
        val qhfs: String,
        val qybz: Int,
        val remoteTaskName: String,
        val sdwh: String,
        val sfbl: String,
        val sfdy: Int,
        val sfth: String,
        val sfyxgq: Int,
        val sfzdgd: Int,
        val sjrdz: String,
        val sjrlxfs: String,
        val sjrmc: String,
        val sort: String,
        var spdCode: String,
        val spdJson: String,
        val spdNodeName: String,
        val spdSprmc: String,
        val spdTaskNodeHis: String,
        val spdVersion: Int,
        val spdid: String,
        val sprid: String,
        val sprmc: String,
        val startFlow: String,
        val status: String,
        val sysTime: String,
        var taskId: String,
        val updateTime: String,
        val url: String,
        val wh: String,
        val whid: String,
        val wjcjfs: Int,
        val xtlx: String,
        val ycid: String,
        val ycrs_input: String,
        val ycspdid: String,
        val ytbm_input: String,
        val ytmc_input: String,
        val ytspdid: String,
        val zfbz: Int,
        val zhmc: String
) : Serializable

data class NodeModel_2(
        val flowCode: String,
        val flowNodeId: String,
        val formIndex: Int,
        val gdlx: String,
        val jdsfmrxz: String,
        val nodeid: String,
        val sfngr: Int,
        val sfqy: Int,
        val sftxspyj: Int,
        val sfzdgd: Int,
        val smsTepId: String,
        val spdCode: String,
        val spyjId: String,
        val spyjList: List<Any>,
        val spyjNodeId: String,
        val spyjNodeName: String,
        val spyjSort: Int,
        val spyjStatus: Int,
        val spyjsfbt: String,
        val whsfbt: Int,
        val yjsfzwlj: String
) : Serializable

data class Cyy(
        val content: String,
        val courtCode: String,
        val id: String,
        val system: String,
        val userId: String,
        val xssx: String
) : Serializable

data class SpdData(
        var create: Boolean = true,
        val createUserId: String = Global.loginInfo!!.userId,
        val createUserName: String = Global.loginInfo!!.userName,
        val spdKey: String,
        val dataType: String = spdKey.split("_")[1],
        val id: String = UUID.randomUUID().toString(),
        val qybz: Int = 0,
        var spdValue: String,
        val spdid: String,
        val sysTime: String = DateTime().toString("yyyy-MM-dd HH:mm:ss"),
        var update: Boolean = false,
        var updateTime: String = "",
        val xtlx: String = "oa"
) : Serializable

data class Spyj(
        var createUserId: String,
        var createUserName: String,
        var flowCode: String,
        val flowName: String = "",
        val formIndex: Int = 0,
        val gdlx: String = "",
        val id: String = "",
        val jdsfmrxz: Int = 0,
        val qybz: Int = 0,
        val sfdxtz: Int = 0,
        val sfngr: Int = 0,
        val sfqy: Int = 0,
        val sftxspyj: Int = 0,
        val sfyjtz: Int = 0,
        val sfzdgd: Int = 0,
        val smsTepId: String = "",
        val spdCode: String = "",
        var spyj: String,
        var spyjId: String,
        val spyjNodeId: String,
        val spyjNodeName: String,
        val spyjSort: Int,
        var spyjSprId: String,
        var spyjSprName: String,
        var spyjStatus: Int,
        var spyjYwid: String,
        val spyjsfbt: Int = 0,
        var sysTime: String,
        val tzbt: String = "",
        val tzfw: Int = 0,
        val tzjd: String = "",
        val tznr: String = "",
        val updateTime: String = "",
        val whsfbt: Int = 0,
        val xtlx: String = "",
        val yjsfzwlj: Int = 0,
        var isCurrentNode :Boolean = false
) : MultiItemEntity, Serializable {
    override fun getItemType() =
            FlowNodeAdapter.TYPE_SPYJ
}

data class Fj(
        val fjdz: String,
        val fjmc: String
) : Serializable

data class SpdMb(
        val addmblj: String,
        val againmblj: String,
        val courtCode: String,
        val createUserId: String,
        val createUserName: String,
        val detialmblj: String,
        val dyType: Int,
        val editmblj: String,
        val fileName: String,
        val flowCode: String,
        val flowName: String,
        val functionCode: String,
        val functionName: String,
        val fyjb: Int,
        val id: String,
        val lastModifiTime: String,
        val mbContent: String,
        val mbmc: String,
        val moduleCode: String,
        val ngryj: Int,
        val qybz: Int,
        val remark: String,
        val spdbm: String,
        val spdmc: String,
        val spyjType: Int,
        val sysTime: String,
        val updateTime: String,
        val version: Int,
        val xtlx: String
) : Serializable

data class NodeModel_1(
        val createUserId: String,
        val createUserName: String,
        val createuserid: String,
        val createusername: String,
        val flowcode: String,
        val flowname: String,
        val fydm: String,
        val gdlx: String,
        val id: String,
        val jdsfmrxz: Int,
        val ngrnode: Int,
        val nodeid: String,
        val nodemc: String,
        val qybz: Int,
        val remark: String,
        val sfdxtz: Int,
        val sfgz: Int,
        val sftxspyj: Int,
        val sfyjtz: Int,
        val sfyxgq: Int,
        val sfzdgd: Int,
        val smsTepId: String,
        val sort: Int,
        val spyjList: List<Any>,
        val spyjsfbt: Int,
        val sysTime: String,
        val systime: Systime,
        val tzbt: String,
        val tzfw: Int,
        val tzjd: String,
        val tznr: String,
        val updateTime: String,
        val whsfbt: Int,
        val xtlx: String,
        val yjsfzwlj: Int
) : Serializable

data class Systime(
        val date: Int,
        val day: Int,
        val hours: Int,
        val minutes: Int,
        val month: Int,
        val nanos: Int,
        val seconds: Int,
        val time: Long,
        val timezoneOffset: Int,
        val year: Int
) : Serializable