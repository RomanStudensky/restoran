package ru.pnzgu.restauran.util.view.settings.builder;

import ru.pnzgu.restauran.util.view.settings.consts.ViewSettingsConst;

public class ViewSettingsBuilder extends SettingsBuilder {

    public ViewSettingsBuilder addSetting(ViewSettingsConst header, String body) {
        if (body == null) {
            body = "";
        }
        getSettings().put(header.name(), body);

        return this;
    }

    public static ViewSettingsBuilder builder() {
        return new ViewSettingsBuilder();
    }
}
