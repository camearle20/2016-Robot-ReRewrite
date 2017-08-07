package org.team401.quetz2016.subsystems

import com.ctre.CANTalon
import edu.wpi.first.wpilibj.Solenoid
import org.team401.quetz2016.DriveStick
import org.team401.quetz2016.MashStick
import org.team401.snakeskin.component.MotorGroup
import org.team401.snakeskin.dsl.buildSubsystem
import org.team401.snakeskin.event.Events
import org.team401.snakeskin.subsystem.Subsystem

/**
 * Created by cameronearle on 8/4/2017.
 */

val Shooter: Subsystem = buildSubsystem {
    val left = CANTalon(2)
    val right = CANTalon(3)

    val motors = MotorGroup(left, right)

    val kicker = Solenoid(0)

    setup {
        left.inverted = true
        left.changeControlMode(CANTalon.TalonControlMode.PercentVbus)
        right.changeControlMode(CANTalon.TalonControlMode.PercentVbus)

        left.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder)
        right.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder)
        left.configEncoderCodesPerRev(20)
        right.configEncoderCodesPerRev(20)
        right.reverseSensor(true)
    }

    on(Events.ENABLED) {
        STATE = "ready"
    }

    state("ready") {
        kicker.set(false)
    }

    state("shoot") {
        kicker.set(true)
    }

    loop {
        when (MODE) {
            "shoot" -> {
                motors.set((MashStick.readAxis { THROTTLE } + 1) / 2)
                //motors.set(1500.0)
                //left.set(1500.0)
                //println("LEFT: ${left.speed} ${left.encVelocity}")
                //println("RIGHT: ${right.speed}")
            }
            "intake" ->
                motors.set(-.5)
            else ->
                motors.set(0.0)

        }
    }


}