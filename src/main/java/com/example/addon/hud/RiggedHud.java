package com.example.addon.hud;

import com.example.addon.AddonTemplate;
import com.example.addon.modules.RiggedDrop;
import meteordevelopment.meteorclient.systems.hud.HudElement;
import meteordevelopment.meteorclient.systems.hud.HudElementInfo;
import meteordevelopment.meteorclient.systems.hud.HudRenderer;
import meteordevelopment.meteorclient.utils.render.color.Color;

public class RiggedHud extends HudElement {
    public static final HudElementInfo<RiggedHud> INFO =
        new HudElementInfo<>(AddonTemplate.HUD_GROUP, "rigged-hud", "Host vs viewers.", RiggedHud::new);

    public RiggedHud() { super(INFO); }

    @Override
    public void render(HudRenderer r) {
        int h1 = RiggedDrop.lastHost, v = RiggedDrop.lastViewers;
        String l1 = "Host: " + (h1 < 0 ? "-" : h1);
        String l2 = "Viewers: " + (v < 0 ? "-" : v);
        String l3 = h1 < 0 ? "Belum ada hasil" : h1 > v ? "HOST LEBIH BESAR" : h1 < v ? "VIEWERS LEBIH BESAR" : "SERI";

        double w = Math.max(r.textWidth(l3), Math.max(r.textWidth(l1), r.textWidth(l2))) + 8;
        double h = r.textHeight() * 3 + 12;
        setSize(w, h);

        r.quad(x, y, w, h, new Color(0, 0, 0, 150));
        r.text(l1, x + 4, y + 3, Color.WHITE, true);
        r.text(l2, x + 4, y + 6 + r.textHeight(), Color.WHITE, true);
        r.text(l3, x + 4, y + 9 + r.textHeight() * 2, Color.GREEN, true);
    }
}
