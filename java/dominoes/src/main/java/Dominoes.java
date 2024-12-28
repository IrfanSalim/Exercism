import java.util.ArrayList;
import java.util.List;

public class Dominoes {
    public List<Domino> formChain(List<Domino> dominoInput) throws ChainNotFoundException {
        return formChain(dominoInput, new ArrayList<>());
    }

    private List<Domino> formChain(List<Domino> dominoInput, List<Domino> chainInput) throws ChainNotFoundException {
        if (dominoInput.size() == 0) {
            if (chainInput.size() == 0
                    || chainInput.get(chainInput.size() - 1).getRight() == chainInput.get(0).getLeft())
                return chainInput;
            throw new ChainNotFoundException("No domino chain found.");
        }
        ArrayList<Domino> dominoes = new ArrayList<>(dominoInput);
        if (chainInput.size() == 0) {
            ArrayList<Domino> chain = new ArrayList<>();
            chain.add(dominoes.remove(0));
            return formChain(dominoes, chain);
        }
        ArrayList<Domino> chain = new ArrayList<>(chainInput);
        int end = chain.get(chain.size() - 1).getRight();
        int index = 0;
        while (index < dominoes.size()) {
            if (dominoes.get(index).getLeft() == end || dominoes.get(index).getRight() == end) {
                Domino nextDomino = dominoes.remove(index);
                if (nextDomino.getRight() == chain.get(chain.size() - 1).getRight()) {
                    nextDomino = new Domino(nextDomino.getRight(), nextDomino.getLeft());
                }
                chain.add(nextDomino);
                try {
                    return formChain(dominoes, chain);
                } catch (ChainNotFoundException e) {
                    dominoes.add(index, chain.remove(chain.size() - 1));
                }
            }
            index++;
        }
        throw new ChainNotFoundException("No domino chain found.");
    }
}