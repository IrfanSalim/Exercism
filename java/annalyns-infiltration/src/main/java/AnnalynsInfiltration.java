class AnnalynsInfiltration {
    public static boolean canFastAttack(boolean knightIsAwake) {
      return !knightIsAwake;
    }

    public static boolean canSpy(boolean knightIsAwake, boolean archerIsAwake, boolean prisonerIsAwake) {
      if (knightIsAwake || archerIsAwake) return true;
      else if (!knightIsAwake && !archerIsAwake && prisonerIsAwake) return true;
      else return false;
    }

    public static boolean canSignalPrisoner(boolean archerIsAwake, boolean prisonerIsAwake) {
      if (prisonerIsAwake && !archerIsAwake) return true;
      else return false;
    }

    public static boolean canFreePrisoner(boolean knightIsAwake, boolean archerIsAwake, boolean prisonerIsAwake, boolean petDogIsPresent) {
      if (!archerIsAwake &&  petDogIsPresent) return true;
      else if (!archerIsAwake && !knightIsAwake && prisonerIsAwake && !petDogIsPresent) return true;
      else return false;
    }
}
