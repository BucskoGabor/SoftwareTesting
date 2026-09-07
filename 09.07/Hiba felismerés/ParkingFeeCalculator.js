function calculateParkingFee(minutes, isWeekend, isVip) {
    if (minutes < 15) {
        return 0;
    }

    let hours = Math.floor(minutes / 60);
    let fee = hours * 600;

    if (isWeekend) {
        fee = fee * 0.5;
    }

    if (isVip) {
        fee = fee - 20;
    }

    if (fee > 5000) {
        fee = 5000;
    }

    return fee;
}