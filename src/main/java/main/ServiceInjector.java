/*  
 *  ServiceInjector.java
 *  
 *  Description: Responsible for injecting dependencies into services based on request.
 *
*/

package main;
import main.injectors.*;
import java.util.ArrayList;

public class ServiceInjector 
{
    private Simulator simulator;
    private ArrayList<Injector> toInject;

    public ServiceInjector(Simulator simulator) 
    { 
        this.simulator = simulator; 
        this.toInject = new ArrayList<Injector>();
        setupInjections();
    }

    private void setupInjections()
    {
        toInject.add(new PlayerInjector());
        toInject.add(new CameraInjector());
        toInject.add(new CollisionCheckerInjector());
    }

    public void injectDependencies()
    {
        for(int i=0; i<toInject.size(); i++)
        {
            toInject.get(i).inject(simulator);
        }
    }
}
