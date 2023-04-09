public class PowerSet <T> {
    private Set<T> [] set; // Declare a private array of Set<T> called 'set'

    public static void main(String[] args) {
        // Main method is empty, so nothing is executed when the program runs
    }

    public PowerSet(T[] elements) {
        set = PowerSetMaker(elements); // Initializes 'set' with the result of calling PowerSetMaker()
    }

    private Set<T>[] PowerSetMaker (T[] elements) {
        int elementsLength = elements.length; // Gets the length of the input array 'elements'
        int arrayPowerSize = 1;
        for (int i = 0; i < elementsLength; i++) {
            arrayPowerSize = arrayPowerSize * 2; // Calculates the power set size (2^n where n = number of elements)
        }

        String[] tempPowerSet = new String[arrayPowerSize]; // Initializes a temporary array of Strings with the power set size

        for (int i = 0; i < arrayPowerSize; i++) {
            String formatStr = "%" + elementsLength + "s"; // Formats the binary string with leading zeros
            String paddedStr = String.format(formatStr, Integer.toBinaryString(i)).replace(' ', '0'); // Pads the binary string with leading zeros and replaces spaces with zeros

            tempPowerSet[i] = paddedStr; // Adds the padded binary string to the temporary array
        }

        Set<T>[] powerSet = new Set[arrayPowerSize]; // Initializes a new array of Set<T> with the power set size
        for (int j = 0; j < tempPowerSet.length; j++) {
            powerSet[j] = new Set<T>(); // Initializes a new Set<T> for each binary string in 'tempPowerSet'
            for (int k = 0; k < elementsLength; k++) {
                if (tempPowerSet[j].charAt(k) == '1') {
                    powerSet[j].add(elements[k]); // Adds the element to the set if the corresponding binary digit is '1'
                }
            }
        }
        set = powerSet; // Assigns the power set to 'set'
        return set; // Returns the power set
    }

    public int getLength(){
        return set.length; // Returns the length of 'set'
    }

    public Set<T> getSet(int index) {
        return set[index]; // Returns the set at the specified index of 'set'
    }
}
