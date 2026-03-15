package com.yuyuto.infinitymaxcore.libs.physics;

import org.joml.Vector3d;

public abstract class PhysicsObject {

    protected final MotionComponent motion;
    protected final PhysicalState physicalState;

    public PhysicsObject(MotionComponent motion,
                         PhysicalState physicalState) {
        this.motion = motion;
        this.physicalState = physicalState;
    }

    public void update(double deltaTime) {
        motion.update(deltaTime);
        onPhysicsUpdate(deltaTime);
    }

    protected abstract void onPhysicsUpdate(double deltaTime);

    public Vector3d getPosition() {
        return motion.getPosition();
    }

    public PhysicalState getPhysicalState() {
        return physicalState;
    }
}