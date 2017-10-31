package org.team401.quetz2016.subsystems

import com.ctre.MotorControl.CANTalon
import com.ctre.MotorControl.SmartMotorController
import edu.wpi.first.wpilibj.Spark
import org.team401.quetz2016.MashStick
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
        motor.changeControlMode(SmartMotorController.TalonControlMode.PercentVbus)
        motor.setVoltageRampRate(12.0)
        motor.enableLimitSwitch(true, true)
    }

    stateMachine("arm") {
        state("move") {
            action {
                motor.set(MashStick.readAxis { PITCH } / 2)
            }
        }

        default {
            action {
                motor.set(0.0)
            }
        }
    }
}