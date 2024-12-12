public class Helpers {

    static void validateMandatoryFields(int size, int mandatoryFields) {
        if (size == mandatoryFields) {
            System.out.println("Error messages are displayed for all mandatory fields: " + size);
        } else {
            System.out.println("Error message validation failed: " + size);
        }
    }

    static void compareColor(String color) {
        if (color.equals("rgba(255, 0, 0, 1)")) {
            System.out.println("Field color error is red.");
        } else {
            System.out.println("Field color error is not red. The color is: " + color);
        }
    }
}