/**
 *  Virtual On/Off Switch Creator
 *
 *  Copyright 2015 Eric Roberts
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
    name: "Virtual On/Off Switch Creator",
    namespace: "baldeagle072",
    author: "Eric Roberts",
    description: "Creates virtual switches!",
    category: "Convenience",
    iconUrl: "http://baldeagle072.github.io/icons/standard-tile@1x.png",
    iconX2Url: "http://baldeagle072.github.io/icons/standard-tile@2x.png",
    iconX3Url: "http://baldeagle072.github.io/icons/standard-tile@3x.png")


preferences {
	section("Create Virtual Switch") {
		input "switchLabel", "text", title: "Switch Label", required: true
	}
}

def installed() {
	log.debug "Installed with settings: ${settings}"

	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"

	unsubscribe()
	initialize()
}

def initialize() {
    def deviceId = app.id + "SimulatedSwitch"
    log.debug(deviceId)
    def existing = getChildDevice(deviceId)
    if (!existing) {
        def childDevice = addChildDevice("smartthings", "On/Off Button Tile", deviceId, null, [label: switchLabel])
    }
}

def uninstalled() {
    removeChildDevices(getChildDevices())
}

private removeChildDevices(delete) {
    delete.each {
        deleteChildDevice(it.deviceNetworkId)
    }
}

