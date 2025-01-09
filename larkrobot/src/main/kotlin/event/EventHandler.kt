package com.merelythis.event

import utils.ALogger
import com.lark.oapi.event.cardcallback.model.P2CardActionTrigger
import com.lark.oapi.event.cardcallback.model.P2CardActionTriggerResponse
import com.lark.oapi.service.im.v1.model.EventMessage
import com.lark.oapi.service.im.v1.model.EventSender
import com.merelythis.model.TextMessage
import utils.json

suspend fun handleMessage(message: EventMessage, sender: EventSender) {
    when (message.messageType) {
        "text" -> {
            val textContent = json.fromJson(message.content, TextMessage::class.java).text
            ALogger.d("EventHandler", "handleMessage: textContent = $textContent")
            handleText(message, sender, textContent)
        }
    }
}

suspend fun handleText(message: EventMessage, sender: EventSender, text: String) {
    validTextMatchers.forEach {
        if (it.match(text)) {
            it.handle(message, sender)
            return
        }
    }
}

fun handleCardAction(event: P2CardActionTrigger?): P2CardActionTriggerResponse {
    val resp = P2CardActionTriggerResponse()


    return resp
}