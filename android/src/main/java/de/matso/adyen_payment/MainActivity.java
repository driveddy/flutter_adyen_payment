package de.matso.adyen_payment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.adyen.checkout.core.CheckoutException;
import com.adyen.checkout.core.PaymentMethodHandler;
import com.adyen.checkout.core.PaymentResult;
import com.adyen.checkout.ui.CheckoutController;
import com.adyen.checkout.ui.CheckoutSetupParameters;
import com.adyen.checkout.ui.CheckoutSetupParametersHandler;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int REQUEST_CODE_CHECKOUT = 1;
    String mPublishableKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
          mPublishableKey =    savedInstanceState.getString("publishableKey");
        }
        if(mPublishableKey != null){
            CheckoutController.startPayment(this, new CheckoutSetupParametersHandler() {
                @Override
                public void onRequestPaymentSession(@NonNull CheckoutSetupParameters checkoutSetupParameters) {
//                    retrievePaymentSession(checkoutSetupParameters);
                }

                @Override
                public void onError(@NonNull CheckoutException checkoutException) {
//                    handleCheckoutException(checkoutException);
                }
            });
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CHECKOUT) {
            if (resultCode == PaymentMethodHandler.RESULT_CODE_OK) {
                PaymentResult paymentResult = PaymentMethodHandler.Util.getPaymentResult(data);
                // Handle PaymentResult.
            } else {
                CheckoutException checkoutException = PaymentMethodHandler.Util.getCheckoutException(data);

                if (resultCode == PaymentMethodHandler.RESULT_CODE_CANCELED) {
                    // Handle cancellation and optional CheckoutException.
                } else {
                    // Handle CheckoutException.
                }
            }
        }
    }
}
