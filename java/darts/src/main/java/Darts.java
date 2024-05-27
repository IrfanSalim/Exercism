class Darts {
    int score(double xOfDart, double yOfDart) {
      double distance = Math.sqrt(xOfDart * xOfDart + yOfDart * yOfDart);
      return distance > 10 ? 0 : distance > 5 ? 1 : distance > 1 ? 5 : 10;
    }
}
