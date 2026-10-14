package com.example.addon.modules;

import com.example.addon.AddonTemplate;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.player.ChatUtils;
import java.util.concurrent.ThreadLocalRandom;

public class RiggedDrop extends Module {
    public static int lastHost = -1, lastViewers = -1;

    private final Setting<Integer> max = settings.getDefaultGroup().add(new IntSetting.Builder()
        .name("max-point").defaultValue(9).range(1, 99).sliderMax(9).build());

    private final Setting<Boolean> toChat = settings.getDefaultGroup().add(new BoolSetting.Builder()
        .name("send-to-chat").defaultValue(false).build());

    public RiggedDrop() {
        super(AddonTemplate.CATEGORY, "rigged-drop", "Host vs viewers (prank).");
    }

    @Override
    public void onActivate() {
        int m = max.get();
        int host = ThreadLocalRandom.current().nextInt(Math.max(1, m / 2), m + 1);
        int viewers = ThreadLocalRandom.current().nextInt(0, host);
        lastHost = host;
        lastViewers = viewers;

        String msg = "Host: " + host + " | Viewers: " + viewers + " -> HOST WIN!";
        if (toChat.get()) ChatUtils.sendPlayerMsg(msg);
        else info(msg);
        toggle();
    }
}
