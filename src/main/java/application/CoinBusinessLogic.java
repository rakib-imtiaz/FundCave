package application;

import mainClass.Coin;

public class CoinBusinessLogic {

    public static void updateCoin(String studentID, double givenAmount) {
        int coinValue = fetchCoinByStudentID(studentID);

        double increasePercentage = 0.0;

        if (givenAmount > 0 && givenAmount <= 100) {
            increasePercentage = 0.02; // 2% increase
        } else if (givenAmount > 100 && givenAmount <= 1000) {
            increasePercentage = 0.05; // 5% increase
        } else if (givenAmount > 1000 && givenAmount <= 10000) {
            increasePercentage = 0.15; // 15% increase
        } else if (givenAmount > 10000 && givenAmount <= 50000) {
            // id verification required
            increasePercentage = 0.25; // 25% increase
        } else if (givenAmount > 50000 && givenAmount <= 100000) {
            // id verification required
            increasePercentage = 0.50; // 50% increase
        } else {
            // cannot perform transaction, limit exceeds
            System.out.println("Cannot perform transaction, limit exceeds.");
            return;
        }

        int increaseAmount = (int) (givenAmount * increasePercentage); // Calculate increase amount
        int newCoinValue = coinValue + increaseAmount; // Calculate new coin value

        updateCoinToDatabase(studentID, newCoinValue); // Update coin value in the database
    }

    private static int fetchCoinByStudentID(String studentID) {
        DataBaseManager.makeConnection();
        DataBaseManager.fetchDataFromDatabase();
        for (Coin coin : DataBaseManager.getCoinArrayList()) {
            if (coin.getStudentID().equals(studentID)) {
                return coin.getValue();
            }
        }
        return 0; // Return 0 if student ID is not found in the database
    }

    private static void updateCoinToDatabase(String studentID, int newValue) {
        String updateQuery = "UPDATE Coin SET value = " + newValue + " WHERE studentID = '" + studentID + "'";
        DataBaseManager.executeUpdate(updateQuery);
    }
}
