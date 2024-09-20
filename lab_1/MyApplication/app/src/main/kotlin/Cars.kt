fun main() {
    val bicycle = Bicycle(
        brand = "Trek",
        frontWheel = BicycleWheel(diameter = 26),
        rearWheel = BicycleWheel(diameter = 26),
        frame = BicycleFrame.CARBON
    )

    val gasolineCar = GasolineCar(
        engine = InternalCombustionEngine(volume = 2.0, fuelType = FuelType.FUEL_95),
        wheels = List(4) { CarWheel(diameter = 18, brand = "Michelin", disk = Disk.CAST) },
        steeringWheel = SteeringWheel(type = "Sport")
    )

    val electricCar = ElectricCar(
        motor = ElectricMotor(power = 150),
        wheels = List(4) { CarWheel(diameter = 20, brand = "Continental", disk = Disk.FORGED) },
        autopilot = Autopilot(system = AutopilotSystem.TESLA)
    )

    println(bicycle)
    println()
    println(gasolineCar)
    println()
    println(electricCar)
}
