package org.team401.quetz2016

import org.team401.quetz2016.subsystems.Arm
import org.team401.quetz2016.subsystems.Drivetrain
import org.team401.quetz2016.subsystems.Shooter
import org.team401.snakeskin.dsl.HumanControls
import org.team401.snakeskin.dsl.machine
import org.team401.snakeskin.logic.Direction

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

val Wheel = HumanControls.drivingForceGT(0) {
    invertAxis(Axes.WHEEL)
}

val DriveStick = HumanControls.attack3(1)

val MashStick = HumanControls.extreme3d(2) {
    whenButton(Buttons.THUMB) {
        pressed {
            Arm.machine("arm").setState("move")
        }
        released {
            Arm.machine("arm").setState("idle")
        }
    }

    whenButton(Buttons.TRIGGER) {
        pressed {
            Shooter.machine("kicker").setState("shoot")
        }
        released {
            Shooter.machine("kicker").setState("idle")
        }
    }

    whenHatChanged(Hats.STICK_HAT) {
        when (it) {
            Direction.NORTH -> Shooter.machine("wheels").setState("shoot")
            Direction.CENTER -> Shooter.machine("wheels").setState("idle")
            Direction.SOUTH -> Shooter.machine("wheels").setState("intake")
        }
    }
}