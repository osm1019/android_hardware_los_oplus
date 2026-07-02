/*
 * Copyright (C) 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.content;

import android.text.TextUtils;

import java.util.List;

public class OplusFeatureConfigManager {
    public static OplusFeatureConfigManager sInstance = null;

    public static OplusFeatureConfigManager getInstance() {
        if (sInstance == null) {
            sInstance = new OplusFeatureConfigManager();
        }
        return sInstance;
    }

    public boolean hasFeature(String featureName) {
        return false;
    }

    private static boolean useOnePlusBaseCamera() {
        return !TextUtils.isEmpty(SystemProperties.get("ro.oplus.version.base"));
    }

    /**
     * Port stub: stock checks a caller allowlist before serving a feature query
     * (OplusAppPlatform's AppFeatureProvider gates reads on this). We allow all callers.
     */
    public boolean isPermit(String name) {
        return true;
    }

    /**
     * Port stub: stock wires carrier/cota dynamic-refresh callbacks here. This port has no dynamic
     * feature source — AppFeatureProvider loads features statically from etc/extension at init — so
     * we accept the registration but never fire the observer. Returning true keeps the platform
     * app's bookkeeping happy. Required surface so OplusAppPlatform can load in system_server
     * without ClassNotFoundException (the historical bootloop cause).
     */
    public boolean registerFeatureActionObserver(OnFeatureActionObserver observer) {
        return true;
    }

    public interface OnFeatureObserver {
        default void onFeatureUpdate(List<String> features) {}
    }

    public interface OnFeatureActionObserver {
        default void onFeaturesActionUpdate(String action, String actionValue,
                IOplusFeatureConfigManager.FeatureID featureId) {}
    }
}
