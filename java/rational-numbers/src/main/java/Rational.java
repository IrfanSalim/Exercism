import java.util.Objects;

class Rational {
    int numerator;
    int denominator;

    Rational(int numerator, int denominator) {
        int g = gcd(numerator, denominator);
        this.numerator = numerator / g;
        this.denominator = denominator / g;
        if (this.denominator < 0) {
            this.numerator *= -1;
            this.denominator *= -1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        Rational other = (Rational) obj;
        return numerator == other.numerator && denominator == other.denominator;
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    Rational add(Rational r) {
        return new Rational(numerator * r.denominator + r.numerator * denominator, denominator * r.denominator);
    }

    Rational subtract(Rational r) {
        return new Rational(numerator * r.denominator - r.numerator * denominator, denominator * r.denominator);
    }

    Rational multiply(Rational r) {
        return new Rational(numerator * r.numerator, denominator * r.denominator);
    }

    Rational divide(Rational r) {
        return new Rational(numerator * r.denominator, denominator * r.numerator);
    }

    Rational abs() {
        return new Rational(Math.abs(numerator), denominator);
    }

    Rational pow(int e) {
        if (e < 0) {
            Rational r = pow(-e);
            return new Rational(r.denominator, r.numerator);
        }
        Rational result = new Rational(1, 1);
        for (int i = 0; i < e; i++) {
            result = result.multiply(this);
        }
        return result;
    }

    double exp(double x) {
        return Math.pow(Math.pow(x, 1.0 / denominator), numerator);
    }
}