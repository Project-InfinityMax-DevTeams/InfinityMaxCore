package com.yuyuto.infinitymaxcore.libs.physics;

import org.joml.Vector3d;

public class MotionComponent {

    private Vector3d position;
    private Vector3d velocity;
    private final Vector3d acceleration;

    public MotionComponent(Vector3d position, Vector3d velocity, MovementType movementType) {

        this.position = new Vector3d(position);
        this.velocity = new Vector3d(velocity);

        if (movementType == MovementType.PROJECTILE) {
            this.acceleration = new Vector3d(0, -9.81, 0); // 重力
        } else {
            this.acceleration = new Vector3d(0, 0, 0);
        }
    }

    public void update(double deltaTime) {

        velocity = velocity.add(new Vector3d(acceleration).mul(deltaTime));
        position = position.add(new Vector3d(velocity).mul(deltaTime));
    }

    public Vector3d getPosition(){
        return new Vector3d(position);
    }

    public Vector3d getVelocity() {
        return new Vector3d(velocity);
    }
}