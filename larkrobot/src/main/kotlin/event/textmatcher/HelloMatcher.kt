package com.merelythis.event.textmatcher

import com.lark.oapi.service.im.v1.model.EventMessage
import com.lark.oapi.service.im.v1.model.EventSender
import com.merelythis.event.ITextMatcher

object HelloMatcher:ITextMatcher {
    override fun match(text: String): Boolean {
        return true
    }

    override suspend fun handle(message: EventMessage, sender: EventSender) {
    }
}