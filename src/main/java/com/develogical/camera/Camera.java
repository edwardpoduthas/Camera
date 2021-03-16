package com.develogical.camera;

public class Camera implements WriteCompleteListener {
    Sensor _sensor;
    MemoryCard _memorycard;

    public Camera(Sensor sensor, MemoryCard memorycard) {
        _sensor = sensor;
        _memorycard = memorycard;
    }

    public void pressShutter() {
        byte[] data = _sensor.readData();
        _memorycard.write(data, this);
    }

    public void powerOn() {
        _sensor.powerUp();
    }

    public void powerOff() {
        _sensor.powerDown();
    }

    @Override
    public void writeComplete() {

    }
}

