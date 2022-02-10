package ru.pnzgu.restauran.util.view.settings.builder;

import ru.pnzgu.restauran.util.view.settings.consts.ViewSettingsConst;

public class CreateSettingsBuilder extends SettingsBuilder {

    public CreateSettingsBuilder addSetting(ViewSettingsConst header, String body) {
        if (body == null) {
            body = "";
        }
        getSettings().put(header.name(), body);
        return this;
    }

    public static CreateSettingsBuilder builder() {
        return new CreateSettingsBuilder();
    }
}
