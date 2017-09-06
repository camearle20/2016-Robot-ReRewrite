package org.team401.quetz2016.subsystems

import com.ctre.CANTalon
import edu.wpi.first.wpilibj.Solenoid
import org.team401.quetz2016.Gamepad
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
        left.changeControlMode(CANTalon.TalonControlMode.PercentVbus)
        right.changeControlMode(CANTalon.TalonControlMode.PercentVbus)
    }

    val shooterMachine = stateMachine("wheels") {
        state("intake") {
            action {
                motors.set(-.5)
            }
            exit {
                motors.set(0.0)
            }
        }

        state("shoot_reduced") {
            action {
                motors.set(Gamepad.readAxis { RIGHT_TRIGGER } / 2)
            }
        }

        default {
            action {
                motors.set(Gamepad.readAxis { RIGHT_TRIGGER })
            }
        }
    }

    val kickerMachine = stateMachine("kicker") {
        state("shoot") {
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

    on(Events.ENABLED) {
        shooterMachine.setState("shoot")
    }
}