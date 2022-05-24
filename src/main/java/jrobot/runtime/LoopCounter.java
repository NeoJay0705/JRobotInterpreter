package jrobot.runtime;

import java.util.ArrayDeque;
import java.util.Deque;

public class LoopCounter {

    public static String VARNAME = "LC";

    class NestCounter {
        private int gotoIndex;
        private int currentRound;
        private int maxRound;

        public void touchEnd() {
            this.currentRound++;
        }

        public boolean continues() {
            return currentRound < maxRound;
        }

        public int gobackTo() {
            return this.gotoIndex;
        }

        public int getCurrentRound() {
            return this.currentRound;
        }
    }

    private Deque<NestCounter> nestCounters;

    public LoopCounter() {
        this.nestCounters = new ArrayDeque<>();
    }

    public int currentIteration() {
        return this.nestCounters.peekLast().getCurrentRound();
    }

    public void newRound(int gotoIndex, int maxRound) {
        NestCounter nc = new NestCounter();
        nc.gotoIndex = gotoIndex;
        nc.currentRound = 0;
        nc.maxRound = maxRound;
        this.nestCounters.push(nc);
    }

    public int endRound() {
        NestCounter nc = nestCounters.pollLast();
        nc.touchEnd();
        if (nc.continues()) {
            this.nestCounters.push(nc);
            return nc.gobackTo();
        }
        return -1;
    }
}
