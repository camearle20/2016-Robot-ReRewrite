package org.team401.quetz2016.subsystems

import com.ctre.MotorControl.CANTalon
import com.ctre.MotorControl.SmartMotorController
import edu.wpi.first.wpilibj.Solenoid
import org.team401.quetz2016.MashStick
import org.team401.snakeskin.component.MotorGroup
import org.team401.snakeskin.dsl.buildSubsystem
import org.team401.snakeskin.event.Events
import org.team401.snakeskin.logging.LoggerManager
import org.team401.snakeskin.subsystem.States
import org.team401.snakeskin.subsystem.Subsystem

val Shooter: Subsystem = buildSubsystem {
    val left = CANTalon(2)
    val right = CANTalon(3)

    val motors = MotorGroup(left, right)

    val kicker = Solenoid(0)

    setup {
        left.inverted = true
    }

    val shooterMachine = stateMachine("wheels") {
        state("intake") {
            action {
                motors.set(-.5)
            }
        }

        state("shoot") {
            action {
                motors.set((MashStick.readAxis {THROTTLE} + 1) / 2)
            }
        }

        default {
            action {
                motors.set(0.0)
            }
        }
    }

    val kickerMachine = stateMachine("kicker") {
        state("shoot") {
            rejectIf {
                shooterMachine.getState() != "shoot"
            }

            entry {
                kicker.set(true)
            }
        }

        default {
            entry {
                kicker.set(false)
            }
        }
    }
}