package org.team401.quetz2016

import org.team401.quetz2016.subsystems.Arm
import org.team401.quetz2016.subsystems.Drivetrain
import org.team401.quetz2016.subsystems.Shooter
import org.team401.snakeskin.controls.Button
import org.team401.snakeskin.controls.Controller
import org.team401.snakeskin.controls.mappings.Extreme3D
import org.team401.snakeskin.dsl.HumanControls
import org.team401.snakeskin.dsl.isInState
import org.team401.snakeskin.dsl.toggleState
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

val DriveWheel = HumanControls.drivingForceGT(0).apply {
    getAxis(0).inverted = true
}
val DriveStick = HumanControls.attack3(1) {
    whenButtonPressed(Buttons.STICK_LEFT) {
        Drivetrain.STATE = "low"
    }

    whenButtonPressed(Buttons.STICK_RIGHT) {
        if (Drivetrain.MODE == "drive") {
            Drivetrain.STATE = "high"
        }
    }

    whenButtonPressed(Buttons.BASE_LEFT_TOP) {
        Drivetrain.MODE = "drive_reduced"
    }

    whenButtonPressed(Buttons.BASE_LEFT_BOTTOM) {
        Drivetrain.MODE = "drive"
    }
}

val MashStick = HumanControls.extreme3d(2) {
    whenButtonPressed(Buttons.THUMB) {
        Arm.MODE = "move"
    }
    whenButtonReleased(Buttons.THUMB) {
        Arm.MODE = "stop"
    }

    whenButtonPressed(Buttons.TRIGGER) {
        if (Shooter.MODE == "shoot") {
            Shooter.STATE = "shoot"
        }
    }
    whenButtonReleased(Buttons.TRIGGER) {
        Shooter.STATE = "ready"
    }

    whenHatChanged(Hats.STICK_HAT) {
        when (it) {
            Direction.NORTH ->
                    Shooter.MODE = "shoot"
            Direction.CENTER ->
                    Shooter.MODE = "ready"
            Direction.SOUTH ->
                    Shooter.MODE = "intake"
        }
    }
}