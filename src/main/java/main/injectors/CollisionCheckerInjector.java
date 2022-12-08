package main.injectors;
import main.Simulator;

public class CollisionCheckerInjector implements Injector
{
    @Override
    public void inject(Simulator simulator)
    {
        simulator.collisionChecker.inject(
            simulator.mapHandler
        );
    }
}
