package org.team401.quetz2016.subsystems

import edu.wpi.first.wpilibj.Solenoid
import edu.wpi.first.wpilibj.Talon
import org.team401.quetz2016.DriveStick
import org.team401.quetz2016.DriveWheel
import org.team401.snakeskin.component.MotorGroup
import org.team401.snakeskin.dsl.buildSubsystem
import org.team401.snakeskin.event.Events
import org.team401.snakeskin.subsystem.Subsystem

/**
 * Created by cameronearle on 7/28/2017.
 */

val Drivetrain: Subsystem = buildSubsystem {
    val left1 = Talon(0)
    val left2 = Talon(1)
    val left3 = Talon(2)
    val right1 = Talon(3)
    val right2 = Talon(4)
    val right3 = Talon(5)
    val left = MotorGroup(left1, left2, left3)
    val right = MotorGroup(right1, right2, right3)

    val shifter = Solenoid(1)

    setup {
        right1.inverted = true
        left2.inverted = true
        left3.inverted = true
    }

    on (Events.ENABLED) {
        MODE = "drive"
        STATE = "low"
    }

    state("low") {
        shifter.set(true)
    }

    state("high") {
        shifter.set(false)
    }

    loop {
        when (MODE) {
            "drive" -> {
                val translation = DriveStick.readAxis { PITCH }
                val rotation = DriveWheel.readAxis { WHEEL }

                left.set(translation + rotation)
                right.set(translation - rotation)
            }

            "drive_reduced" -> {
                val translation = DriveStick.readAxis { PITCH } / 3
                val rotation = DriveWheel.readAxis { WHEEL } / 1.5

                left.set(translation + rotation)
                right.set(translation - rotation)
            }

        }
    }
}