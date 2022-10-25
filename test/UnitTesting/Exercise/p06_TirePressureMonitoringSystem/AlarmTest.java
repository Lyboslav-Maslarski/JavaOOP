package UnitTesting.Exercise.p06_TirePressureMonitoringSystem;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class AlarmTest {

    @Test
    public void test_AlarmWithLowPressure() {
        Sensor sensor = Mockito.mock(Sensor.class);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(12.0);
        Alarm alarm =new Alarm(sensor);
        alarm.check();
        assertTrue(alarm.getAlarmOn());
    }
    @Test
    public void test_AlarmWithHighPressure() {
        Sensor sensor = Mockito.mock(Sensor.class);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(22.0);
        Alarm alarm =new Alarm(sensor);
        alarm.check();
        assertTrue(alarm.getAlarmOn());
    }
    @Test
    public void test_AlarmWithNormalPressure() {
        Sensor sensor = Mockito.mock(Sensor.class);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(20.0);
        Alarm alarm =new Alarm(sensor);
        alarm.check();
        assertFalse(alarm.getAlarmOn());
    }

}