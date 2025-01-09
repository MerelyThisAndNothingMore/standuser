package com.merelythis

import utils.ALogger
import com.lark.oapi.event.EventDispatcher
import com.lark.oapi.event.cardcallback.P2CardActionTriggerHandler
import com.lark.oapi.event.cardcallback.model.P2CardActionTrigger
import com.lark.oapi.event.cardcallback.model.P2CardActionTriggerResponse
import com.lark.oapi.service.im.ImService
import com.lark.oapi.service.im.v1.model.P2MessageReceiveV1
import com.lark.oapi.ws.Client
import com.merelythis.event.handleCardAction
import com.merelythis.event.handleMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import utils.LocalConfigManager

object LarkClient {

    private const val TAG = "LarkClient"

    fun launch() {
        val appId = LocalConfigManager.getProperty(APP_ID)
        val appSecret = LocalConfigManager.getProperty(APP_SECRET)
        ALogger.d(TAG, "appId: $appId, appSecret: $appSecret")
        Client
            .Builder(appId, appSecret)
            .eventHandler(getEventHandler())
            .build()
            .start()
    }

    private fun getEventHandler(): EventDispatcher {
        val verificationToken = LocalConfigManager.getProperty(VERIFICATION_TOKEN)
        val encryptKey = LocalConfigManager.getProperty(ENCRYPT_KEY)
        return EventDispatcher.newBuilder(
            verificationToken, encryptKey
        ).onP2MessageReceiveV1(
            object : ImService.P2MessageReceiveV1Handler() {
                override fun handle(event: P2MessageReceiveV1?) {
                    event ?: return
                    CoroutineScope(Dispatchers.Default).launch {
                        handleMessage(event.event.message, event.event.sender)
                    }
                }
            }
        ).onP2CardActionTrigger(object : P2CardActionTriggerHandler() {
            override fun handle(event: P2CardActionTrigger?): P2CardActionTriggerResponse {
                return handleCardAction(event)
            }
        })
            .build()
    }
}