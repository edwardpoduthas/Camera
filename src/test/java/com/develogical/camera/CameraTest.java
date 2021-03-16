package com.develogical.camera;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;

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

    @Test
    public void pressingTheShutterDoesNothingWhenOff() {
        MemoryCard memorycard = mock(MemoryCard.class);
        Sensor sensor  = mock(Sensor.class);
        Camera underTest = new Camera(sensor, memorycard);
        underTest.powerOff();
        underTest.powerOn();
        underTest.powerOff();
        underTest.pressShutter();
        verify(sensor, Mockito.times(0)).readData();
    }

    @Test
    public void continueWriteWhileCameraOff() {
        MemoryCard memorycard = mock(MemoryCard.class);
        Sensor sensor  = mock(Sensor.class);
        Camera underTest = new Camera(sensor, memorycard);
        byte[] dummyData = new byte[]{
                1,1,1,0,0,0,0,0,1
        };
        given(sensor.readData()).willReturn(dummyData);
        underTest.powerOn();
        underTest.pressShutter();
        underTest.powerOff();
        verify(sensor, Mockito.times(0)).powerDown();
    }

    @Test
    public void powerDownOnceFinishedWriting() {
        MemoryCard memorycard = mock(MemoryCard.class);
        Sensor sensor  = mock(Sensor.class);
        Camera underTest = new Camera(sensor, memorycard);
        byte[] dummyData = new byte[]{
                1,1,1,0,0,0,0,0,1
        };
        given(sensor.readData()).willReturn(dummyData);
        underTest.powerOn();
        underTest.pressShutter();
        underTest.powerOff();
        ArgumentCaptor<WriteCompleteListener> writeCompleteListenerArgumentCaptor = ArgumentCaptor.forClass(WriteCompleteListener.class);
        verify(memorycard).write(eq(dummyData), writeCompleteListenerArgumentCaptor.capture());
        writeCompleteListenerArgumentCaptor.getValue().writeComplete();
        // some how we need to say that writing has finsihed
        verify(sensor).powerDown();
    }

    //TODO: another test to verify that camera can take multiple photos
}
