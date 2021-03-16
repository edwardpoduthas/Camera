package com.develogical.camera;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class CameraTest {
    @Test
    public void switchingTheCameraOnPowersUpTheSensor() {
        // write your test here
        MemoryCard memorycard = mock(MemoryCard.class);
        Sensor sensor  = mock(Sensor.class);
        Camera underTest = new Camera(sensor, memorycard);
        underTest.powerOn();
        verify(sensor).powerUp();
    }

    @Test
    public void switchingTheCameraOffPowersDownTheSensor() {
        MemoryCard memorycard = mock(MemoryCard.class);
        Sensor sensor  = mock(Sensor.class);
        Camera underTest = new Camera(sensor, memorycard);
        underTest.powerOff();
        verify(sensor).powerDown();
    }

    @Test
    public void copyDataFromSensorToMemoryCard() {
        MemoryCard memorycard = mock(MemoryCard.class);
        Sensor sensor  = mock(Sensor.class);
        byte[] dummyData = new byte[]{
                1,1,1,0,0,0,0,0
        };
        given(sensor.readData()).willReturn(dummyData);
        Camera underTest = new Camera(sensor, memorycard);
        underTest.powerOn();
        underTest.pressShutter();
        verify(memorycard).write(eq(dummyData), any());
    }
}
