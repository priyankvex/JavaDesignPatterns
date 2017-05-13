/**
 * Created by priyankvex on 13/5/17.
 *
 * Strategy Pattern :
 * The strategy pattern defines a family of algorithms, encapsulates each one of them,
 * and makes them interchangeable. Strategy pattern allows the algorithm to be varied independently
 * from clients that use it.
 *
 * In this demo, we'll implement the strategy design pattern to implement a payments feature.
 * We'll implement it to change the billing behaviour at runtime by using different payment gateway as needed.
 */
public class PaymentsStrategyPattern {

    /**
     * Base payment class
     */
    private static abstract class Payment {

        double amount;

        BillingBehaviour billingBehaviour;

        public BillingBehaviour getBillingBehaviour() {
            return billingBehaviour;
        }

        /**
         * Method to change the billing behaviour at runtime
         * @param billingBehaviour some {@link BillingBehaviour} implementation
         */
        void setBillingBehaviour(BillingBehaviour billingBehaviour) {
            this.billingBehaviour = billingBehaviour;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        /**
         * Method called to perform billing
         * @param amount amount to be billed
         */
        void bill(double amount){
            // delegate the task to billing behaviour
            billingBehaviour.bill(amount);
        }
    }

    private static class CardPayment extends Payment {

        private boolean isCreditCard;
        private boolean isDebitCard;

        private String bankName;

        CardPayment(boolean isCreditCard, boolean isDebitCard){
            this.isCreditCard = isCreditCard;
            this.isDebitCard = isDebitCard;
            // our default billing mechanism is to use RazorPay billing API
            this.billingBehaviour = new RazorPayBilling();
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

    }

    /**
     * Interface to define the billing behaviour
     */
    private static interface BillingBehaviour{
        boolean bill(double amount);
    }

    /**
     * Implementation of Stripe billing behaviour
     */
    private static class StripeBilling implements BillingBehaviour {

        @Override
        public boolean bill(double amount) {
            System.out.println("Billing using Sprint Billing API");
            return true;
        }
    }

    /**
     * Implementation of RazorPay billing behaviour
     */
    private static class RazorPayBilling implements BillingBehaviour {

        @Override
        public boolean bill(double amount) {
            System.out.println("Billing using RazorPay Billing API");
            return true;
        }
    }

    /**
     * Implementation of PayPal billing behaviour
     */
    private static class PayPalBilling implements BillingBehaviour {

        @Override
        public boolean bill(double amount) {
            System.out.println("Billing using PayPal API");
            return true;
        }
    }


    public static void main(String[] args) {

        // Programming to a super type
        Payment payment = new CardPayment(true, false);
        // set the billing behaviour at runtime
        payment.setBillingBehaviour(new PayPalBilling());
        // billed by PayPal API
        payment.bill(1234);
        // set the billing behaviour at runtime
        payment.setBillingBehaviour(new RazorPayBilling());
        // billed by RazorPay API
        payment.bill(1234);

    }


}
