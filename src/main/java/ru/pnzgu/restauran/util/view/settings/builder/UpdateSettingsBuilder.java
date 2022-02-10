package ru.pnzgu.restauran.util.view.settings.builder;

import ru.pnzgu.restauran.util.view.settings.consts.ViewSettingsConst;

public class UpdateSettingsBuilder extends SettingsBuilder {

    public UpdateSettingsBuilder addSetting(ViewSettingsConst header, String body) {
        if (body == null) {
            body = "";
        }
        getSettings().put(header.name(), body);

        return this;
    }

    public static UpdateSettingsBuilder builder() {
        return new UpdateSettingsBuilder();
    }
}
