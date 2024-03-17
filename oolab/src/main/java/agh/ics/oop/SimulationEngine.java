package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SimulationEngine{
    private final List<Simulation> simulations;
    private final List<Thread> threads = new ArrayList<>();

    public SimulationEngine(List<Simulation> simulations){
        this.simulations = simulations;
    }

    public void runSync(){
        for (Simulation simulation: simulations){
            simulation.run();
        }
    }

    public void runAsync (){
        for (Simulation simulation: simulations){
            Thread engineThread = new Thread(simulation);
            threads.add(engineThread);
            engineThread.start();
        }
        awaitSimulationEnd();
    }

    public void runAsyncInThreadPool(){
        try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {
            for (Simulation simulation : simulations) {
                executorService.submit(simulation);
            }
            executorService.shutdown();
        }
        awaitSimulationEnd();
    }

    public void awaitSimulationEnd(){
        long deadline = System.currentTimeMillis() + 10000;
        for (Thread thread: threads){
            long waitTime = deadline - System.currentTimeMillis();
            if (waitTime <= 0){
                break;
            }
            try {
                thread.join(waitTime);
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception);
            }
        }
    }
}
