package main.injectors;
import main.Simulator;

public class CameraInjector implements Injector
{
    @Override
    public void inject(Simulator simulator)
    {
        simulator.camera.inject(
            simulator.player
        );
    }
}
