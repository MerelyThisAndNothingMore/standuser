package com.merelythis.event

import com.lark.oapi.service.im.v1.model.EventMessage
import com.lark.oapi.service.im.v1.model.EventSender
import com.merelythis.event.textmatcher.HelloMatcher
import com.merelythis.event.textmatcher.HelpMatcher

interface ITextMatcher {
    fun match(text: String): Boolean
    suspend fun handle(message: EventMessage, sender: EventSender)
}

val validTextMatchers: List<ITextMatcher> = listOf(
    HelloMatcher,
    HelpMatcher
)