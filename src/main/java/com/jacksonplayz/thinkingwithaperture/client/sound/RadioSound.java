package com.jacksonplayz.thinkingwithaperture.client.sound;

import com.jacksonplayz.thinkingwithaperture.entity.EntityRadio;
import com.jacksonplayz.thinkingwithaperture.init.ModSounds;

import net.minecraft.client.audio.MovingSound;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;

public class RadioSound extends MovingSound {

    private EntityRadio radio;

    public RadioSound(EntityRadio radio) {
        super(ModSounds.RADIO_LOOP, SoundCategory.RECORDS);
        this.radio = radio;
        this.repeat = true;
        this.volume = 0.5f;
    }

    @Override
    public void update() {
        this.xPosF = (float) radio.posX;
        this.yPosF = (float) radio.posY;
        this.zPosF = (float) radio.posZ;
        if (this.radio == null || (!this.radio.isActivated() && !this.donePlaying)) {
            this.donePlaying = true;
        }
    }
    
    public EntityRadio getRadio() {
        return radio;
    }
}