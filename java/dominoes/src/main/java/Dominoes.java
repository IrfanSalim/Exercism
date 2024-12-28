// import java.util.ArrayList;
// import java.util.List;

// public class Dominoes {
//     public List<Domino> formChain(List<Domino> dominoInput) throws ChainNotFoundException {
//         return formChain(dominoInput, new ArrayList<>());
//     }

//     private List<Domino> formChain(List<Domino> dominoInput, List<Domino> chainInput) throws ChainNotFoundException {
//         if (dominoInput.size() == 0) {
//             if (chainInput.size() == 0
//                     || chainInput.get(chainInput.size() - 1).getRight() == chainInput.get(0).getLeft())
//                 return chainInput;
//             throw new ChainNotFoundException("No domino chain found.");
//         }
//         ArrayList<Domino> dominoes = new ArrayList<>(dominoInput);
//         if (chainInput.size() == 0) {
//             ArrayList<Domino> chain = new ArrayList<>();
//             chain.add(dominoes.remove(0));
//             return formChain(dominoes, chain);
//         }
//         ArrayList<Domino> chain = new ArrayList<>(chainInput);
//         int end = chain.get(chain.size() - 1).getRight();
//         int index = 0;
//         while (index < dominoes.size()) {
//             if (dominoes.get(index).getLeft() == end || dominoes.get(index).getRight() == end) {
//                 Domino nextDomino = dominoes.remove(index);
//                 if (nextDomino.getRight() == chain.get(chain.size() - 1).getRight()) {
//                     nextDomino = new Domino(nextDomino.getRight(), nextDomino.getLeft());
//                 }
//                 chain.add(nextDomino);
//                 try {
//                     return formChain(dominoes, chain);
//                 } catch (ChainNotFoundException e) {
//                     dominoes.add(index, chain.remove(chain.size() - 1));
//                 }
//             }
//             index++;
//         }
//         throw new ChainNotFoundException("No domino chain found.");
//     }
// }

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

class Dominoes {
    List<Domino> formChain(List<Domino> inputDominoes) throws ChainNotFoundException {
        if (inputDominoes.isEmpty()) {
            return List.of();
        }
        Domino first = inputDominoes.get(0);
        return findContinuation(first.getLeft(), first.getRight(), inputDominoes.subList(1, inputDominoes.size()))
                .map(continuation -> {
                    List<Domino> result = new ArrayList<>();
                    result.add(first);
                    result.addAll(continuation);
                    return result;
                })
                .orElseThrow(() -> new ChainNotFoundException("No domino chain found."));
    }

    private Optional<List<Domino>> findContinuation(int end, int start, List<Domino> dominoes) {
        if (dominoes.isEmpty()) {
            return end == start ? Optional.of(List.of()) : Optional.empty();
        }
        return dominoes
                .stream()
                .<Domino>mapMulti((candidate, stream) -> {
                    if (candidate.getLeft() == start) {
                        stream.accept(candidate);
                    } else if (candidate.getRight() == start) {
                        stream.accept(new Domino(candidate.getRight(), candidate.getLeft()));
                    }
                })
                .flatMap(candidate -> {
                    int idx = dominoes.indexOf(candidate);
                    List<Domino> withoutCandidate = Stream.concat(
                            dominoes.subList(0, idx).stream(),
                            dominoes.subList(idx + 1, dominoes.size()).stream()).toList();
                    return findContinuation(end, candidate.getRight(), withoutCandidate)
                            .map(continuation -> {
                                List<Domino> result = new ArrayList<>();
                                result.add(candidate);
                                result.addAll(continuation);
                                return result;
                            })
                            .stream();
                })
                .findFirst();
    }
}