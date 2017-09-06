
import org.team401.quetz2016.Gamepad
import org.team401.quetz2016.subsystems.Arm
import org.team401.quetz2016.subsystems.Drivetrain
import org.team401.quetz2016.subsystems.Shooter
import org.team401.snakeskin.registry.Controllers
import org.team401.snakeskin.registry.Subsystems

/*
 * 2016-Robot-Code - Created on 7/18/17
 * Author: Cameron Earle
 * 
 * This code is licensed under the GNU GPL v3
 * You can find more info in the LICENSE file at project root
 */

/**
 * @author Cameron Earle
 * @version 7/18/17
 */

fun setup() {
    Controllers.add(Gamepad)
    Subsystems.add(Drivetrain, Arm, Shooter)
}

fun auto() {

}