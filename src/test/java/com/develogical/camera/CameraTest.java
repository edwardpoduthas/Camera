package com.develogical.camera;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

public class CameraTest {
    @Test
    public void switchingTheCameraOnPowersUpTheSensor() {
        // write your test here
        Sensor sensor  = mock(Sensor.class);
        Camera underTest = new Camera(sensor);
        underTest.powerOn();
        verify(sensor).powerUp();
    }
}
