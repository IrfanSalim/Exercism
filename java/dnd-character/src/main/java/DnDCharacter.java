import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Arrays;
import java.lang.Math;

class DnDCharacter {

    static String[] abilityNames = { "strength", "dexterity", "constitution", "intelligence", "wisdom", "charisma" };
    HashMap<String, Integer> characterAbilities = new HashMap<String, Integer>();

    DnDCharacter() {
        for (int i = 0; i < 6; i++) {
            List<Integer> diceList = rollDice();

            characterAbilities.put(abilityNames[i], ability(diceList));
        }
        characterAbilities.put("hp", 10 + modifier(getConstitution()));
    }

    int ability(List<Integer> scores) {
        Integer[] abilityDiceList = scores.toArray(new Integer[0]);
        Arrays.sort(abilityDiceList);

        int abilityValue = 0;
        for (int j = 1; j < 4; j++) {
            abilityValue += abilityDiceList[j];
        }
        return abilityValue;
    }

    List<Integer> rollDice() {
        List<Integer> diceList = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < 4; i++) {
            diceList.add(rand.nextInt(6) + 1);
        }

        return diceList;
    }

    int modifier(int input) {
        return (int) Math.floor((input - 10) / 2.0);
    }

    int getStrength() {
        return characterAbilities.get("strength");
    }

    int getDexterity() {
        return characterAbilities.get("dexterity");
    }

    int getConstitution() {
        return characterAbilities.get("constitution");
    }

    int getIntelligence() {
        return characterAbilities.get("intelligence");
    }

    int getWisdom() {
        return characterAbilities.get("wisdom");
    }

    int getCharisma() {
        return characterAbilities.get("charisma");
    }

    int getHitpoints() {
        return characterAbilities.get("hp");
    }
}
