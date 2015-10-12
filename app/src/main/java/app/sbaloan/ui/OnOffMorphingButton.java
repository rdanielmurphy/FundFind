package app.sbaloan.ui;

import android.content.Context;
import android.util.AttributeSet;

import com.dd.morphingbutton.MorphingButton;

/**
 * Created by danielmurphy on 10/11/15.
 */
public class OnOffMorphingButton extends MorphingButton {
    private boolean isButtonOn = true;

    private int duration = 500;

    private MorphingButton.Params onParams, offParams;

    public OnOffMorphingButton(Context context) {
        super(context);
    }

    public OnOffMorphingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OnOffMorphingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setMorphOnParams(MorphingButton.Params params) {
        onParams = params;
    }

    public void setMorphOffParams(MorphingButton.Params params) {
        offParams = params;
    }

    public void setOn() {
        isButtonOn = true;
        turnOn(0);
    }

    public void setOff() {
        isButtonOn = false;
        turnOff(0);
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isButtonOn() {
        return isButtonOn;
    }

    public void switchState() {
        if (isButtonOn) {
            turnOff();
            isButtonOn = false;
        }
        else {
            turnOn();
            isButtonOn = true;
        }
    }

    private void turnOn() {
        morph(onParams);
    }

    private void turnOff() {
        morph(offParams);
    }

    private void turnOn(int duration) {
        onParams.duration(duration);
        turnOn();
        onParams.duration(this.duration);
    }

    private void turnOff(int duration) {
        offParams.duration(duration);
        turnOff();
        offParams.duration(this.duration);
    }
}
