/*  
 *  Injector.java
 *  
 *  Description: Injects required dependencies into a specific class.
 *
*/

package main.injectors;
import main.Simulator;

public interface Injector 
{
    // Injects denpendencies
    public void inject(Simulator simulator);
}
