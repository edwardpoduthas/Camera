package com.develogical.camera;

public class Camera {
    Sensor _sensor;


    public Camera(Sensor sensor) {
        _sensor = sensor;
    }

    public void pressShutter() {
        // not implemented
    }

    public void powerOn() {
        _sensor.powerUp();
    }

    public void powerOff() {
        _sensor.powerDown();
    }
}

