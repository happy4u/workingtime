import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * Created by goodfeel on 2018. 1. 7..
 */
public class BusinessHourOneDayCalcuratorTest {

    @Test
    public void invalidCase(){
        LocalDateTime begin = LocalDateTime.of(2018, 1, 14, 9, 10, 45);
        LocalDateTime end = LocalDateTime.of(2018, 1, 10, 9, 10, 50);
        assertEquals(0, BusinessHourCalcurator.getSeconds(begin, end));
    }

    @Test
    public void testSameDay(){
        // 근무 시간 내 시작 종료
        LocalDateTime begin = LocalDateTime.of(2018, 1, 10, 9, 10, 45);
        LocalDateTime end = LocalDateTime.of(2018, 1, 10, 9, 10, 50);
        assertEquals(5, BusinessHourCalcurator.getSeconds(begin, end));


        // 근무 시간 이후 종료
        begin = LocalDateTime.of(2018, 1, 10, 18, 50, 10);
        end = LocalDateTime.of(2018, 1, 10, 19, 10, 50);
        assertEquals(600 - 10, BusinessHourCalcurator.getSeconds(begin, end));
    }

    @Test
    public void testTwoDay(){

        LocalDateTime begin = LocalDateTime.of(2018, 1, 10, 18, 50, 0);
        LocalDateTime end = LocalDateTime.of(2018, 1, 11, 9, 10, 0);
        assertEquals(20 * 60, BusinessHourCalcurator.getSeconds(begin, end));
    }

    @Test
    public void testMoreThanTwoDay(){

        // expected 값을 근무시간을 통해 계산하도록 해놔야 나중에 근무시간 변경해도 테스트가 안깨질텐데. 나중에..
        LocalDateTime begin = LocalDateTime.of(2018, 1, 10, 18, 50, 0);
        LocalDateTime end = LocalDateTime.of(2018, 1, 12, 9, 10, 0);
        assertEquals((20 * 60) + BusinessHourOneDayTest.SECONDS_OF_ONE_WORKING_DAY, BusinessHourCalcurator.getSeconds(begin, end));
    }

}