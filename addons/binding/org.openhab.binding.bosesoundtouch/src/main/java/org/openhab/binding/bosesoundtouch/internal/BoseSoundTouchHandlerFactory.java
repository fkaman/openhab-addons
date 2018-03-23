/**
 * Copyright (c) 2010-2016 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.bosesoundtouch.internal;

import static org.openhab.binding.bosesoundtouch.BoseSoundTouchBindingConstants.*;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.openhab.binding.bosesoundtouch.handler.BoseSoundTouchHandler;

/**
 * The {@link BoseSoundTouchHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Christian Niessner - Initial contribution
 */
public class BoseSoundTouchHandlerFactory extends BaseThingHandlerFactory {

    private Map<String, BoseSoundTouchHandler> mapOfAllSoundTouchDevices = new HashMap<>();

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (thingTypeUID.equals(THING_TYPE_DEVICE)) {
            return new BoseSoundTouchHandler(thing, this);
        }

        return null;
    }

    public void registerSoundTouchDevice(BoseSoundTouchHandler handler) {
        mapOfAllSoundTouchDevices.put(handler.getMacAddress(), handler);
    }

    public void removeSoundTouchDevice(BoseSoundTouchHandler handler) {
        mapOfAllSoundTouchDevices.remove(handler.getMacAddress());
    }

    public Map<String, BoseSoundTouchHandler> getAllSoundTouchDevices() {
        return mapOfAllSoundTouchDevices;
    }

    public BoseSoundTouchHandler getBoseSoundTouchDevice(String mac) {
        return mapOfAllSoundTouchDevices.get(mac);
    }
}
