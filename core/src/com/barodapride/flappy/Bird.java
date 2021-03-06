package com.barodapride.flappy;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

/**
 * Created by Mike on 3/20/2015.
 */
public class Bird extends Actor {

    public static final int WIDTH = 32; // pixels
    public static final int HEIGHT = 32;

    public static final float GRAVITY = 920f; // pixels per second per second
    public static final float JUMP_VELOCITY = 350f; // pixels per second

    // Actor keeps track of position so we just need to keep track of velocity and acceleration
    private Vector2 vel;
    private Vector2 accel;

    private TextureRegion region;

    private State state;

    private enum State { alive, dead };

    public Bird() {
        region = new TextureRegion(Assets.bird);
        setWidth(WIDTH);
        setHeight(HEIGHT);
        state = State.alive;

        vel = new Vector2(0, 0);
        accel = new Vector2(0, -GRAVITY);

        // An actor's origin defines the center for rotation.
        setOrigin(Align.center);
    }

    public void jump() {
        vel.y = JUMP_VELOCITY;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        switch (state){
            case alive:
                actAlive(delta);
                break;
            case dead:
                vel = Vector2.Zero;
                accel = Vector2.Zero;
                break;
        }
    }

    private void actAlive(float delta) {
        applyAccel(delta);
        updatePosition(delta);

        setRotation(MathUtils.clamp(vel.y / JUMP_VELOCITY * 45f, -90, 45));

        if (isBelowGround()){
            setY(FlappyGame.GROUND_LEVEL);
            state = State.dead;
        }

        if (isAboveCeiling()){
            setY(FlappyGame.HEIGHT - getHeight());
            state = State.dead;
        }
    }

    private boolean isAboveCeiling() {
        return (getY(Align.top) >= FlappyGame.HEIGHT);
    }

    private boolean isBelowGround() {
        return (getY(Align.bottom) <= FlappyGame.GROUND_LEVEL);
    }

    private void updatePosition(float delta) {
        setX(getX() + vel.x * delta);
        setY(getY() + vel.y * delta);
    }

    private void applyAccel(float delta) {
        vel.add(accel.x * delta, accel.y * delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(),
                getScaleY(), getRotation());
    }

}
