package de.yuki6942.labynotes;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;


@AddonMain
public class LabyNotesAddon extends LabyAddon<LabyNotesConfiguration> {

  private

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this
    this.logger().info("Enabled the Addon");
  }

  @Override
  protected Class<LabyNotesConfiguration> configurationClass() {
    return LabyNotesConfiguration.class;
  }
}
