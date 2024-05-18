package Stretagy;

import Repository.SlabRepository;

import java.util.Calendar;
import java.util.Date;

public class CalculateFeesStrategyFactory {
    SlabRepository slabRepository;

    public CalculateFeesStrategyFactory(SlabRepository slabRepository) {
        this.slabRepository = slabRepository;
    }
    public CalculateFeesStrategy getCalculateFeesStrategy(Date exitTime){
        CalculateFeesStrategy calculateFeesStrategy;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(exitTime);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        boolean isWeekend = day == Calendar.SATURDAY || day == Calendar.SUNDAY;
        if(isWeekend){
            calculateFeesStrategy = new WeekEndStretagy(slabRepository);
        }
        else {
            calculateFeesStrategy = new WeekDayStretagy();
        }
        return calculateFeesStrategy;
    }
}
