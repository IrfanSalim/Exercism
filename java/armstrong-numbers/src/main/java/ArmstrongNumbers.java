class ArmstrongNumbers {

    boolean isArmstrongNumber(int numberToCheck) {

      int[] number = Integer.toString(numberToCheck).chars().map(Character::getNumericValue).toArray();
      int power = number.length;
      int result = 0;
      for (int num : number) {
        result += Math.pow(num, power);
      }
      return result == numberToCheck;

    }

}

class ArmstrongNumbers {

	boolean isArmstrongNumber(int numberToCheck) {
		String digits = String.valueOf(numberToCheck);
		return numberToCheck == digits.chars().parallel()
			.map(d -> (int) Math.pow(Character.getNumericValue(d), digits.length()))
			.sum();
	}

}
