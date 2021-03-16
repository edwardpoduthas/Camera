package com.develogical.camera;

public class Camera implements WriteCompleteListener {
    Sensor _sensor;
    MemoryCard _memorycard;
    boolean powerOn = false, isWriting = false;

    public Camera(Sensor sensor, MemoryCard memorycard) {
        _sensor = sensor;
        _memorycard = memorycard;
    }

    public void pressShutter() {
        if(powerOn){
            isWriting = true;
            byte[] data = _sensor.readData();
            _memorycard.write(data, this);
        }
    }

    public void powerOn() {
        powerOn = true;
        _sensor.powerUp();
    }

    public void powerOff() {
        if (!isWriting){
            powerOn = false;
            _sensor.powerDown();
        }
    }

    @Override
    public void writeComplete() {

    }
}

