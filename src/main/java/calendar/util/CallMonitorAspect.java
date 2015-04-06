package calendar.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.util.StopWatch;

/**
 * Created by Marat on 16.03.2015.
 */
@ManagedResource("calendar:type=CallMonitor")
@Aspect
public class CallMonitorAspect {
    private boolean enabled = true;
    private int callCount = 0;
    private long accumulatedCallTime = 0;

    @ManagedAttribute
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @ManagedAttribute
    public boolean isEnabled() {
        return enabled;
    }

    @ManagedAttribute
    public int getCallCount() {
        return callCount;
    }

    @ManagedOperation
    public void reset() {
        callCount = 0;
        accumulatedCallTime = 0;
    }

    @ManagedAttribute
    public long getAccumulatedCallTime() {
        if (callCount > 0)
            return accumulatedCallTime / callCount;
        return 0;
    }

    @Around("within(@org.springframework.stereotype.Repository *)")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        if(this.enabled) {
            StopWatch sw = new StopWatch(joinPoint.toShortString());

            sw.start("invoke");
            try {
                return joinPoint.proceed();
            }
            finally {
                sw.stop();
                synchronized (this) {
                    this.callCount++;
                    this.accumulatedCallTime += sw.getTotalTimeMillis();
                }
            }
        }
        else {
            return joinPoint.proceed();
        }
    }
}
