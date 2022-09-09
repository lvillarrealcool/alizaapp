package com.alianza.alianzapp.services.impl;

import com.alianza.alianzapp.constants.Constant;
import com.alianza.alianzapp.dtos.HumanDTO;
import com.alianza.alianzapp.exceptions.HumanException;
import com.alianza.alianzapp.services.IMutantService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class MutantServiceImpl implements IMutantService {

    @Override
    public boolean isMutant(HumanDTO humanDTO) throws HumanException {
        Function<String[], Boolean> isMutant = s -> {
            String[][] matriz = convertDnaToMatriz(s);
            return dnaAnalyzer(matriz);
        };
        return isMutant.apply(humanDTO.getDna());
    }

    private boolean dnaAnalyzer(String[][] matriz) {
        List<String> bases = Constant.codesDna;
        List<Integer> totales = new ArrayList<>();
        int diagonalLetterSequenceCounter = 0;
        int horizontalLetterSequenceCounter = 0;
        int verticalLetterSequenceCounter = 0;

        for (String letter : bases) {
            diagonalLetterSequenceCounter = howManyLettersAreDiagonal(letter, matriz);
            horizontalLetterSequenceCounter = howManyLettersAreHorizontal(letter, matriz);
            verticalLetterSequenceCounter = howManyLettersAreVertical(letter, matriz);

            totales.add(diagonalLetterSequenceCounter +
                    horizontalLetterSequenceCounter + verticalLetterSequenceCounter);
        }
        return totales.stream().mapToInt(Integer::intValue).sum() > 2;
    }

    private String[][] convertDnaToMatriz(String[] dna) {
        return getMatrizDna(dna);
    }

    private String[][] getMatrizDna(String[] dnas) {
        int size = dnas.length;
        String[][] matriz = new String[size][size];

        for (int i = 0; i < dnas.length; i++) {
            char[] fragmentCodeDna = dnas[i].toCharArray();
            String[] arrCodeDna = convertCharacterArrayToStringArray(fragmentCodeDna);
            for (int j = 0; j < arrCodeDna.length; j++) {
                matriz[i][j] = arrCodeDna[j];
            }
        }

        return matriz;
    }

    private static String[] convertCharacterArrayToStringArray(char[] chars) {
        int charsSize = chars.length;
        String[] strings = new String[charsSize];

        for (int i = 0; i < charsSize; i++) {
            strings[i] = String.valueOf(chars[i]);
        }

        return strings;
    }

    private int howManyLettersAreDiagonal(String letter, String[][] matriz) {
        int high = matriz.length;
        int wight = matriz.length;
        List<String> fragments = new ArrayList<>();
        StringBuilder fragment;

        for (int diagonal = 1 - wight; diagonal <= high - 1; diagonal++) {
            fragment = new StringBuilder();
            for (int vertical = Math.max(0, diagonal), horizontal = -Math.min(0, diagonal);
                 vertical < high && horizontal < wight;
                 vertical++, horizontal++
            ) {
                String data = matriz[vertical][horizontal];
                fragment.append(data);
            }
            fragments.add(fragment.toString());
        }

        return sequenceLetterCounter(letter, fragments);
    }

    private int howManyLettersAreHorizontal(String letter, String [][] matriz){
        List<String> fragments = new ArrayList<>();
        StringBuilder fragment;
        for(int row = 0; row<matriz.length; row++){
            fragment = new StringBuilder();
            for(int column = 0; column<matriz[row].length; column++){
                String data = matriz[row][column];
                fragment.append(data);
            }
            fragments.add(fragment.toString());
        }
        return sequenceLetterCounter(letter,fragments);
    }

    private int howManyLettersAreVertical(String letter, String [][] matriz){
        List<String> fragments = new ArrayList<>();
        StringBuilder fragment;
        for(int row = 0; row<matriz.length; row++){
            fragment = new StringBuilder();
            for(int column = 0; column<matriz[row].length; column++){
                String data = matriz[column][row];
                fragment.append(data);
            }
            fragments.add(fragment.toString());
        }
        return sequenceLetterCounter(letter,fragments);
    }

    private int sequenceLetterCounter(String letter, List<String> letters) {
        int sequenceCounter = 0;
        for (String sequence : letters) {
            if (isTheLetterInTheSequence(sequence, letter)) {
                char[] separateDnaSquence = sequence.toCharArray();
                int counter = 1;
                
                for (int i = 0; i < separateDnaSquence.length; i++) {
                    char currentPositionOfTheDnaCode = separateDnaSquence[i];
                    char nextPositionOfTheDnaCode;
                    int dnaCodePosition = i + 1;
                    
                    if (dnaCodePosition == separateDnaSquence.length) {
                        nextPositionOfTheDnaCode = separateDnaSquence[i];
                        
                        if (areTheDnaCodesTheSame(letter, currentPositionOfTheDnaCode, nextPositionOfTheDnaCode)) {
                            counter++;
                            if (counter >= 4) {
                                sequenceCounter++;
                                counter = 0;
                                break;
                            }
                        } else {
                            counter = 0;
                        }
                    }

                    if (dnaCodePosition < separateDnaSquence.length) {
                        nextPositionOfTheDnaCode = separateDnaSquence[dnaCodePosition];
                        if (areTheDnaCodesTheSame(letter, currentPositionOfTheDnaCode, nextPositionOfTheDnaCode)) {
                            counter++;
                            if (counter >= 4) {
                                sequenceCounter++;
                                counter = 0;
                            }
                        } else {
                            counter = 0;
                        }
                    }
                }
            }
        }
        return sequenceCounter;
    }

    private boolean areTheDnaCodesTheSame(String letter, char currentPositionOfTheDnaCode, char nextPositionOfTheDnaCode) {
        char letterCharacter = letter.charAt(0);
        return currentPositionOfTheDnaCode == nextPositionOfTheDnaCode
                && (currentPositionOfTheDnaCode == letterCharacter
                && nextPositionOfTheDnaCode == letterCharacter);
    }

    private boolean isTheLetterInTheSequence(String sequenceDna, String letter) {
        boolean containLetter = sequenceDna.contains(letter);
        long count = sequenceDna.chars().filter(ch -> ch == letter.charAt(0)).count();
        boolean theNumberOfLetterIsGreaterThanFour = count >= 4;
        return containLetter && theNumberOfLetterIsGreaterThanFour;
    }

}
