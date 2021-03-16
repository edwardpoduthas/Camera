package com.develogical.camera;

public class Camera {
    Sensor _sensor;
    MemoryCard _memorycard;

    public Camera(Sensor sensor, MemoryCard memorycard) {
        _sensor = sensor;
        _memorycard = memorycard;
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

