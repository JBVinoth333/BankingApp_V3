package model;

class Bank {
    String bankName;
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getMinimumLoanAmount() {
        return minimumLoanAmount;
    }

    public void setMinimumLoanAmount(double minimumLoanAmount) {
        this.minimumLoanAmount = minimumLoanAmount;
    }

    public double getMaximumPersonalLoanAmount() {
        return maximumPersonalLoanAmount;
    }

    public void setMaximumPersonalLoanAmount(double maximumPersonalLoanAmount) {
        this.maximumPersonalLoanAmount = maximumPersonalLoanAmount;
    }

    public double getMaximumHouseLoanAmount() {
        return maximumHouseLoanAmount;
    }

    public void setMaximumHouseLoanAmount(double maximumHouseLoanAmount) {
        this.maximumHouseLoanAmount = maximumHouseLoanAmount;
    }

    public double getMaximumBuisnessLoanAmount() {
        return maximumBuisnessLoanAmount;
    }

    public void setMaximumBuisnessLoanAmount(double maximumBuisnessLoanAmount) {
        this.maximumBuisnessLoanAmount = maximumBuisnessLoanAmount;
    }

    double minimumLoanAmount;
    double maximumPersonalLoanAmount;
    double maximumHouseLoanAmount;
    double maximumBuisnessLoanAmount;

    Bank(String bankName, double minimumLoanAmount,
         double maximumPersonalLoanAmount, double maximumHouseLoanAmount, double maximumBuisnessLoanAmount){
        this.bankName = bankName;
        this.minimumLoanAmount = minimumLoanAmount;
        this.maximumPersonalLoanAmount = maximumPersonalLoanAmount;
        this.maximumHouseLoanAmount = maximumHouseLoanAmount;
        this.maximumBuisnessLoanAmount = maximumBuisnessLoanAmount;
    }


}