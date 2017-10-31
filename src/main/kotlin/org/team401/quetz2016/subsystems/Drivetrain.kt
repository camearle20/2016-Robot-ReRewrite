package org.team401.quetz2016.subsystems

import edu.wpi.first.wpilibj.Solenoid
import edu.wpi.first.wpilibj.Talon
import org.team401.quetz2016.DriveStick
import org.team401.quetz2016.Wheel
import org.team401.snakeskin.component.MotorGroup
import org.team401.snakeskin.dsl.buildSubsystem
import org.team401.snakeskin.event.Events
import org.team401.snakeskin.subsystem.States
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

    val driveMachine = stateMachine("drive") {
        var translation = 0.0
        var rotation = 0.0

        fun drive() {
            left.set(translation + rotation)
            right.set(translation - rotation)
        }

        state("drive") {
            action {
                translation = DriveStick.readAxis { PITCH }
                rotation = Wheel.readAxis { WHEEL }
                drive()
            }
        }

        default {
            action {
                translation = 0.0
                rotation = 0.0
                drive()
            }
        }
    }

    val shiftMachine = stateMachine("shifting") {
        state("low") {
            entry {
                shifter.set(true)
            }
        }

        state("high") {
            entry {
                shifter.set(false)
            }
        }
    }

    on (Events.ENABLED) {
        driveMachine.setState("drive")
        shiftMachine.setState("low")
    }
}