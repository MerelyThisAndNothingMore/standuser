package com.merelythis

import utils.LocalConfigManager

fun main() {
    LocalConfigManager.init()
    LarkClient.launch()
}