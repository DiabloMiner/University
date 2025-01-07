import java.util.Arrays;

public class DritterTest {

    public static void main(String[] args) {
        int[][] test1  = {{5, 2, 4}, {2, 7, 3}, {9, 5, 8}};
        int[][] test2  = {{1}};
        int[][] test3  = {{6, 7, 8}, {7, 5, 3, 1}, {2}};
        String seq1 = "ABBAACBA";

        int[][] result1 = generate(test1);
        System.out.println(Arrays.deepToString(result1));
        int[][] result2 = generate(test2);
        System.out.println(Arrays.deepToString(result2));

        reorder(result1);
        System.out.println(Arrays.deepToString(result1));
        reorder(result2);
        System.out.println(Arrays.deepToString(result2));
        reorder(test3);
        System.out.println(Arrays.deepToString(test3));

        System.out.println(isPresentNTimes(seq1, 'A', 4));
        System.out.println(isPresentNTimes(seq1, 'A', 3));
        System.out.println(isPresentNTimes(seq1, 'A', 5));
        System.out.println(isPresentNTimes(seq1, 'Z', 0));

        System.out.print("\n\n\n");


        test1 = new int[][] { {1, 2, 0, -1, -2, 3}, {-1, 2, 3}, {0, 0}, {}, {4, 5, -1} };
        test2 = new int[][] { {1, 2, 3}, {4, 5, 2}, {-2, -3, 2, -1}, {3, 2, 1, 5}, {4, 5, 1, 4} };
        test3 = new int[][] { {1, -1, 2, -2, 3}, {1, 2, 3}, {-3, -1, -2} };
        char[] age1 = {'d', 'u', '-', 'd', 'u', '-', 'd', 'a', '-', 'd', 'a'};
        char[] age2 = {'m', 'a', 'm', 'a', '!', 'n', 'e', 'i', 'n'};

        result1 = generate2(test1);
        System.out.println(Arrays.deepToString(result1));
        result2 = generate2(test2);
        System.out.println(Arrays.deepToString(result2));

        fill(test1, test2[3], test2[4]);
        System.out.println(Arrays.deepToString(test1));
        fill(result1, test2[2], test2[4]);
        System.out.println(Arrays.deepToString(result1));
        fill(test3, test2[2], test2[3]);
        System.out.println(Arrays.deepToString(test3));

        System.out.println(extractString(age1, 0, age1.length, '-'));
        System.out.println(extractString(age1, 1, 7, 'u'));
        System.out.println(extractString(age2, 0, 5, 'a'));
        System.out.println(extractString(age2, 5, age2.length, 'n'));

        System.out.print("\n\n\n");


        int[][] data0 = {{3, 0}, {0, 1}, {2, 2}};
        int[][] data1 = {{0, 1, 0, 0, 1, 0}, {}, {2, 2, 2, 2, 0, 1}};
        int[] target1 = {0, 0, 0};
        int[] target2 = {9, 9, 9, 9};

        System.out.println(Arrays.deepToString(labelPath(3, new int[][]{})));
        System.out.println(Arrays.deepToString(labelPath(4, data0)));
        findMatches(data0, data0[1], target1);
        System.out.println(Arrays.toString(target1));
        findMatches(data1, data0[1], target1);
        System.out.println(Arrays.toString(target1));
        findMatches(data1, data0[2], target2);
        System.out.println(Arrays.toString(target2));

        System.out.println(insertMiddle("XY", "abc"));
        System.out.println(insertMiddle("01234", "abc"));
        System.out.println(insertMiddle("01234567890123", "./-"));

        System.out.print("\n\n\n");


        test1 = new int[][] {{5, 2, 4}, {8, 5, 4}, {9, 6, 8, 7}};
        test2 = new int[][] {{0, 1, 2}, {0, 1, 2}, {0, 1, 2}};
        test3 = new int[][] {{6}, {2, 4}, {2, 4}, {2, 4}, {4, 2}};
        int[] seq = new int[] {4, 3, 2, 1, 10, 5, 5, 5};

        result1 = rearrange(test1);
        System.out.println(Arrays.deepToString(result1));
        result2 = rearrange(test3);
        System.out.println(Arrays.deepToString(result2));
        System.out.println(Arrays.deepToString(rearrange(new int[][]{})));

        label(test1);
        System.out.println(Arrays.deepToString(test1));
        label(test2);
        System.out.println(Arrays.deepToString(test2));
        label(test3);
        System.out.println(Arrays.deepToString(test3));

        System.out.println(findMaxOppositeSum(seq, 0, 7));
        System.out.println(findMaxOppositeSum(seq, 0, 5));
        System.out.println(findMaxOppositeSum(seq, 4, 7));


        System.out.print("\n\n\n");


        test1 = new int[][] {{0, 2, 4}, {2, 0, 0}, {0, 0, 1}};
        test2 = new int[][] {{1, 2, 3}, {1, 2, 3, 4, 5}, {1, 2, 3}, {1, 2, 3, 4, 5}};
        test3 = new int[][] {{2}, {0, 7}, {6, 7, 8}, {6, 0}, {0, 0}};
        seq1 = "ABA";

        result1 = removeLeadingZeros(test1);
        System.out.println(Arrays.deepToString(result1));
        result2 = removeLeadingZeros(test3);
        System.out.println(Arrays.deepToString(result2));

        mask(test2,new int[]{1,2,3},new int[]{0,1,4});
        System.out.println(Arrays.deepToString(test2));
        mask(test3,new int[]{0,2,4},new int[]{0,1});
        System.out.println(Arrays.deepToString(test3));
        mask(test1,new int[]{},new int[]{0,1});
        System.out.println(Arrays.deepToString(test1));

        System.out.println(replicateCharacters(seq1, "010"));
        System.out.println(replicateCharacters("SAMBA", "10001"));


        System.out.print("\n\n\n");


    }

    public static String replicateCharacters(String sequence, String repSequence) {
        // Base case
        if (repSequence.isEmpty() || sequence.isEmpty()) {
            return sequence;
        }

        // Recursive case
        return sequence.substring(0, 1) + (repSequence.charAt(0) == '1' ? sequence.charAt(0) : "") + replicateCharacters(sequence.substring(1), repSequence.substring(1));
    }

    public static void mask(int[][] inputArray, int[] rows, int[] cols) {
        for (int row : rows) {
            for (int col : cols) {
                if (row < inputArray.length && col < inputArray[row].length) {
                    inputArray[row][col] = 0;
                }
            }
        }
    }

    public static int[][] removeLeadingZeros(int[][] inputArray) {
        int[][] result = new int[inputArray.length][];

        for (int i = 0; i < inputArray.length; i++) {
            int size = 0;
            boolean leading = true;
            for (int j = 0; j < inputArray[i].length; j++) {
                if (inputArray[i][j] == 0) {
                    size += leading ? 0 : 1;
                } else {
                    leading = false;
                    size++;
                }
            }

            int resultJ = 0;
            leading = true;
            result[i] = new int[size];
            for (int j = 0; j < inputArray[i].length; j++) {
                if (inputArray[i][j] == 0) {
                    if (!leading) {
                        result[i][resultJ++] = inputArray[i][j];
                    }
                } else {
                    leading = false;
                    result[i][resultJ++] = inputArray[i][j];
                }
            }
        }

        return result;
    }

    public static int findMaxOppositeSum(int[] sequence, int start, int end) {
        // Base case
        if (start == end) {
            return sequence[start];
        } else if (end < start) {
            return 0;
        }

        // Recursive case:
        return Math.max(sequence[start] + sequence[end], findMaxOppositeSum(sequence, start + 1, end - 1));
    }

    public static void label(int[][] inputArray) {
        int[][] tempArray = new int[inputArray.length][];

        for (int i = 0; i < inputArray.length; i++) {
            tempArray[i] = new int[inputArray[i].length];

            boolean equal = true;
            int[] row = inputArray[i];
            if (i + 1 < inputArray.length) {
                int[] nextRow = inputArray[i + 1];
                for (int j = 0; j < nextRow.length; j++) {
                    if (j >= row.length || row[j] != nextRow[j])  {
                        equal = false;
                        break;
                    }
                }
            } else {
                equal = false;
            }

            if (equal) {
                for (int j = 0; j < tempArray[i].length; j++) {
                    tempArray[i][j] = -9;
                }
                tempArray[i + 1] = tempArray[i];
                i++;
            } else {
                for (int j = 0; j < tempArray[i].length; j++) {
                    tempArray[i] = inputArray[i];
                }
            }
        }

        for (int i = 0; i < tempArray.length; i++) {
            inputArray[i] = tempArray[i];
        }
    }

    public static int[][] rearrange(int[][] inputArray) {
        int size = 0;
        for (int[] ints : inputArray) {
            size += ints.length;
        }
        size = (size % 4 == 0) ? size : (size + (4 - size % 4));

        int[][] result = new int[size / 4][4];
        boolean left = true;
        int inputColumn = 0, inputRow = 0;
        for (int i = 0; i < result.length; i++) {
            int resultIndex = left ? 0 : 3;
            while (resultIndex < result[i].length && resultIndex >= 0) {
                result[i][resultIndex] = (inputRow < inputArray.length && inputColumn < inputArray[inputRow].length) ? inputArray[inputRow][inputColumn] : 0;

                inputColumn++;
                if (inputRow < inputArray.length && inputColumn == inputArray[inputRow].length) {
                    inputColumn = 0;
                    inputRow++;
                }

                resultIndex += left ? 1 : -1;
            }
            left = !left;
        }

        return result;
    }

    public static String insertMiddle(String input, String seps) {
        // Base case
        if (seps.isEmpty() || input.length() == 1) {
            return input;
        }

        // Recursive case
        return insertMiddle(input.substring(0, input.length() / 2), seps.substring(1)) + seps.charAt(0) + insertMiddle(input.substring(input.length() / 2), seps.substring(1));
    }

    public static void findMatches(int[][] data, int[] pattern, int[] target) {
        for (int i = 0; i < data.length; i++) {
            target[i] = 0;
        }

        for (int i = 0; i < data.length; i++) {
            int[] row = data[i];
            int patternIndex = 0, lastIndex = 0;
            while (lastIndex < data[i].length) {
                for (int j = lastIndex; j < data[i].length; j++) {
                    int datum = row[j];
                    if (datum == pattern[patternIndex]) {
                        patternIndex += 1;
                    } else if (patternIndex > 0 && datum == pattern[patternIndex - 1]) {} else {
                        patternIndex = 0;
                    }
                    if (patternIndex == pattern.length) {
                        patternIndex = 0;
                        target[i] += 1;
                        break;
                    }
                    lastIndex++;
                }
            }
        }
    }

    public static int[][] labelPath(int n, int[][] points) {
        int[][] result = new int[n][n];

        for (int[] point : points) {
            int row = point[0], column = point[1];
            result[row][column] = -1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = (result[i][j] != -1) ? n : -1;
            }
        }

        return result;
    }

    public static String extractString(char[] sequence, int start, int end, char omit) {
        String result = "";

        for (int i = start; i < end; i++) {
            result += sequence[i] != omit ? sequence[i] : "";
        }

        return result;
    }

    public static void fill(int[][] target, int[] values, int[] times) {
        int[][] temp = new int[target.length][];

        int valueIndex = 0, timesIndex = 0, value = values[valueIndex], counter = times[timesIndex];
        for (int i = 0; i < target.length; i++) {
            temp[i] = new int[target[i].length];
            for (int j = 0; j < target[i].length; j++) {
                temp[i][j] = value;

                if (counter == 1 && !(j == target[i].length - 1 && i == target.length - 1)) {
                    counter = times[++timesIndex];
                    value = values[++valueIndex];
                } else {
                    counter--;
                }
            }
        }


        for (int i = 0; i < target.length; i++) {
            target[i] = temp[i];
        }
    }

    public static int[][] generate2(int[][] input) {
        int[][] output = new int[input.length][];

        for (int i = 0; i < input.length; i++) {
            int outputIndex = 0;
            output[i] = new int[input[i].length];

            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] < 0) {
                    output[i][outputIndex++] = input[i][j];
                }
            }
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] >= 0) {
                    output[i][outputIndex++] = input[i][j];
                }
            }
        }

        return output;
    }

    public static int[][] generate(int[][] input) {
        int[][] output = new int[2 * input.length - 1][];

        for (int i = 0; i < input.length; i++) {
            int size = input.length - i;
            output[i] = new int[size];
            for (int j = 0; j < size; j++) {
                output[i][j] = input[i][j];
            }

        }
        for (int i = 0; i < input.length - 1; i++) {
            int size = i + 2, outputIndex = i + input.length, inputIndex = input.length - (i + 2);
            output[outputIndex] = new int[size];
            for (int j = 0; j < size; j++) {
                output[outputIndex][j] = input[inputIndex][j];
            }
        }

        return output;
    }

    public static void reorder(int[][] input) {
        int[][] temp = new int[input.length][];

        for (int i = 0; i < input.length; i++) {
            temp[i] = new int[input[i].length];
            for (int j = input[i].length - 1; j >= 0; j--) {
                int tempIndex = Math.abs(j - input[i].length + 1);
                temp[i][tempIndex] = input[i][j];
            }
        }

        for (int i = 0; i < input.length; i++) {
            input[i] = temp[i];
        }
    }

    public static boolean isPresentNTimes(String sequence, char marker, int count) {
        int counter = 0;

        for (int i = 0; i < sequence.length(); i++) {
            counter += sequence.charAt(i) == marker ? 1 : 0;
        }

        return counter == count;
    }

}
