package main.injectors;
import main.Simulator;

public class PlayerInjector implements Injector
{
    @Override
    public void inject(Simulator simulator)
    {
        simulator.player.inject(
            simulator, 
            simulator.keyboard
        );
    }
}
