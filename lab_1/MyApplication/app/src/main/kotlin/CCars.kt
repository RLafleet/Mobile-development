sealed interface Vehicle

data object Scooter : Vehicle

data class Bicycle(
    val brand: String,
    val frontWheel: BicycleWheel,
    val rearWheel: BicycleWheel,
    val frame: BicycleFrame
) : Vehicle

data class BicycleWheel(val diameter: Int)

enum class BicycleFrame {
    ALUMINUM, STEEL, CARBON
}

sealed interface Car : Vehicle

// добавить класс машины отдельно от vehicle
data class GasolineCar(
    val engine: InternalCombustionEngine,
    val wheels: List<CarWheel>,
    val steeringWheel: SteeringWheel
) : Car

data class ElectricCar(
    val motor: ElectricMotor,
    val wheels: List<CarWheel>,
    val autopilot: Autopilot
) : Car

data class InternalCombustionEngine(
    val volume: Double,
    val fuelType: FuelType
)

enum class FuelType {
    DIESEL, FUEL_92, FUEL_95
}

data class ElectricMotor(val power: Int)

data class Autopilot(val system: AutopilotSystem)

enum class AutopilotSystem {
    OWN, TESLA
}

data class CarWheel(val diameter: Int, val brand: String, val disk: Disk)

enum class Disk {
    CAST, FORGED
}

data class SteeringWheel(val type: String)
