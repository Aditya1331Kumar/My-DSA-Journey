class problem__168 {
    public static void main(String[] args) {
        String obj = "";
        System.out.println(convertToTitle(28)); 
    }
    public static String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;  // Important step to make it 0-based
            int rem = columnNumber % 26;
            sb.insert(0, (char)(rem + 'A'));
            columnNumber = columnNumber / 26;
        }
        return sb.toString();
    }//df
}