package ru.pnzgu.restauran.util.view.settings.builder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class SettingsBuilder {
    protected ViewSettings viewSettings;

    public ViewSettings build() {
        viewSettings.build();
        return viewSettings;
    }

    protected SettingsBuilder() {
        viewSettings = new ViewSettings();
    }

    protected Map<String, String> getSettings() {
        return viewSettings.settings;
    }

    protected static final class ViewSettings {
        private Map<String, String> settings;

        private ViewSettings() {
            settings = new HashMap<>();
        }

        private void build() {
            settings = Collections.unmodifiableMap(settings);
        }
    }
}



