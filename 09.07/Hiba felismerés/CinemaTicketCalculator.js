function calculateTicketPrice(age, isStudent, is3D) {
    let price = 3000;

    if (age <= 6) {
        price = 0;
    } else if (age < 18) {
        price = price * 0.7;
    } else if (age > 65) {
        price = price * 0.6;
    }

    if (isStudent) {
        price = price * 0.8;
    }

    if (is3D) {
        price = price * 1.8;
    }

    return price;
}