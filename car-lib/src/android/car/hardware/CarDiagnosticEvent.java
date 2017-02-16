/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.car.hardware;

import android.annotation.IntDef;
import android.annotation.Nullable;
import android.car.annotation.FutureFeature;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.util.SparseIntArray;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/**
 * A CarDiagnosticEvent object corresponds to a single diagnostic event frame coming from the car.
 *
 * @hide
 */
@FutureFeature
public class CarDiagnosticEvent implements Parcelable {
    /** This class contains the indices of the standard OBD2 sensors with an integer value */
    public static final class Obd2IntegerSensorIndex {
        private Obd2IntegerSensorIndex() {}

        public static final int FUEL_SYSTEM_STATUS = 0;
        public static final int MALFUNCTION_INDICATOR_LIGHT_ON = 1;
        public static final int IGNITION_MONITORS_SUPPORTED = 2;
        public static final int IGNITION_SPECIFIC_MONITORS = 3;
        public static final int INTAKE_AIR_TEMPERATURE = 4;
        public static final int COMMANDED_SECONDARY_AIR_STATUS = 5;
        public static final int NUM_OXYGEN_SENSORS_PRESENT = 6;
        public static final int RUNTIME_SINCE_ENGINE_START = 7;
        public static final int DISTANCE_TRAVELED_WITH_MALFUNCTION_INDICATOR_LIGHT_ON = 8;
        public static final int WARMUPS_SINCE_CODES_CLEARED = 9;
        public static final int DISTANCE_TRAVELED_SINCE_CODES_CLEARED = 10;
        public static final int ABSOLUTE_BAROMETRIC_PRESSURE = 11;
        public static final int CONTROL_MODULE_VOLTAGE = 12;
        public static final int AMBIENT_AIR_TEMPERATURE = 13;
        public static final int TIME_WITH_MALFUNCTION_LIGHT_ON = 14;
        public static final int TIME_SINCE_TROUBLE_CODES_CLEARED = 15;
        public static final int MAX_FUEL_AIR_EQUIVALENCE_RATIO = 16;
        public static final int MAX_OXYGEN_SENSOR_VOLTAGE = 17;
        public static final int MAX_OXYGEN_SENSOR_CURRENT = 18;
        public static final int MAX_INTAKE_MANIFOLD_ABSOLUTE_PRESSURE = 19;
        public static final int MAX_AIR_FLOW_RATE_FROM_MASS_AIR_FLOW_SENSOR = 20;
        public static final int FUEL_TYPE = 21;
        public static final int FUEL_RAIL_ABSOLUTE_PRESSURE = 22;
        public static final int ENGINE_OIL_TEMPERATURE = 23;
        public static final int DRIVER_DEMAND_PERCENT_TORQUE = 24;
        public static final int ENGINE_ACTUAL_PERCENT_TORQUE = 25;
        public static final int ENGINE_REFERENCE_PERCENT_TORQUE = 26;
        public static final int ENGINE_PERCENT_TORQUE_DATA_IDLE = 27;
        public static final int ENGINE_PERCENT_TORQUE_DATA_POINT1 = 28;
        public static final int ENGINE_PERCENT_TORQUE_DATA_POINT2 = 29;
        public static final int ENGINE_PERCENT_TORQUE_DATA_POINT3 = 30;
        public static final int ENGINE_PERCENT_TORQUE_DATA_POINT4 = 31;
        public static final int LAST_SYSTEM = ENGINE_PERCENT_TORQUE_DATA_POINT4;
        public static final int VENDOR_START = LAST_SYSTEM + 1;
    }

    /** This class contains the indices of the standard OBD2 sensors with a floating-point value */
    public static final class Obd2FloatSensorIndex {

        private Obd2FloatSensorIndex() {}

        public static final int CALCULATED_ENGINE_LOAD = 0;
        public static final int ENGINE_COOLANT_TEMPERATURE = 1;
        public static final int SHORT_TERM_FUEL_TRIM_BANK1 = 2;
        public static final int LONG_TERM_FUEL_TRIM_BANK1 = 3;
        public static final int SHORT_TERM_FUEL_TRIM_BANK2 = 4;
        public static final int LONG_TERM_FUEL_TRIM_BANK2 = 5;
        public static final int FUEL_PRESSURE = 6;
        public static final int INTAKE_MANIFOLD_ABSOLUTE_PRESSURE = 7;
        public static final int ENGINE_RPM = 8;
        public static final int VEHICLE_SPEED = 9;
        public static final int TIMING_ADVANCE = 10;
        public static final int MAF_AIR_FLOW_RATE = 11;
        public static final int THROTTLE_POSITION = 12;
        public static final int OXYGEN_SENSOR1_VOLTAGE = 13;
        public static final int OXYGEN_SENSOR1_SHORT_TERM_FUEL_TRIM = 14;
        public static final int OXYGEN_SENSOR1_FUEL_AIR_EQUIVALENCE_RATIO = 15;
        public static final int OXYGEN_SENSOR2_VOLTAGE = 16;
        public static final int OXYGEN_SENSOR2_SHORT_TERM_FUEL_TRIM = 17;
        public static final int OXYGEN_SENSOR2_FUEL_AIR_EQUIVALENCE_RATIO = 18;
        public static final int OXYGEN_SENSOR3_VOLTAGE = 19;
        public static final int OXYGEN_SENSOR3_SHORT_TERM_FUEL_TRIM = 20;
        public static final int OXYGEN_SENSOR3_FUEL_AIR_EQUIVALENCE_RATIO = 21;
        public static final int OXYGEN_SENSOR4_VOLTAGE = 22;
        public static final int OXYGEN_SENSOR4_SHORT_TERM_FUEL_TRIM = 23;
        public static final int OXYGEN_SENSOR4_FUEL_AIR_EQUIVALENCE_RATIO = 24;
        public static final int OXYGEN_SENSOR5_VOLTAGE = 25;
        public static final int OXYGEN_SENSOR5_SHORT_TERM_FUEL_TRIM = 26;
        public static final int OXYGEN_SENSOR5_FUEL_AIR_EQUIVALENCE_RATIO = 27;
        public static final int OXYGEN_SENSOR6_VOLTAGE = 28;
        public static final int OXYGEN_SENSOR6_SHORT_TERM_FUEL_TRIM = 29;
        public static final int OXYGEN_SENSOR6_FUEL_AIR_EQUIVALENCE_RATIO = 30;
        public static final int OXYGEN_SENSOR7_VOLTAGE = 31;
        public static final int OXYGEN_SENSOR7_SHORT_TERM_FUEL_TRIM = 32;
        public static final int OXYGEN_SENSOR7_FUEL_AIR_EQUIVALENCE_RATIO = 33;
        public static final int OXYGEN_SENSOR8_VOLTAGE = 34;
        public static final int OXYGEN_SENSOR8_SHORT_TERM_FUEL_TRIM = 35;
        public static final int OXYGEN_SENSOR8_FUEL_AIR_EQUIVALENCE_RATIO = 36;
        public static final int FUEL_RAIL_PRESSURE = 37;
        public static final int FUEL_RAIL_GAUGE_PRESSURE = 38;
        public static final int COMMANDED_EXHAUST_GAS_RECIRCULATION = 39;
        public static final int EXHAUST_GAS_RECIRCULATION_ERROR = 40;
        public static final int COMMANDED_EVAPORATIVE_PURGE = 41;
        public static final int FUEL_TANK_LEVEL_INPUT = 42;
        public static final int EVAPORATION_SYSTEM_VAPOR_PRESSURE = 43;
        public static final int CATALYST_TEMPERATURE_BANK1_SENSOR1 = 44;
        public static final int CATALYST_TEMPERATURE_BANK2_SENSOR1 = 45;
        public static final int CATALYST_TEMPERATURE_BANK1_SENSOR2 = 46;
        public static final int CATALYST_TEMPERATURE_BANK2_SENSOR2 = 47;
        public static final int ABSOLUTE_LOAD_VALUE = 48;
        public static final int FUEL_AIR_COMMANDED_EQUIVALENCE_RATIO = 49;
        public static final int RELATIVE_THROTTLE_POSITION = 50;
        public static final int ABSOLUTE_THROTTLE_POSITION_B = 51;
        public static final int ABSOLUTE_THROTTLE_POSITION_C = 52;
        public static final int ACCELERATOR_PEDAL_POSITION_D = 53;
        public static final int ACCELERATOR_PEDAL_POSITION_E = 54;
        public static final int ACCELERATOR_PEDAL_POSITION_F = 55;
        public static final int COMMANDED_THROTTLE_ACTUATOR = 56;
        public static final int ETHANOL_FUEL_PERCENTAGE = 57;
        public static final int ABSOLUTE_EVAPORATION_SYSTEM_VAPOR_PRESSURE = 58;
        public static final int SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK1 = 59;
        public static final int SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK2 = 60;
        public static final int SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK3 = 61;
        public static final int SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK4 = 62;
        public static final int LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK1 = 63;
        public static final int LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK2 = 64;
        public static final int LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK3 = 65;
        public static final int LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK4 = 66;
        public static final int RELATIVE_ACCELERATOR_PEDAL_POSITION = 67;
        public static final int HYBRID_BATTERY_PACK_REMAINING_LIFE = 68;
        public static final int FUEL_INJECTION_TIMING = 69;
        public static final int ENGINE_FUEL_RATE = 70;
        public static final int LAST_SYSTEM = ENGINE_FUEL_RATE;
        public static final int VENDOR_START = LAST_SYSTEM + 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
        Obd2IntegerSensorIndex.FUEL_SYSTEM_STATUS,
        Obd2IntegerSensorIndex.MALFUNCTION_INDICATOR_LIGHT_ON,
        Obd2IntegerSensorIndex.IGNITION_MONITORS_SUPPORTED,
        Obd2IntegerSensorIndex.IGNITION_SPECIFIC_MONITORS,
        Obd2IntegerSensorIndex.INTAKE_AIR_TEMPERATURE,
        Obd2IntegerSensorIndex.COMMANDED_SECONDARY_AIR_STATUS,
        Obd2IntegerSensorIndex.NUM_OXYGEN_SENSORS_PRESENT,
        Obd2IntegerSensorIndex.RUNTIME_SINCE_ENGINE_START,
        Obd2IntegerSensorIndex.DISTANCE_TRAVELED_WITH_MALFUNCTION_INDICATOR_LIGHT_ON,
        Obd2IntegerSensorIndex.WARMUPS_SINCE_CODES_CLEARED,
        Obd2IntegerSensorIndex.DISTANCE_TRAVELED_SINCE_CODES_CLEARED,
        Obd2IntegerSensorIndex.ABSOLUTE_BAROMETRIC_PRESSURE,
        Obd2IntegerSensorIndex.CONTROL_MODULE_VOLTAGE,
        Obd2IntegerSensorIndex.AMBIENT_AIR_TEMPERATURE,
        Obd2IntegerSensorIndex.TIME_WITH_MALFUNCTION_LIGHT_ON,
        Obd2IntegerSensorIndex.TIME_SINCE_TROUBLE_CODES_CLEARED,
        Obd2IntegerSensorIndex.MAX_FUEL_AIR_EQUIVALENCE_RATIO,
        Obd2IntegerSensorIndex.MAX_OXYGEN_SENSOR_VOLTAGE,
        Obd2IntegerSensorIndex.MAX_OXYGEN_SENSOR_CURRENT,
        Obd2IntegerSensorIndex.MAX_INTAKE_MANIFOLD_ABSOLUTE_PRESSURE,
        Obd2IntegerSensorIndex.MAX_AIR_FLOW_RATE_FROM_MASS_AIR_FLOW_SENSOR,
        Obd2IntegerSensorIndex.FUEL_TYPE,
        Obd2IntegerSensorIndex.FUEL_RAIL_ABSOLUTE_PRESSURE,
        Obd2IntegerSensorIndex.ENGINE_OIL_TEMPERATURE,
        Obd2IntegerSensorIndex.DRIVER_DEMAND_PERCENT_TORQUE,
        Obd2IntegerSensorIndex.ENGINE_ACTUAL_PERCENT_TORQUE,
        Obd2IntegerSensorIndex.ENGINE_REFERENCE_PERCENT_TORQUE,
        Obd2IntegerSensorIndex.ENGINE_PERCENT_TORQUE_DATA_IDLE,
        Obd2IntegerSensorIndex.ENGINE_PERCENT_TORQUE_DATA_POINT1,
        Obd2IntegerSensorIndex.ENGINE_PERCENT_TORQUE_DATA_POINT2,
        Obd2IntegerSensorIndex.ENGINE_PERCENT_TORQUE_DATA_POINT3,
        Obd2IntegerSensorIndex.ENGINE_PERCENT_TORQUE_DATA_POINT4,
        Obd2IntegerSensorIndex.LAST_SYSTEM,
        Obd2IntegerSensorIndex.VENDOR_START,
    })
    public @interface IntegerSensorIndex {}

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
        Obd2FloatSensorIndex.CALCULATED_ENGINE_LOAD,
        Obd2FloatSensorIndex.ENGINE_COOLANT_TEMPERATURE,
        Obd2FloatSensorIndex.SHORT_TERM_FUEL_TRIM_BANK1,
        Obd2FloatSensorIndex.LONG_TERM_FUEL_TRIM_BANK1,
        Obd2FloatSensorIndex.SHORT_TERM_FUEL_TRIM_BANK2,
        Obd2FloatSensorIndex.LONG_TERM_FUEL_TRIM_BANK2,
        Obd2FloatSensorIndex.FUEL_PRESSURE,
        Obd2FloatSensorIndex.INTAKE_MANIFOLD_ABSOLUTE_PRESSURE,
        Obd2FloatSensorIndex.ENGINE_RPM,
        Obd2FloatSensorIndex.VEHICLE_SPEED,
        Obd2FloatSensorIndex.TIMING_ADVANCE,
        Obd2FloatSensorIndex.MAF_AIR_FLOW_RATE,
        Obd2FloatSensorIndex.THROTTLE_POSITION,
        Obd2FloatSensorIndex.OXYGEN_SENSOR1_VOLTAGE,
        Obd2FloatSensorIndex.OXYGEN_SENSOR1_SHORT_TERM_FUEL_TRIM,
        Obd2FloatSensorIndex.OXYGEN_SENSOR1_FUEL_AIR_EQUIVALENCE_RATIO,
        Obd2FloatSensorIndex.OXYGEN_SENSOR2_VOLTAGE,
        Obd2FloatSensorIndex.OXYGEN_SENSOR2_SHORT_TERM_FUEL_TRIM,
        Obd2FloatSensorIndex.OXYGEN_SENSOR2_FUEL_AIR_EQUIVALENCE_RATIO,
        Obd2FloatSensorIndex.OXYGEN_SENSOR3_VOLTAGE,
        Obd2FloatSensorIndex.OXYGEN_SENSOR3_SHORT_TERM_FUEL_TRIM,
        Obd2FloatSensorIndex.OXYGEN_SENSOR3_FUEL_AIR_EQUIVALENCE_RATIO,
        Obd2FloatSensorIndex.OXYGEN_SENSOR4_VOLTAGE,
        Obd2FloatSensorIndex.OXYGEN_SENSOR4_SHORT_TERM_FUEL_TRIM,
        Obd2FloatSensorIndex.OXYGEN_SENSOR4_FUEL_AIR_EQUIVALENCE_RATIO,
        Obd2FloatSensorIndex.OXYGEN_SENSOR5_VOLTAGE,
        Obd2FloatSensorIndex.OXYGEN_SENSOR5_SHORT_TERM_FUEL_TRIM,
        Obd2FloatSensorIndex.OXYGEN_SENSOR5_FUEL_AIR_EQUIVALENCE_RATIO,
        Obd2FloatSensorIndex.OXYGEN_SENSOR6_VOLTAGE,
        Obd2FloatSensorIndex.OXYGEN_SENSOR6_SHORT_TERM_FUEL_TRIM,
        Obd2FloatSensorIndex.OXYGEN_SENSOR6_FUEL_AIR_EQUIVALENCE_RATIO,
        Obd2FloatSensorIndex.OXYGEN_SENSOR7_VOLTAGE,
        Obd2FloatSensorIndex.OXYGEN_SENSOR7_SHORT_TERM_FUEL_TRIM,
        Obd2FloatSensorIndex.OXYGEN_SENSOR7_FUEL_AIR_EQUIVALENCE_RATIO,
        Obd2FloatSensorIndex.OXYGEN_SENSOR8_VOLTAGE,
        Obd2FloatSensorIndex.OXYGEN_SENSOR8_SHORT_TERM_FUEL_TRIM,
        Obd2FloatSensorIndex.OXYGEN_SENSOR8_FUEL_AIR_EQUIVALENCE_RATIO,
        Obd2FloatSensorIndex.FUEL_RAIL_PRESSURE,
        Obd2FloatSensorIndex.FUEL_RAIL_GAUGE_PRESSURE,
        Obd2FloatSensorIndex.COMMANDED_EXHAUST_GAS_RECIRCULATION,
        Obd2FloatSensorIndex.EXHAUST_GAS_RECIRCULATION_ERROR,
        Obd2FloatSensorIndex.COMMANDED_EVAPORATIVE_PURGE,
        Obd2FloatSensorIndex.FUEL_TANK_LEVEL_INPUT,
        Obd2FloatSensorIndex.EVAPORATION_SYSTEM_VAPOR_PRESSURE,
        Obd2FloatSensorIndex.CATALYST_TEMPERATURE_BANK1_SENSOR1,
        Obd2FloatSensorIndex.CATALYST_TEMPERATURE_BANK2_SENSOR1,
        Obd2FloatSensorIndex.CATALYST_TEMPERATURE_BANK1_SENSOR2,
        Obd2FloatSensorIndex.CATALYST_TEMPERATURE_BANK2_SENSOR2,
        Obd2FloatSensorIndex.ABSOLUTE_LOAD_VALUE,
        Obd2FloatSensorIndex.FUEL_AIR_COMMANDED_EQUIVALENCE_RATIO,
        Obd2FloatSensorIndex.RELATIVE_THROTTLE_POSITION,
        Obd2FloatSensorIndex.ABSOLUTE_THROTTLE_POSITION_B,
        Obd2FloatSensorIndex.ABSOLUTE_THROTTLE_POSITION_C,
        Obd2FloatSensorIndex.ACCELERATOR_PEDAL_POSITION_D,
        Obd2FloatSensorIndex.ACCELERATOR_PEDAL_POSITION_E,
        Obd2FloatSensorIndex.ACCELERATOR_PEDAL_POSITION_F,
        Obd2FloatSensorIndex.COMMANDED_THROTTLE_ACTUATOR,
        Obd2FloatSensorIndex.ETHANOL_FUEL_PERCENTAGE,
        Obd2FloatSensorIndex.ABSOLUTE_EVAPORATION_SYSTEM_VAPOR_PRESSURE,
        Obd2FloatSensorIndex.SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK1,
        Obd2FloatSensorIndex.SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK2,
        Obd2FloatSensorIndex.SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK3,
        Obd2FloatSensorIndex.SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK4,
        Obd2FloatSensorIndex.LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK1,
        Obd2FloatSensorIndex.LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK2,
        Obd2FloatSensorIndex.LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK3,
        Obd2FloatSensorIndex.LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_BANK4,
        Obd2FloatSensorIndex.RELATIVE_ACCELERATOR_PEDAL_POSITION,
        Obd2FloatSensorIndex.HYBRID_BATTERY_PACK_REMAINING_LIFE,
        Obd2FloatSensorIndex.FUEL_INJECTION_TIMING,
        Obd2FloatSensorIndex.ENGINE_FUEL_RATE,
        Obd2FloatSensorIndex.LAST_SYSTEM,
        Obd2FloatSensorIndex.VENDOR_START,
    })
    public @interface FloatSensorIndex {}

    /** Whether this frame represents a live or a freeze frame */
    public final int frameType;

    /**
     * When this data was acquired in car or received from car. It is elapsed real-time of data
     * reception from car in nanoseconds since system boot.
     */
    public final long timestamp;

    /**
     * Sparse array that contains the mapping of OBD2 diagnostic properties to their values for
     * integer valued properties
     */
    private final SparseIntArray intValues;

    /**
     * Sparse array that contains the mapping of OBD2 diagnostic properties to their values for
     * float valued properties
     */
    private final SparseArray<Float> floatValues;

    /**
     * Diagnostic Troubleshooting Code (DTC) that was detected and caused this frame to be stored
     * (if a freeze frame). Always null for a live frame.
     */
    public final String DTC;

    public CarDiagnosticEvent(Parcel in) {
        frameType = in.readInt();
        timestamp = in.readLong();
        int len = in.readInt();
        floatValues = new SparseArray<>(len);
        for (int i = 0; i < len; ++i) {
            int key = in.readInt();
            float value = in.readFloat();
            floatValues.put(key, value);
        }
        len = in.readInt();
        intValues = new SparseIntArray(len);
        for (int i = 0; i < len; ++i) {
            int key = in.readInt();
            int value = in.readInt();
            intValues.put(key, value);
        }
        DTC = (String)in.readValue(String.class.getClassLoader());
        // version 1 up to here
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(frameType);
        dest.writeLong(timestamp);
        dest.writeInt(floatValues.size());
        for (int i = 0; i < floatValues.size(); ++i) {
            int key = floatValues.keyAt(i);
            dest.writeInt(key);
            dest.writeFloat(floatValues.get(key));
        }
        dest.writeInt(intValues.size());
        for (int i = 0; i < intValues.size(); ++i) {
            int key = intValues.keyAt(i);
            dest.writeInt(key);
            dest.writeInt(intValues.get(key));
        }
        dest.writeValue(DTC);
    }

    public static final Parcelable.Creator<CarDiagnosticEvent> CREATOR =
            new Parcelable.Creator<CarDiagnosticEvent>() {
                public CarDiagnosticEvent createFromParcel(Parcel in) {
                    return new CarDiagnosticEvent(in);
                }

                public CarDiagnosticEvent[] newArray(int size) {
                    return new CarDiagnosticEvent[size];
                }
            };

    private CarDiagnosticEvent(
            int frameType,
            long timestamp,
            SparseArray<Float> floatValues,
            SparseIntArray intValues,
            String dtc) {
        this.frameType = frameType;
        this.timestamp = timestamp;
        this.floatValues = floatValues;
        this.intValues = intValues;
        this.DTC = dtc;
    }

    public static class Builder {
        private int mType = CarDiagnosticManager.FRAME_TYPE_FLAG_LIVE;
        private long mTimestamp = 0;
        private SparseArray<Float> mFloatValues = new SparseArray<>();
        private SparseIntArray mIntValues = new SparseIntArray();
        private String mDTC = null;

        private Builder(int type) {
            mType = type;
        }

        public static Builder newLiveFrameBuilder() {
            return new Builder(CarDiagnosticManager.FRAME_TYPE_FLAG_LIVE);
        }

        public static Builder newFreezeFrameBuilder() {
            return new Builder(CarDiagnosticManager.FRAME_TYPE_FLAG_FREEZE);
        }

        public Builder atTimestamp(long timestamp) {
            mTimestamp = timestamp;
            return this;
        }

        public Builder withIntValue(int key, int value) {
            mIntValues.put(key, value);
            return this;
        }

        public Builder withFloatValue(int key, float value) {
            mFloatValues.put(key, value);
            return this;
        }

        public Builder withDTC(String DTC) {
            mDTC = DTC;
            return this;
        }

        public CarDiagnosticEvent build() {
            return new CarDiagnosticEvent(mType, mTimestamp, mFloatValues, mIntValues, mDTC);
        }
    }

    public boolean isLiveFrame() {
        return CarDiagnosticManager.FRAME_TYPE_FLAG_LIVE == frameType;
    }

    public boolean isFreezeFrame() {
        return CarDiagnosticManager.FRAME_TYPE_FLAG_FREEZE == frameType;
    }

    public boolean isEmptyFrame() {
        boolean empty = (0 == intValues.size());
        empty &= (0 == floatValues.size());
        if (isFreezeFrame()) empty &= DTC.isEmpty();
        return empty;
    }

    /** @hide */
    public CarDiagnosticEvent checkLiveFrame() {
        if (!isLiveFrame()) throw new IllegalStateException("frame is not a live frame");
        return this;
    }

    /** @hide */
    public CarDiagnosticEvent checkFreezeFrame() {
        if (!isFreezeFrame()) throw new IllegalStateException("frame is not a freeze frame");
        return this;
    }

    /** @hide */
    public boolean isEarlierThan(CarDiagnosticEvent otherEvent) {
        otherEvent = Objects.requireNonNull(otherEvent);
        return (timestamp < otherEvent.timestamp);
    }

    @Override
    public String toString() {
        return String.format(
                "%s diagnostic frame {\n"
                        + "\ttimestamp: %d, "
                        + "DTC: %s\n"
                        + "\tintValues: %s\n"
                        + "\tfloatValues: %s\n}",
                isLiveFrame() ? "live" : "freeze",
                timestamp,
                DTC,
                intValues.toString(),
                floatValues.toString());
    }

    public int getSystemIntegerSensor(@IntegerSensorIndex int sensor, int defaultValue) {
        return intValues.get(sensor, defaultValue);
    }

    public float getSystemFloatSensor(@FloatSensorIndex int sensor, float defaultValue) {
        return floatValues.get(sensor, defaultValue);
    }

    public int getVendorIntegerSensor(int sensor, int defaultValue) {
        return intValues.get(sensor, defaultValue);
    }

    public float getVendorFloatSensor(int sensor, float defaultValue) {
        return floatValues.get(sensor, defaultValue);
    }

    public @Nullable Integer getSystemIntegerSensor(@IntegerSensorIndex int sensor) {
        int index = intValues.indexOfKey(sensor);
        if(index < 0) return null;
        return intValues.valueAt(index);
    }

    public @Nullable Float getSystemFloatSensor(@FloatSensorIndex int sensor) {
        int index = floatValues.indexOfKey(sensor);
        if(index < 0) return null;
        return floatValues.valueAt(index);
    }

    public @Nullable Integer getVendorIntegerSensor(int sensor) {
        int index = intValues.indexOfKey(sensor);
        if(index < 0) return null;
        return intValues.valueAt(index);
    }

    public @Nullable Float getVendorFloatSensor(int sensor) {
        int index = floatValues.indexOfKey(sensor);
        if(index < 0) return null;
        return floatValues.valueAt(index);
    }

    /**
     * Represents possible states of the fuel system;
     * see {@link Obd2IntegerSensorIndex#FUEL_SYSTEM_STATUS}
     */
    public static final class FuelSystemStatus {
        private FuelSystemStatus() {}

        public static final int OPEN_INSUFFICIENT_ENGINE_TEMPERATURE = 1;
        public static final int CLOSED_LOOP = 2;
        public static final int OPEN_ENGINE_LOAD_OR_DECELERATION = 4;
        public static final int OPEN_SYSTEM_FAILURE = 8;
        public static final int CLOSED_LOOP_BUT_FEEDBACK_FAULT = 16;

        @Retention(RetentionPolicy.SOURCE)
        @IntDef({
            OPEN_INSUFFICIENT_ENGINE_TEMPERATURE,
            CLOSED_LOOP,
            OPEN_ENGINE_LOAD_OR_DECELERATION,
            OPEN_SYSTEM_FAILURE,
            CLOSED_LOOP_BUT_FEEDBACK_FAULT
        })
        public @interface Status {}
    }

    /**
     * Represents possible states of the secondary air system;
     * see {@link Obd2IntegerSensorIndex#COMMANDED_SECONDARY_AIR_STATUS}
     */
    public static final class SecondaryAirStatus {
        private SecondaryAirStatus() {}

        public static final int UPSTREAM = 1;
        public static final int DOWNSTREAM_OF_CATALYCIC_CONVERTER = 2;
        public static final int FROM_OUTSIDE_OR_OFF = 4;
        public static final int PUMP_ON_FOR_DIAGNOSTICS = 8;

        @Retention(RetentionPolicy.SOURCE)
        @IntDef({
            UPSTREAM,
            DOWNSTREAM_OF_CATALYCIC_CONVERTER,
            FROM_OUTSIDE_OR_OFF,
            PUMP_ON_FOR_DIAGNOSTICS
        })
        public @interface Status {}
    }

    /**
     * Represents possible types of fuel;
     * see {@link Obd2IntegerSensorIndex#FUEL_TYPE}
     */
    public static final class FuelType {
        private FuelType() {}

        public static final int NOT_AVAILABLE = 0;
        public static final int GASOLINE = 1;
        public static final int METHANOL = 2;
        public static final int ETHANOL = 3;
        public static final int DIESEL = 4;
        public static final int LPG = 5;
        public static final int CNG = 6;
        public static final int PROPANE = 7;
        public static final int ELECTRIC = 8;
        public static final int BIFUEL_RUNNING_GASOLINE = 9;
        public static final int BIFUEL_RUNNING_METHANOL = 10;
        public static final int BIFUEL_RUNNING_ETHANOL = 11;
        public static final int BIFUEL_RUNNING_LPG = 12;
        public static final int BIFUEL_RUNNING_CNG = 13;
        public static final int BIFUEL_RUNNING_PROPANE = 14;
        public static final int BIFUEL_RUNNING_ELECTRIC = 15;
        public static final int BIFUEL_RUNNING_ELECTRIC_AND_COMBUSTION = 16;
        public static final int HYBRID_GASOLINE = 17;
        public static final int HYBRID_ETHANOL = 18;
        public static final int HYBRID_DIESEL = 19;
        public static final int HYBRID_ELECTRIC = 20;
        public static final int HYBRID_RUNNING_ELECTRIC_AND_COMBUSTION = 21;
        public static final int HYBRID_REGENERATIVE = 22;
        public static final int BIFUEL_RUNNING_DIESEL = 23;

        @Retention(RetentionPolicy.SOURCE)
        @IntDef({
            NOT_AVAILABLE,
            GASOLINE,
            METHANOL,
            ETHANOL,
            DIESEL,
            LPG,
            CNG,
            PROPANE,
            ELECTRIC,
            BIFUEL_RUNNING_GASOLINE,
            BIFUEL_RUNNING_METHANOL,
            BIFUEL_RUNNING_ETHANOL,
            BIFUEL_RUNNING_LPG,
            BIFUEL_RUNNING_CNG,
            BIFUEL_RUNNING_PROPANE,
            BIFUEL_RUNNING_ELECTRIC,
            BIFUEL_RUNNING_ELECTRIC_AND_COMBUSTION,
            HYBRID_GASOLINE,
            HYBRID_ETHANOL,
            HYBRID_DIESEL,
            HYBRID_ELECTRIC,
            HYBRID_RUNNING_ELECTRIC_AND_COMBUSTION,
            HYBRID_REGENERATIVE,
            BIFUEL_RUNNING_DIESEL
        })
        public @interface Type {}
    }

    /**
     * Represents possible states of the ignition monitors on the vehicle;
     * see {@link Obd2IntegerSensorIndex#IGNITION_MONITORS_SUPPORTED}
     * see {@link Obd2IntegerSensorIndex#IGNITION_SPECIFIC_MONITORS}
     */
    public static final class IgnitionMonitors {
        public static final class IgnitionMonitor {
            public final boolean mAvailable;
            public final boolean mIncomplete;

            IgnitionMonitor(boolean available, boolean incomplete) {
                mAvailable = available;
                mIncomplete = incomplete;
            }

            public static final class Builder {
                private int mAvailableBitmask;
                private int mIncompleteBitmask;

                Builder() {
                    mAvailableBitmask = 0;
                    mIncompleteBitmask = 0;
                }

                public Builder withAvailableBitmask(int bitmask) {
                    mAvailableBitmask = bitmask;
                    return this;
                }

                public Builder withIncompleteBitmask(int bitmask) {
                    mIncompleteBitmask = bitmask;
                    return this;
                }

                public IgnitionMonitor buildForValue(int value) {
                    boolean available = (0 != (value & mAvailableBitmask));
                    boolean incomplete = (0 != (value & mIncompleteBitmask));

                    return new IgnitionMonitor(available, incomplete);
                }
            }
        }

        public static class CommonIgnitionMonitors {
            public final IgnitionMonitor components;
            public final IgnitionMonitor fuelSystem;
            public final IgnitionMonitor misfire;

            static final int COMPONENTS_AVAILABLE = 0x1 << 0;
            static final int COMPONENTS_INCOMPLETE = 0x1 << 1;

            static final int FUEL_SYSTEM_AVAILABLE = 0x1 << 2;
            static final int FUEL_SYSTEM_INCOMPLETE = 0x1 << 3;

            static final int MISFIRE_AVAILABLE = 0x1 << 4;
            static final int MISFIRE_INCOMPLETE = 0x1 << 5;

            static final IgnitionMonitor.Builder COMPONENTS_BUILDER =
                    new IgnitionMonitor.Builder()
                            .withAvailableBitmask(COMPONENTS_AVAILABLE)
                            .withIncompleteBitmask(COMPONENTS_INCOMPLETE);

            static final IgnitionMonitor.Builder FUEL_SYSTEM_BUILDER =
                    new IgnitionMonitor.Builder()
                            .withAvailableBitmask(FUEL_SYSTEM_AVAILABLE)
                            .withIncompleteBitmask(FUEL_SYSTEM_INCOMPLETE);

            static final IgnitionMonitor.Builder MISFIRE_BUILDER =
                    new IgnitionMonitor.Builder()
                            .withAvailableBitmask(MISFIRE_AVAILABLE)
                            .withIncompleteBitmask(MISFIRE_INCOMPLETE);

            CommonIgnitionMonitors(int bitmask) {
                components = COMPONENTS_BUILDER.buildForValue(bitmask);
                fuelSystem = FUEL_SYSTEM_BUILDER.buildForValue(bitmask);
                misfire = MISFIRE_BUILDER.buildForValue(bitmask);
            }

            public @Nullable SparkIgnitionMonitors asSparkIgnitionMonitors() {
                if (this instanceof SparkIgnitionMonitors)
                    return (SparkIgnitionMonitors)this;
                return null;
            }

            public @Nullable CompressionIgnitionMonitors asCompressionIgnitionMonitors() {
                if (this instanceof CompressionIgnitionMonitors)
                    return (CompressionIgnitionMonitors)this;
                return null;
            }
        }

        public static final class SparkIgnitionMonitors extends CommonIgnitionMonitors {
            public final IgnitionMonitor EGR;
            public final IgnitionMonitor oxygenSensorHeater;
            public final IgnitionMonitor oxygenSensor;
            public final IgnitionMonitor ACRefrigerant;
            public final IgnitionMonitor secondaryAirSystem;
            public final IgnitionMonitor evaporativeSystem;
            public final IgnitionMonitor heatedCatalyst;
            public final IgnitionMonitor catalyst;

            static final int EGR_AVAILABLE = 0x1 << 6;
            static final int EGR_INCOMPLETE = 0x1 << 7;

            static final int OXYGEN_SENSOR_HEATER_AVAILABLE = 0x1 << 8;
            static final int OXYGEN_SENSOR_HEATER_INCOMPLETE = 0x1 << 9;

            static final int OXYGEN_SENSOR_AVAILABLE = 0x1 << 10;
            static final int OXYGEN_SENSOR_INCOMPLETE = 0x1 << 11;

            static final int AC_REFRIGERANT_AVAILABLE = 0x1 << 12;
            static final int AC_REFRIGERANT_INCOMPLETE = 0x1 << 13;

            static final int SECONDARY_AIR_SYSTEM_AVAILABLE = 0x1 << 14;
            static final int SECONDARY_AIR_SYSTEM_INCOMPLETE = 0x1 << 15;

            static final int EVAPORATIVE_SYSTEM_AVAILABLE = 0x1 << 16;
            static final int EVAPORATIVE_SYSTEM_INCOMPLETE = 0x1 << 17;

            static final int HEATED_CATALYST_AVAILABLE = 0x1 << 18;
            static final int HEATED_CATALYST_INCOMPLETE = 0x1 << 19;

            static final int CATALYST_AVAILABLE = 0x1 << 20;
            static final int CATALYST_INCOMPLETE = 0x1 << 21;

            static final IgnitionMonitor.Builder EGR_BUILDER =
                new IgnitionMonitor.Builder()
                    .withAvailableBitmask(EGR_AVAILABLE)
                    .withIncompleteBitmask(EGR_INCOMPLETE);

            static final IgnitionMonitor.Builder OXYGEN_SENSOR_HEATER_BUILDER =
                new IgnitionMonitor.Builder()
                    .withAvailableBitmask(OXYGEN_SENSOR_HEATER_AVAILABLE)
                    .withIncompleteBitmask(OXYGEN_SENSOR_HEATER_INCOMPLETE);

            static final IgnitionMonitor.Builder OXYGEN_SENSOR_BUILDER =
                new IgnitionMonitor.Builder()
                    .withAvailableBitmask(OXYGEN_SENSOR_AVAILABLE)
                    .withIncompleteBitmask(OXYGEN_SENSOR_INCOMPLETE);

            static final IgnitionMonitor.Builder AC_REFRIGERANT_BUILDER =
                new IgnitionMonitor.Builder()
                    .withAvailableBitmask(AC_REFRIGERANT_AVAILABLE)
                    .withIncompleteBitmask(AC_REFRIGERANT_INCOMPLETE);

            static final IgnitionMonitor.Builder SECONDARY_AIR_SYSTEM_BUILDER =
                new IgnitionMonitor.Builder()
                    .withAvailableBitmask(SECONDARY_AIR_SYSTEM_AVAILABLE)
                    .withIncompleteBitmask(SECONDARY_AIR_SYSTEM_INCOMPLETE);

            static final IgnitionMonitor.Builder EVAPORATIVE_SYSTEM_BUILDER =
                new IgnitionMonitor.Builder()
                    .withAvailableBitmask(EVAPORATIVE_SYSTEM_AVAILABLE)
                    .withIncompleteBitmask(EVAPORATIVE_SYSTEM_INCOMPLETE);

            static final IgnitionMonitor.Builder HEATED_CATALYST_BUILDER =
                new IgnitionMonitor.Builder()
                    .withAvailableBitmask(HEATED_CATALYST_AVAILABLE)
                    .withIncompleteBitmask(HEATED_CATALYST_INCOMPLETE);

            static final IgnitionMonitor.Builder CATALYST_BUILDER =
                new IgnitionMonitor.Builder()
                    .withAvailableBitmask(CATALYST_AVAILABLE)
                    .withIncompleteBitmask(CATALYST_INCOMPLETE);

            SparkIgnitionMonitors(int bitmask) {
                super(bitmask);
                EGR = EGR_BUILDER.buildForValue(bitmask);
                oxygenSensorHeater = OXYGEN_SENSOR_HEATER_BUILDER.buildForValue(bitmask);
                oxygenSensor = OXYGEN_SENSOR_BUILDER.buildForValue(bitmask);
                ACRefrigerant = AC_REFRIGERANT_BUILDER.buildForValue(bitmask);
                secondaryAirSystem = SECONDARY_AIR_SYSTEM_BUILDER.buildForValue(bitmask);
                evaporativeSystem = EVAPORATIVE_SYSTEM_BUILDER.buildForValue(bitmask);
                heatedCatalyst = HEATED_CATALYST_BUILDER.buildForValue(bitmask);
                catalyst = CATALYST_BUILDER.buildForValue(bitmask);
            }
        }

        public static final class CompressionIgnitionMonitors extends CommonIgnitionMonitors {
            public final IgnitionMonitor EGROrVVT;
            public final IgnitionMonitor PMFilter;
            public final IgnitionMonitor exhaustGasSensor;
            public final IgnitionMonitor boostPressure;
            public final IgnitionMonitor NOxSCR;
            public final IgnitionMonitor NMHCCatalyst;

            static final int EGR_OR_VVT_AVAILABLE = 0x1 << 6;
            static final int EGR_OR_VVT_INCOMPLETE = 0x1 << 7;

            static final int PM_FILTER_AVAILABLE = 0x1 << 8;
            static final int PM_FILTER_INCOMPLETE = 0x1 << 9;

            static final int EXHAUST_GAS_SENSOR_AVAILABLE = 0x1 << 10;
            static final int EXHAUST_GAS_SENSOR_INCOMPLETE = 0x1 << 11;

            static final int BOOST_PRESSURE_AVAILABLE = 0x1 << 12;
            static final int BOOST_PRESSURE_INCOMPLETE = 0x1 << 13;

            static final int NOx_SCR_AVAILABLE = 0x1 << 14;
            static final int NOx_SCR_INCOMPLETE = 0x1 << 15;

            static final int NMHC_CATALYST_AVAILABLE = 0x1 << 16;
            static final int NMHC_CATALYST_INCOMPLETE = 0x1 << 17;

            static final IgnitionMonitor.Builder EGR_OR_VVT_BUILDER =
                new IgnitionMonitor.Builder()
                    .withAvailableBitmask(EGR_OR_VVT_AVAILABLE)
                    .withIncompleteBitmask(EGR_OR_VVT_INCOMPLETE);

            static final IgnitionMonitor.Builder PM_FILTER_BUILDER =
                new IgnitionMonitor.Builder()
                    .withAvailableBitmask(PM_FILTER_AVAILABLE)
                    .withIncompleteBitmask(PM_FILTER_INCOMPLETE);

            static final IgnitionMonitor.Builder EXHAUST_GAS_SENSOR_BUILDER =
                new IgnitionMonitor.Builder()
                    .withAvailableBitmask(EXHAUST_GAS_SENSOR_AVAILABLE)
                    .withIncompleteBitmask(EXHAUST_GAS_SENSOR_INCOMPLETE);

            static final IgnitionMonitor.Builder BOOST_PRESSURE_BUILDER =
                new IgnitionMonitor.Builder()
                    .withAvailableBitmask(BOOST_PRESSURE_AVAILABLE)
                    .withIncompleteBitmask(BOOST_PRESSURE_INCOMPLETE);

            static final IgnitionMonitor.Builder NOx_SCR_BUILDER =
                new IgnitionMonitor.Builder()
                    .withAvailableBitmask(NOx_SCR_AVAILABLE)
                    .withIncompleteBitmask(NOx_SCR_INCOMPLETE);

            static final IgnitionMonitor.Builder NMHC_CATALYST_BUILDER =
                new IgnitionMonitor.Builder()
                    .withAvailableBitmask(NMHC_CATALYST_AVAILABLE)
                    .withIncompleteBitmask(NMHC_CATALYST_INCOMPLETE);

            CompressionIgnitionMonitors(int bitmask) {
                super(bitmask);
                EGROrVVT = EGR_OR_VVT_BUILDER.buildForValue(bitmask);
                PMFilter = PM_FILTER_BUILDER.buildForValue(bitmask);
                exhaustGasSensor = EXHAUST_GAS_SENSOR_BUILDER.buildForValue(bitmask);
                boostPressure = BOOST_PRESSURE_BUILDER.buildForValue(bitmask);
                NOxSCR = NOx_SCR_BUILDER.buildForValue(bitmask);
                NMHCCatalyst = NMHC_CATALYST_BUILDER.buildForValue(bitmask);
            }
        }
    }

    public @Nullable @FuelSystemStatus.Status Integer getFuelSystemStatus() {
        return getSystemIntegerSensor(Obd2IntegerSensorIndex.FUEL_SYSTEM_STATUS);
    }

    public @Nullable @SecondaryAirStatus.Status Integer getSecondaryAirStatus() {
        return getSystemIntegerSensor(Obd2IntegerSensorIndex.COMMANDED_SECONDARY_AIR_STATUS);
    }

    public @Nullable IgnitionMonitors.CommonIgnitionMonitors getIgnitionMonitors() {
        Integer ignitionMonitorsType = getSystemIntegerSensor(
            Obd2IntegerSensorIndex.IGNITION_MONITORS_SUPPORTED);
        Integer ignitionMonitorsBitmask = getSystemIntegerSensor(
            Obd2IntegerSensorIndex.IGNITION_SPECIFIC_MONITORS);
        if (null == ignitionMonitorsType) return null;
        if (null == ignitionMonitorsBitmask) return null;
        switch (ignitionMonitorsType) {
            case 0: return new IgnitionMonitors.SparkIgnitionMonitors(
                    ignitionMonitorsBitmask);
            case 1: return new IgnitionMonitors.CompressionIgnitionMonitors(
                    ignitionMonitorsBitmask);
            default: return null;
        }
    }

    public @Nullable @FuelType.Type Integer getFuelType() {
        return getSystemIntegerSensor(Obd2IntegerSensorIndex.FUEL_TYPE);
    }
}