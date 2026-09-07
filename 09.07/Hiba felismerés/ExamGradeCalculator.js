function calculateGrade(theory, practice) {
    let total = theory + practice;

    if (theory < 30 && practice < 20) {
        return 1;
    }

    if (total < 50) {
        return 1;
    } else if (total <= 60) {
        return 2;
    } else if (total <= 70) {
        return 3;
    } else if (total <= 85) {
        return 4;
    } else {
        return 5;
    }
}