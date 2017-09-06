package org.team401.quetz2016

import org.team401.quetz2016.subsystems.Arm
import org.team401.quetz2016.subsystems.Drivetrain
import org.team401.quetz2016.subsystems.Shooter
import org.team401.snakeskin.controls.Button
import org.team401.snakeskin.controls.Controller
import org.team401.snakeskin.controls.mappings.Extreme3D
import org.team401.snakeskin.dsl.HumanControls
import org.team401.snakeskin.dsl.machine
import org.team401.snakeskin.logic.Direction
import org.team401.snakeskin.subsystem.States

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

val Gamepad = HumanControls.f310(0) {
    invertAxis(Axes.RIGHT_X)

    whenButton(Buttons.START) {
        pressed {
            Drivetrain.machine("drive").setState("drive")
            Shooter.machine("wheels").setState("shoot")
        }
    }

    whenButton(Buttons.BACK) {
        pressed {
            Drivetrain.machine("drive").setState("drive_reduced")
            Shooter.machine("wheels").setState("shoot_reduced")
        }
    }

    whenButton(Buttons.LEFT_BUMPER) {
        pressed {
            Drivetrain.machine("shift").setState("low")
        }
    }

    whenButton(Buttons.RIGHT_BUMPER) {
        pressed {
            Drivetrain.machine("shift").setState("high")
        }
    }

    whenButton(Buttons.RIGHT_STICK) {
        pressed {
            Arm.machine("arm").setState("move")
        }

        released {
            Arm.machine("arm").setState("stop")
        }
    }

    whenButton(Buttons.B) {
        pressed {
            Shooter.machine("kicker").setState("shoot")
        }

        released {
            Shooter.machine("kicker").setState("ready")
        }
    }

    whenButton(Buttons.LEFT_STICK) {
        pressed {
            Shooter.machine("wheels").setState("intake")
        }

        released {
            Shooter.machine("wheels").back()
        }
    }
}