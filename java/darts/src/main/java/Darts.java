class Darts {
    int score(double xOfDart, double yOfDart) {
      int distance = (int) Math.sqrt(xOfDart * xOfDart + yOfDart * yOfDart);
      return distance > 10 ? 0 : distance > 5 ? 1 : distance > 1 ? 5 : 10;
    }
}
