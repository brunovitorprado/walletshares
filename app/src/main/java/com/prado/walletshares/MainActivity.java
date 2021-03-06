package com.prado.walletshares;

import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.prado.walletshares.Application.CalculatesEquityService;
import com.prado.walletshares.Application.IPurchaseTransactionAppService;
import com.prado.walletshares.Application.PurchaseTransactionAppService;
import com.prado.walletshares.Infra.StockHoldingRepository;
import com.prado.walletshares.Presentation.PurchaseTransactionActivity;
import com.prado.walletshares.Presentation.SaleTransactionActivity;
import com.prado.walletshares.Presentation.StocksListActivity;

public class MainActivity extends AppCompatActivity {
    IPurchaseTransactionAppService service;
    TextView equity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = new PurchaseTransactionAppService(StockHoldingRepository.getInstance(this));
        this.setupDashBoard(service);
    }

    private void setupDashBoard(IPurchaseTransactionAppService service) {
        Double equityValue = new CalculatesEquityService().calculates(service.getTransactions());
        equity = (TextView)findViewById(R.id.lbl_equity);

        StringBuilder txtLblEquity = new StringBuilder();
        txtLblEquity.append("$ ").append(equityValue.toString());

        equity.setText(txtLblEquity);
    }

    public void startPurchaseActivity(View view){
        Intent purchaseActivity = new Intent(this, PurchaseTransactionActivity.class);
        startActivity(purchaseActivity);
    }

    public void startSaleActivity(View view){
        Intent saleActivity = new Intent(this, SaleTransactionActivity.class);
        startActivity(saleActivity);
    }

    public void startStocListActivity(View view){
        Intent stockListActivity = new Intent(this,StocksListActivity.class);
        startActivity(stockListActivity);
    }

}
