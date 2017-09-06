package org.team401.quetz2016.subsystems

import com.ctre.CANTalon
import edu.wpi.first.wpilibj.Spark
import org.team401.quetz2016.Gamepad
import org.team401.snakeskin.dsl.buildSubsystem
import org.team401.snakeskin.event.Events
import org.team401.snakeskin.subsystem.States
import org.team401.snakeskin.subsystem.Subsystem

/**
 * Created by cameronearle on 8/1/2017.
 */

val Arm: Subsystem = buildSubsystem {
    val motor = CANTalon(1)

    setup {
        motor.changeControlMode(CANTalon.TalonControlMode.PercentVbus)
        motor.setVoltageRampRate(12.0)
        motor.enableLimitSwitch(true, true)
    }

    stateMachine("arm") {
        state("move") {
            action {
                motor.set(Gamepad.readAxis(5) / 2)
            }
        }

        default {
            action {
                motor.set(0.0)
            }
        }
    }
}